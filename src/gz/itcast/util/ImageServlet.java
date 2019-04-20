package gz.itcast.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 用于显示验证码的servlet
 * @author APPle
 */
public class ImageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//得到session对象
		HttpSession session = request.getSession();
		/**
		 * 把验证码输出到浏览器
		 */
		OutputStream out = response.getOutputStream();
		String code = ValidateCodeUtil.genNewCode(out);
		//把验证保存到session域中,以备用户登录时校验之时用
		session.setAttribute("sCode", code);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
