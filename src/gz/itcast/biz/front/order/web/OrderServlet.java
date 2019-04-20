package gz.itcast.biz.front.order.web;



import gz.itcast.biz.front.order.service.OrderService;
import gz.itcast.biz.front.order.service.OrderServiceImpl;
import gz.itcast.entity.Book;
import gz.itcast.entity.Order;
import gz.itcast.entity.OrderLine;
import gz.itcast.entity.User;
import gz.itcast.util.BaseServlet;
import gz.itcast.util.WebUtil;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ����ģ���servlet
 * @author APPle
 *
 */
public class OrderServlet extends BaseServlet {
	OrderService service = new OrderServiceImpl();
	
	//׼������
	public void toOrder(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Order order = WebUtil.copyRequestToBean(request, Order.class);
		order.setId(WebUtil.getOrderNo(order.hashCode()));
		order.setOrderdate(WebUtil.getDateStr());
		request.setAttribute("order",order);
		request.getRequestDispatcher("/jsp/front/order/order.jsp").forward(request,response);
	}
	
	
	//���ɶ���
	public void save(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Order order = WebUtil.copyRequestToBean(request, Order.class);
		User user = (User)request.getSession().getAttribute("user");
		order.setUserid(user.getId());
		order.setOrderdate(WebUtil.getDateStr());
		//��map��ȡ�����е�book
		Map<String, Book> car = (Map<String, Book>) request.getSession().getAttribute("car");
		Iterator<Map.Entry<String,Book>> it = car.entrySet().iterator();
		while(it.hasNext()){
			Book book = it.next().getValue();
			OrderLine ol = new OrderLine();
			ol.setId(UUID.randomUUID().toString().replace("-", ""));
			ol.setBookid(book.getId());
			ol.setOrderid(order.getId());
			ol.setAmt(book.getAmt());
			ol.setPrice(book.getCurrentPrice());
			order.getOrderLines().add(ol);
		}
		//��������ݷ�װ��ɣ���ʼ����˶���
		service.saveOrder(order);
		//��session����չ��ﳵ����Ϣ
		request.getSession().removeAttribute("car");
		request.setAttribute("order",order);
		request.getRequestDispatcher("/jsp/front/order/ordersucc.jsp").forward(request, response);
	}
	
	//��ѯ���ж���
	public void queryOrders(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		User user=(User)request.getSession().getAttribute("user");
		List<Order> orders = service.queryOrders(user.getId());
		request.setAttribute("orders",orders);
		request.getRequestDispatcher("/jsp/front/order/list.jsp").forward(request, response);
	}
	
	//����id��ѯ����
	public void queryOne(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Order order = WebUtil.copyRequestToBean(request, Order.class);
		order = service.oneOrder(order);
		request.setAttribute("order",order);
		request.getRequestDispatcher("/jsp/front/order/detail.jsp").forward(request, response);
	}
}
