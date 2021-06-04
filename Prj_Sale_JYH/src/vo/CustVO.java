package vo;

public class CustVO {
	private int customerId;
	private String cName;
	private String fName;
	private String lName;
	private String add;
	private String city;
	private String code;
	private String phone;
	
	public CustVO(String fName, String lName, String add, String city, String code, String phone) {
		this.fName = fName;
		this.lName = lName;
		this.add = add;
		this.city = city;
		this.code = code;
		this.phone = phone;
	}
	
	
	public CustVO(int customerId, String fName, String lName, String add, String city, String code, String phone) {
		this.customerId = customerId; 
		this.fName = fName;
		this.lName = lName;
		this.add = add;
		this.city = city;
		this.code = code;
		this.phone = phone;
	}
	
	public CustVO(int customerId, String cName) {
		this.customerId = customerId;
		this.cName = cName;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	


	
}