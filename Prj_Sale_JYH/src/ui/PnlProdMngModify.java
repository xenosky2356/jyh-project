package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DaoCust;
import dao.DaoProd;
import vo.CustVO;
import vo.ProdVO;

public class PnlProdMngModify extends JFrame implements ActionListener{
	private JButton btnConfirm;
	private JTextField tfPname, tfDesc, tfCate, tfPrice;
	
	public PnlProdMngModify(String prodName, String desc, String cate, int price) {
		getContentPane().setLayout(null);
		setTitle(getClass().getName().toString().split("\\.")[1]);

		setVisible(true);
		setSize(460,430);
		
		JLabel lblNewLabel = new JLabel("제품이름");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel.setBounds(25, 30, 70, 15);
		getContentPane().add(lblNewLabel);
		
		tfPname = new JTextField();
		tfPname.setEditable(false);
		tfPname.setBounds(107, 28, 285, 21);
		tfPname.setText(prodName);
		tfPname.setColumns(10);
		getContentPane().add(tfPname);
		
		JLabel lblNewLabel_1 = new JLabel("제품설명");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(25, 74, 70, 15);
		getContentPane().add(lblNewLabel_1);
				
		tfDesc = new JTextField();
		tfDesc.setBounds(107, 72, 285, 21);
		tfDesc.setText(desc);
		tfDesc.setColumns(10);
		getContentPane().add(tfDesc);
		
		JLabel lblNewLabel_2 = new JLabel("카테고리");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(25, 121, 70, 15);
		getContentPane().add(lblNewLabel_2);
		
		tfCate = new JTextField();
		tfCate.setColumns(10);
		tfCate.setBounds(107, 121, 285, 77);
		tfCate.setText(cate);
		getContentPane().add(tfCate);
		
		
		JLabel lblNewLabel_4 = new JLabel("가격");
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(25, 210, 57, 15);
		getContentPane().add(lblNewLabel_4);
		
		tfPrice = new JTextField();
		tfPrice.setColumns(10);
		tfPrice.setBounds(107, 208, 285, 21);
		tfPrice.setText(Integer.toString(price));
		getContentPane().add(tfPrice);		
		
		btnConfirm = new JButton("확인");
		btnConfirm.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnConfirm.setBounds(161, 344, 97, 23);
		getContentPane().add(btnConfirm);
		
		btnConfirm.addActionListener(this);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnConfirm) {
			String pName = tfPname.getText();
			String desc = tfDesc.getText();
			String cate = tfCate.getText();
			int price = Integer.parseInt(tfPrice.getText());
			
			boolean insert =  new DaoProd().ProdModify(new ProdVO(pName,desc,cate,price));
			
			if(insert) {
				JOptionPane.showMessageDialog(btnConfirm, "성공하였습니다. 조회를 눌러 다시 한번 확인하시기 바랍니다");
				dispose();
			}
			else JOptionPane.showMessageDialog(btnConfirm, "실패하였습니다");
		}
	}
}
