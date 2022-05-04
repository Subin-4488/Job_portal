package controllers.provider;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.JobSeeker;
import model.Resume;

public class ApplicantProfileShowController implements Initializable {

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
    private Button viewresumebtn;
    
    @FXML
    private TextArea resumetxtarea;

    
    private JobSeeker seekerobj;
    private Resume resumeobj;

    @FXML
    void viewResumeAction(ActionEvent event) {
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if (arg0==null && arg1==null) {
		avatar.setImage(new Image(seekerobj.getAvatar()));
		nametxt.setText(seekerobj.getName());
		emailtxt.setText(seekerobj.getEmail());
		mobiletxt.setText(seekerobj.getMobile());
		citytxt.setText(seekerobj.getCity());
		dobtxt.setText(seekerobj.getDob());
		gendertxt.setText(seekerobj.getGender());
		
		
		schooltxt.setText(resumeobj.getSchool());
		streamtxt.setText(resumeobj.getStream());
		cgpatxt.setText(resumeobj.getCgpa());
		
		int ch;
		String resume="";
		try {
			while ((ch=resumeobj.getFs().read())!=-1) {
				resume+=(char)ch;
			}
			resumetxtarea.setText(resume);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	public void setObjects(JobSeeker seeker,Resume resume) {
		this.seekerobj=seeker;
		this.resumeobj=resume;
		initialize(null, null);
	}
}
