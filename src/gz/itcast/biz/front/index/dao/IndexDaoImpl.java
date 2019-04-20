package gz.itcast.biz.front.index.dao;

import gz.itcast.entity.Book;
import gz.itcast.entity.Types;
import gz.itcast.util.JdbcUtil;
import gz.itcast.util.WebUtil;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 * ��ҳdaoʵ��
 * 
 * @author APPle
 * 
 */
public class IndexDaoImpl implements IndexDao {
	/**
	 * ��ѯ����ͼ�����
	 */
	public List<Types> queryTypes() {
		try {
			String sql = "select * from types";// ������Ϣ��
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			List<Types> list = (List<Types>) qr.query(sql, new BeanListHandler(
					Types.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * ���ݷ���id��ѯͼ��
	 */
	public List<Book> queryBooks(String typeId) {
		try {
			// ��ѯ���е�ͼ��
			String sql = "select id,name,price,auth,img,round(price*rebate,2) as currentPrice from books";
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			List<Book> books = null;
			if (!WebUtil.isEmpty(typeId)) {
				sql += " where id in(select bookid from booktype where typeid=?)";
				books = (List<Book>) qr.query(sql, new BeanListHandler(
						Book.class), new Object[] { typeId });
			} else {
				books = (List<Book>) qr.query(sql, new BeanListHandler(
						Book.class));
			}
			return books;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ���ݷ���id��ѯͼ��(��ҳ��ʾ)
	 */
	public List<Book> queryBooks(String typeId,int curPage,int pageSize) {
		try {
			// ��ѯ���е�ͼ��
			String sql = "select id,name,price,auth,img,round(price*rebate,2) as currentPrice from books";
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			List<Book> books = null;
			if (!WebUtil.isEmpty(typeId)) {
				sql += " where id in(select bookid from booktype where typeid=?)";
				sql += " limit ?,?";
				books = (List<Book>) qr.query(sql, new BeanListHandler(
						Book.class), new Object[] { typeId,(curPage-1)*pageSize,pageSize });
			} else {
				sql += " limit ?,?";
				books = (List<Book>) qr.query(sql, new BeanListHandler(
						Book.class),new Object[]{(curPage-1)*pageSize,pageSize});
			}
			return books;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * ������ܼ�����
	 */
	public int queryBooksCount(String typeId) {
		try {
			String sql = "select count(*) from books where 1=1 ";
			if(!WebUtil.isEmpty(typeId)){
				sql += " and id in(select bookid from booktype where typeid='"+typeId+"')";
			}
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			Long count = (Long)qr.query(sql, new ScalarHandler(), new Object[]{});
			return count.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ����id��ѯһ����
	 */
	public Book queryBook(String bookId) {
		try {
			String sql = "select id,name,price,rebate,auth,"
					+ "img,round(price*rebate,2) as currentPrice,"
					+ "publisher,publishdate,pages,size,"
					+ "printtimes,versions,brief,content,onlinetime from books where id=?";
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			Book book = (Book)qr.query(sql, new BeanHandler(Book.class), new Object[]{bookId});
			return book;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	
	public static void main(String[] args) {
		IndexDao dao = new IndexDaoImpl();
		/*List<Types> list = dao.queryTypes();
		for (Types types : list) {
			System.out.println(types);
		}*/
		
		/*List<Book> list = dao.queryBooks("T001");
		for (Book book : list) {
			System.out.println(book);
		}*/
		
		Book book = dao.queryBook("B001");
		System.out.println(book);
	}

	

}
