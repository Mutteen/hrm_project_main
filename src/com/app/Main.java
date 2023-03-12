package com.app;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	public static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		
		try {
			Main.primaryStage = primaryStage;
			primaryStage.setTitle("WorkUp");
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../hrm/view/login.fxml"));
			Image icon = new Image("D:\\Aptech\\Subject\\Session 2\\Java2\\13-2\\hrm_project_main\\src\\com\\hrm\\assets\\logo\\logo-icon.png");
			primaryStage.getIcons().add(icon);
			Scene scene = new Scene(root,635,420);
			scene.getStylesheets().add(getClass().getResource("../hrm/assets/css/login.css").toExternalForm());
			Main.primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
