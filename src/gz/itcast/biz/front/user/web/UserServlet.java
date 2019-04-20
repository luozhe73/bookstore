package gz.itcast.biz.front.user.web;



import gz.itcast.biz.front.user.service.UserService;
import gz.itcast.biz.front.user.service.UserServiceImpl;
import gz.itcast.entity.User;
import gz.itcast.util.BaseServlet;
import gz.itcast.util.MD5Util;
import gz.itcast.util.WebUtil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * �û�ģ���servlet
 * @author APPle
 *
 */
public class UserServlet extends BaseServlet {
	UserService service = new UserServiceImpl();
	
	//ע��
	public void reg(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//��������
		User user = WebUtil.copyRequestToBean(request, User.class);
		//����
		user = service.reg(user);
		//��ת��ע��ɹ�ҳ��
		response.sendRedirect(request.getContextPath()+"/jsp/front/user/regsucc.jsp");
	}
	
	//��¼
	public void login(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//��ȡ�û��������֤��
		String code = request.getParameter("code");//����֤��֤��
		//��ȡ���ɵ���֤��
		String sCode = (String) request.getSession().getAttribute("sCode");
		if(code.trim().equals(sCode)){
			//ȥ��֤�û����Ƿ���ȷ
			User user = WebUtil.copyRequestToBean(request, User.class);
			//��֤�û���
			User loginUser = service.login(user.getName());
			if(loginUser!=null){
				//��֤����
				if(MD5Util.md5(user.getPassword()).equals(loginUser.getPassword())){
					//��¼�ɹ�
					//���û���Ϣ�ŵ�session��
					request.getSession().setAttribute("user",loginUser);
					response.sendRedirect(request.getContextPath()+"/index.jsp");
					return;
				}else{
					//�������
					request.setAttribute("msg", "������������");
					request.getRequestDispatcher("/jsp/front/user/login.jsp").forward(request, response);
					return;
				}
			}else{
				//�û���������
				request.setAttribute("msg", "�û���������");
				request.getRequestDispatcher("/jsp/front/user/login.jsp").forward(request, response);
				return;
			}
		}else{
			//��֤�����
			request.setAttribute("msg", "��֤�����");
			request.getRequestDispatcher("/jsp/front/user/login.jsp").forward(request, response);
			return;
		}
	}
}
