package gz.itcast.biz.front.address.web;



import gz.itcast.biz.front.address.service.AddressService;
import gz.itcast.biz.front.address.service.AddressServiceImpl;
import gz.itcast.entity.Address;
import gz.itcast.entity.User;
import gz.itcast.util.BaseServlet;
import gz.itcast.util.WebUtil;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * �ջ���ַģ���servlet
 * @author APPle
 *
 */
public class AddressServlet extends BaseServlet {
	AddressService service = new AddressServiceImpl();
	
	
	//�鿴ĳ���û����ջ���ַ�б�
	public void queryAddress(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//��ȡ�û�id
		User user = (User)request.getSession().getAttribute("user");
		List<Address> addrs = service.queryAddress(user.getId());
		//�ŵ�request��
		request.setAttribute("addrs",addrs);
		request.getRequestDispatcher("/jsp/front/address/list.jsp").forward(request, response);
	}
	
	//����ջ���ַ
	public void addAddress(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Address addr = WebUtil.copyRequestToBean(request, Address.class);
		User user = (User)request.getSession().getAttribute("user");
		addr.setUserid(user.getId());
		addr = service.add(addr);
		request.setAttribute("addr",addr);
		request.getRequestDispatcher("/jsp/front/address/addback.jsp").forward(request, response);
	}
	
	//ɾ���ջ���ַ
	public void delAddress(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Address addr = WebUtil.copyRequestToBean(request, Address.class);
		service.delete(addr);
		this.queryAddress(request, response);
	}
	
	//����ΪĬ���ջ���ַ
	public void changeToDefault(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Address addr = WebUtil.copyRequestToBean(request, Address.class);
		User user = (User)request.getSession().getAttribute("user");
		addr.setUserid(user.getId());
		service.changeToDefault(addr);
		this.queryAddress(request, response);
	}
}
