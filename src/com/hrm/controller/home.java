package com.hrm.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class home implements Initializable{
	@FXML
	public TreeView<String> menuTreeView;
	
	@FXML 
	public Button btnInfo;
	
	@FXML
	public Button btnDashboard;
	
	@FXML
	public Button btnEmployee;
	
	@FXML
	public Button btnDepartment;
	
	@FXML
	public Button btnPrincipal;
	
	@FXML
	public Button btnSalary;

	public home() {
		// TODO Auto-generated constructor stub
	}

	
	
	public void btnInfoOnAction(ActionEvent event) {

	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	

	
	
}
