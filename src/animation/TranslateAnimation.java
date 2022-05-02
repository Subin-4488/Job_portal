package animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class TranslateAnimation {
	public TranslateAnimation(Node node) {
		// TODO Auto-generated constructor stub
		TranslateTransition transition=new TranslateTransition();
		transition.setNode(node);
		transition.setFromX(0f);
		transition.setByX(15f);
		transition.setCycleCount(20);
		transition.setDuration(Duration.millis(50));
		transition.setAutoReverse(true);
		transition.playFromStart();
	}
}
