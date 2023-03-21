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

public class position_controller implements Initializable {

	public position_controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	private TableView<?> table_position;

	@FXML
	private TableColumn<?, ?> ID_col;

	@FXML
	private TableColumn<?, ?> position_col;

	@FXML
	private TableColumn<?, ?> create_coll;

	@FXML
	private TableColumn<?, ?> who_col;

	@FXML
	private TableColumn<?, ?> descrip_col;

	@FXML
	private TableColumn<?, ?> action_field;

	@FXML
	private Button save_btn;

	@FXML
	private Button print_btn;

	@FXML
	private Button refresh_btn;

	@FXML
	private Pagination pagination;

	@FXML
	private TextArea descrip_field;

	@FXML
	private TextField position_field;

	@FXML
	private DatePicker createat_field;

	@FXML
	private TextField whocreate_field;

	@FXML
	private TextField search_field;

	@FXML
	private Button search_btn;

	@FXML
	private Button edit_btn;

	@FXML
	void EditPosition(ActionEvent event) {

	}

	@FXML
	void Print(ActionEvent event) {

	}

	@FXML
	void Refresh(ActionEvent event) {

	}

	@FXML
	void SavePosition(ActionEvent event) {

	}

	@FXML
	void SearchPosition(ActionEvent event) {

	}

}
