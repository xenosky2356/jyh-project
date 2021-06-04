package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;

import dao.DaoProd;
import dao.DaoSearch;
import util.MyCalendar;
import vo.SearchVO;

public class PnlGroupDate extends JPanel implements ActionListener{
	private JDatePicker dPickStart, dPickEnd;
	private DefaultTableModel mdResult;
	private JTable tblResult;
	private JScrollPane scpResult;
	private JButton btnSearch;
	private SearchVO searchVO;
	
	public PnlGroupDate() {
		setBackground(new Color(240, 248, 255));
		setSize(860,560);
		setLayout(null);
		
		JPanel pnlSearch = new JPanel();
		pnlSearch.setBackground(new Color(245, 255, 250));
		pnlSearch.setBounds(12, 10, 831, 116);
		pnlSearch.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("시작날짜");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel.setBounds(12, 58, 70, 29);
		pnlSearch.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("끝날자");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(345, 56, 48, 33);
		pnlSearch.add(lblNewLabel_1);
		
		dPickStart = new JDateComponentFactory().createJDatePicker();
		dPickEnd = new JDateComponentFactory().createJDatePicker();
		
		dPickStart.setTextEditable(true);
		dPickEnd.setTextEditable(true);
		
		dPickStart.setShowYearButtons(true);
		dPickEnd.setShowYearButtons(true);
				
		JPanel pnlDpic1 = new JPanel();
		JPanel pnlDpic2 = new JPanel();
		pnlDpic1.add((JComponent)dPickStart);
		pnlDpic2.add((JComponent)dPickEnd);
		pnlDpic1.setBounds(94,54,218,33);
		pnlDpic2.setBounds(405,54,218,33);
		pnlSearch.add(pnlDpic1);
		pnlSearch.add(pnlDpic2);
		
		btnSearch = new JButton("조회");
		btnSearch.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnSearch.setBounds(658, 55, 97, 34);
		pnlSearch.add(btnSearch);
		
		mdResult = new DefaultTableModel(new String[] {"기간","고객명", "총액"} , 0);
		tblResult = new JTable(mdResult);	
		scpResult = new JScrollPane(tblResult);
		scpResult.setBounds(0, 0, 831, 404);
		tblResult.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		
		add(pnlSearch);
		
		JLabel lblNewLabel_2 = new JLabel("조회하시려는 시작날짜와 끝날짜를 입력해주세요");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(12, 10, 358, 29);
		pnlSearch.add(lblNewLabel_2);
		
		JPanel pnlResultTbl = new JPanel();
		pnlResultTbl.setBounds(12, 146, 831, 404);
		add(pnlResultTbl);
		pnlResultTbl.add(scpResult);
		pnlResultTbl.setLayout(null);
		
		btnSearch.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSearch) {
			
			mdResult.setNumRows(0);
 			tblResult.setModel(new DaoSearch().getSearchDate(mdResult, searchVO, MyCalendar.getCal(dPickStart), MyCalendar.getCal(dPickEnd)));
		}
	}
}
