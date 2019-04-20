package gz.itcast.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
/**
 * 管理员登录权限过滤
 * @author APPle
 *
 */
public class AdminLoginFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if(req.getSession().getAttribute("admin")==null){
			req.getRequestDispatcher("/jsp/admin/login.jsp").forward(request, response);
		}else{
			chain.doFilter(request, response);
		}
	}
	public void destroy() {
	}
}
