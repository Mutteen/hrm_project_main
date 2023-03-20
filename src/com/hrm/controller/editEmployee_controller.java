
package com.hrm.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class editEmployee_controller implements Initializable {

	public editEmployee_controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	private Label Title;
	@FXML
	private Button save_btn;

	@FXML
	private Button refresh_btn;

	@FXML
	private Button add_avatar_btn;

	@FXML
	private ImageView avatar;

	@FXML
	private TextField middle_name;

	@FXML
	private TextField last_name;

	@FXML
	private TextField first_name;

	@FXML
	private DatePicker DOB;

	@FXML
	private ComboBox<?> department_file;

	@FXML
	private TextField gmail;

	@FXML
	private TextArea descip_field;

	@FXML
	private ComboBox<?> status_box;

	@FXML
	private ComboBox<?> role;

	@FXML
	private TextField phone;

	@FXML
	private TextField user_name;

	@FXML
	private TextArea address_field;

	@FXML
	private DatePicker create_at;

	@FXML
	private TextField on_leave;

	@FXML
	void AddAvatar(ActionEvent event) {

	}

	@FXML
	void Refresh(ActionEvent event) {

	}

	@FXML
	void SaveEmployee(ActionEvent event) {

	}

}
