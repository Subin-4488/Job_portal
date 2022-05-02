package controllers.seeker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import controllers.SeekerDashboardController;
import database.DBHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import model.JobSeeker;
import model.Mail;
import model.Places;
import model.Resume;

public class ProfileController extends SeekerDashboardController implements Initializable {

	@FXML
    private ImageView avatar;
	
    @FXML
    private TextField cgpatxt;

    @FXML
    private TextField citytxt;

    @FXML
    private TextField dobtxt;

    @FXML
    private TextField emailtxt;

    @FXML
    private TextField gendertxt;

    @FXML
    private TextField mobiletxt;

    @FXML
    private TextField nametxt;

    @FXML
    private AnchorPane profilePane;

    @FXML
    private TextField schooltxt;

    @FXML
    private TextField streamtxt;

    @FXML
    private Button updatecgpa;

    @FXML
    private Button updatecity;

    @FXML
    private Button updatedob;

    @FXML
    private Button updateemail;

    @FXML
    private Button updategender;

    @FXML
    private Button updatemobile;

    @FXML
    private Button updatename;

    @FXML
    private Button updateschool;

    @FXML
    private Button updatestream;

    @FXML
    private Button viewresumebtn;
    
    String nameString;
    String emailString;
    String mobileString;
    String cityString;
    String dobString;
    String passString;
    String genderString;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		JobSeeker seeker=null;
		seeker=new DBHandler().getSeekerObject(SeekerDashboardController.id);
		
		nametxt.setText(seeker.getName());
		emailtxt.setText(seeker.getEmail());
		mobiletxt.setText(seeker.getMobile());
		citytxt.setText(seeker.getCity());
		dobtxt.setText(seeker.getDob());
		gendertxt.setText(seeker.getGender());
		
		TextFields.bindAutoCompletion(citytxt, Places.placelist);
		TextFields.bindAutoCompletion(emailtxt, Mail.emaillist);
		
		Resume resume=new DBHandler().getResumeObject(SeekerDashboardController.id);
		schooltxt.setText(resume.getSchool());
		streamtxt.setText(resume.getStream());
		cgpatxt.setText(resume.getCgpa());
		
		if (seeker.getAvatar()!=null) {
			avatar.setImage(new Image(seeker.getAvatar()));
			//super.initialize(null, null);
		}
	}
	
	 @FXML
	 void update(ActionEvent event) {
		 Button btn=(Button)event.getSource();
		 System.out.println(btn.getId());
		 
		 if (btn.getId().equals("updatename")) {
			 new DBHandler().updateProfile("jobseeker", "name", nametxt.getText(), SeekerDashboardController.id);
			 initialize(null, null);
		 }
		 else if(btn.getId().equals("updateemail")) {
			 new DBHandler().updateProfile("jobseeker", "email", emailtxt.getText(), SeekerDashboardController.id);
			 initialize(null, null);
		 }
		 else if(btn.getId().equals("updatemobile")) {
			 new DBHandler().updateProfile("jobseeker", "mobile", mobiletxt.getText(), SeekerDashboardController.id);
			 initialize(null, null);
		 }
		 else if(btn.getId().equals("updatecity")) {
			 new DBHandler().updateProfile("jobseeker", "city", citytxt.getText(), SeekerDashboardController.id);
			 initialize(null, null);
		 }
		 else if(btn.getId().equals("updatedob")) {
			 new DBHandler().updateProfile("jobseeker", "dob", dobtxt.getText(), SeekerDashboardController.id);
			 initialize(null, null);
		 }
		 else if(btn.getId().equals("updategender")) {
			 new DBHandler().updateProfile("jobseeker", "gender", gendertxt.getText(), SeekerDashboardController.id);
			 initialize(null, null);
		 }
		 else if(btn.getId().equals("updateschool")) {
			 new DBHandler().updateProfile("resume", "school", schooltxt.getText(), SeekerDashboardController.id);
			 initialize(null, null);
		 }
		 else if(btn.getId().equals("updatestream")) {
			 new DBHandler().updateProfile("resume", "stream", streamtxt.getText(), SeekerDashboardController.id);
			 initialize(null, null);
		 }
		 else if(btn.getId().equals("updatecgpa")) {
			 new DBHandler().updateProfile("resume", "cgpa", cgpatxt.getText(), SeekerDashboardController.id);
			 initialize(null, null);
		 }
	 }
	 
	 @FXML
	 void uploadPhoto(MouseEvent event) throws FileNotFoundException {
		 File file=new FileChooser().showOpenDialog(avatar.getScene().getWindow());
		 FileInputStream fis=new FileInputStream(file); 
		 new DBHandler().uploadImage(id, fis);
		 
		 initialize(null, null);
	 }
}
