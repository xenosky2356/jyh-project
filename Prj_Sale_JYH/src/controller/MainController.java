package controller;

import ui.JoinFrm;
import ui.LoginFrm;
import ui.MainFrm;
import vo.UserVO;

public class MainController {
	private static MainController controller;
	private UserVO session;
	private MainController() {}
	
	public static void getController(String cmd) {
		if(cmd.equals("Main")) {
			new MainFrm();
		}
		else if (cmd.equals("Join")) {
			new JoinFrm();
		}else if(cmd.equals("Login")) {
			new LoginFrm();
		}
	}
		
	public static MainController getInstance() {
		if(controller==null) controller = new MainController();
		return controller;
	}
		
	public UserVO getSession() {
		return session;
	}

	public void setSession(UserVO session) {
		this.session = session;
	}

	public static void main(String[] args) {
		new LoginFrm();
	}
}
