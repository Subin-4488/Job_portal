package controllers.provider;

import java.io.IOException;
import java.util.ArrayList;

import animation.FadeAnimation;
import controllers.ProviderDashboardController;
import controllers.SeekerDashboardController;
import database.DBHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Applied;
import model.Job;
import model.JobProvider;
import model.JobSeeker;

public class viewAnalyticsController extends ProviderDashboardController {

	@FXML
    private Button jobspostedbtn;

    @FXML
    private AnchorPane pane;

    @FXML
    private VBox vbox;

    @FXML
    private Button viewapplicatinsbtn;
    
    @FXML
    private Button deletebtn;

    @FXML
    private TextArea descriptiontextarea;

    @FXML
    private Label idlabel;

    @FXML
    private Label locationlabel;

    @FXML
    private Label titlelabel;

    @FXML
    private VBox vboxinner;
    
    @FXML
    private Label salarylabel;
    
    @FXML
    private Button viewapplicantsbtn;
    
    private Stage ministage;
    private Scene miniscene;


    //job list
    @FXML
    void listJobsPosted(ActionEvent event) throws IOException {
    	vbox.getChildren().clear();
    	ArrayList<Job> list=new DBHandler().getPostJobs("cid",Integer.toString(ProviderDashboardController.id));
    	for (int i=0;i<list.size();i++) {
    		FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/fxml/provider/listpostedjobs.fxml"));
        	loader.setController(this);
    		vbox.getChildren().add(loader.load());
    		titlelabel.setText(list.get(i).getTitle());
    		descriptiontextarea.setText(list.get(i).getDescription());
    		locationlabel.setText(list.get(i).getLocation());
    		salarylabel.setText(Float.toString(list.get(i).getSalary()));
    		idlabel.setText(Integer.toString(list.get(i).getId()));
    		deletebtn.setOnAction(deleteJobAction(event,idlabel.getText()));
    	}
    	new FadeAnimation().FadeIn(vbox);
    }

    EventHandler<ActionEvent> deleteJobAction(ActionEvent event,String id) {
		
    	return new EventHandler<ActionEvent>() {
    		
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new DBHandler().deleteJob(Integer.parseInt(id));
				try {
					listJobsPosted(null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};	
    }
    
    //applicant list
    @FXML
    void viewApplicantsAction(ActionEvent event) {
    	vbox.getChildren().clear();
    	ArrayList<Job> joblist=new DBHandler().getPostJobs("cid", Integer.toString(ProviderDashboardController.id));
		
		for (int i=0;i<joblist.size();i++) {
			
			try { 
				//1st setController then load...
				FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/fxml/provider/applicants.fxml"));
				loader.setController(this);
				vbox.getChildren().add(loader.load());
				
				titlelabel.setText(joblist.get(i).getTitle());
				idlabel.setText(Integer.toString(joblist.get(i).getId()));
				descriptiontextarea.setText(joblist.get(i).getDescription());
				salarylabel.setText(Float.toString(joblist.get(i).getSalary()));
				locationlabel.setText(joblist.get(i).getLocation());
				
				int count=0;
				count=new DBHandler().applicantCount(joblist.get(i).getId(), ProviderDashboardController.id);
				viewapplicantsbtn.setText("View Applicants: "+count);
				viewapplicantsbtn.setOnAction(viewApplicants(count,joblist.get(i).getCid(),joblist.get(i).getId()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		new FadeAnimation().FadeIn(vbox);
		ministage =new Stage();
    }
    
    private EventHandler<ActionEvent> viewApplicants(int count,int pid,int jid){
		return new EventHandler<ActionEvent>() {
			Parent node;
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
					try {
						FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/fxml/provider/applicantselect.fxml"));
						node=loader.load();
						miniscene=new Scene(node);
						ApplicantSelectController obj=loader.getController();
						
						obj.ids(jid);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ministage.setScene(miniscene);
					ministage.setTitle("Applicants");
					if (!ministage.isShowing())
					ministage.show();
					new FadeAnimation().FadeIn(node);
			}
		};
    	
    }
}
