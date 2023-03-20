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

public class principal_controller implements Initializable {

	public principal_controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	private TableView<?> table_principal;

	@FXML
	private TableColumn<?, ?> ID_col;

	@FXML
	private TableColumn<?, ?> emid_col;

	@FXML
	private TableColumn<?, ?> emname_col;

	@FXML
	private TableColumn<?, ?> money_col;

	@FXML
	private TableColumn<?, ?> type_col;

	@FXML
	private TableColumn<?, ?> date_col;

	@FXML
	private TableColumn<?, ?> create_col;

	@FXML
	private TableColumn<?, ?> descrip_col;

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
	private TextArea descrip;

	@FXML
	private TextField money_ffield;

	@FXML
	private TextField type_field;

	@FXML
	private TextField emid_field;

	@FXML
	private DatePicker date_field;

	@FXML
	private DatePicker creat_field;

	@FXML
	private TextField emname_field;

	@FXML
	private Button edit_btn;

	@FXML
	private TextField search_field;

	@FXML
	private ComboBox<?> department_box;

	@FXML
	private Button search_btn;

	@FXML
	void AddPrinciple(ActionEvent event) {

	}

	@FXML
	void EditPrinciple(ActionEvent event) {

	}

	@FXML
	void Print(ActionEvent event) {

	}

	@FXML
	void Refresh(ActionEvent event) {

	}

	@FXML
	void SearchPrincipal(ActionEvent event) {

	}

}
