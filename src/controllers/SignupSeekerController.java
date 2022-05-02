package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import animation.TranslateAnimation;
import database.DBHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import model.JobSeeker;
import model.Mail;
import model.Places;
import model.Resume;
import others.AlertClass;
import others.Load;

public class SignupSeekerController implements Initializable {

    @FXML
    private ComboBox<String> city;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField emailtxt;

    @FXML
    private Label filelabel;

    @FXML
    private TextField mobiletxt;

    @FXML
    private TextField nametxt;

    @FXML
    private Button resumebtn;
    
    @FXML
    private RadioButton femaleradio;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton maleradio;

    @FXML
    private Button signupbtn;
    
    @FXML
    private ImageView backbtn;
    
    @FXML
    private PasswordField passtxt;
    
    String nameString;
    String emailString;
    String mobileString;
    String cityString;
    String dobString;
    String passString;
    String genderString;
    
    
    File file=null;
    FileInputStream fis=null;

    @FXML
    void resumeSelectAction(ActionEvent event) {
    	FileChooser filechooser=new FileChooser();
    	file=filechooser.showOpenDialog(resumebtn.getScene().getWindow()); 
    	filelabel.setText(file.getName());
    	filelabel.setStyle("-fx-text-fill :red");
    }

    @FXML
    void signupAction(ActionEvent event) throws SQLException {
    	
    	if (validate()) {
    		nameString=nametxt.getText();
        	emailString=emailtxt.getText();
        	mobileString=mobiletxt.getText();
        	cityString=city.getValue();
        	dobString=dob.getValue().toString();
        	passString=passtxt.getText();
        	
        	if (maleradio.isSelected()) genderString="Male";
        	else	genderString="Female";
        	
        	new DBHandler().registerJobseeker(new JobSeeker(nameString, mobileString, cityString, emailString, dobString, passString,genderString,null));
        	
        	//resume
        	int id;
        	id=new DBHandler().getId("jobseeker", emailString);
        	try {
				fis=new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	new DBHandler().insertResume(new Resume(id,fis,null,null,null));

    	}
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		city.getItems().addAll(Places.placelist);
		TextFields.bindAutoCompletion(emailtxt, Mail.emaillist);
	}
	
	boolean validate() {
		if (nametxt.getText().length()>0 
				&& (emailtxt.getText().length()>0 && (emailtxt.getText().contains("@gmail.com")|| emailtxt.getText().contains("@yahoo.com")))
				&& mobiletxt.getText().length()>0
				&& city.getValue()!=null
				&& dob.getValue()!=null
				&& !filelabel.getText().equals("no file choosen...")
				&& passtxt.getText().length()>=8) return true;
		
		else {
			
			if (nametxt.getText().length()==0)
				new TranslateAnimation(nametxt);
			if (emailtxt.getText().length()==0) 
				new TranslateAnimation(emailtxt);
			if (!(emailtxt.getText().contains("@gmail.com")|| emailtxt.getText().contains("@yahoo.com"))) {
				new AlertClass("Invalid email", 'E');
			}
			if (mobiletxt.getText().length()==0) 
				new TranslateAnimation(mobiletxt);
			if (city.getValue()==null) 
				new TranslateAnimation(city);
			if (dob.getValue()==null) 
				new TranslateAnimation(dob);
			if (filelabel.getText().equals("no file choosen...")) 
				new TranslateAnimation(resumebtn);
			if (passtxt.getText().length()<8)
				new AlertClass("Password must have atleast 8 characters", 'E');
		
			return false;
		}
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
