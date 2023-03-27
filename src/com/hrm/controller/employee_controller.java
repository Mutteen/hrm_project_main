package com.hrm.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import com.hrm.assets.lib.alert;
import com.hrm.model.beans.employee;
import com.hrm.model.business_objects.bo_employee;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;

public class employee_controller implements Initializable {
	
	ObservableList<employee> emloyee_table_obList = FXCollections.observableArrayList();
	
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

	private static int ROWS_PER_PAGE = 8;
	private ObservableList<employee> masterData = FXCollections.observableArrayList();
	private FilteredList<employee> filteredData;
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
		
		filteredData = new FilteredList<>(masterData, p -> true);
		// Search
		search_field.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(
					Employee -> Employee.getFirst_name().toLowerCase().contains(newValue.toLowerCase()));
			changeTableView(0, masterData.size());
		});
		
		
		//id
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
	
		//dob
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
		
		//action
		Callback<TableColumn<employee, String>, TableCell<employee, String>> cellFoctory = (
				TableColumn<employee, String> param) -> {
			// make cell containing buttons
			final TableCell<employee, String> cell = new TableCell<employee, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					// that cell created only on non-empty rows
					if (empty) {
						setGraphic(null);
						setText(null);

					} else {

						Label deleteIcon = GlyphsDude.createIconLabel(FontAwesomeIcons.TRASH, "", "25px", "25px",
								ContentDisplay.LEFT);

						Label editIcon = GlyphsDude.createIconLabel(FontAwesomeIcons.PENCIL_SQUARE, "", "25px", "25px",
								ContentDisplay.LEFT);
						deleteIcon.getStyleClass().add("delete-label");
						editIcon.getStyleClass().add("update-label");
						// delete event
//						deleteIcon.setOnMouseClicked((MouseEvent event) -> {
//							// Alert delete
//							Alert alert1 = new Alert(AlertType.CONFIRMATION);
//							alert1.setTitle("Delete File");
//							alert1.setHeaderText("Are you sure want to move this file to the Recycle Bin?");
//
//							// option != null.
//							Optional<ButtonType> option = alert1.showAndWait();
//
//							if (option.get() == ButtonType.OK) {
//								employee employee = table_employee.getSelectionModel().getSelectedItem();
//								boolean checkDelete = true;
//								try {
//									checkDelete = bo_employee.delete(employee.getId());
//								} catch (SQLException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//
//								if (checkDelete == true) {
//									alert.Success("Delete employee " + employee.getId() + " ");
//								}
//							}
//
//						});

						// update event

//						editIcon.setOnMouseClicked((MouseEvent event) -> {
//
//							department Department = table_department.getSelectionModel().getSelectedItem();
//							depast_name.setText(Department.getDepartment_name());
//							depast_name1.setText(String.valueOf(Department.getId()));
//							// setdatepciker fomat date sql
//							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//							LocalDate localDate = LocalDate.parse(String.valueOf(Department.getCreated_at()),
//									formatter);
//
//							create_at_field.setValue(formatDate(String.valueOf(Department.getCreated_at())));
//							description_field.setText(Department.getDescription());
//
//						});
						HBox managebtn = new HBox(editIcon, deleteIcon);
						managebtn.setStyle("-fx-alignment:center");
						HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
						HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

						setGraphic(managebtn);

						setText(null);

					}
				}

			};

			return cell;
		};

		action_col.setCellFactory(cellFoctory);
		// set page in table view
//		int totalPage = (int) (Math.ceil(masterData.size() * 1.0 / ROWS_PER_PAGE));
//		pagination.setCurrentPageIndex(0);
//		pagination.setPageCount(totalPage);
//		changeTableView(0, ROWS_PER_PAGE);
//		pagination.currentPageIndexProperty().addListener((observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));
//		action_col.setCellValueFactory(new PropertyValueFactory<>("status"));

	}
	
	// change table view Method
	private void changeTableView(int index, int limit) {
		int fromIndex = index * limit;
		int toIndex = Math.min(fromIndex + limit, masterData.size());
		int minIndex = Math.min(toIndex, filteredData.size());
		SortedList<employee> sortedData = new SortedList<>(
				FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
		sortedData.comparatorProperty().bind(table_employee.comparatorProperty());
		table_employee.setItems(sortedData);
	}
	

	public void refreshTable() throws SQLException {
		emloyee_table_obList.clear();
		ArrayList<employee> listEmployee = bo_employee.getListEmployee();
		for (employee employee : listEmployee) {
			emloyee_table_obList.add(employee);
			table_employee.setItems(emloyee_table_obList);
		}
		
	}

}
