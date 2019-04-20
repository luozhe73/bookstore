package gz.itcast.entity;
import java.util.ArrayList;
/**
 * 订单
 */
import java.util.List;
public class Order {
	private String id;
	private String userid;
	private String consignee;//收货人明细：格式为：收货地址，电话，邮编
	private String paytype;
	private Double amt;
	private String state;
	private String orderdate;
	private List<OrderLine> orderLines = new ArrayList<OrderLine>();
	public List<OrderLine> getOrderLines() {
		return orderLines;
	}
	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public Double getAmt() {
		return amt;
	}
	public void setAmt(Double amt) {
		this.amt = amt;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	
	
}
