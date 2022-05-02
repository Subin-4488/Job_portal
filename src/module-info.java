module Job_Portal {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires mysql.connector.java;
	requires javafx.base;
	requires java.desktop;
	requires org.controlsfx.controls;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controllers to javafx.fxml;
	opens controllers.seeker to javafx.fxml;
	opens controllers.provider to javafx.fxml;
}
