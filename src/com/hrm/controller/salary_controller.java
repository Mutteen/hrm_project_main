package com.hrm.controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.hrm.model.beans.employee;
import com.hrm.model.beans.salary;
import com.hrm.model.business_objects.bo_employee;
import com.hrm.model.business_objects.bo_salary;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
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
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class salary_controller implements Initializable {

	public salary_controller() {
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
	
	ObservableList<salary> salary_table_obList = FXCollections.observableArrayList();
	private static int ROWS_PER_PAGE = 8;
	private ObservableList<salary> masterData = FXCollections.observableArrayList();
	private FilteredList<salary> filteredData;
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	@FXML
	private TableView<salary> table_salary;

//	@FXML
//	private TableColumn<salary, Integer> ID_col;

	@FXML
	private TableColumn<salary, String> name_col;

	@FXML
	private TableColumn<salary, Integer> money_col;

	@FXML
	private TableColumn<salary, Integer> reward_col;

	@FXML
	private TableColumn<salary, Integer> work_col;

	@FXML
	private TableColumn<salary, Date> timepay_col;

	@FXML
	private TableColumn<salary, String> whopay_col;

	@FXML
	private TableColumn<salary, Date> createat_col;

	@FXML
	private TableColumn<salary, String> descip_col;

	@FXML
	private TableColumn<salary, String> action_col;

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
	private ComboBox<String> derpart_box;

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
	
	public void loadTable() throws SQLException {
		refreshTable();
		
		filteredData = new FilteredList<>(masterData, p -> true);
		// Search
		search_field.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(
					Salary -> Salary.getWho_pay().toLowerCase().contains(newValue.toLowerCase()));
			changeTableView(0, masterData.size());
		});
		
		
		//id
//		ID_col.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		//fullname
		name_col.setCellValueFactory(cellData -> {
		    String fullname = cellData.getValue().getEmployee().getLast_name() + " " + cellData.getValue().getEmployee().getMiddle_name() + " " + cellData.getValue().getEmployee().getFirst_name();
		    return new SimpleStringProperty(fullname);
		});

		//value_money
		money_col.setCellValueFactory(new PropertyValueFactory<>("value_money"));
		
		//reward
		reward_col.setCellValueFactory(new PropertyValueFactory<>("value_money_reward"));
		
		//working day
		work_col.setCellValueFactory(new PropertyValueFactory<>("working_day"));
		
		//time_to_pay
		timepay_col.setCellValueFactory(new Callback<CellDataFeatures<salary, Date>, ObservableValue<Date>>() {
		    @Override
		    public ObservableValue<Date> call(CellDataFeatures<salary, Date> cellDataFeatures) {
		        return new SimpleObjectProperty<>(cellDataFeatures.getValue().getTime_to_pay());
		    }
		});

		timepay_col.setCellFactory(column -> new TableCell<salary, Date>() {
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
		
		//who_pay
		whopay_col.setCellValueFactory(new PropertyValueFactory<>("who_pay"));
		
		//created_at
		createat_col.setCellValueFactory(new Callback<CellDataFeatures<salary, Date>, ObservableValue<Date>>() {
		    @Override
		    public ObservableValue<Date> call(CellDataFeatures<salary, Date> cellDataFeatures) {
		        return new SimpleObjectProperty<>(cellDataFeatures.getValue().getCreated_at());
		    }
		});

		createat_col.setCellFactory(column -> new TableCell<salary, Date>() {
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
		
		//description
		descip_col.setCellValueFactory(new PropertyValueFactory<>("description"));
				
		//action
		Callback<TableColumn<salary, String>, TableCell<salary, String>> cellFoctory = (
				TableColumn<salary, String> param) -> {
			// make cell containing buttons
			final TableCell<salary, String> cell = new TableCell<salary, String>() {
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
	}
	
	public void refreshTable() throws SQLException {
		salary_table_obList.clear();
		ArrayList<salary> listSalary = bo_salary.getListSalary();
		for (salary salary : listSalary) {
			salary_table_obList.add(salary);
			table_salary.setItems(salary_table_obList);
		}
		
	}
	
	// change table view Method
	private void changeTableView(int index, int limit) {
		int fromIndex = index * limit;
		int toIndex = Math.min(fromIndex + limit, masterData.size());
		int minIndex = Math.min(toIndex, filteredData.size());
		SortedList<salary> sortedData = new SortedList<>(
				FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
		sortedData.comparatorProperty().bind(table_salary.comparatorProperty());
		table_salary.setItems(sortedData);
	}
	


}
