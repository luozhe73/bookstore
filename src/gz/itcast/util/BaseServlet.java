package gz.itcast.util;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基础的servlet，该类中存放通常的servlet代码
 * 		注意：具体的业务servlet需要继承此类。
 * @author APPle
 */
public class BaseServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.得到action的参数值
		String action = request.getParameter("action");
		//2.根据action参数值调用对应的方法（约定前提： action值和方法名称保持一致！！！)
		//通过反射调用方法
		//得到类对象
		Class clazz = this.getClass();
		
		try {
			//得到方法对象
			Method method = clazz.getDeclaredMethod(action, 
					HttpServletRequest.class,HttpServletResponse.class);
			
			//调用方法
			method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
