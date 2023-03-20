package com.hrm.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.hrm.model.usersession;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class login implements Initializable {

	@FXML
	public TextField tfUsername;
	@FXML
	public BorderPane borderPaneLogin;

	@FXML
	public TextField tfPassword;

	@FXML
	public Hyperlink btnForgotPassword;

	@FXML
	public Button btnLogin;

	private Stage stage;
	private Scene scene;
	private Pane homePage;

	public login() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void btnLoginOnAction(ActionEvent event) throws IOException {
		String username = tfUsername.getText();
		String password = tfPassword.getText();
		if (username.equals("Hung") && password.equals("Thuan123")) {
			usersession.getInstace(username, null);
			homePage = (Pane) FXMLLoader.load(getClass().getResource("../view/home.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(homePage);
			scene.getStylesheets().add(getClass().getResource("../assets/css/home.css").toExternalForm());
			stage.setScene(scene);
			stage.show();

		} else {
//			loginController.
		}
	}

	public void btnForgotPasswordOnAction(ActionEvent event) throws IOException {
		homePage = (Pane) FXMLLoader.load(getClass().getResource("../view/forgotpassword.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(homePage);
//		scene.getStylesheets().add(getClass().getResource("../assets/css/home.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
}
