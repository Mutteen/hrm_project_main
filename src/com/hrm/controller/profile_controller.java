package com.hrm.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class profile_controller implements Initializable {

	public profile_controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	private MenuButton setting;

	@FXML
	private MenuItem password_item;

	@FXML
	private MenuItem edit_item;

	@FXML
	private TextArea descrip_text;

	@FXML
	private Text department_text;

	@FXML
	private Text work_text;

	@FXML
	private TextArea position_text;

	@FXML
	private Text principal_text;

	@FXML
	private Text salary_text;

	@FXML
	private Text onleave_text;

	@FXML
	private ImageView avatar;

	@FXML
	private Text DOB_text;

	@FXML
	private Text gmail_text;

	@FXML
	private Text phone_text;

	@FXML
	private Text fullname_txt;

	@FXML
	private Button refresh_btn;

	@FXML
	void EditPassword(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getResource("../view/createNewPassword.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML
	void EditProfile(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getResource("../view/editEmployee.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML
	void Refresh(ActionEvent event) {

	}

}
