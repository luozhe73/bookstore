package gz.itcast.biz.front.user.dao;

import gz.itcast.entity.User;
import gz.itcast.util.BaseDao;
import gz.itcast.util.JdbcUtil;
import gz.itcast.util.MD5Util;

import java.sql.SQLException;
import java.util.UUID;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
/**
 * �û���daoʵ����
 * @author APPle
 *
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao{

	//��ѯ�û�
	public User queryByName(String name) {
		try {
			String sql = "select * from users where name=?";
			QueryRunner run = new QueryRunner(JdbcUtil.getDataSource());
			User user = (User)run.query(sql,new BeanHandler(User.class),new Object[]{name});
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	//�����û�
	public User save(User user) {
		try {
			String sql = "insert into users(id,name,password,phone,email) values(?,?,?,?,?)";
			user.setId(UUID.randomUUID().toString().replace("-", ""));
			user.setPassword(MD5Util.md5(user.getPassword().trim()));
			//����Runner
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			qr.update(sql,new Object[]{user.getId(),user.getName().trim(),user.getPassword().trim(),user.getPhone(),user.getEmail()});
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}	
}
