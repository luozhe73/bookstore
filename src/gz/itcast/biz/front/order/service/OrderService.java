package gz.itcast.biz.front.order.service;

import gz.itcast.entity.Order;

import java.util.List;

public interface OrderService {
	public void saveOrder(Order order);
	public List<Order> queryOrders(String userId);
	public Order oneOrder(Order order);
}
