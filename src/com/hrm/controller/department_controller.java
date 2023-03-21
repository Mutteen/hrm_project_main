package com.hrm.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class department_controller implements Initializable {

	public department_controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	private TableView<?> table_department;

	@FXML
	private TableColumn<?, ?> ID_col;

	@FXML
	private TableColumn<?, ?> department_name_col;

	@FXML
	private TableColumn<?, ?> create_at_col;

	@FXML
	private TableColumn<?, ?> des_col;

	@FXML
	private TableColumn<?, ?> action_col;

	@FXML
	private Button save_btn;

	@FXML
	private Button print_btn;

	@FXML
	private Button refresh_btn;

	@FXML
	private Pagination pagination;

	@FXML
	private TextArea Description;

	@FXML
	private TextField status_field;

	@FXML
	private TextField depast_name;

	@FXML
	private DatePicker create_at_field;

	@FXML
	private TextField search_field;

	@FXML
	private Button search_btn;

	@FXML
	void Print(ActionEvent event) {

	}

	@FXML
	void Refresh(ActionEvent event) {

	}

	@FXML
	void SaveDepartment(ActionEvent event) {

	}

	@FXML
	void SearchDepartment(ActionEvent event) {

	}

}
