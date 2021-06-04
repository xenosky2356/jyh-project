package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import vo.CustVO;
import vo.ProdVO;

public class DaoProd extends DaoSet {
	public ProdVO[] getProdAll() {
		ArrayList<ProdVO> list = new ArrayList<>();
		try {
			conn = connDB();
			String sql = "select product_id, product_name, category, list_price, product_image from demo_product_info ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProdVO tmp = new ProdVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBinaryStream(5));
				list.add(tmp);
			}
			
		} catch (SQLException e) {	
		}finally {closeDB();}
		return (ProdVO[]) list.toArray();
	}
	public DefaultTableModel getProdAll(DefaultTableModel model) {
		try {
			conn = connDB();
			String sql = "select product_id, product_name, category, list_price from demo_product_info ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Object[] tmp = new Object[] {rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)};
				model.addRow(tmp);
			}
			
		} catch (SQLException e) {	e.printStackTrace();	
		}finally {closeDB();}
		return model;
	}
	
	public DefaultTableModel getProdAllMng(DefaultTableModel model) {
		try {
			conn = connDB();
			String sql = "select product_id, product_name, product_description, category, list_price "
					+ "from demo_product_info order by product_id ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Object[] tmp = new Object[] {rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)};
				model.addRow(tmp);
			}
			
		} catch (SQLException e) {	e.printStackTrace();	
		}finally {closeDB();}
		return model;
	}
	
	public boolean ProdAdd(ProdVO vo) {
		boolean result = false;
		try {
			conn = connDB();
			String sql = "insert into demo_product_info (product_id, product_name, product_description, category, list_price) "
					+ "values(DEMO_PROD_SEQ.nextval, ?, ?, ?, ?) " ;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getpName());
			pstmt.setString(2, vo.getDesc());
			pstmt.setString(3, vo.getCate());
			pstmt.setInt(4, vo.getPrice());
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				result = true;
			}
			
		} catch (SQLException e) {	e.printStackTrace();	
		}finally {closeDB();}
		
		return result;
	}
	
	public boolean ProdModify(ProdVO vo) {
		boolean result = false;
		
		try {
			conn = connDB();
			String sql = "update demo_product_info set product_name = ?, product_description = ?, category = ?, list_price = ? where product_name = ? " ;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getpName());
			pstmt.setString(2, vo.getDesc());
			pstmt.setString(3, vo.getCate());
			pstmt.setInt(4, vo.getPrice());
			pstmt.setString(5, vo.getpName());
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				result = true;
			}
			
		} catch (SQLException e) {	e.printStackTrace();	
		}finally {closeDB();}
		
		return result;
	}
	
	public boolean ProdDel(int id) {
		boolean result = false;
		
		try {
			conn = connDB();
			String sql = "delete from demo_product_info where product_id = ? " ;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				result = true;
			}
			
		} catch (SQLException e) {	e.printStackTrace();	
		}finally {closeDB();}
		
		return result;
	}
	
}
