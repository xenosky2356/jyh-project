package vo;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ProdVO {
	private int pId;
	private String pName;
	private String desc;
	private String cate;
	private int price;
	private ImageIcon image;
	
	public ProdVO(String pName, String desc, String cate, int price) {
		this.pName = pName;
		this.desc = desc;
		this.cate = cate;
		this.price = price;
	}
	
	public ProdVO(int pId, String pName, String cate, int price, InputStream in) {
		this.pId = pId; this.pName = pName; this.cate = cate; this.price = price; 
		try {
			this.image = new ImageIcon(ImageIO.read(in));
		}
		catch(IOException e) {e.printStackTrace();}
	}
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public int getpId() {return pId;}
	public void setpId(int pId) {this.pId = pId;}
	public String getpName() {return pName;}
	public void setpName(String pName) {this.pName = pName;}
	public String getCate() {return cate;}
	public void setCate(String cate) {this.cate = cate;}
	public int getPrice() {return price;}
	public void setPrice(int price) {this.price = price;}
	public ImageIcon getImage() {return image;}
	public void setImage(InputStream in) {
		try {
			this.image = new ImageIcon(ImageIO.read(in));
		}catch(IOException e) {e.printStackTrace();}
	}
	
	
}
