package com.hrm.controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import com.hrm.model.beans.department;
import com.hrm.model.beans.employee;
import com.hrm.model.beans.role;
import com.hrm.model.business_objects.bo_department;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class createEmployee_controller implements Initializable {

	public createEmployee_controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bo_department getDepName = new bo_department();
		ObservableList<department> departmentList = getDepName.getAll();
		
		for (department department : departmentList) {
			department_file.getItems().addAll(department.getDepartment_name());
		}
	}

	ZoneId defaultZoneId = ZoneId.systemDefault();
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
	private ComboBox<String> department_file;

	@FXML
	private TextField gmail;

	@FXML
	private PasswordField password;

	@FXML
	private ComboBox<String> status_box;

	@FXML
	private ComboBox<String> role;

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
	void AddAvatar(ActionEvent event) throws SQLException {
		System.out.println(bo_department.getByDepartmentName(department_file.getValue()));
	}

	@FXML
	void Refresh(ActionEvent event) {

	}

	@FXML
	void SaveEmployee(ActionEvent event) throws SQLException {
			employee employee = new employee();
			employee.setUsername(user_name.getText());
			employee.setPassword(password.getText());
			employee.setLast_name(last_name.getText());
			employee.setMiddle_name(middle_name.getText());
			employee.setFirst_name(first_name.getText());
			
			LocalDate localDob = DOB.getValue();
			Date dateDob = Date.from(localDob.atStartOfDay(defaultZoneId).toInstant());
			
			employee.setDob((java.sql.Date) dateDob);
			employee.setTelephone(phone.getText());
			employee.setEmail(gmail.getText());
			employee.setAddress(address_field.getText());
			employee.setOn_leave(Integer.parseInt(on_leave.getText()));	
			
			LocalDate localHire = DOB.getValue();
			Date dateHire = Date.from(localHire.atStartOfDay(defaultZoneId).toInstant());
			employee.setHire_date((java.sql.Date) dateHire);
			
			employee.setStatus(Integer.parseInt(status_box.getValue()));	
			
//			department department = new department();
//			department.setId(getIdFromDepartment(department_file.getValue()));
//			employee.setDepartment(department);
			
			role roleC = new role();
			roleC.setId(Integer.parseInt(role.getValue()));
			employee.setRole(roleC);
	}
	
	private int getIdFromDepartment(String department_name) throws SQLException {
		return bo_department.getByDepartmentName(department_name);
	}

}
