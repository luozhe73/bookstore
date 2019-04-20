package gz.itcast.biz.front.order.dao;

import gz.itcast.entity.Order;
import gz.itcast.entity.OrderLine;
import gz.itcast.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
/**
 * 订单dao实现类
 * @author APPle
 *
 */
public class OrderDaoImpl implements OrderDao {
	public void saveOrder(Order order) {
		Connection conn = null;
		try {
			conn = JdbcUtil.getDataSource().getConnection();
			//开启事务
			conn.setAutoCommit(false);
			//保存订单和订单明细
			String sql = "insert into orders(id,userid,consignee,paytype,amt,state,orderdate) " +
					"values(?,?,?,?,?,?,?)";
			QueryRunner qr = new QueryRunner();
			qr.update(conn,sql, new Object[]{order.getId(),order.getUserid(),
					   order.getConsignee(),order.getPaytype(),order.getAmt(),
					   "0",order.getOrderdate()});
			List<OrderLine> lines = order.getOrderLines();
			for(OrderLine ol:lines){
				sql = "insert into orderline(id,orderid,bookid,amt,price) values(?,?,?,?,?)";
				qr.update( conn,sql, new Object[]{ol.getId(),ol.getOrderid(),ol.getBookid(),ol.getAmt(),ol.getPrice()});
			}
			//提交事务
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
		
	}
	public List<Order> queryOrders(String userId) {
		try {
			String sql = "select * from orders where userid=? order by orderdate desc";
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			List<Order> orders=(List<Order>)qr.query(sql,new BeanListHandler(Order.class),new Object[]{userId});
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public Order oneOrder(Order order) {
		try {
			String sql = "select * from orders where id=?";
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			order = (Order)qr.query(sql,new BeanHandler(Order.class),new Object[]{order.getId()});
			if(order!=null){
				//查询订单明细
				sql = "SELECT ol.id as id,ol.orderid as orderid,ol.bookid as bookid,ol.amt as amt,ol.price as price,"+
					  "ROUND(ol.amt*ol.price,2) AS sum,"+
					  "b.name AS name"+
					  " FROM orderline ol INNER JOIN books b ON ol.bookid=b.id"+
					  " where ol.orderid=?";
				List<OrderLine> lines = (List<OrderLine>)qr.query(sql,new BeanListHandler(OrderLine.class),new Object[]{order.getId()});
				order.setOrderLines(lines);
				return order;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	

	

}
