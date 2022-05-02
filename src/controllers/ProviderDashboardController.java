package controllers;

import java.io.IOException;

import animation.FadeAnimation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import others.Load;

public class ProviderDashboardController {
	
	protected static int id;

    @FXML
    private Button postjobbtn;

    @FXML
    private Button profilebtn;

    @FXML
	private AnchorPane rootpane;
    
    @FXML
    private Button signoutBtn;

    @FXML
    private Button viewanalyticsbtn;

    @FXML
    void postjobaction(ActionEvent event) throws IOException {
    	rootpane.getChildren().clear();
    	rootpane.getChildren().add((AnchorPane)FXMLLoader.load(getClass().getResource("/views/fxml/provider/jobpost.fxml")));
    	new FadeAnimation().FadeIn(rootpane);
    }

    @FXML
    void profileButtonAction(ActionEvent event) throws IOException {
    	rootpane.getChildren().clear();
    	rootpane.getChildren().add((AnchorPane)FXMLLoader.load(getClass().getResource("/views/fxml/provider/profile.fxml")));
    	new FadeAnimation().FadeIn(rootpane);
    }

    @FXML
    void signoutButtonAction(ActionEvent event) {
    	try {
    		signoutBtn.getScene().getWindow().hide();
			new Load("login.fxml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void viewanalyticsaction(ActionEvent event) throws IOException {
    	rootpane.getChildren().clear();
    	rootpane.getChildren().add(FXMLLoader.load(getClass().getResource("/views/fxml/provider/viewanalytics.fxml")));
    	new FadeAnimation().FadeIn(rootpane);
    }

}
