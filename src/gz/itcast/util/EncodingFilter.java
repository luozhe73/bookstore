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
 * ϵͳ��ȫ������������������
 * ע�⣺ 
 * 		���������װ����ģʽ��request�������װ�Σ���д��صĻ�ȡ�����ķ���
 * @author APPle
 *
 */
public class EncodingFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	public void destroy() {
	}

	/**
	 * ִ�й�������
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		//1.����װ����
		MyRequest myRequest = new MyRequest((HttpServletRequest)request);
		
		//������Ӧ��ʽ�ͱ���
		response.setContentType("text/html;charset=utf-8");
		
		//2.���У�ִ��servlet
		chain.doFilter(myRequest, response);
	}
}

/**
 * request��װ����
 * 1)�̳�HttpServletRequestWrapper��
*/
class MyRequest extends HttpServletRequestWrapper{
	/**
	 * 2)����һ����װ���������
	 */
	private HttpServletRequest request;

	public MyRequest(HttpServletRequest request) {
		super(request);
		/**
		 * 3)���ձ�װ����Ķ���
		 */
		this.request = request;
	}
	
	/**
	 * 4)��д��װ����ķ���
	 */
	@Override
	public String getParameter(String name) {
		//�õ�����ֵ
		String value = null;
		try {
			/**
			 * Post��ʽ
			 */
			this.request.setCharacterEncoding("utf-8");
			value = this.request.getParameter(name);
			/**
			 * Get��ʽ���ֶ�����
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
			 * Post��ʽ
			 */
			this.request.setCharacterEncoding("utf-8");
			//�õ�����ֵ
			String[] values = this.request.getParameterValues(name);
			/**
			 * Get�������ֶ�����
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
			 * Post��ʽ
			 */
			request.setCharacterEncoding("utf-8");
			
			Map map = request.getParameterMap();
			
			if("GET".equals(request.getMethod())){
				if(map!=null){
					Map newMap = new HashMap();
					Set<Entry> set = map.entrySet();
					for(Entry entry:set){
						String[] value = (String[])entry.getValue();
						
						//��Map��value���ַ������飩�������½��룬֮������µ��ַ�������
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