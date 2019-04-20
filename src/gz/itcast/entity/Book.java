package gz.itcast.entity;
/**
 * 图书
 * @author APPle
 *
 */
public class Book {
	private String id;
	private String name;
	private Double price;
	private Double currentPrice; //现价=原价（price）*折扣(rebate)
	private String auth;
	private String img;
	private Double rebate; //折扣
	private Integer stock;
	private String publisher;
	private String publishdate;
	private Integer pages;
	private Integer size;
	private Integer printtimes;
	private Integer versions;
	private String brief;
	private String content;
	private Integer amt;
	private String onlinetime;
	private String[] types; //图书分类
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
	public void setOnlinetime(String onlinetime) {
		this.onlinetime = onlinetime;
	}
	public String getOnlinetime() {
		return onlinetime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Double getRebate() {
		return rebate;
	}
	public void setRebate(Double rebate) {
		this.rebate = rebate;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublishdate() {
		return publishdate;
	}
	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getPrinttimes() {
		return printtimes;
	}
	public void setPrinttimes(Integer printtimes) {
		this.printtimes = printtimes;
	}
	public Integer getVersions() {
		return versions;
	}
	public void setVersions(Integer versions) {
		this.versions = versions;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public Integer getAmt() {
		return amt;
	}
	public void setAmt(Integer amt) {
		this.amt = amt;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price
				+ ", currentPrice=" + currentPrice + ", auth=" + auth
				+ ", img=" + img + ", rebate=" + rebate + ", stock=" + stock
				+ ", publisher=" + publisher + ", publishdate=" + publishdate
				+ ", pages=" + pages + ", size=" + size + ", printtimes="
				+ printtimes + ", versions=" + versions + ", brief=" + brief
				+ ", content=" + content + ", amt=" + amt + "]";
	}
}
