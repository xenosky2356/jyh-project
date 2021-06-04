package dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import vo.CustVO;

public class DaoCust extends DaoSet{
	public Object[] getCustAll() {
		CustVO[] arr = null;
		ArrayList<CustVO> list = new ArrayList<>();
		try {
			conn = connDB();
			String sql = "select customer_id, cust_first_name || ' ' || cust_last_name as cname from demo_customers ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CustVO tmp = new CustVO(rs.getInt(1), rs.getString(2));
				list.add(tmp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return list.toArray();
	}
	
	public DefaultTableModel getCustAll(DefaultTableModel model) {
		try {
			conn = connDB();
			String sql = "select customer_id, cust_first_name, cust_last_name, CUST_STREET_ADDRESS1, CUST_CITY, CUST_POSTAL_CODE, PHONE_NUMBER1 from demo_customers order by customer_id ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Object[] tmp = new Object[] {rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)};
				model.addRow(tmp);
			}
			
		} catch (SQLException e) {	e.printStackTrace();	
		}finally {closeDB();}
		return model;
	}
	
	public CustVO[] getCustName(String name) {
		CustVO[] arr = null;
		ArrayList<CustVO> list = new ArrayList<>();
		try {
			conn = connDB();
			String sql = "select customer_id, cust_first_name || ' ' || cust_last_name from demo_customers where lower(cust_first_name||' ' ||cust_last_name) like lower('%' || ? || '%') ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				CustVO tmp = new CustVO(rs.getInt(1), rs.getString(2));
				list.add(tmp);
			}
			
		} catch (SQLException e) {	e.printStackTrace();	
		}finally {closeDB();}
		return (CustVO[]) list.toArray();
	}
	
	public DefaultTableModel getCustAdd(DefaultTableModel model, String name) {
		try {
			conn = connDB();
			String sql = "insert into demo_orders values(demo_ord_seq.nextval,?,?,to_date(?,'YYYY/MM/DD HH24:MI:SS'),?) ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Object[] tmp = new Object[] {rs.getString(1), rs.getString(2)};
				model.addRow(tmp);
			}
			
		} catch (SQLException e) {	e.printStackTrace();	
		}finally {closeDB();}
		return model;
	}
	
	public boolean CustAdd(CustVO vo) {
		boolean result = false;
		try {
			conn = connDB();
			String sql = "insert into demo_customers VALUES(demo_cust_seq.nextval, ?, ?, ?, null, ?, null, ?, ?, null, 1000, null)" ;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getfName());
			pstmt.setString(2, vo.getlName());
			pstmt.setString(3, vo.getAdd());
			pstmt.setString(4, vo.getCity());
			pstmt.setString(5, vo.getCode());
			pstmt.setString(6, vo.getPhone());
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				result = true;
			}
			
		} catch (SQLException e) {	e.printStackTrace();	
		}finally {closeDB();}
		
		return result;
	}
	
	public boolean CustModify(CustVO vo) {
		boolean result = false;
		
		try {
			conn = connDB();
			String sql = "update demo_customers set cust_street_address1 = ?, cust_city = ?, cust_postal_code = ?, phone_number1 = ? where cust_first_name = ? and cust_last_name = ? " ;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getAdd());
			pstmt.setString(2, vo.getCity());
			pstmt.setString(3, vo.getCode());
			pstmt.setString(4, vo.getPhone());
			pstmt.setString(5, vo.getfName());
			pstmt.setString(6, vo.getlName());
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				result = true;
			}
			
		} catch (SQLException e) {	e.printStackTrace();	
		}finally {closeDB();}
		
		return result;
	}
	
	public boolean CustDel(int id) {
		boolean result = false;
		
		try {
			conn = connDB();
			String sql = "delete from demo_customers where CUSTOMER_ID = ? " ;
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
