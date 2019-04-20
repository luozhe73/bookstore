package gz.itcast.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/**
 * 系统的全局请求参数编码过滤器
 * 注意： 
 * 		这里采用了装饰者模式对request对象进行装饰，重写相关的获取参数的方法
 * @author APPle
 *
 */
public class EncodingFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	public void destroy() {
	}

	/**
	 * 执行过滤任务
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		//1.创建装饰类
		MyRequest myRequest = new MyRequest((HttpServletRequest)request);
		
		//设置响应格式和编码
		response.setContentType("text/html;charset=utf-8");
		
		//2.放行，执行servlet
		chain.doFilter(myRequest, response);
	}
}

/**
 * request的装饰类
 * 1)继承HttpServletRequestWrapper类
*/
class MyRequest extends HttpServletRequestWrapper{
	/**
	 * 2)声明一个被装饰类的类型
	 */
	private HttpServletRequest request;

	public MyRequest(HttpServletRequest request) {
		super(request);
		/**
		 * 3)接收被装饰类的对象
		 */
		this.request = request;
	}
	
	/**
	 * 4)重写被装饰类的方法
	 */
	@Override
	public String getParameter(String name) {
		//得到参数值
		String value = null;
		try {
			/**
			 * Post方式
			 */
			this.request.setCharacterEncoding("utf-8");
			value = this.request.getParameter(name);
			/**
			 * Get方式、手动解码
			 */
			if("GET".equals(this.request.getMethod())){
				if(value!=null){
					value = new String(value.getBytes("iso-8859-1"),"utf-8");
				}
			}
			return value;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public String[] getParameterValues(String name) {
		try {
			/**
			 * Post方式
			 */
			this.request.setCharacterEncoding("utf-8");
			//得到参数值
			String[] values = this.request.getParameterValues(name);
			/**
			 * Get方法，手动解码
			 */
			if("GET".equals(this.request.getMethod())){
				if(values!=null){
					String[] newParam = new String[values.length];
					for(int i=0;i<values.length;i++){
						newParam[i] = new String(values[i].getBytes("iso-8859-1"),"utf-8");
					}
					return newParam;
				}
			}
			return values;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Map getParameterMap() {
		try {
			/**
			 * Post方式
			 */
			request.setCharacterEncoding("utf-8");
			
			Map map = request.getParameterMap();
			
			if("GET".equals(request.getMethod())){
				if(map!=null){
					Map newMap = new HashMap();
					Set<Entry> set = map.entrySet();
					for(Entry entry:set){
						String[] value = (String[])entry.getValue();
						
						//把Map的value（字符串数组）进行重新解码，之后放入新的字符串数组
						String[] newValue = new String[value.length];
						for(int i=0;i<newValue.length;i++){
							newValue[i] = new String(value[i].getBytes("iso-8859-1"),"utf-8");
						}
						newMap.put(entry.getKey(), newValue);
					}
					return newMap;
				}
			}
			return map;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
} 