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
	//��¼
	public void login(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Admin admin = WebUtil.copyRequestToBean(request, Admin.class);
		Admin loginAdmin = service.login(admin.getName());
		if(loginAdmin!=null){
			if(  MD5Util.md5(admin.getPassword().trim()).equals(loginAdmin.getPassword()) ){
				request.getSession().setAttribute("admin",admin);
				//ת��������Ա��ҳ
				//�ض���
				response.sendRedirect(request.getContextPath()+"/jsp/admin/index.jsp");
			}else{
				request.setAttribute("msg", "������������");
				request.getRequestDispatcher("/jsp/admin/login.jsp").forward(request, response);
				return;
			}
		}else{
			request.setAttribute("msg", "�û��������ڣ�");
			request.getRequestDispatcher("/jsp/admin/login.jsp").forward(request, response);
			return;
		}
	}
}
