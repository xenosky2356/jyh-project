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
import dao.DaoProd;

public class PnlProdMng extends JPanel implements ActionListener {
	private DefaultTableModel md =  new DefaultTableModel(new String[] {"제품번호", "제품명", "제품설명", "카테고리", "단가"}, 0);
	private JTable tbl = new JTable(md);
	private JScrollPane scp = new JScrollPane(tbl);
	private JPanel pnl = new JPanel();
	private JButton btnAdd, btnModify, btnRe, btnDel;
	private String prodName, desc, cate;
	private int price;
	
	
	public PnlProdMng() {
		setSize(900, 700);
		setLayout(null);
		pnl.setSize(876, 670);
		pnl.setLocation(12, 10);
		pnl.setLayout(null);
		scp.setBounds(0, 0, 876, 596);
		
		pnl.add(scp);
		add(pnl);
		
		tbl.setModel(new DaoProd().getProdAllMng(md));
		
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
		btnDel.addActionListener(this);
		btnRe.addActionListener(this);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		int row = tbl.getSelectedRow();
		if(e.getSource() == btnAdd) {
			new PnlProdMngAdd();
		}
		
		if(e.getSource() == btnModify){
			
			if(row < 0) {
				JOptionPane.showMessageDialog(btnModify, "수정하시려는 물품을 먼저 선택해주세요");
				return ;
			}
			
			prodName = tbl.getValueAt(row,1).toString();
			desc = tbl.getValueAt(row,2).toString();
			cate = tbl.getValueAt(row,3).toString();
			price = Integer.parseInt(tbl.getValueAt(row,4).toString());
			
			new PnlProdMngModify(prodName, desc, cate, price);
		}
		
		if(e.getSource() == btnDel) {
			int id = (int) tbl.getValueAt(row,0);
			
			if(id < 0) {
				JOptionPane.showMessageDialog(btnDel, "삭제하시려는 물품을 먼저 선택해 주세요");
				return ;
			}
			
			boolean result = new DaoProd().ProdDel(id);
			
			if(result) {
				JOptionPane.showMessageDialog(btnDel, "성공하였습니다. 조회를 눌러 다시 한번 확인하시기 바랍니다");
			}
			else JOptionPane.showMessageDialog(btnDel, "실패하였습니다");
		}
		
		if(e.getSource() == btnRe) {
			md.setNumRows(0);
			tbl.setModel(new DaoProd().getProdAllMng(md));
		}
	}
}
