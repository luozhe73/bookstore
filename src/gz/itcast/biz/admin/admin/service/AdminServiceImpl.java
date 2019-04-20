package gz.itcast.biz.admin.admin.service;

import gz.itcast.biz.admin.admin.dao.AdminDao;
import gz.itcast.biz.admin.admin.dao.AdminDaoImpl;
import gz.itcast.entity.Admin;

public class AdminServiceImpl implements AdminService{
	private AdminDao dao = new AdminDaoImpl();
	public Admin login(String name) {
		return dao.queryByName(name);
	}
}
