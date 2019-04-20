package gz.itcast.biz.admin.admin.web;



import gz.itcast.biz.admin.admin.service.AdminService;
import gz.itcast.biz.admin.admin.service.AdminServiceImpl;
import gz.itcast.entity.Admin;
import gz.itcast.util.BaseServlet;
import gz.itcast.util.MD5Util;
import gz.itcast.util.WebUtil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminServlet extends BaseServlet {
	AdminService service = new AdminServiceImpl();
	//登录
	public void login(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Admin admin = WebUtil.copyRequestToBean(request, Admin.class);
		Admin loginAdmin = service.login(admin.getName());
		if(loginAdmin!=null){
			if(  MD5Util.md5(admin.getPassword().trim()).equals(loginAdmin.getPassword()) ){
				request.getSession().setAttribute("admin",admin);
				//转到管理人员主页
				//重定向
				response.sendRedirect(request.getContextPath()+"/jsp/admin/index.jsp");
			}else{
				request.setAttribute("msg", "密码输入有误！");
				request.getRequestDispatcher("/jsp/admin/login.jsp").forward(request, response);
				return;
			}
		}else{
			request.setAttribute("msg", "用户名不存在！");
			request.getRequestDispatcher("/jsp/admin/login.jsp").forward(request, response);
			return;
		}
	}
}
