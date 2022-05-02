package controllers;

import java.io.IOException;
import java.sql.SQLException;

import animation.*;
import database.DBHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import model.JobSeeker;
import others.AlertClass;
import others.Load;

public class LoginController {
	
	@FXML
    private Button loginbtn;

    @FXML
    private PasswordField passtxt;

    @FXML
    private Button providerbtn;

    @FXML
    private RadioButton providertoggle;

    @FXML
    private ToggleGroup role;

    @FXML
    private Button seekerbtn;

    @FXML
    private RadioButton seekertoggle;

    @FXML
    private TextField usrtxt;
    
    @FXML
    private AnchorPane pane;

    
    String email;
    String pass;
    
    
    @FXML
    public void loginAction(ActionEvent e) throws SQLException, IOException {
    	if (usrtxt.getText().length()>0 && passtxt.getText().length()>0) {
    			if (passtxt.getText().length()>=8) {
    				email=usrtxt.getText();
    				pass=passtxt.getText();
    				if (seekertoggle.isSelected()) {
    					if (new DBHandler().validateSeeker(email, pass)) {
    						loginbtn.getScene().getWindow().hide();
    						
    						SeekerDashboardController.id=new DBHandler().getId("jobseeker", email);
    						new Load("seekerdashboard.fxml");
    					}
    					else {
    						new AlertClass("Invalid credentials for Jobseeker", 'E');
    					}
    				}
    				else {
    					if (new DBHandler().validateProvider(email, pass)) {
    						loginbtn.getScene().getWindow().hide();
    						
    						ProviderDashboardController.id=new DBHandler().getId("jobprovider", email);
    						new Load("providerdashboard.fxml");
    					}
    					else {
    						new AlertClass("Invalid credentials for Jobprovider", 'E');
    					}
    				}
    			}
    			else {
    				new AlertClass("Password must have atleast 8 characters", 'E');
    			}
    	}
    	else {
    		if (usrtxt.getText().length()==0) {
    			new TranslateAnimation(usrtxt);
    		}
    		if (passtxt.getText().length()==0) {
    			new TranslateAnimation(passtxt);
    		}
    	}
    }
    
    @FXML
    public void signupSeekerAction(ActionEvent e) {
    	seekerbtn.getScene().getWindow().hide();
    	try {	
			new Load("signupseeker.fxml");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    @FXML
    public void signupProviderAction(ActionEvent e) {
    	providerbtn.getScene().getWindow().hide();
    	try {
			new Load("signupprovider.fxml");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
}
