package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.DaoCust;
import dao.DaoProd;
import vo.CustVO;
import vo.ProdVO;

public class PnlProdMngAdd extends JFrame implements ActionListener {
	private JTextField tfProdname;
	private JTextField tfDesc;
	private JTextField tfCate;
	private JTextField tfPrice;
	private JButton btnConfirm;
	
	public PnlProdMngAdd() {
		getContentPane().setLayout(null);
		setTitle(getClass().getName().toString().split("\\.")[1]);
		setLocationRelativeTo(this);
		setVisible(true);
		
		setSize(436,430);
		
		JLabel lblNewLabel = new JLabel("제품이름");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel.setBounds(25, 30, 70, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("제품설명");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(25, 74, 70, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("카테고리");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(25, 165, 70, 15);
		getContentPane().add(lblNewLabel_2);
			
		JLabel lblNewLabel_4 = new JLabel("단가");
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(25, 210, 57, 15);
		getContentPane().add(lblNewLabel_4);

		
		tfProdname = new JTextField();
		tfProdname.setBounds(136, 28, 256, 21);
		getContentPane().add(tfProdname);
		tfProdname.setColumns(10);
		
		tfDesc = new JTextField();
		tfDesc.setColumns(10);
		tfDesc.setBounds(136, 72, 256, 70);
		getContentPane().add(tfDesc);
		
		tfCate = new JTextField();
		tfCate.setColumns(10);
		tfCate.setBounds(136, 163, 256, 26);
		getContentPane().add(tfCate);
			
		tfPrice = new JTextField();
		tfPrice.setColumns(10);
		tfPrice.setBounds(136, 208, 256, 21);
		getContentPane().add(tfPrice);
		
		btnConfirm = new JButton("확인");
		btnConfirm.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnConfirm.setBounds(152, 304, 97, 23);
		getContentPane().add(btnConfirm);
		
		btnConfirm.addActionListener(this);
			
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String prodname = tfProdname.getText();
		String desc = tfDesc.getText();
		String cate = tfCate.getText();
		int price = Integer.parseInt(tfPrice.getText());

		
		if(e.getSource() == btnConfirm) {
			if(prodname.equals("")) {
				JOptionPane.showMessageDialog(btnConfirm, "제품이름을 입력하세요");
				return ;
			}
			if(desc.equals("")) {
				JOptionPane.showMessageDialog(btnConfirm, "제품설명을 입력하세요");
				return ;
			}
			if(cate.equals("")) {
				JOptionPane.showMessageDialog(btnConfirm, "카테고리를 입력하세요");
				return ;
			}
			if(price < 0) {
				JOptionPane.showMessageDialog(btnConfirm, "단가를 입력하세요");
				return ;
			}
			
			boolean insert = new DaoProd().ProdAdd(new ProdVO(prodname,desc,cate,price));
			
			if(insert) {
				JOptionPane.showMessageDialog(btnConfirm, "성공하였습니다. 조회를 눌러 다시 한번 확인하시기 바랍니다");
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(btnConfirm, "실패하였습니다.");
			}	
		}
	}
}
