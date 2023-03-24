package com.hrm.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import com.hrm.model.beans.employee;
import com.hrm.model.business_objects.bo_employee;

public class employee_controller implements Initializable {
	
	ObservableList<employee> emloyeeTable = FXCollections.observableArrayList();
	
	public employee_controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			loadTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	private TableView<employee> table_employee;

	@FXML
	private TableColumn<employee, Integer> ID_col;

	@FXML
	private TableColumn<employee, String> name_col;

	@FXML
	private TableColumn<employee, Date> DOB_col;

	@FXML
	private TableColumn<employee, String> department_col;

	@FXML
	private TableColumn<employee, String> positon_col;

	@FXML
	private TableColumn<employee, Integer> salary_col;

	@FXML
	private TableColumn<employee, String> action_col;

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
	
	public void loadTable() throws SQLException {
		refreshTable();
		
		ID_col.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		//fullname
		name_col.setCellValueFactory(cellData -> {
		    String fullname = cellData.getValue().getLast_name() + " " + cellData.getValue().getMiddle_name() + " " + cellData.getValue().getFirst_name();
		    return new SimpleStringProperty(fullname);
		});

		//department
		department_col.setCellValueFactory(cellData -> {
		    String department_name = cellData.getValue().getDepartment().getDepartment_name();
		    return new SimpleStringProperty(department_name);
		});

		//position
		positon_col.setCellValueFactory(cellData -> {
		    String position_name = cellData.getValue().getPosition().getPosition_name();
		    return new SimpleStringProperty(position_name);
		});
	
		DOB_col.setCellValueFactory(new Callback<CellDataFeatures<employee, Date>, ObservableValue<Date>>() {
		    @Override
		    public ObservableValue<Date> call(CellDataFeatures<employee, Date> cellDataFeatures) {
		        return new SimpleObjectProperty<>(cellDataFeatures.getValue().getDob());
		    }
		});

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DOB_col.setCellFactory(column -> new TableCell<employee, Date>() {
		    @Override
		    protected void updateItem(Date date, boolean empty) {
		        super.updateItem(date, empty);
		        if (empty || date == null) {
		            setText(null);
		        } else {
		            setText(dateFormatter.format(date.toLocalDate()));
		        }
		    }
		});
		
		//salary
		salary_col.setCellValueFactory(cellData -> {
		    Integer salary = cellData.getValue().getSalary().getValue_money();
		    return new SimpleIntegerProperty(salary).asObject();
		});
		
		action_col.setCellValueFactory(new PropertyValueFactory<>("status"));

	}
	

	public void refreshTable() throws SQLException {
		emloyeeTable.clear();
		ArrayList<employee> listEmployee = bo_employee.getListEmployee();
		for (employee employee : listEmployee) {
			emloyeeTable.add(employee);
			table_employee.setItems(emloyeeTable);
		}
		
	}

}
