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
import vo.CustVO;

public class PnlCustMngModify extends JFrame implements ActionListener{
	private JButton btnConfirm;
	private JTextField tfFname, tfLname, tfAdd, tfCity, tfCode, tfPhone;
	
	public PnlCustMngModify(String fname, String lname, String add1, String city, String code, String phone) {
		getContentPane().setLayout(null);
		setTitle(getClass().getName().toString().split("\\.")[1]);
		setLocationRelativeTo(this);
		setVisible(true);
		setSize(434,430);
		
		JLabel lblNewLabel = new JLabel("성");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel.setBounds(25, 30, 57, 15);
		getContentPane().add(lblNewLabel);
		
		tfFname = new JTextField();
		tfFname.setEditable(false);
		tfFname.setBounds(107, 28, 285, 21);
		tfFname.setText(fname);
		tfFname.setColumns(10);
		getContentPane().add(tfFname);
		
		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(25, 74, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		
		tfLname = new JTextField();
		tfLname.setEditable(false);
		tfLname.setBounds(107, 72, 285, 21);
		tfLname.setText(lname);
		tfLname.setColumns(10);
		getContentPane().add(tfLname);
		
		JLabel lblNewLabel_2 = new JLabel("주소");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(25, 121, 57, 15);
		getContentPane().add(lblNewLabel_2);
		
		tfAdd = new JTextField();
		tfAdd.setColumns(10);
		tfAdd.setBounds(107, 121, 285, 77);
		tfAdd.setText(add1);
		getContentPane().add(tfAdd);
		
		
		JLabel lblNewLabel_4 = new JLabel("도시");
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(25, 210, 57, 15);
		getContentPane().add(lblNewLabel_4);
		
		tfCity = new JTextField();
		tfCity.setColumns(10);
		tfCity.setBounds(107, 208, 285, 21);
		tfCity.setText(city);
		getContentPane().add(tfCity);
		
		JLabel lblNewLabel_5 = new JLabel("우편번호");
		lblNewLabel_5.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(25, 255, 57, 15);
		getContentPane().add(lblNewLabel_5);
		
		tfCode = new JTextField();
		tfCode.setColumns(10);
		tfCode.setBounds(107, 253, 285, 21);
		tfCode.setText(code);
		getContentPane().add(tfCode);
		
		JLabel lblNewLabel_6 = new JLabel("전화번호");
		lblNewLabel_6.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(25, 294, 57, 15);
		getContentPane().add(lblNewLabel_6);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(107, 292, 285, 21);
		tfPhone.setText(phone);
		getContentPane().add(tfPhone);
		
		
		btnConfirm = new JButton("확인");
		btnConfirm.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnConfirm.setBounds(161, 344, 97, 23);
		getContentPane().add(btnConfirm);
		
		btnConfirm.addActionListener(this);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnConfirm) {
			String fname = tfFname.getText();
			String lname = tfLname.getText();
			String add = tfAdd.getText();
			String city = tfCity.getText();
			String code = tfCode.getText();
			String phone = tfPhone.getText();
			
			boolean insert =  new DaoCust().CustModify(new CustVO(fname,lname,add,city,code,phone));
			
			if(insert) {
				JOptionPane.showMessageDialog(btnConfirm, "성공하였습니다. 조회를 눌러 다시 한번 확인하시기 바랍니다");
				dispose();
			}
			else JOptionPane.showMessageDialog(btnConfirm, "실패하였습니다");
		}
	}
}
