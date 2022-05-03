package controllers.provider;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import animation.TranslateAnimation;
import controllers.ProviderDashboardController;
import database.DBHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Job;
import model.Places;
import others.AlertClass;

public class JobPostController extends ProviderDashboardController implements Initializable {

    @FXML
    private TextArea descriptiontxt;

    @FXML
    private ComboBox<String> experiencecombo;

    @FXML
    private Button jobpostbtn;

    @FXML
    private TextField locationtxt;

    @FXML
    private TextField salarytxt;

    @FXML
    private TextField titletxt;
    
    private String title;
    private String description;
    private String location;
    private String salary;

    @FXML
    void jobPostAction(ActionEvent event) {
    	if (validate()) {
    		new DBHandler().postJob(new Job(title, description, location, Float.parseFloat(salary), Integer.parseInt(experiencecombo.getValue()),ProviderDashboardController.id));
    	}
    }
    
    private boolean validate() {
    	title=titletxt.getText();
    	description=descriptiontxt.getText();
    	location=locationtxt.getText();
    	salary=salarytxt.getText();
    	if (title.length()>0 &&
    			description.length()>0 &&
    			location.length()>0 &&
    			experiencecombo.getValue()!=null &&
    			salary.length()>0) {
    		try {
				Float.parseFloat(salary);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				new TranslateAnimation(salarytxt);
				return false;
			}
    	}
    	else
    		if (title.length()==0)
				new TranslateAnimation(titletxt);
			if (description.length()==0) 
				new TranslateAnimation(descriptiontxt);
			if (location.length()==0) 
				new TranslateAnimation(locationtxt);
			if (experiencecombo.getValue()==null) 
				new TranslateAnimation(experiencecombo);
			if (salary.length()==0)
				new TranslateAnimation(salarytxt);
    		return false;
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		TextFields.bindAutoCompletion(titletxt, Job.jobs);
		TextFields.bindAutoCompletion(locationtxt, Places.placelist);
		experiencecombo.getItems().addAll("0","1","2","3","4","5");
	}

}
