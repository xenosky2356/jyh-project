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

import dao.DaoProd;
import dao.DaoUser;

public class PnlUserMng extends JPanel implements ActionListener {
	private DefaultTableModel md =  new DefaultTableModel(new String[] {"사용자번호", "사용자이름", "사용자ID", "패스워드", "입사일자", "할당량", "관리자여부"}, 0);
	private JTable tbl = new JTable(md);
	private JScrollPane scp = new JScrollPane(tbl);
	private JPanel pnl = new JPanel();
	private JButton btnModify, btnRe;
	private String userName, id, pass, create, admintf;
	private int quota;
	private int price;
	
	
	public PnlUserMng() {
		setSize(900, 700);
		setLayout(null);
		pnl.setSize(876, 670);
		pnl.setLocation(12, 10);
		pnl.setLayout(null);
		scp.setBounds(0, 0, 876, 596);
		
		pnl.add(scp);
		add(pnl);
		
		tbl.setModel(new DaoUser().getUserAllMng(md));
		
		btnModify = new JButton("변경");
		btnModify.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnModify.setBounds(227, 616, 97, 23);
		pnl.add(btnModify);
			
		btnRe = new JButton("조회");
		btnRe.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnRe.setBounds(551, 616, 97, 23);
		pnl.add(btnRe);
		
		btnModify.addActionListener(this);
		btnRe.addActionListener(this);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {	
		if(e.getSource() == btnModify){
			int row = tbl.getSelectedRow();
			
			if(row < 0) {
				JOptionPane.showMessageDialog(btnModify, "수정하시려는 사용자을 먼저 선택해주세요");
				return ;
			}
			
			userName = tbl.getValueAt(row,1).toString();
			id = tbl.getValueAt(row,2).toString();
			pass = tbl.getValueAt(row,3).toString();
			create = tbl.getValueAt(row,4).toString();
			quota = Integer.parseInt(tbl.getValueAt(row,5).toString());
			admintf = tbl.getValueAt(row,6).toString();
			
			new PnlUserMngModify(userName, id, pass, quota, admintf);
		}
		
		if(e.getSource() == btnRe) {
			md.setNumRows(0);
			tbl.setModel(new DaoUser().getUserAllMng(md));
		}
	}
}
