package gz.itcast.biz.front.address.dao;

import gz.itcast.entity.Address;
import gz.itcast.util.BaseDao;
import gz.itcast.util.JdbcUtil;
import gz.itcast.util.WebUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
/**
 * 收货地址的dao实现类
 * @author APPle
 *
 */
public class AddressDaoImpl extends BaseDao<Address> implements AddressDao {
	public List<Address> queryAddress(String userId) {
		try {
			String sql = "select * from address where userid=? order by mktime desc";
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			List<Address> adds = (List<Address>)qr.query(sql,new BeanListHandler(Address.class),new Object[]{userId});
			return adds;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Address add(Address addr) {
		try {
			String sql = "insert into address(id,name,phone,zip,userid,mktime) values(?,?,?,?,?,?)";
			addr.setId(UUID.randomUUID().toString().replace("-", ""));
			addr.setMktime(WebUtil.getDateStr());
			QueryRunner run = new QueryRunner(JdbcUtil.getDataSource());
			run.update(sql,new Object[]{addr.getId(),addr.getName(),addr.getPhone(),addr.getZip(),addr.getUserid(),addr.getMktime()});
			return addr;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void changeToDefault(Address addr) {
		try {
			String sql = "update address set dft='0' where userid=?";
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			qr.update(sql,addr.getUserid());
			sql = "update address set dft='1' where id=?";
			qr.update(sql,addr.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void delete(Address addr) {
		try {
			String sql = "delete from address where id=?";
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			qr.update(sql,addr.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	

}
