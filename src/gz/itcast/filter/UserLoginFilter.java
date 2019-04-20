package gz.itcast.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



/**
 * 过滤前台用户权限(访问权限资源，如果没有登录，则无法访问)
 * @author APPle
 *
 */
public class UserLoginFilter implements Filter{
	
	
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		HttpSession session = req.getSession(false);
		if(session==null){
			req.getRequestDispatcher("/jsp/front/user/login.jsp").forward(request, response);
			return;
		}else{
			if(session.getAttribute("user")==null){
				req.getRequestDispatcher("/jsp/front/user/login.jsp").forward(request, response);
				return;
			}
		}
		//放行
		chain.doFilter(request, response);
	}
	public void destroy() {
		
	}
}
