package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;

import dao.DaoCust;
import dao.DaoUser;
import util.MyCalendar;
import vo.CustVO;
import vo.UserVO;

public class PnlUserMngModify extends JFrame implements ActionListener{
	private JButton btnConfirm;
	private JTextField tfuName, tfId, tfPass, tfQuota, tfAdmin;
	
	public PnlUserMngModify(String uname, String id, String pass, int quota, String admin) {
		getContentPane().setLayout(null);
		setTitle(getClass().getName().toString().split("\\.")[1]);
		setLocationRelativeTo(this);
		setVisible(true);
		setSize(434,430);
		
		JLabel lblNewLabel = new JLabel("이름");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel.setBounds(25, 30, 57, 15);
		getContentPane().add(lblNewLabel);
		
		tfuName = new JTextField();
		tfuName.setEditable(false);
		tfuName.setBounds(107, 28, 285, 21);
		tfuName.setText(uname);
		tfuName.setColumns(10);
		getContentPane().add(tfuName);
		
		JLabel lblNewLabel_1 = new JLabel("아이디");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(25, 74, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		tfId = new JTextField();
		tfId.setBounds(107, 72, 285, 21);
		tfId.setText(id);
		tfId.setColumns(10);
		getContentPane().add(tfId);
		
		JLabel lblNewLabel_2 = new JLabel("비밀번호");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(25, 121, 70, 15);
		getContentPane().add(lblNewLabel_2);
		
		tfPass = new JTextField();
		tfPass.setColumns(10);
		tfPass.setBounds(107, 121, 285, 21);
		tfPass.setText(pass);
		getContentPane().add(tfPass);
				
		JLabel lblNewLabel_5 = new JLabel("할당량");
		lblNewLabel_5.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(25, 231, 57, 15);
		getContentPane().add(lblNewLabel_5);
		
		tfQuota = new JTextField();
		tfQuota.setColumns(10);
		tfQuota.setBounds(107, 231, 285, 21);
		tfQuota.setText(Integer.toString(quota));
		getContentPane().add(tfQuota);
		
		JLabel lblNewLabel_6 = new JLabel("관리자여부(Y/N)");
		lblNewLabel_6.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(25, 287, 125, 23);
		getContentPane().add(lblNewLabel_6);
		
		tfAdmin = new JTextField();
		tfAdmin.setColumns(10);
		tfAdmin.setBounds(161, 291, 231, 21);
		tfAdmin.setText(admin);
		getContentPane().add(tfAdmin);
		
		
		btnConfirm = new JButton("확인");
		btnConfirm.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnConfirm.setBounds(161, 344, 97, 23);
		getContentPane().add(btnConfirm);
		
		btnConfirm.addActionListener(this);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnConfirm) {
			String name = tfuName.getText();
			String id = tfId.getText();
			String pass = tfPass.getText();
			int quota = Integer.parseInt(tfQuota.getText());
			String admin = tfAdmin.getText();
						
			boolean insert =  new DaoUser().UserModify(new UserVO(name,id,pass,quota,admin));
			
			if(insert) {
				JOptionPane.showMessageDialog(btnConfirm, "성공하였습니다. 조회를 눌러 다시 한번 확인하시기 바랍니다");
				dispose();
			}
			else JOptionPane.showMessageDialog(btnConfirm, "실패하였습니다");
		}
	}	
}
