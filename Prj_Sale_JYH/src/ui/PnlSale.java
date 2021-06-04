package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controller.MainController;
import dao.DaoCust;
import dao.DaoOrder;
import dao.DaoProd;
import util.MyCalendar;
import vo.CustVO;
import vo.OrderVO;
import vo.UserVO;
import org.jdatepicker.util.JDatePickerUtil;

public class PnlSale extends JPanel implements ActionListener{
	private DefaultTableModel mdProd, modelBuylist;
	private JTable tblProd, tblBuylist;
	private JScrollPane scp, paneProd, scpBuylist;
	private JButton btnAdd , btnPay, btnProdSelDel;
	private UserVO vo;
	private DaoProd daoProd;
	private JLabel lblAmount, lblTotal, lblNewLabel, lblNewLabel_1;
	private JSpinner spQt;
	private Object[] custs, prods;
	private JComboBox cbCust;
	private String search;
	private JDatePicker dPick;
	private JButton btnProdAllDel;
	private int custId; 
	private ArrayList searchIdSet = new ArrayList();
	private JLabel lbInfo;
	private JLabel lbCustName;
			
	public PnlSale() {
		setSize(900, 700);
		setLayout(null);
		
		JPanel pnlFind = new JPanel();
		pnlFind.setForeground(UIManager.getColor("Button.background"));
		pnlFind.setBackground(new Color(230, 230, 250));
		pnlFind.setBorder(new LineBorder(null));
		pnlFind.setBounds(12, 20, 425, 670);
		add(pnlFind);
		pnlFind.setLayout(null);
		
		dPick = new JDateComponentFactory().createJDatePicker();
		dPick.setTextEditable(false);
		dPick.setShowYearButtons(true);
		JPanel pnlDpic = new JPanel();
		pnlDpic.add((JComponent)dPick);
		pnlDpic.setBounds(109,43,218,31);
		pnlFind.add(pnlDpic);
		
		JLabel lblCustId = new JLabel("고객 이름");
		lblCustId.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblCustId.setBounds(16, 124, 74, 31);
		pnlFind.add(lblCustId);
		
		JLabel lblNewLabel_3 = new JLabel("원하시는 제품을 선택해주세요");
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(16, 272, 242, 23);
		pnlFind.add(lblNewLabel_3);
		
		mdProd = new DefaultTableModel(new String[] {"상품번호","상품명","카테고리","단가"}, 0);
		tblProd = new JTable(mdProd);
		tblProd.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		paneProd = new JScrollPane(tblProd);
		paneProd.setBounds(12, 318, 382, 226);
		tblProd.setModel(new DaoProd().getProdAll(mdProd));
		pnlFind.add(paneProd);
		
		cbCust = new JComboBox();
		cbCust.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		cbCust.setEditable(true);
		cbCust.setBounds(109, 121, 285, 36);
		pnlFind.add(cbCust);
		
		lblAmount = new JLabel("수량");
		lblAmount.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblAmount.setBounds(12, 573, 32, 38);
		pnlFind.add(lblAmount);
		
		spQt = new JSpinner();
		spQt.setModel(new SpinnerNumberModel(new Integer(1), new Integer(0), null, new Integer(1)));
		spQt.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		spQt.setBounds(56, 573, 59, 38);
		pnlFind.add(spQt);
		
		btnAdd = new JButton("추가");
		btnAdd.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnAdd.setBounds(323, 573, 71, 38);
		pnlFind.add(btnAdd);
		
		lblNewLabel = new JLabel("주문 일자");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel.setBounds(16, 43, 74, 31);
		pnlFind.add(lblNewLabel);
		
		lbInfo = new JLabel("고객 이름:");
		lbInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lbInfo.setBounds(16, 204, 74, 31);
		pnlFind.add(lbInfo);
		
		lbCustName = new JLabel(" ");
		lbCustName.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		lbCustName.setBounds(109, 204, 285, 31);
		pnlFind.add(lbCustName);
		
		JPanel pnlBuylist = new JPanel();
		pnlBuylist.setBackground(new Color(255, 192, 203));
		pnlBuylist.setForeground(UIManager.getColor("Button.background"));
		pnlBuylist.setBorder(new LineBorder(null));
		pnlBuylist.setBounds(449, 20, 439, 670);
		add(pnlBuylist);
		pnlBuylist.setLayout(null);
		
		btnPay = new JButton("결제");
		btnPay.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnPay.setBounds(33, 570, 382, 46);
		pnlBuylist.add(btnPay);
		
		JLabel lblToName = new JLabel("총 합계");
		lblToName.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblToName.setBounds(33, 499, 66, 46);
		pnlBuylist.add(lblToName);
		
		btnProdSelDel = new JButton("선택 삭제");
		btnProdSelDel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnProdSelDel.setBounds(186, 31, 107, 46);
		pnlBuylist.add(btnProdSelDel);
		
		modelBuylist = new DefaultTableModel(new String[] {"상품번호","구분", "상품명", "단가", "수량", "소계"}, 0);
		tblBuylist = new JTable(modelBuylist);	
		scpBuylist = new JScrollPane(tblBuylist);
		tblBuylist.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		scpBuylist.setBounds(28, 87, 387, 402);
		pnlBuylist.add(scpBuylist);
		
		lblTotal = new JLabel("0");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblTotal.setBounds(111, 499, 304, 46);
		pnlBuylist.add(lblTotal);
		
		lblNewLabel_1 = new JLabel("장바구니");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(33, 31, 66, 46);
		pnlBuylist.add(lblNewLabel_1);
		
		btnProdAllDel = new JButton("전체 삭제");
		btnProdAllDel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnProdAllDel.setBounds(308, 31, 107, 46);
		pnlBuylist.add(btnProdAllDel);
		
		cbCust.addActionListener(this);
		btnAdd.addActionListener(this); 
		btnPay.addActionListener(this);
		btnProdSelDel.addActionListener(this);
		btnProdAllDel.addActionListener(this);
		
		custs = new DaoCust().getCustAll();
		
		cbCust.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyChar() >= 'A' && e.getKeyChar() <= 'Z' ||
					e.getKeyChar() >= 'a' && e.getKeyChar() <= 'z' || e.getKeyChar() == 8) {
					search = cbCust.getEditor().getItem().toString().trim();
					ArrayList searchSet = new ArrayList<>();
					searchIdSet = new ArrayList();
					searchSet.add(search);
					searchIdSet.add(search);
					for (int i = 0; i < custs.length; i++) {
						CustVO vo = (CustVO) custs[i];
						if (vo.getcName().toLowerCase().contains(search.toLowerCase())) {
							searchSet.add(vo.getcName());
							searchIdSet.add(vo.getCustomerId());
						}
					}
					cbCust.setModel(new DefaultComboBoxModel<>(searchSet.toArray()));
					if (cbCust.getItemCount() > 1)
						cbCust.showPopup();
				}			
			}		
		});
		
		cbCust.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				cbCust.getSelectedObjects();
			}
		});
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cbCust) {
			custId = (int) searchIdSet.get(cbCust.getSelectedIndex());
			lbCustName.setText((String) cbCust.getSelectedItem());
		}
		
		if(e.getSource() == btnAdd) {
			int row = tblProd.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(btnAdd, "제품목록에서 제품을 먼저 선택하세요");
				return ;
			}
			String pId = tblProd.getValueAt(row,0).toString();
			String cate = tblProd.getValueAt(row,1).toString();
			String pName = tblProd.getValueAt(row,2).toString();
			int price = Integer.parseInt(tblProd.getValueAt(row,3).toString());
			int quantity = Integer.parseInt(spQt.getValue().toString());
			int subtotal = quantity * price;
			String subTotal = String.valueOf(subtotal);
			modelBuylist.addRow(new Object[] {pId,cate,pName,price,quantity,subTotal});
			lblTotal.setText(String.valueOf(Integer.parseInt(lblTotal.getText())+ Integer.parseInt(subTotal)));
			spQt.setValue(1);
			
		}
		else if(e.getSource() == btnProdSelDel) {
			DefaultTableModel mdBuylist = (DefaultTableModel)tblBuylist.getModel();
			
			int row = tblBuylist.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(btnProdSelDel, "제품목록에서 제품을 먼저 선택하세요");
				return ;
			}
			int cha = Integer.parseInt(tblBuylist.getValueAt(row, 5).toString());
			mdBuylist.removeRow(row);
			lblTotal.setText(String.valueOf(Integer.parseInt(lblTotal.getText())-cha));
		}
		
		else if(e.getSource() == btnPay) {
			int row = modelBuylist.getRowCount();
			
			if (cbCust.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(btnPay, "고객명을 선택하세요");
				return ;
			}
			else if (row == 0){
				JOptionPane.showMessageDialog(btnPay, "물품을 선택하세요");
				return ;
			}
			
			int confirm = JOptionPane.showConfirmDialog(btnPay, "결제하시겠습니까?","결제 확인",JOptionPane.YES_NO_OPTION);
			System.out.println(confirm);
			
			if (confirm == 0) {
				boolean result = new DaoOrder().orderPay(new OrderVO
						(custId, Integer.parseInt(lblTotal.getText()), MyCalendar.getCal(dPick), MainController.getInstance().getSession().getUserId(), modelBuylist));
				
				
				JOptionPane.showMessageDialog(btnPay, (result)?"등록되었습니다":"실패했습니다");
			}
			lblTotal.setText("0");
			modelBuylist.setNumRows(0);
		}
		else if(e.getSource() == btnProdAllDel) {
			int confirm = JOptionPane.showConfirmDialog(btnPay, "YES를 누르면 목록에 있는 내용이 전부 사라집니다. 삭제하시겠습니까?","전체 삭제",JOptionPane.YES_NO_OPTION);
			if(confirm == 0) {
				lblTotal.setText("0");
				modelBuylist.setNumRows(0);
			}
		}
	}
}
