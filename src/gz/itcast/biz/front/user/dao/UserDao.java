package gz.itcast.biz.front.user.dao;

import gz.itcast.entity.User;
/**
 * �û���dao�ӿ�
 * @author APPle
 *
 */
public interface UserDao{
	//����
	public User save(User user);
	//�����û�����ѯ
	public User queryByName(String name);
}
