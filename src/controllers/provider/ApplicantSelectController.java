package controllers.provider;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import controllers.ProviderDashboardController;
import database.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Applied;
import model.JobSeeker;
import model.Mail;
import model.Resume;
import javafx.fxml.Initializable;

public class ApplicantSelectController extends viewAnalyticsController implements Initializable {

	@FXML
    private VBox vbox;
    
	private ArrayList<Applied> appliedlist;
	private int jobid;
	private JobSeeker seekerobj;
	private Resume resumeobj;
    
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
    private Label datelabel;
    
    @FXML
    private TextField statustxt;

    @FXML
    private Label namelabel;

    @FXML
    private Button updatestatusbtn;

    @FXML
    private Button viewprofilebtn;
    
    private static ObservableList<String> statuslist=FXCollections.observableArrayList("SHORTLISTED","REJECTED","ACCEPTED");

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			if (arg0==null && arg1==null) {
				vbox.getChildren().clear();
				appliedlist=new DBHandler().getAppliedJobs(ProviderDashboardController.id,'P',jobid);
				for (int i=0;i<appliedlist.size();i++) {
					FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/fxml/provider/applicantslist.fxml"));
					loader.setController(this);
					
					seekerobj=new DBHandler().getSeekerObject(appliedlist.get(i).getSeekerid());
					resumeobj=new DBHandler().getResumeObject(appliedlist.get(i).getSeekerid());
					
					try {
						vbox.getChildren().add(loader.load());
						
						namelabel.setText(seekerobj.getName());
						datelabel.setText(appliedlist.get(i).getDate().toString());
						viewprofilebtn.setOnAction(viewProfileAction(seekerobj,resumeobj));
						updatestatusbtn.setOnAction(updateStatusAction());
						statustxt.setOnKeyPressed(getStatusAction((appliedlist.get(i).getSeekerid())));
						TextFields.bindAutoCompletion(statustxt,ApplicantSelectController.statuslist);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
	}
	
	private EventHandler<ActionEvent> updateStatusAction(){
		return new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
			
			}
			
		};
	}
	
	private EventHandler<KeyEvent> getStatusAction(int sid){
		return new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent arg0) {
				System.out.println(sid+": "+arg0.getText());
				
			}	
		};
	}
	
	private EventHandler<ActionEvent> viewProfileAction(JobSeeker seeker,Resume resume){
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/fxml/provider/applicantprofile.fxml"));
					Stage stage=new Stage();
					stage.setTitle("Applicant profile");
					stage.setResizable(false);
					stage.setScene(new Scene(loader.load()));
					ApplicantProfileShowController obj=loader.getController();
					obj.setObjects(seeker, resume);
					stage.show();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
		};
	}
	
	public void ids(int jid) {
		this.jobid=jid;
		initialize(null, null);
	}

}
