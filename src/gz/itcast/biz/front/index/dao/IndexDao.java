package gz.itcast.biz.front.index.dao;

import gz.itcast.entity.Book;
import gz.itcast.entity.Types;

import java.util.List;
/**
 * 首页dao
 * @author APPle
 */
public interface IndexDao {
	/**
	 * 查询所有图书分类
	 * @return
	 */
	public List<Types> queryTypes();
	/**
	 * 根据分类查询图书
	 * @param typeId
	 * @return
	 */
	public List<Book> queryBooks(String typeId);
	/**
	 * 分页显示
	 * @param typeId
	 * @return
	 */
	public List<Book> queryBooks(String typeId,int curPage,int pageSize);
	//根据分类查询后的总记录数
	public int queryBooksCount(String typeId);
	/**
	 * 查询一本书
	 */
	public Book queryBook(String bookId);
}
