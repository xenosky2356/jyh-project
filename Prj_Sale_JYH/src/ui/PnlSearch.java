package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PnlSearch extends JPanel {
	private JPanel pnlNorth, pnlCenter;
	private CardLayout cLay;
	private JLabel[] lbArr = new JLabel[3];
	private String[] titleArr = {"기간", "상품", "고객"};
	private JPanel[] pnlArr = { new PnlGroupDate(), new PnlGroupItem(), new PnlGroupCust()};
	
	public PnlSearch() {
		setSize(900, 700);
		
		cLay = new CardLayout(10, 10);
		pnlCenter = new JPanel(cLay);
		pnlCenter.setBounds(12, 90, 876, 600);

		pnlNorth = new JPanel();
		pnlNorth.setBounds(266, 10, 322, 70);
		pnlNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnlNorth.setBackground(new Color(165, 252, 3));
		
		
		for(int i=0;i<lbArr.length;i++) {
			pnlCenter.add(titleArr[i], pnlArr[i]);
			lbArr[i] = new JLabel(titleArr[i]);
			pnlNorth.add(lbArr[i]);
			lbArr[i].setPreferredSize(new Dimension(100, 50));
			lbArr[i].setFont(new Font("나눔고딕", Font.BOLD, 20));
			lbArr[i].setOpaque(true);
			lbArr[i].setBackground(new Color(3, 152, 252));
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
		setLayout(null);
		
		add(pnlNorth);add(pnlCenter);
		
	}

}
