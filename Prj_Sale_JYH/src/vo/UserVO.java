package vo;

import java.util.Date;

public class UserVO {
	private int userId;
	private String userName;
	private String password;
	private Date createdOn;
	private int quota;
	private String products;
	private Date expiresOn;
	private String adminUser;
	private static String id;
	
	//UserVO의 정보를 가져올 때
	public UserVO(int userId, String userName, String password, Date createdOn, int quota, String products,
			Date expiresOn, String adminUser, String id) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.createdOn = createdOn;
		this.quota = quota;
		this.products = products;
		this.expiresOn = expiresOn;
		this.adminUser = adminUser;
		this.id = id;
	}

	public UserVO(String id, String userName, String password, int quota, String adminUser) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.quota = quota;
		this.adminUser = adminUser;
	}

	//demo_user에 insert할때
	public UserVO(String userName, String id, String password, Date createdOn) {
		super();
		this.userName = userName;
		this.password = password;
		this.createdOn = createdOn;
		this.id = id;
	}
	@Override
	public String toString() {return userId + ":::" +userName + "/" + id;}
	
	public int getUserId() {return userId;}
	public void setUserId(int userId) {this.userId = userId;}
	
	public String getUserName() {return userName;}
	public void setUserName(String userName) {this.userName = userName;}
	
	public String getPassword() {return password;	}
	public void setPassword(String password) {this.password = password;}

	public Date getCreatedOn() {return createdOn;}
	public void setCreatedOn(Date createdOn) {this.createdOn = createdOn;}

	public int getQuota() {return quota;}
	public void setQuota(int quota) {this.quota = quota;}

	public String getProducts() {return products;}
	public void setProducts(String products) {this.products = products;}

	public Date getExpiresOn() {return expiresOn;}
	public void setExpiresOn(Date expiresOn) {this.expiresOn = expiresOn;}

	public String getAdminUser() {return adminUser;}
	public void setAdminUser(String adminUser) {this.adminUser = adminUser;}

	public static String getId() {return id;}
	public void setId(String id) {this.id = id;}
	
}
