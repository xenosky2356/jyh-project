package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DaoCust;
import vo.CustVO;

class PnlCustMngAdd extends JFrame implements ActionListener{
	private JButton btnConfirm;
	private JTextField tfFname;
	private JTextField tfLname;
	private JTextField tfAdd;
	private JTextField tfCity;
	private JTextField tfCode;
	private JTextField tfPhone;
	
	public PnlCustMngAdd() {
		getContentPane().setLayout(null);
		setTitle(getClass().getName().toString().split("\\.")[1]);
		setLocationRelativeTo(this);
		setVisible(true);
		
		setSize(436,430);
		
		JLabel lblNewLabel = new JLabel("성");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel.setBounds(25, 30, 57, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(25, 74, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("주소");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(25, 121, 57, 15);
		getContentPane().add(lblNewLabel_2);
			
		JLabel lblNewLabel_4 = new JLabel("도시");
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(25, 210, 57, 15);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("우편번호");
		lblNewLabel_5.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(25, 255, 57, 15);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("전화번호");
		lblNewLabel_6.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(25, 294, 57, 15);
		getContentPane().add(lblNewLabel_6);
		
		btnConfirm = new JButton("확인");
		btnConfirm.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnConfirm.setBounds(147, 350, 97, 23);
		getContentPane().add(btnConfirm);
		
		tfFname = new JTextField();
		tfFname.setBounds(107, 28, 285, 21);
		getContentPane().add(tfFname);
		tfFname.setColumns(10);
		
		tfLname = new JTextField();
		tfLname.setColumns(10);
		tfLname.setBounds(107, 72, 285, 21);
		getContentPane().add(tfLname);
		
		tfAdd = new JTextField();
		tfAdd.setColumns(10);
		tfAdd.setBounds(107, 119, 285, 61);
		getContentPane().add(tfAdd);
			
		tfCity = new JTextField();
		tfCity.setColumns(10);
		tfCity.setBounds(107, 208, 285, 21);
		getContentPane().add(tfCity);
		
		tfCode = new JTextField();
		tfCode.setColumns(10);
		tfCode.setBounds(107, 253, 285, 21);
		getContentPane().add(tfCode);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(107, 292, 285, 21);
		getContentPane().add(tfPhone);
		
		btnConfirm.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String fname = tfFname.getText();
		String lname = tfLname.getText();
		String add = tfAdd.getText();
		String city = tfCity.getText();
		String code = tfCode.getText();
		String phone = tfPhone.getText();
		
		if(e.getSource() == btnConfirm) {
			if(fname.equals("")) {
				JOptionPane.showMessageDialog(btnConfirm, "성을 입력하세요");
				return ;
			}
			if(lname.equals("")) {
				JOptionPane.showMessageDialog(btnConfirm, "이름을 입력하세요");
				return ;
			}
			if(add.equals("")) {
				JOptionPane.showMessageDialog(btnConfirm, "주소를 입력하세요");
				return ;
			}
			if(city.equals("")) {
				JOptionPane.showMessageDialog(btnConfirm, "도시를 입력하세요");
				return ;
			}
			if(code.equals("")) {
				JOptionPane.showMessageDialog(btnConfirm, "우편번호을 입력하세요");
				return ;
			}
			if(phone.equals("")) {
				JOptionPane.showMessageDialog(btnConfirm, "전화번호을 입력하세요");
				return ;
			}
			
			boolean insert = new DaoCust().CustAdd(new CustVO(fname,lname,add,city,code,phone));
			
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