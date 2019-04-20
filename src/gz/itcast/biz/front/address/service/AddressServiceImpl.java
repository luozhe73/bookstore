package gz.itcast.biz.front.address.service;

import gz.itcast.biz.front.address.dao.AddressDao;
import gz.itcast.biz.front.address.dao.AddressDaoImpl;
import gz.itcast.entity.Address;

import java.util.List;

public class AddressServiceImpl implements AddressService {
	AddressDao dao = new AddressDaoImpl();
	
	public Address add(Address addr) {
		return dao.add(addr);
	}

	public void changeToDefault(Address addr) {
		dao.changeToDefault(addr);
	}

	public void delete(Address addr) {
		dao.delete(addr);
	}

	public List<Address> queryAddress(String userId) {
		return dao.queryAddress(userId);
	}

}
