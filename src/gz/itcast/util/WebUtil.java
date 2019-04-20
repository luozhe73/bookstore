package gz.itcast.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

/**
 * web开发的一些工具类
 * @author APPle
 *
 */
public class WebUtil {

	/**
	 * 把请求数据拷贝到javabean中
	 * <T> T: 声明一个泛型返回值类型
	 */
	public static <T> T copyRequestToBean(HttpServletRequest request,Class<T> clazz){
		try {
			T obj = clazz.newInstance();
			//取出请求数据
			Map map = request.getParameterMap();
			//使用beanutils工具把map数据拷贝到javabean对象
			BeanUtils.copyProperties(obj, map);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 用于判断变量是否为空（null 或 空字符串）
	 * true: 空
	 * false: 不为空
	 */
	public static boolean isEmpty(String str){
		return str==null || str.trim().equals("");
	}
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddhhmmss");
	/**
	 * 获取当前时间的字符串
	 */
	public static String getDateStr(){
		return sdf.format(new Date());
	}
	
	/**
	 * 生成订单号的方法
	 */
	public static String getOrderNo(int hashCode){
		Date date = new Date();
		String string  = sdf2.format(date);
		return hashCode+string;
	}
}

