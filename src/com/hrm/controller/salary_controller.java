package com.hrm.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class salary_controller implements Initializable {

	public salary_controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	private TableView<?> table_salary;

	@FXML
	private TableColumn<?, ?> ID_col;

	@FXML
	private TableColumn<?, ?> name_col;

	@FXML
	private TableColumn<?, ?> money_col;

	@FXML
	private TableColumn<?, ?> reward_col;

	@FXML
	private TableColumn<?, ?> work_col;

	@FXML
	private TableColumn<?, ?> timepay_col;

	@FXML
	private TableColumn<?, ?> whopay_col;

	@FXML
	private TableColumn<?, ?> createat_col;

	@FXML
	private TableColumn<?, ?> descip_col;

	@FXML
	private TableColumn<?, ?> action_col;

	@FXML
	private Button svae_btn;

	@FXML
	private Button print_btn;

	@FXML
	private Button refresh_btn;

	@FXML
	private Pagination pagiaton;

	@FXML
	private TextArea descip_field;

	@FXML
	private TextField work_field;

	@FXML
	private TextField whopay_field;

	@FXML
	private TextField reward_field;

	@FXML
	private TextField money_field;

	@FXML
	private TextField name_field;

	@FXML
	private DatePicker timepay_fileld;

	@FXML
	private DatePicker createat_field;

	@FXML
	private Button edir_btn;

	@FXML
	private TextField search_field;

	@FXML
	private ComboBox<?> derpart_box;

	@FXML
	private Button search_btn;

	@FXML
	void EdirSalary(ActionEvent event) {

	}

	@FXML
	void Print(ActionEvent event) {

	}

	@FXML
	void Refresh(ActionEvent event) {

	}

	@FXML
	void Save(ActionEvent event) {

	}

	@FXML
	void SearchSalary(ActionEvent event) {

	}

}
