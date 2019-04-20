package gz.itcast.biz.front.index.service;

import gz.itcast.entity.Book;
import gz.itcast.entity.Types;
import gz.itcast.util.PageBean;

import java.util.List;

/**
 * ��ҳservice�ӿ�
 * @author APPle
 *
 */
public interface IndexService {
	/**
	 * ��ѯ������Ϣ
	 */
	public List<Types> queryTypes();
	/**
	 * ��ѯ����ͼ��
	 */
	public List<Book> queryBooks(String typeId);
	/**
	 * ��ҳ��ʾͼ��
	 * @param typeId
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public PageBean<Book> queryBooks(String typeId,int curPage,int pageSize) ;
	public Book queryBook(String bookId);
}
