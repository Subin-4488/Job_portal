package others;

import java.io.IOException;

import animation.FadeAnimation;
import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Load extends Main{
	public Load(String txt) throws IOException {
		String loadString="/views/fxml/"+txt;
		FXMLLoader loader=new FXMLLoader(getClass().getResource(loadString));
		Parent root =loader.load();
		Stage stage=Main.mainStage;		
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		new FadeAnimation().FadeIn(root);
	}
}
