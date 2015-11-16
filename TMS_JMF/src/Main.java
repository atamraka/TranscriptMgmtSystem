import main.uno.edu.controller.MainApplicationController;
import main.uno.edu.view.MainApplicationWindow;


public class Main {

	
	public static void main(String[] args) {
		 MainApplicationController mCtrl=new MainApplicationController();
		 MainApplicationWindow mView= new MainApplicationWindow(mCtrl);
		 mCtrl.setMainView(mView);
		 mView.setWindowTitle("Transcript Management System");
		 mView.setVisible(true);
	}
}
