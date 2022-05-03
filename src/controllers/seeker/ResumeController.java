package controllers.seeker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controllers.SeekerDashboardController;
import database.DBHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import model.Resume;

public class ResumeController extends ProfileController implements Initializable {

    @FXML
    private TextArea resumetxtflow;

    private Resume obj;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		resumetxtflow.clear();
		obj=new DBHandler().getResumeObject(SeekerDashboardController.id);
		int ch=0;
		try {
			while ((ch=obj.getFs().read())!=-1) {
				resumetxtflow.setText(resumetxtflow.getText()+(char)ch);
				System.out.print(ch);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
