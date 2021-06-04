package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DaoCust;
import dao.DaoUser;

public class PnlCustMng extends JPanel implements ActionListener{
	private DefaultTableModel md =  new DefaultTableModel(new String[] {"고객번호", "성", "이름", "주소", "도시", "우편번호", "전화번호"}, 0);
	private JTable tbl = new JTable(md);
	private JScrollPane scp = new JScrollPane(tbl);
	private JPanel pnl = new JPanel();
	private JButton btnAdd, btnModify, btnRe, btnDel;
	private String fname, lname, add, city, code, phone, email;
	
	
	public PnlCustMng() {
		setSize(900, 700);
		setLayout(null);
		pnl.setSize(876, 670);
		pnl.setLocation(12, 10);
		pnl.setLayout(null);
		scp.setBounds(0, 0, 876, 596);
		
		pnl.add(scp);
		add(pnl);
		
		tbl.setModel(new DaoCust().getCustAll(md));
		
		btnAdd = new JButton("추가");
		btnAdd.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnAdd.setBounds(97, 616, 97, 23);
		pnl.add(btnAdd);
		
		btnModify = new JButton("변경");
		btnModify.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnModify.setBounds(291, 616, 97, 23);
		pnl.add(btnModify);
			
		btnRe = new JButton("조회");
		btnRe.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnRe.setBounds(679, 616, 97, 23);
		pnl.add(btnRe);
		
		btnDel = new JButton("삭제");
		btnDel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnDel.setBounds(485, 616, 97, 23);
		pnl.add(btnDel);
		
		btnAdd.addActionListener(this);
		btnModify.addActionListener(this);
		btnRe.addActionListener(this);
		btnDel.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		int row = tbl.getSelectedRow();
		if(e.getSource() == btnAdd) {
			new PnlCustMngAdd();
		}
		if(e.getSource() == btnModify) {
			
			if(row < 0) {
				JOptionPane.showMessageDialog(btnModify, "먼저 어느 고객을 수정할지 선택하세요");
				return ;
			}
			
			fname = tbl.getValueAt(row,1).toString();
			lname = tbl.getValueAt(row,2).toString();
			add = tbl.getValueAt(row,3).toString();
			city = tbl.getValueAt(row,4).toString();
			code = tbl.getValueAt(row,5).toString();
			phone = tbl.getValueAt(row,6).toString();
			
			new PnlCustMngModify(fname, lname, add, city, code, phone);
		}
		
		if(e.getSource() == btnDel) {
			if(row < 0) {
				JOptionPane.showMessageDialog(btnDel, "먼저 어느 고객을 삭제할지 선택하세요");
				return ;
			}
			int select = JOptionPane.showConfirmDialog(btnDel, "삭제하시겠습니까?", "삭제확인",JOptionPane.YES_NO_OPTION);
			
			if(select == 0) {
				int num = (int) tbl.getValueAt(row, 0);
				boolean result = new DaoCust().CustDel(num);
				
				if(result) JOptionPane.showMessageDialog(btnDel, "삭제 성공했습니다. 조회버튼을 눌러 다시 확인해주세요.");
				else JOptionPane.showMessageDialog(btnDel, "삭제 실패했습니다.");
			}
			
			
			
		}
		if(e.getSource() == btnRe) {
			md.setNumRows(0);
			tbl.setModel(new DaoCust().getCustAll(md));
		}
	}	
}

