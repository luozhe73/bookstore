package gz.itcast.biz.front.order.dao;

import gz.itcast.entity.Order;

import java.util.List;
/**
 * 订单dao接口
 * @author APPle
 *
 */
public interface OrderDao {
	//保存订单
	public void saveOrder(Order order);
	//查询订单
	public List<Order> queryOrders(String userId);
	//查询订单
	public Order oneOrder(Order order);
}
