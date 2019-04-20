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
 * 用户模块的servlet
 * @author APPle
 *
 */
public class UserServlet extends BaseServlet {
	UserService service = new UserServiceImpl();
	
	//注册
	public void reg(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//拷贝数据
		User user = WebUtil.copyRequestToBean(request, User.class);
		//保存
		user = service.reg(user);
		//跳转到注册成功页面
		response.sendRedirect(request.getContextPath()+"/jsp/front/user/regsucc.jsp");
	}
	
	//登录
	public void login(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//获取用户输入的验证码
		String code = request.getParameter("code");//先验证验证码
		//获取生成的验证码
		String sCode = (String) request.getSession().getAttribute("sCode");
		if(code.trim().equals(sCode)){
			//去验证用户名是否正确
			User user = WebUtil.copyRequestToBean(request, User.class);
			//验证用户名
			User loginUser = service.login(user.getName());
			if(loginUser!=null){
				//验证密码
				if(MD5Util.md5(user.getPassword()).equals(loginUser.getPassword())){
					//登录成功
					//将用户信息放到session中
					request.getSession().setAttribute("user",loginUser);
					response.sendRedirect(request.getContextPath()+"/index.jsp");
					return;
				}else{
					//密码错误
					request.setAttribute("msg", "密码输入有误");
					request.getRequestDispatcher("/jsp/front/user/login.jsp").forward(request, response);
					return;
				}
			}else{
				//用户名不存在
				request.setAttribute("msg", "用户名不存在");
				request.getRequestDispatcher("/jsp/front/user/login.jsp").forward(request, response);
				return;
			}
		}else{
			//验证码错误
			request.setAttribute("msg", "验证码错误");
			request.getRequestDispatcher("/jsp/front/user/login.jsp").forward(request, response);
			return;
		}
	}
}
