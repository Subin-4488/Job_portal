package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import animation.TranslateAnimation;
import database.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.JobProvider;
import model.JobSeeker;
import model.Places;
import others.AlertClass;
import others.Load;
import model.Mail;

public class SignupProviderController implements Initializable {

    @FXML
    private ComboBox<String> city;

    @FXML
    private TextField emailtxt;

    @FXML
    private TextField nametxt;

    @FXML
    private Button signupbtn;

    @FXML
    private ComboBox<String> type;
    
    @FXML
    private ImageView backbtn;
    
    @FXML
    private PasswordField passtxt;
    
    String nameString;
    String emailString;
    String organizationString;
    String cityString;
    String passString;

    @FXML
    void signupAction(ActionEvent event) throws SQLException {
    	
    	if (validate()) {
    		nameString=nametxt.getText();
        	emailString=emailtxt.getText();
        	organizationString=type.getValue();
        	cityString=city.getValue();
        	passString=passtxt.getText();
        	
        	new DBHandler().registerJobprovider(new JobProvider(nameString, emailString, cityString, organizationString, passString));
    	}
    	
    	//database
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		city.getItems().addAll(Places.placelist);
		
		ObservableList<String>types=FXCollections.observableArrayList();
		types.addAll("Government","Private");
		type.getItems().addAll(types);
		TextFields.bindAutoCompletion(emailtxt,Mail.emaillist);
	}
	
	boolean validate() {
		if (nametxt.getText().length()>0 
				&& city.getValue()!=null
				&& type.getValue()!=null
				&& passtxt.getText().length()>=8) return true;
		
		else {
			
			if (nametxt.getText().length()==0)
				new TranslateAnimation(nametxt);
			if (emailtxt.getText().length()==0) 
				new TranslateAnimation(emailtxt);
			}
			if (city.getValue()==null) 
				new TranslateAnimation(city);
			if (type.getValue()==null) 
				new TranslateAnimation(type);
			if (passtxt.getText().length()<8)
				new AlertClass("Password must have atleast 8 characters", 'E');
			
			return false;
	}
	
	@FXML
    void backButton(MouseEvent event) {
		backbtn.getScene().getWindow().hide();
		try {
			new Load("login.fxml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
