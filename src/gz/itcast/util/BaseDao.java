package gz.itcast.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 * ͨ�õ�Dao�࣬�����װ��һЩ����DAOͨ�õķ���
 * 		ע�⣺�����ҵ��DAO����Ҫ�̳д���
 * @author APPle
 * Լ��ǰ�᣺
 * 	   1��������javabean�����Ʊ���һ�£�
 * 	   2������ֶ�����javabean���������Ʊ���һ�£�
 */
public abstract class BaseDao<T> {
	private String tableName;//����
	private Class clazz;//��ǰ���͵�����
	/**
	 * �õ�T�����ͣ���ֵ��clazz
	 * @param id
	 * @return
	 */
	public BaseDao(){
		//System.out.println("����BaseDao�Ĺ��췽��");
		//System.out.println(this.getClass());//StudentDao 
		//�õ���������(����������)
		Type parentType = this.getClass().getGenericSuperclass(); //gz.itcast.dao.BaseDao<gz.itcast.entity.Student>
		//ת�ɲ��������ͽӿ�
		ParameterizedType paramterType = (ParameterizedType)parentType;//gz.itcast.dao.BaseDao<gz.itcast.entity.Student>
		//�õ������������еĲ������ͣ��������ͣ�
		Type[] types = paramterType.getActualTypeArguments(); //<gz.itcast.entity.Student>
		//�õ���һ�����±�Ϊ0��
		clazz =  (Class)types[0]; //<gz.itcast.entity.Student>
		//System.out.println("���ͣ�"+clazz);
		
		//�õ�����
		tableName = clazz.getSimpleName().toLowerCase();
		
		//System.out.println("������"+tableName);
	}
	
	
	/**
	 * ��ȡ��������
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
	 * ��ȡ���ж���
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
