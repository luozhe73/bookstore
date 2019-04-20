package gz.itcast.entity;
/**
 *  ’ªıµÿ÷∑
 * @author APPle
 *
 */
public class Address {
	private String id;
	private String name;
	private String phone;
	private String zip;
	private String dft;
	private String userid;
	private String mktime;
	public String getMktime() {
		return mktime;
	}
	public void setMktime(String mktime) {
		this.mktime = mktime;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getDft() {
		return dft;
	}
	public void setDft(String dft) {
		this.dft = dft;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", name=" + name + ", phone=" + phone
				+ ", zip=" + zip + ", dft=" + dft + ", userid=" + userid + "]";
	}
}
