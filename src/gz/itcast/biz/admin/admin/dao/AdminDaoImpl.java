package gz.itcast.biz.admin.admin.dao;

import java.sql.SQLException;

import gz.itcast.entity.Admin;
import gz.itcast.util.JdbcUtil;
import gz.itcast.util.MD5Util;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
public class AdminDaoImpl implements AdminDao{
	public Admin queryByName(String name){
		try {
			Admin admin = null;
			String sql = "select * from admins where name=?";
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			admin = (Admin)qr.query(sql,new BeanHandler(Admin.class),new Object[]{name});
			return admin;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
