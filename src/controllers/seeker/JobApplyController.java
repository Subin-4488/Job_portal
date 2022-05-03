package controllers.seeker;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import animation.FadeAnimation;
import database.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import model.Job;
import model.Places;

public class JobApplyController implements Initializable {

    @FXML
    private ComboBox<String> filterbycombo;

    @FXML
    private ComboBox<String> filtercombo;

    @FXML
    private VBox vbox;
    
    @FXML
    private Button applybtn;

    @FXML
    private TextArea descriptiontextarea;

    @FXML
    private Label idlabel;

    @FXML
    private Label locationlabel;

    @FXML
    private Label salarylabel;

    @FXML
    private Label titlelabel;

    @FXML
    private VBox vboxinner;
    
    @FXML
    private Label companylabel;
    
    private ObservableList<String> filterbylist=FXCollections.observableArrayList(
    		"title",
    		"location",
    		"Salary(High to low)"    		
    		);
    
    private ObservableList<String> locationfilterlist=Places.placelist;
    private ObservableList<String> titlefilterlist=Job.jobs;
    
    private boolean flag=false;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		filterbycombo.setItems(filterbylist);
		if (!flag) {
		try {
			flag=true;
			listFilteredJobs(null, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
    @FXML
    void setFilter(ActionEvent event) throws IOException {
    	
    	
    	
    	String filtertype=filterbycombo.getValue();
    	
    	if (filtertype.equals("title")) {
    		filtercombo.setVisible(true);
    		filtercombo.setValue("Choose title");
			filtercombo.setItems(titlefilterlist);
    	}
    	else if(filtertype.equals("location")) {
    		filtercombo.setVisible(true);
			filtercombo.setValue("Choose location");
			filtercombo.setItems(locationfilterlist);
    	}
    	else if(filtertype.equals("Salary(High to low)")) {
    		filtercombo.setVisible(false);
    		listFilteredJobs("salary",null);
    	}
    }
    
    @FXML
    void getFilterData(ActionEvent  event) throws IOException {
    	String filtertype=filterbycombo.getValue();
    	String filter=filtercombo.getValue();
    	
    	listFilteredJobs(filtertype, filter);
    }
    
    private void listFilteredJobs(String a,String filter) throws IOException {
    	vbox.getChildren().clear();
    	ArrayList<Job> list=new DBHandler().getPostJobs(a, filter);
    	
    	for (int i=0;i<list.size();i++) {
    		FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/fxml/seeker/filteredjobs.fxml"));
        	loader.setController(this);
    		
        	vbox.getChildren().add(loader.load());
    		
        	titlelabel.setText(list.get(i).getTitle());
    		descriptiontextarea.setText(list.get(i).getDescription());
    		locationlabel.setText(list.get(i).getLocation());
    		salarylabel.setText(Float.toString(list.get(i).getSalary()));
    		idlabel.setText(Integer.toString(list.get(i).getCid()));
    		companylabel.setText(list.get(i).getCompany());
    		applybtn.setOnAction(applyButtonAction(new ActionEvent(), Integer.parseInt(idlabel.getText())));
    	}
    	new FadeAnimation().FadeIn(vbox);
    }
    
    EventHandler<ActionEvent> applyButtonAction(ActionEvent event,int id){
		return new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		};
    	
    }
}