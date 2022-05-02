package controllers.provider;

import java.io.IOException;
import java.util.ArrayList;

import animation.FadeAnimation;
import controllers.ProviderDashboardController;
import database.DBHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Job;

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


    //job list
    @FXML
    void listJobsPosted(ActionEvent event) throws IOException {
    	vbox.getChildren().clear();
    	ArrayList<Job> list=new DBHandler().getPostJobs(ProviderDashboardController.id);
    	for (int i=0;i<list.size();i++) {
    		FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/fxml/provider/listpostedjobs.fxml"));
        	loader.setController(this);
    		vbox.getChildren().add(loader.load());
    		titlelabel.setText(list.get(i).getTitle());
    		descriptiontextarea.setText(list.get(i).getDescription());
    		locationlabel.setText(list.get(i).getLocation());
    		salarylabel.setText(Float.toString(list.get(i).getSalary()));
    		idlabel.setText(Integer.toString(list.get(i).getCid()));
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
    	
    }
}
