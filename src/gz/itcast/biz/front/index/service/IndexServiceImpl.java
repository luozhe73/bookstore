package gz.itcast.biz.front.index.service;

import gz.itcast.biz.front.index.dao.IndexDao;
import gz.itcast.biz.front.index.dao.IndexDaoImpl;
import gz.itcast.entity.Book;
import gz.itcast.entity.Types;
import gz.itcast.util.PageBean;

import java.util.List;
/**
 * 首页service实现类
 * @author APPle
 *
 */
public class IndexServiceImpl implements IndexService {
	
	private IndexDao dao = new IndexDaoImpl();
	
	public List<Types> queryTypes() {
		return dao.queryTypes();
	}
	public List<Book> queryBooks(String typeId) {
		return dao.queryBooks(typeId);
	}
	//分页
	public PageBean<Book> queryBooks(String typeId,int curPage,int pageSize) {
		PageBean<Book> pageBean = new PageBean<Book>();
		pageBean.setCurrentPage(curPage);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(dao.queryBooksCount(typeId));
		pageBean.setData(dao.queryBooks(typeId, curPage, pageSize));
		return pageBean;
	}
	public Book queryBook(String bookId) {
		return dao.queryBook(bookId);
	}
}
