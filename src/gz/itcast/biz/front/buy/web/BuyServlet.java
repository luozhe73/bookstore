package gz.itcast.biz.front.buy.web;



import gz.itcast.entity.Book;
import gz.itcast.util.BaseServlet;
import gz.itcast.util.WebUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 购物的servlet
 * @author APPle
 *
 */
public class BuyServlet extends BaseServlet {

	//把一本图书添加到购物车
	public void addCat(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Book book = WebUtil.copyRequestToBean(request, Book.class);
		Map<String,Book> map = (Map<String, Book>) request.getSession().getAttribute("car");
		if(map==null){
			map = new HashMap<String, Book>();
			request.getSession().setAttribute("car",map);//放一次到session中就可以
		}
		if(map.containsKey(book.getId())){
			book = map.get(book.getId());
			book.setAmt(book.getAmt()+1);
		}else{
			book.setAmt(1);
			map.put(book.getId(), book);
		}
		Map<String,Book> car = (Map<String, Book>) request.getSession().getAttribute("car");
		Iterator<Map.Entry<String, Book>> it = car.entrySet().iterator();
		while(it.hasNext()){
			Book boo = it.next().getValue();
		}
		//获取路径
		request.getRequestDispatcher("/jsp/front/buy/car.jsp").forward(request, response);
	}
	
	// 修改购物车的图书数量(加或减)
	public void modifyCar(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String bookId = request.getParameter("id");
		String num= request.getParameter("num");//获取是+还是-
		Integer add = Integer.parseInt(num);
		bookId = bookId.trim();
		Map<String,Book> car = (Map<String,Book>)request.getSession().getAttribute("car");
		int result = 0;
		if(car.containsKey(bookId)){
			Book book = car.get(bookId);
			book.setAmt(book.getAmt()+add);
			if(book.getAmt()==0){
				car.remove(bookId);
				result = 0;
			}
			result = book.getAmt();
		}
		response.sendRedirect(request.getContextPath()+"/jsp/front/buy/car.jsp");
	}
}
