package controllers.provider;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import controllers.ProviderDashboardController;
import controllers.SeekerDashboardController;
import database.DBHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.JobProvider;
import model.Mail;
import model.Places;

public class ProfileController extends ProviderDashboardController implements Initializable {

    @FXML
    private TextField citytxt;

    @FXML
    private TextField emailtxt;

    @FXML
    private TextField nametxt;

    @FXML
    private AnchorPane profilePane;

    @FXML
    private TextField typetxt;

    @FXML
    private Button updatecity;

    @FXML
    private Button updateemail;

    @FXML
    private Button updatename;

    @FXML
    private Button updatetype;

    @FXML
    void update(ActionEvent event) {
    	if (((Button)event.getSource()).getId().equals("updatename")) {
    		new DBHandler().updateProfile("jobprovider", "name", nametxt.getText(), ProviderDashboardController.id);
			 initialize(null, null);
    	}
    	else if (((Button)event.getSource()).getId().equals("updateemail")) {
    		new DBHandler().updateProfile("jobprovider", "email", emailtxt.getText(), ProviderDashboardController.id);
			 initialize(null, null);
    	}
		else if (((Button)event.getSource()).getId().equals("updateype")) {
			new DBHandler().updateProfile("jobprovider", "type", typetxt.getText(), ProviderDashboardController.id);
			 initialize(null, null);	
    	}
		else if (((Button)event.getSource()).getId().equals("updatecity")) {
			new DBHandler().updateProfile("jobprovider", "city", citytxt.getText(), ProviderDashboardController.id);
			 initialize(null, null);
		}
   	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		JobProvider provider=null;
		provider=new DBHandler().getProviderObject(ProviderDashboardController.id);
		
		nametxt.setText(provider.getName());
		emailtxt.setText(provider.getEmail());
		citytxt.setText(provider.getCity());
		typetxt.setText(provider.getType());
		
		TextFields.bindAutoCompletion(citytxt, Places.placelist);
		TextFields.bindAutoCompletion(emailtxt, Mail.emaillist);
	}
    

}
