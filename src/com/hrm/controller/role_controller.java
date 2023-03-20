package com.hrm.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class role_controller implements Initializable {

	public role_controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	private TextField search_field;

	@FXML
	private Button search_btn;

	@FXML
	private TableView<?> table_role;

	@FXML
	private TableColumn<?, ?> ID_col;

	@FXML
	private TableColumn<?, ?> role_col;

	@FXML
	private TableColumn<?, ?> descrip_col;

	@FXML
	private TableColumn<?, ?> action_col;

	@FXML
	private TextArea descip_field;

	@FXML
	private TextField role_field;

	@FXML
	private TextField ID_filed;

	@FXML
	private Pagination pagination;

	@FXML
	private Button save_btn;

	@FXML
	private Button print_btn;

	@FXML
	private Button refresh_btn;

	@FXML
	private Button edit_btn;

	@FXML
	void EditRole(ActionEvent event) {

	}

	@FXML
	void Print(ActionEvent event) {

	}

	@FXML
	void Refresh(ActionEvent event) {

	}

	@FXML
	void SaveRole(ActionEvent event) {

	}

	@FXML
	void SearchRole(ActionEvent event) {

	}

}
