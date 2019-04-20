package gz.itcast.biz.front.index.web;



import gz.itcast.biz.front.index.service.IndexService;
import gz.itcast.biz.front.index.service.IndexServiceImpl;
import gz.itcast.entity.Book;
import gz.itcast.entity.Types;
import gz.itcast.util.BaseServlet;
import gz.itcast.util.PageBean;
import gz.itcast.util.WebUtil;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 首页servlet
 * @author APPle
 *
 */
public class IndexServlet extends BaseServlet {
	private IndexService service = new IndexServiceImpl();
	
	//主页
	public void showIndex(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//查询
		List<Types> types = service.queryTypes();
		//放到request中去
		request.setAttribute("types",types);
		request.getRequestDispatcher("/jsp/front/shopIndex.jsp").forward(request, response);
	}
	
	//图书列表
	public void queryBooks(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String curPage = request.getParameter("currentPage");
		String pageSize = request.getParameter("pageSize");
		if(WebUtil.isEmpty(curPage)){
			curPage = "1";
		}
		if(WebUtil.isEmpty(pageSize)){
			pageSize = "8";
		}
		
		//先获取分类的id
		String typeId = request.getParameter("typeId");
		PageBean<Book> pageBean = service.queryBooks(typeId, Integer.parseInt(curPage), Integer.parseInt(pageSize));
		
		//放到request中去
		request.setAttribute("pageBean", pageBean);
		//转发到一个页面上去显示
		request.getRequestDispatcher("/jsp/front/book/list.jsp").forward(request, response);
	}
	
	//图书明细
	public void queryBook(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//获取书的id
		String bookId = request.getParameter("id");
		Book book = service.queryBook(bookId);
		//将book放到request
		request.setAttribute("book",book);
		//转到一个明细页面上去显示
		request.getRequestDispatcher("/jsp/front/book/detail.jsp").forward(request, response);
	}
	
}
