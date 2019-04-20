package gz.itcast.biz.admin.type.service;

import gz.itcast.entity.Types;

import java.util.List;

public interface TypeService {
	public List<Types> queryTypes();
	public Types add(Types types);
}
