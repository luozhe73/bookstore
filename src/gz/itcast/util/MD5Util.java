package gz.itcast.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * MD5加密工具类
 * @author APPle
 */
public class MD5Util {
	
	/**
	 * md5加密方法
	 * @param password 没有加密之前的字符串
	 * @return 经过md5加密之后的16进制的32位字符串
	 */
	public static String md5(String password){
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			//加密
			byte[] byteArray = md.digest(password.getBytes());
			
			//-31 10 -36 57 73 -70 89 -85 -66 86 -32 87 -14 15 -120 62  
			//e1  0a dc    39  49  ba 59 ab be 56 e0 57 f2 0f 88 3e
			StringBuffer sb = new StringBuffer();
			
			for(byte b:byteArray){
				//System.out.print(b+" ");
				sb.append(byteToHexString(b));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 根据字节数转换成一个对应的两位的十六进制的字符串
	 * @param b
	 * @return
	 */
	private static String byteToHexString(byte b){
		/**
		 * byte: 无符号 （正数）  取值范围：0-255
		 *       有符号（有负数） 取值范围： -128-127 
		 */
		//byte -> 16进制
		//如果是负数
		int num = 0;
		if(b<0){
			num=256+b;
		}else{
			num=b;
		}
		
		//求商，除数第一位
		int first = num/16;
		
		//求模，余数第二位
		int second = num%16; 

		return hexs[first]+hexs[second];
	}
	
	private static String[] hexs = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println(MD5Util.md5("root"));
	}
	//63a9f0ea7bb98050796b649e85481845

}
