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
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class employee_controller implements Initializable {

	public employee_controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	private AnchorPane employee;

	@FXML
	private TextField search_field;

	@FXML
	private Button search_btn;

	@FXML
	private Pagination pagination;

	@FXML
	private Button refresh_btn;

	@FXML
	private Button addemployee_btn;

	@FXML
	private Button print_btn;

	@FXML
	private TableView<?> table_employee;

	@FXML
	private TableColumn<?, ?> ID_col;

	@FXML
	private TableColumn<?, ?> name_col;

	@FXML
	private TableColumn<?, ?> DOB_col;

	@FXML
	private TableColumn<?, ?> department_col;

	@FXML
	private TableColumn<?, ?> positom_col;

	@FXML
	private TableColumn<?, ?> salary_col;

	@FXML
	private TableColumn<?, ?> action_col;

	@FXML
	void AddEmployee(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getResource("../view/createEmployee.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML
	void Print(ActionEvent event) {

	}

	@FXML
	void Refresh(ActionEvent event) {

	}

	@FXML
	void SearchEmployee(ActionEvent event) {

	}

}
