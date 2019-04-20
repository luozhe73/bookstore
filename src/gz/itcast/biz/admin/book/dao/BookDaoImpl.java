package gz.itcast.biz.admin.book.dao;

import gz.itcast.entity.Book;
import gz.itcast.util.BaseDao;
import gz.itcast.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
public class BookDaoImpl extends BaseDao<Book> implements BookDao {
	
	public void save(Book book) {
		Connection conn = null;
		try {
			conn = JdbcUtil.getDataSource().getConnection();
			conn.setAutoCommit(false);
			
			String sql = "insert into books(id,name,price,auth,img,rebate,stock," +
					      "publisher,publishdate,pages,size,printtimes,versions," +
					      "brief,content,onlinetime)" +
					      " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			QueryRunner run = new QueryRunner();
			run.update(conn, sql,new Object[]{book.getId(),book.getName(),book.getPrice(),
										   book.getAuth(),book.getImg(),book.getRebate(),
										   book.getStock(),book.getPublisher(),book.getPublishdate(),
										   book.getPages(),book.getSize(),book.getPrinttimes(),
										   book.getVersions(),book.getBrief(),book.getContent(),
										   book.getOnlinetime()
			});
			sql = "insert into booktype(bookid,typeid) values(?,?)";
			for(String typeId:book.getTypes()){
				run.update(conn,sql,new Object[]{book.getId(),typeId});
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
	}
	
}
