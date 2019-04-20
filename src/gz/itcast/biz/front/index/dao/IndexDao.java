package gz.itcast.biz.front.index.dao;

import gz.itcast.entity.Book;
import gz.itcast.entity.Types;

import java.util.List;
/**
 * ��ҳdao
 * @author APPle
 */
public interface IndexDao {
	/**
	 * ��ѯ����ͼ�����
	 * @return
	 */
	public List<Types> queryTypes();
	/**
	 * ���ݷ����ѯͼ��
	 * @param typeId
	 * @return
	 */
	public List<Book> queryBooks(String typeId);
	/**
	 * ��ҳ��ʾ
	 * @param typeId
	 * @return
	 */
	public List<Book> queryBooks(String typeId,int curPage,int pageSize);
	//���ݷ����ѯ����ܼ�¼��
	public int queryBooksCount(String typeId);
	/**
	 * ��ѯһ����
	 */
	public Book queryBook(String bookId);
}
