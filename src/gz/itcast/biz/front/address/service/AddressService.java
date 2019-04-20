package gz.itcast.biz.front.address.service;

import gz.itcast.entity.Address;

import java.util.List;

public interface AddressService {
	//��ѯĳ�û����ջ���ַ
	public List<Address> queryAddress(String userId);
	//����ջ���ַ
	public Address add(Address addr);
	//����ΪĬ���ջ���ַ
	public void changeToDefault(Address addr);
	//ɾ���ջ���ַ
	public void delete(Address addr);
}
