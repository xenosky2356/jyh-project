package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.DaoSearch;

import java.awt.Color;

public class PnlGroupCust extends JPanel implements ActionListener {
	private JTextField tfSeatchCust;
	private DefaultTableModel mdSearch, mdResult;
	private JTable tblSearch, tblResult;
	private JScrollPane scpSearch, scpResult;
	private JButton btnSearchItem;
	private JPanel pnlResult;
	private JLabel lbCustId;
	private JLabel lbCustName;
	private JLabel lbInfo;
	private JButton btnSearchCust;
	private JTextField[] tfCustResult = new JTextField[2];
	private String searchCust;
	public PnlGroupCust() {
		setBackground(new Color(240, 248, 255));
		setSize(860,560);
		setLayout(null);
		
		JPanel pnlItemSearch = new JPanel();
		pnlItemSearch.setBackground(new Color(224, 255, 255));
		pnlItemSearch.setBounds(12, 10, 836, 198);
		pnlItemSearch.setLayout(null);
		
		tfSeatchCust = new JTextField();
		tfSeatchCust.setText("");
		tfSeatchCust.setBounds(289, 11, 222, 20);
		pnlItemSearch.add(tfSeatchCust);
		tfSeatchCust.setColumns(10);
		
		searchCust = tfSeatchCust.getText();
		
		btnSearchCust = new JButton("검색");
		btnSearchCust.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		btnSearchCust.setBounds(534, 8, 69, 22);
		pnlItemSearch.add(btnSearchCust);
		
		mdSearch = new DefaultTableModel(new String[] {"고객 ID","고객명"}, 0);
		tblSearch = new JTable(mdSearch);	
		tblSearch.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		scpSearch = new JScrollPane(tblSearch);
		scpSearch.setBounds(12, 41, 812, 108);
		pnlItemSearch.add(scpSearch);
			
		for(int i = 0; i < tfCustResult.length; i++) {
			tfCustResult[i] = new JTextField();  
			tfCustResult[i].setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			tfCustResult[i].setEditable(false);
			pnlItemSearch.add(tfCustResult[i]);
		}
		tfCustResult[0].setBounds(158,159,207,29);
		tfCustResult[1].setBounds(475,159,207,29);
		
		
		add(pnlItemSearch);
		
		btnSearchItem = new JButton("조회");
		btnSearchItem.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnSearchItem.setBounds(755, 159, 69, 29);
		pnlItemSearch.add(btnSearchItem);
		
		lbCustId = new JLabel("고객ID");
		lbCustId.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lbCustId.setBounds(59, 158, 56, 30);
		pnlItemSearch.add(lbCustId);
		
		lbCustName = new JLabel("고객명");
		lbCustName.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lbCustName.setBounds(394, 159, 56, 29);
		pnlItemSearch.add(lbCustName);
		
		lbInfo = new JLabel("찾으시는 고객이름를 입력해주세요");
		lbInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lbInfo.setBounds(12, 10, 265, 21);
		pnlItemSearch.add(lbInfo);
		
		pnlResult = new JPanel();
		pnlResult.setBounds(22, 218, 812, 321);
		
		mdResult = new DefaultTableModel(new String[] {"구매일자","상품명","고객명","단가","수량","총액"} , 0);
		pnlResult.setLayout(null);
		tblResult = new JTable(mdResult);	
		tblResult.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		scpResult = new JScrollPane(tblResult);
		scpResult.setBounds(0, 0, 812, 321);
		
		btnSearchCust.addActionListener(this);
		btnSearchItem.addActionListener(this);
		tfSeatchCust.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					SearchCust();
				}
			}
		});
		
		pnlResult.add(scpResult);
		add(pnlResult);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSearchCust) {
			SearchCust();
		}
		if(e.getSource() == btnSearchItem) {
			mdResult.setNumRows(0);
			if(tfCustResult[1].getText().equals("")) {
				JOptionPane.showMessageDialog(btnSearchItem,"고객명을 선택해주세요");
				return ;
			}
			new DaoSearch().getCustResult(mdResult, Integer.parseInt(tfCustResult[0].getText()));
			tfSeatchCust.setText("");
		}
	}
	
	public void SearchCust() {
		if(tfSeatchCust.getText().equals("")) {
			JOptionPane.showMessageDialog(btnSearchCust,"고객명을 입력해주세요");
			return ;
		}
		mdSearch.setNumRows(0);
		new DaoSearch().getShowCust(mdSearch, tfSeatchCust.getText());
		
		tblSearch.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				  int row = tblSearch.getSelectedRow();
				  for(int i = 0; i < tfCustResult.length; i++) {
					  tfCustResult[i].setText(String.valueOf(tblSearch.getValueAt(row, i)));
				  }
			}
		});
	}
}
