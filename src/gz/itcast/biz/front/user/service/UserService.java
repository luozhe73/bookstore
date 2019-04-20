package gz.itcast.biz.front.user.service;

import gz.itcast.entity.User;

public interface UserService {

	public User login(String name);
	public User reg(User user);
}
