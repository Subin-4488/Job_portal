package controllers.seeker;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controllers.SeekerDashboardController;
import database.DBHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import model.Applied;
import model.Job;
import model.JobProvider;

public class TrackController extends SeekerDashboardController implements Initializable {

	 @FXML
	    private Label companylabel;

	    @FXML
	    private TextArea descriptiontextarea;

	    @FXML
	    private Label idlabel;

	    @FXML
	    private Label locationlabel;
	    
	    @FXML
	    private Label datelabel;
	    
	    @FXML
	    private Label message;

	    @FXML
	    private Label salarylabel;

	    @FXML
	    private Label statuslabel;

	    @FXML
	    private Label titlelabel;
	
	    @FXML
	    private VBox pane;

    private boolean flag=false;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (!flag) {
			flag=true;
			ArrayList<Applied> list=new DBHandler().getAppliedJobs(SeekerDashboardController.id,'S',-1);
			JobProvider provider;
			Job job;
			
			for (int i=0;i<list.size();i++) {
				
				try { 
					//1st setController then load...
					FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/fxml/seeker/trackjobs.fxml"));
					
					provider=new DBHandler().getProviderObject(list.get(i).getProviderid());
					job=new DBHandler().getPostJobs("id", Integer.toString(list.get(i).getJobid())).get(0);
					loader.setController(this);
					pane.getChildren().add(loader.load());
					
					titlelabel.setText(job.getTitle());
					idlabel.setText(Integer.toString(job.getId()));
					descriptiontextarea.setText(job.getDescription());
					salarylabel.setText(Float.toString(job.getSalary()));
					locationlabel.setText(job.getLocation());
					companylabel.setText(provider.getName());
					statuslabel.setText(list.get(i).getStatus());
					datelabel.setText(list.get(i).getDate().toString());
					
					if (statuslabel.getText().equals("ACCEPTED")||statuslabel.getText().equals("SHORTLISTED"))
						message.setVisible(true);
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
    
}
