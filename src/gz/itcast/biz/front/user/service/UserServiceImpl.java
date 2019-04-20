package gz.itcast.biz.front.user.service;

import gz.itcast.biz.front.user.dao.UserDao;
import gz.itcast.biz.front.user.dao.UserDaoImpl;
import gz.itcast.entity.User;

public class UserServiceImpl implements UserService {
	UserDao userDao = new UserDaoImpl();
	
	public User login(String name) {
		return userDao.queryByName(name);
	}

	public User reg(User user) {
		return userDao.save(user);
	}

}
