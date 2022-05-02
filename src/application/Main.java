package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	
	protected static Stage mainStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			mainStage=primaryStage;
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/fxml/login.fxml"));
			Parent root=loader.load();
			Scene scene=new Scene(root);
			mainStage.setScene(scene);
			mainStage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/icon.png")));
			mainStage.setTitle("Job Portal");
			mainStage.setResizable(false);
			mainStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
