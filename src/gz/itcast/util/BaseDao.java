package gz.itcast.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 * 通用的Dao类，该类封装了一些所有DAO通用的方法
 * 		注意：具体的业务DAO类需要继承次类
 * @author APPle
 * 约定前提：
 * 	   1）表名和javabean的名称保持一致！
 * 	   2）表的字段名和javabean的属性名称保持一致！
 */
public abstract class BaseDao<T> {
	private String tableName;//表名
	private Class clazz;//当前泛型的类型
	/**
	 * 得到T的类型，赋值给clazz
	 * @param id
	 * @return
	 */
	public BaseDao(){
		//System.out.println("调用BaseDao的构造方法");
		//System.out.println(this.getClass());//StudentDao 
		//得到父类类型(参数化类型)
		Type parentType = this.getClass().getGenericSuperclass(); //gz.itcast.dao.BaseDao<gz.itcast.entity.Student>
		//转成参数类类型接口
		ParameterizedType paramterType = (ParameterizedType)parentType;//gz.itcast.dao.BaseDao<gz.itcast.entity.Student>
		//得到参数化类型中的参数类型（泛型类型）
		Type[] types = paramterType.getActualTypeArguments(); //<gz.itcast.entity.Student>
		//得到第一个（下标为0）
		clazz =  (Class)types[0]; //<gz.itcast.entity.Student>
		//System.out.println("类型："+clazz);
		
		//得到表名
		tableName = clazz.getSimpleName().toLowerCase();
		
		//System.out.println("表名："+tableName);
	}
	
	
	/**
	 * 获取单个对象
	 * @param id
	 * @return
	 */
	public T findById(int id){
		try {
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			return (T)qr.query("select * from "+tableName+"  where id=?", new BeanHandler(clazz), new Object[]{id});
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取所有对象
	 * @return
	 */
	public List<T> findAll(){
		try {
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			return (List<T>)qr.query("select * from "+tableName+"", new BeanListHandler(clazz));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
