package gz.itcast.biz.front.address.dao;

import gz.itcast.entity.Address;

import java.util.List;
/**
 * �ջ���ַdao�ӿ�
 * @author APPle
 *
 */
public interface AddressDao {
	//��ѯĳ�û����ջ���ַ
	public List<Address> queryAddress(String userId);
	//����ջ���ַ
	public Address add(Address addr);
	//����ΪĬ���ջ���ַ
	public void changeToDefault(Address addr);
	//ɾ���ջ���ַ
	public void delete(Address addr);
}
