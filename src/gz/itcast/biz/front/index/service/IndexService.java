package gz.itcast.biz.front.index.service;

import gz.itcast.entity.Book;
import gz.itcast.entity.Types;
import gz.itcast.util.PageBean;

import java.util.List;

/**
 * 首页service接口
 * @author APPle
 *
 */
public interface IndexService {
	/**
	 * 查询分类信息
	 */
	public List<Types> queryTypes();
	/**
	 * 查询所有图书
	 */
	public List<Book> queryBooks(String typeId);
	/**
	 * 分页显示图书
	 * @param typeId
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public PageBean<Book> queryBooks(String typeId,int curPage,int pageSize) ;
	public Book queryBook(String bookId);
}
