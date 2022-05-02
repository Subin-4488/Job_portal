package others;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertClass {
	Alert alert;
	public AlertClass(String txt,char c) {
		// TODO Auto-generated constructor stub
		if (c=='W') {
			alert=new Alert(AlertType.WARNING);
			alert.setHeaderText(txt);
			alert.show();
		}
		else if(c=='E') {
			alert=new Alert(AlertType.ERROR);
			alert.setHeaderText(txt);
			alert.show();
		}
		else if(c=='C') {
			alert=new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText(txt);
			alert.show();
		}
	}
}
