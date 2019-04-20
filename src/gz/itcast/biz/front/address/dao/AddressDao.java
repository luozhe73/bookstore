package gz.itcast.biz.front.address.dao;

import gz.itcast.entity.Address;

import java.util.List;
/**
 * 收货地址dao接口
 * @author APPle
 *
 */
public interface AddressDao {
	//查询某用户的收货地址
	public List<Address> queryAddress(String userId);
	//添加收货地址
	public Address add(Address addr);
	//设置为默认收货地址
	public void changeToDefault(Address addr);
	//删除收货地址
	public void delete(Address addr);
}
