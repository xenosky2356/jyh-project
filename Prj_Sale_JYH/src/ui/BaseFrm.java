package ui;

import javax.swing.JFrame;

public abstract class BaseFrm extends JFrame{
	public void inflate(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(getClass().getName().toString().split("\\.")[1]);
		setLocationRelativeTo(this);
		setVisible(true);
	}
}
