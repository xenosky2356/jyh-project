package ui;

import java.util.Date;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.MainController;
import util.MyCalendar;

public class MainFrm extends BaseFrm{
	private JPanel pnlNorth, pnlCenter;
	private CardLayout cLay;
	private JLabel[] lbArr = new JLabel[5];
	private String[] titleArr = {"판매", "조회", "고객관리", "상품관리", "사용자관리"};
	private JPanel[] pnlArr = {new PnlSale(),new PnlSearch(), new PnlCustMng(),
			new PnlProdMng(), new PnlUserMng()};
	private JButton btnLogout;
	
	public MainFrm() {
		setSize(950,850);
		init();
		arrange();
		inflate();
		setTitle(MainController.getInstance().getSession().getUserName()+
				"님 환영합니다.");
	}
	private void init() {
		cLay = new CardLayout(10, 10);
		pnlCenter = new JPanel(cLay);

		pnlNorth = new JPanel();
		pnlNorth.setBackground(Color.white);
		pnlNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
				
		for(int i=0;i<lbArr.length;i++) {
			pnlCenter.add(titleArr[i], pnlArr[i]);
			lbArr[i] = new JLabel(titleArr[i]);
			pnlNorth.add(lbArr[i]);
			lbArr[i].setPreferredSize(new Dimension(100, 50));
			lbArr[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
			lbArr[i].setOpaque(true);
			lbArr[i].setBackground(new Color(50, 168, 141));
			lbArr[i].setForeground(Color.white);
			lbArr[i].setHorizontalAlignment(JLabel.CENTER);
			final int TMP = i;
			lbArr[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					cLay.show(pnlCenter, titleArr[TMP]);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					lbArr[TMP].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
			});
		}
		pnlArr[0].setBackground(new Color(252, 227, 3));
		pnlArr[1].setBackground(new Color(165, 252, 3));
		pnlArr[2].setBackground(new Color(3, 252, 111));
		pnlArr[3].setBackground(new Color(3, 227, 252));
		pnlArr[4].setBackground(new Color(78, 3, 252));
		
		btnLogout = new JButton("로그아웃");
		btnLogout.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnLogout) {
					dispose();
					new LoginFrm();
				}
			}
		});
	}

	private void arrange() {
		pnlNorth.add(new Lbl());
		pnlNorth.add(btnLogout);
		add(pnlNorth,"North");add(pnlCenter,"Center");
	}
	
}

class Lbl extends JLabel implements Runnable{
	Thread timeThread = null;
	
	public Lbl() {
		setText("현재 시간: " + MyCalendar.Today());
		setFont(new Font("맑은 고딕",Font.BOLD,16));
		setHorizontalAlignment(JLabel.LEFT);
		timeThread = new Thread(Lbl.this);
		timeThread.start();
		
	}
	
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {e.printStackTrace();}
			setText("현재 시간: " + MyCalendar.Today());
		}
	}
	
}