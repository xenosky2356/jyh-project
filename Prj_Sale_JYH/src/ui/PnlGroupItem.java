package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.DaoSearch;

public class PnlGroupItem extends JPanel implements ActionListener{
	private JTextField tfSeatchItem;
	private DefaultTableModel mdSearch, mdResult;
	private JTable tblSearch, tblResult;
	private JScrollPane scpSearch, scpResult;
	private JButton btnResult;
	private JPanel pnlResult;
	private JLabel lbInfo;
	private JButton btnSearchItem;
	private JLabel[] lbSelect = new JLabel[4];
	private String[] lbSelectName = {"상품번호", "상품명", "카테고리", "단가"};
	
	public PnlGroupItem() {
		setBackground(new Color(240, 248, 255));
		setSize(860,560);
		setLayout(null);
		
		JPanel pnlItemSearch = new JPanel();
		pnlItemSearch.setBackground(new Color(224, 255, 255));
		pnlItemSearch.setBounds(12, 10, 836, 198);
		pnlItemSearch.setLayout(null);
		
		tfSeatchItem = new JTextField();
		tfSeatchItem.setText("");
		tfSeatchItem.setBounds(271, 10, 207, 21);
		pnlItemSearch.add(tfSeatchItem);
		tfSeatchItem.setColumns(10);
		
		btnSearchItem = new JButton("검색");
		btnSearchItem.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnSearchItem.setBounds(490, 7, 69, 23);
		btnSearchItem.addActionListener(this);
		pnlItemSearch.add(btnSearchItem);
		
		for(int i = 0; i < lbSelect.length; i++) {
			lbSelect[i] = new JLabel(lbSelectName[i]);  
			lbSelect[i].setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			pnlItemSearch.add(lbSelect[i]);
		}
		
		lbSelect[0].setBounds(12,168,251,21);
		lbSelect[1].setBounds(206,168,251,21);
		lbSelect[2].setBounds(388,168,251,21);
		lbSelect[3].setBounds(554,168,251,21);
		
		lbInfo = new JLabel("찾으시는 상품명을 입력해주세요");
		lbInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lbInfo.setBounds(12, 10, 251, 21);
		pnlItemSearch.add(lbInfo);
		
		mdSearch = new DefaultTableModel(new String[] {"상품번호","상품명", "카테고리","단가"} , 0);
		tblSearch = new JTable(mdSearch);	
		tblSearch.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		scpSearch = new JScrollPane(tblSearch);
		scpSearch.setBounds(12, 41, 812, 108);
		pnlItemSearch.add(scpSearch);
		
		btnResult = new JButton("조회");
		btnResult.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnResult.setBounds(755, 159, 69, 29);
		pnlItemSearch.add(btnResult);
		
		
		pnlResult = new JPanel();
		pnlResult.setBounds(22, 218, 812, 321);
		
		mdResult = new DefaultTableModel(new String[] {"구매일자","상품명","고객명","단가","수량", "총액"} , 0);
		pnlResult.setLayout(null);
		tblResult = new JTable(mdResult);	
		tblResult.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		scpResult = new JScrollPane(tblResult);
		scpResult.setBounds(0, 0, 812, 321);
		pnlResult.add(scpResult);
		
		btnResult.addActionListener(this);
		
		tfSeatchItem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					printItem();
				}
			}
		});
		
		add(pnlItemSearch);
		add(pnlResult);	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSearchItem) {
			printItem();
		}
		if(e.getSource() == btnResult) {
			mdResult.setNumRows(0);
			
			if(lbSelect[0].getText().equals("상품번호")) {
				JOptionPane.showMessageDialog(btnResult, "입력해서 나온 결과값을 선택해주세요");
				return ;
			}
			
			tblResult.setModel(new DaoSearch().getItemResult(mdResult, Integer.parseInt(lbSelect[0].getText())));
		}
	}
	
	public void printItem() {
		if(tfSeatchItem.getText().equals("")) {
			JOptionPane.showMessageDialog(btnSearchItem,"상품명을 입력해주세요");
			return ;
		}
		mdSearch.setNumRows(0);
		tblSearch.setModel(new DaoSearch().getShowItems(mdSearch, tfSeatchItem.getText()));
		tblSearch.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				  int row = tblSearch.getSelectedRow();
				  for(int i = 0; i < lbSelect.length; i++) {
					  lbSelect[i].setText(String.valueOf(tblSearch.getValueAt(row, i)));
				  }
			}
		});
	}
}
