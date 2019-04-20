package gz.itcast.biz.admin.type.service;

import gz.itcast.biz.admin.type.dao.TypeDao;
import gz.itcast.biz.admin.type.dao.TypeDaoImpl;
import gz.itcast.entity.Types;

import java.util.List;

public class TypeServiceImpl implements TypeService{
	private TypeDao dao = new TypeDaoImpl();
	
	public List<Types> queryTypes(){
		return dao.queryTypes();
	}

	public Types add(Types types) {
		return dao.add(types);
	}
	
}
