package ui;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;

import controller.MainController;
import dao.DaoUser;
import util.MyCalendar;
import vo.UserVO;

public class JoinFrm extends BaseFrm implements ActionListener{
	private JTextField tfName;
	private JTextField tfId;
	private JPasswordField pfPw;
	private JPasswordField pfRepw;
	private JDatePicker dPick;
	private JButton btnJoin, btnCancel;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnJoin) {
			Available();
	
		}else if(e.getSource() == btnCancel) {
			dispose();
			MainController.getController("Login");
		}		
	}
	
	public JoinFrm() {
		getContentPane().setLayout(null);
		setSize(475,679);
		
		btnJoin = new JButton("가입");
		btnJoin.addActionListener(this);
		btnJoin.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		btnJoin.setBounds(74, 530, 97, 46);
		getContentPane().add(btnJoin);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		btnCancel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		btnCancel.setBounds(304, 530, 97, 46);
		getContentPane().add(btnCancel);
		
		JLabel lblName = new JLabel("이름");
		lblName.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblName.setBounds(74, 36, 48, 30);
		getContentPane().add(lblName);
		
		JLabel lbliD = new JLabel("아이디");
		lbliD.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lbliD.setBounds(74, 135, 66, 39);
		getContentPane().add(lbliD);
		
		JLabel lblPw = new JLabel("비밀번호");
		lblPw.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblPw.setBounds(74, 244, 88, 38);
		getContentPane().add(lblPw);
		
		JLabel lblRepw = new JLabel("비밀번호 재확인");
		lblRepw.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblRepw.setBounds(74, 353, 161, 30);
		getContentPane().add(lblRepw);
		
		tfName = new JTextField();
		tfName.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		tfName.setBounds(74, 76, 327, 46);
		getContentPane().add(tfName);
		tfName.setColumns(10);
		
		tfId = new JTextField();
		tfId.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		tfId.setColumns(10);
		tfId.setBounds(74, 185, 327, 46);
		getContentPane().add(tfId);
		
		pfPw = new JPasswordField();
		pfPw.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		pfPw.setBounds(74, 292, 327, 46);
		getContentPane().add(pfPw);
		
		pfRepw = new JPasswordField();
		pfRepw.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		pfRepw.setBounds(74, 393, 327, 46);
		getContentPane().add(pfRepw);
		
		pfRepw.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				Available();
			}
		});
		
		JLabel lblHiredate = new JLabel("입사일자");
		lblHiredate.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblHiredate.setBounds(74, 465, 80, 27);
		getContentPane().add(lblHiredate);
		
		dPick = new JDateComponentFactory().createJDatePicker();
		dPick.setTextEditable(false);
		dPick.setShowYearButtons(true);
		JPanel pnlDpic = new JPanel();
		pnlDpic.add((JComponent)dPick);
		pnlDpic.setBounds(166,465,235,39);
		getContentPane().add(pnlDpic);
		
		inflate();
	}
	
	public void Available() {
		String name = tfName.getText();
		String id = tfId.getText();
		String pw = String.valueOf(pfPw.getPassword());
		String repw = String.valueOf(pfRepw.getPassword());
		String date = MyCalendar.getCal(dPick);
		
		if(name.equals("")) {
			JOptionPane.showMessageDialog(btnJoin, "이름를 확인하세요");
			tfName.requestFocus();
			return ;
		}
		if(id.equals("")) {
			JOptionPane.showMessageDialog(btnJoin, "아이디를 확인하세요");
			tfId.requestFocus();
			return ;
		}
		if(pw.equals("")) {
			JOptionPane.showMessageDialog(btnJoin, "비밀번호를 입력하세요");
			pfPw.requestFocus();
			return ;
		}
		if(!(pw.equals(repw))) {
			JOptionPane.showMessageDialog(btnJoin, "비밀번호를 다시 확인하세요");
			pfRepw.requestFocus();
			return ;
		}
		if (new DaoUser().duplicateId(id)) {
			JOptionPane.showMessageDialog(null, "중복된 아이디가 있습니다.");
			tfId.requestFocus();
			return ;
		}
		
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		Date to = new Date();
		try {
			to = fm.parse(date);
		}catch(ParseException e1) { }
		
		boolean insert = new DaoUser().addUser(new UserVO(name, id, pw, to));
		
		if(insert) {
			JOptionPane.showMessageDialog(btnJoin, "회원가입이 성공되었습니다.");
			MainController.getController("Login");
			dispose();
		}
		else {
			JOptionPane.showMessageDialog(btnJoin, "회원가입이 실패하였습니다.");
		}
	}
}
