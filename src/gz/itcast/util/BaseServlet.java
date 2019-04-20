package gz.itcast.util;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ������servlet�������д��ͨ����servlet����
 * 		ע�⣺�����ҵ��servlet��Ҫ�̳д��ࡣ
 * @author APPle
 */
public class BaseServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.�õ�action�Ĳ���ֵ
		String action = request.getParameter("action");
		//2.����action����ֵ���ö�Ӧ�ķ�����Լ��ǰ�᣺ actionֵ�ͷ������Ʊ���һ�£�����)
		//ͨ��������÷���
		//�õ������
		Class clazz = this.getClass();
		
		try {
			//�õ���������
			Method method = clazz.getDeclaredMethod(action, 
					HttpServletRequest.class,HttpServletResponse.class);
			
			//���÷���
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
