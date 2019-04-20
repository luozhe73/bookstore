package gz.itcast.biz.admin.type.dao;
import gz.itcast.entity.Types;
import gz.itcast.util.BaseDao;
import gz.itcast.util.JdbcUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
public class TypeDaoImpl extends BaseDao<Types> implements TypeDao {
	
	public List<Types> queryTypes(){
		try {
			String sql = "select * from types";
			QueryRunner run = new QueryRunner(JdbcUtil.getDataSource());
			List<Types> types = (List<Types>)run.query(sql,new BeanListHandler(Types.class));
			return types;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * ÃÌº”∑÷¿‡
	 */
	public Types add(Types types){
		try {
			String sql = "insert into types(id,name,descr) values(?,?,?)";
			types.setId(UUID.randomUUID().toString().replace("-", ""));
			QueryRunner run = new QueryRunner(JdbcUtil.getDataSource());
			run.update(sql,new Object[]{types.getId(),types.getName(),types.getDescr()});
			return types;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
