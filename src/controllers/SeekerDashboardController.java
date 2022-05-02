package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import animation.FadeAnimation;
import database.DBHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.JobSeeker;
import others.Load;

public class SeekerDashboardController implements Initializable {
	
	protected static int id;

    @FXML
    private Button applybtn;

    @FXML
    private ImageView mainavatar;

    @FXML
    private Button profilebtn;

    @FXML
    private AnchorPane rootpane;

    @FXML
    private Button signoutBtn;

    @FXML
    private Button trackbtn;

    @FXML
    void applyButtonAction(ActionEvent event) {
    	
    }

    @FXML
    void profileButtonAction(ActionEvent event) throws IOException {
    	rootpane.getChildren().clear();
    	rootpane.getChildren().add((AnchorPane)FXMLLoader.load(getClass().getResource("/views/fxml/seeker/profile.fxml")));
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
    void trackButtonAction(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		JobSeeker seeker=null;
		seeker=new DBHandler().getSeekerObject(SeekerDashboardController.id);
		if (seeker.getAvatar()!=null) {
			mainavatar.setImage(new Image(seeker.getAvatar()));
		}
	}
}
