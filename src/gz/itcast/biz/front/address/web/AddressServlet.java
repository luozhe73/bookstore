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
 * 收货地址模块的servlet
 * @author APPle
 *
 */
public class AddressServlet extends BaseServlet {
	AddressService service = new AddressServiceImpl();
	
	
	//查看某个用户的收货地址列表
	public void queryAddress(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//获取用户id
		User user = (User)request.getSession().getAttribute("user");
		List<Address> addrs = service.queryAddress(user.getId());
		//放到request中
		request.setAttribute("addrs",addrs);
		request.getRequestDispatcher("/jsp/front/address/list.jsp").forward(request, response);
	}
	
	//添加收货地址
	public void addAddress(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Address addr = WebUtil.copyRequestToBean(request, Address.class);
		User user = (User)request.getSession().getAttribute("user");
		addr.setUserid(user.getId());
		addr = service.add(addr);
		request.setAttribute("addr",addr);
		request.getRequestDispatcher("/jsp/front/address/addback.jsp").forward(request, response);
	}
	
	//删除收货地址
	public void delAddress(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Address addr = WebUtil.copyRequestToBean(request, Address.class);
		service.delete(addr);
		this.queryAddress(request, response);
	}
	
	//设置为默认收货地址
	public void changeToDefault(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Address addr = WebUtil.copyRequestToBean(request, Address.class);
		User user = (User)request.getSession().getAttribute("user");
		addr.setUserid(user.getId());
		service.changeToDefault(addr);
		this.queryAddress(request, response);
	}
}
