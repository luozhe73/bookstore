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
 * ��ҳservlet
 * @author APPle
 *
 */
public class IndexServlet extends BaseServlet {
	private IndexService service = new IndexServiceImpl();
	
	//��ҳ
	public void showIndex(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//��ѯ
		List<Types> types = service.queryTypes();
		//�ŵ�request��ȥ
		request.setAttribute("types",types);
		request.getRequestDispatcher("/jsp/front/shopIndex.jsp").forward(request, response);
	}
	
	//ͼ���б�
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
		
		//�Ȼ�ȡ�����id
		String typeId = request.getParameter("typeId");
		PageBean<Book> pageBean = service.queryBooks(typeId, Integer.parseInt(curPage), Integer.parseInt(pageSize));
		
		//�ŵ�request��ȥ
		request.setAttribute("pageBean", pageBean);
		//ת����һ��ҳ����ȥ��ʾ
		request.getRequestDispatcher("/jsp/front/book/list.jsp").forward(request, response);
	}
	
	//ͼ����ϸ
	public void queryBook(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//��ȡ���id
		String bookId = request.getParameter("id");
		Book book = service.queryBook(bookId);
		//��book�ŵ�request
		request.setAttribute("book",book);
		//ת��һ����ϸҳ����ȥ��ʾ
		request.getRequestDispatcher("/jsp/front/book/detail.jsp").forward(request, response);
	}
	
}
