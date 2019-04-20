package gz.itcast.biz.front.order.service;
import gz.itcast.biz.front.order.dao.OrderDao;
import gz.itcast.biz.front.order.dao.OrderDaoImpl;
import gz.itcast.entity.Order;

import java.util.List;

public class OrderServiceImpl implements OrderService {
	private OrderDao dao = new OrderDaoImpl();
	public void saveOrder(Order order) {
		dao.saveOrder(order);
	}
	public List<Order> queryOrders(String userId) {
		return dao.queryOrders(userId);
	}
	public Order oneOrder(Order order) {
		return dao.oneOrder(order);
	}
}
