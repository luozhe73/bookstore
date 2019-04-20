package gz.itcast.biz.admin.admin.dao;

import gz.itcast.entity.Admin;


public interface AdminDao {
	public Admin queryByName(String name);
}
