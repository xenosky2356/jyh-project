package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import vo.CustVO;
import vo.ProdVO;
import vo.SearchVO;

public class DaoSearch extends DaoSet{
	public DefaultTableModel getSearchDate(DefaultTableModel model, SearchVO vo, String StartDate, String EndDate) {
		try {
			conn = connDB();
			String sql = "select order_timestamp, cust_first_name || ' ' || cust_last_name, order_total "
					+ "from demo_customers c, demo_orders o "
					+ "where c.customer_id = o.customer_id and order_timestamp BETWEEN TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') order by order_timestamp desc ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, StartDate);
			pstmt.setString(2, EndDate);
		
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Object[] tmp = new Object[] {rs.getDate(1), rs.getString(2), rs.getInt(3)};
				model.addRow(tmp);
			}
			
		} catch (SQLException e) {	e.printStackTrace();	
		}finally {closeDB();}
		return model;
	}
	
	public DefaultTableModel getShowItems(DefaultTableModel model, String pName) {
		try {
			conn = connDB();
			String sql = "select product_id, product_name, category, list_price from demo_product_info where product_name like INITCAP('%' || ? || '%') ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pName);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Object[] tmp = new Object[] {rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)};
				model.addRow(tmp);
			}
			
		} catch (SQLException e) {	e.printStackTrace();	
		}finally {closeDB();}
		return model;
	}
	
	public DefaultTableModel getItemResult(DefaultTableModel model, int id) {
		try {
			conn = connDB();
			String sql = "select DISTINCT order_timestamp, product_name, cust_first_name || ' ' || cust_last_name,unit_price, quantity, unit_price * quantity "
					+ "from demo_product_info pi, demo_orders os, demo_customers cs, demo_order_items oi "
					+ "where pi.product_id = oi.product_id and cs.customer_id = os.customer_id and oi.product_id = ? and oi.order_id = os.order_id order by order_timestamp desc ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Object[] tmp = new Object[] {rs.getDate(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)};
				model.addRow(tmp);
			}
			
		} catch (SQLException e) {	e.printStackTrace();	
		}finally {closeDB();}
		return model;
	}
	
	public DefaultTableModel getShowCust(DefaultTableModel model, String pName) {
		try {
			conn = connDB();
			String sql = "select customer_id, cust_first_name || ' ' || cust_last_name "
					+ "from demo_customers "
					+ "where cust_first_name || ' ' || cust_last_name like INITCAP('%' || ? || '%') ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pName);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Object[] tmp = new Object[] {rs.getString(1), rs.getString(2)};
				model.addRow(tmp);
			}
			
		} catch (SQLException e) {	e.printStackTrace();	
		}finally {closeDB();}
		return model;
	}
	
	public DefaultTableModel getCustResult(DefaultTableModel model, int pId) {
		try {
			conn = connDB();
			String sql = "select DISTINCT order_timestamp, product_name, cust_first_name || ' ' || cust_last_name, unit_price, quantity, unit_price * quantity " +
					"from demo_product_info pi, demo_orders os, demo_customers cs, demo_order_items oi " +
					"where pi.product_id = oi.product_id and cs.customer_id = os.customer_id and cs.customer_id = ? and oi.order_id = os.order_id order by order_timestamp desc ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Object[] tmp = new Object[] {rs.getDate(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)};
				model.addRow(tmp);
			}
			
		} catch (SQLException e) {	e.printStackTrace();	
		}finally {closeDB();}
		return model;
	}
}
