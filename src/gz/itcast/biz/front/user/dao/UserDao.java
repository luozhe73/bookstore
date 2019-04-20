package gz.itcast.biz.front.user.dao;

import gz.itcast.entity.User;
/**
 * 用户的dao接口
 * @author APPle
 *
 */
public interface UserDao{
	//保存
	public User save(User user);
	//根据用户名查询
	public User queryByName(String name);
}
