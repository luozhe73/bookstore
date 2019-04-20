package gz.itcast.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * ������ʾ��֤���servlet
 * @author APPle
 */
public class ImageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�õ�session����
		HttpSession session = request.getSession();
		/**
		 * ����֤������������
		 */
		OutputStream out = response.getOutputStream();
		String code = ValidateCodeUtil.genNewCode(out);
		//����֤���浽session����,�Ա��û���¼ʱУ��֮ʱ��
		session.setAttribute("sCode", code);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
