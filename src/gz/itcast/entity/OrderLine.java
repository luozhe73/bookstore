package gz.itcast.entity;
/**
 *  ¶©µ¥Ã÷Ï¸
 * @author APPle
 */
public class OrderLine {
	private String id;
	private String orderid;
	private String bookid;
	private Integer amt;
	private Number price;
	private String name;	
	private Number sum;	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Number getSum() {
		return sum;
	}
	public void setSum(Number sum) {
		this.sum = sum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public Integer getAmt() {
		return amt;
	}
	public void setAmt(Integer amt) {
		this.amt = amt;
	}
	public Number getPrice() {
		return price;
	}
	public void setPrice(Number price) {
		this.price = price;
	}
}
