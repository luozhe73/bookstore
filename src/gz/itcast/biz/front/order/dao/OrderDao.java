package gz.itcast.biz.front.order.dao;

import gz.itcast.entity.Order;

import java.util.List;
/**
 * ����dao�ӿ�
 * @author APPle
 *
 */
public interface OrderDao {
	//���涩��
	public void saveOrder(Order order);
	//��ѯ����
	public List<Order> queryOrders(String userId);
	//��ѯ����
	public Order oneOrder(Order order);
}
