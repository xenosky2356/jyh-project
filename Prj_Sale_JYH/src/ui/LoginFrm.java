package ui;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.MainController;
import dao.DaoUser;
import vo.UserVO;

public class LoginFrm extends BaseFrm implements ActionListener{
	private JTextField tfId;
	private JPasswordField pfPw;
	private JButton btnJoin, btnLogin, btnClose;
	private JLabel lblID, lblPW;
	
	public LoginFrm() {
		setSize(412,262);
		getContentPane().setLayout(null);
		
		btnJoin = new JButton("가입");
		btnJoin.addActionListener(this);
		btnJoin.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnJoin.setBounds(29, 169, 87, 29);
		getContentPane().add(btnJoin);
		
		btnLogin = new JButton("로그인");
		btnLogin.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnLogin.addActionListener(this);
		btnLogin.setBounds(139, 169, 87, 29);
		getContentPane().add(btnLogin);
		
		btnClose = new JButton("취소");
		btnClose.addActionListener(this);
		btnClose.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnClose.setBounds(252, 169, 87, 29);
		getContentPane().add(btnClose);
		
		lblID = new JLabel("아이디");
		lblID.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblID.setBounds(58, 50, 55, 27);
		getContentPane().add(lblID);
		
		lblPW = new JLabel("비밀번호");
		lblPW.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblPW.setBounds(47, 109, 72, 27);
		getContentPane().add(lblPW);
		
		tfId = new JTextField();
		tfId.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		tfId.setBounds(139, 49, 200, 29);
		getContentPane().add(tfId);
		tfId.setColumns(10);
		
		pfPw = new JPasswordField();
		pfPw.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		pfPw.setBounds(139, 108, 200, 29);
		getContentPane().add(pfPw);
		inflate();
		
		pfPw.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {
					ProcessLogin();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogin) {
			ProcessLogin();
		}
		if(e.getSource() == btnJoin) {
			MainController.getController("Join");
			dispose();
		}	
		if(e.getSource() == btnClose) {
			System.exit(0);
		}
	}
	
	public void ProcessLogin() {
		UserVO vo = new DaoUser().loginCheck(tfId.getText(),String.valueOf(pfPw.getPassword()));
		if (vo == null) {			
			JOptionPane.showMessageDialog(btnLogin, "아이디와 비밀번호를 확인하시기 바립니다");
			tfId.setText("");pfPw.setText("");
			tfId.requestFocus();
			return ;
		}
		MainController.getInstance().setSession(vo);
		MainController.getController("Main");
		dispose();
	}
}
