package animation;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class FadeAnimation {
	FadeTransition fd ;
	
	public void FadeOut(Node node) {
		// TODO Auto-generated constructor stub
		fd=new FadeTransition();
		fd.setNode(node);
		fd.setAutoReverse(false);
		fd.setCycleCount(1);
		fd.setDuration(Duration.seconds(1f));
		fd.setFromValue(1f);
		fd.setToValue(0f);
		fd.playFromStart();
	}
	
	public void FadeIn(Node node) {
		// TODO Auto-generated constructor stub
		fd=new FadeTransition();
		fd.setNode(node);
		fd.setAutoReverse(false);
		fd.setCycleCount(1);
		fd.setDuration(Duration.seconds(1f));
		fd.setFromValue(0f);
		fd.setToValue(1f);
		fd.playFromStart();
	}
}
