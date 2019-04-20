package gz.itcast.biz.admin.type.dao;

import gz.itcast.entity.Types;

import java.util.List;

public interface TypeDao {
	public List<Types> queryTypes();
	public Types add(Types types);
}
