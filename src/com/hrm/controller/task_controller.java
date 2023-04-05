package com.hrm.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import com.hrm.assets.lib.alert;
import com.hrm.model.usersession;
import com.hrm.model.beans.task;
import com.hrm.model.beans.taskPosition;
import com.hrm.model.business_objects.bo_task;
import com.hrm.model.business_objects.bo_taskPosition;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class task_controller implements Initializable {
	
	@FXML
	private TableView<task> table_PandE;
	
    @FXML
    private AnchorPane paneAdmin;

	@FXML
	private TableColumn<task, Integer> ID_col;

	@FXML
	private TableColumn<task, String> title_col;

	@FXML
	private TableColumn<task, String> assignee_col;

	@FXML
	private TableColumn<task, String> status_col;

	@FXML
	private TableColumn<task, String> priority_col;

	@FXML
	private TableColumn<task, DatePicker> deadline_col;

	@FXML
	private TableColumn<task, DatePicker> create_col;

	@FXML
	private TableColumn<task, String> descrip_col;

	@FXML
	private TableColumn<task, String> action_col;

	@FXML
	private Pagination pagination;

	@FXML
	private TextArea description_field;

	@FXML
	private TextField employee_field;

	@FXML
	private DatePicker create_at_field;

	@FXML
	private TextField ID_field;

	@FXML
	private TextArea title_field;

	@FXML
	private DatePicker dealine_field;

	@FXML
	private ComboBox<String> status_box;

	@FXML
	private Button Search_P;

	@FXML
	private Button save_btn2;

	@FXML
	private Button print_btn1;

	@FXML
	private Button refresh_btn2;

	@FXML
	private ComboBox<String> priority_field;

	@FXML
	private TextField search_field;

	@FXML
	private TableView<taskPosition> table_Po;

	@FXML
	private TableColumn<taskPosition, Integer> id_P_col;

	@FXML
	private TableColumn<taskPosition, String> task_col;

	@FXML
	private TableColumn<taskPosition, String> position_col;

	@FXML
	private TableColumn<taskPosition, String> action_P_col;

	@FXML
	private TextField ID_TP;

	@FXML
	private TextField Po_ID;

	@FXML
	private TextField Task_iD;

	@FXML
	private Button searh_P;

	@FXML
	private Button save_btn1;

	@FXML
	private Button refresh_btn1;

	@FXML
	private AnchorPane paneStaff;

	 @FXML
    private TableColumn<task, Integer> ID_col_task;

    @FXML
    private TableColumn<task, String> action_col1;

    @FXML
    private TableColumn<task, String> assignee_col_task;

    @FXML
    private TableColumn<task, DatePicker> deadline_col_task;

    @FXML
    private TableColumn<task, String> department_col_task;

    @FXML
    private Pagination pagination_task;

    @FXML
    private Button print_btn;

    @FXML
    private TableColumn<task, String> priority_col_task;

    @FXML
    private Button refresh_btn_task;

    @FXML
    private TextField search_field_task;

    @FXML
    private TableColumn<task, String> status_col_task;

    @FXML
    private TableView<task> table_task_staff;

    @FXML
    private TableColumn<task, String> title_col_task;
	
	@FXML
	private Pagination pagination1;
	
	private ObservableList<String> statusBoxList = FXCollections.observableArrayList("Todo", "In process", "Complete");
	private ObservableList<String> priorituList = FXCollections.observableArrayList("High", "Low", "Medium");
	private bo_task dataDao = new bo_task();
	private bo_task dataDaoTask = new bo_task();
	private bo_taskPosition daTaskPosition = new bo_taskPosition();

	private ObservableList<task> masterData = FXCollections.observableArrayList();
	private ObservableList<task> masterDataTask = FXCollections.observableArrayList();
	private ObservableList<taskPosition> secondData = FXCollections.observableArrayList();
	private static int ROWS_PER_PAGE = 8;

	private FilteredList<task> filteredData;
	private FilteredList<taskPosition> filteredDataTP;

	@FXML
	void AddPandE(ActionEvent event) {
		if (ID_field.getText().equals("")) {
			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Delete File");
			alert1.setHeaderText("Are you sure Save " + ID_field.getText() + ".");

			// option != null.
			Optional<ButtonType> option = alert1.showAndWait();

			if (option.get() == ButtonType.OK) {

				task MR = new task();
				MR.setTitle(title_field.getText());
				MR.setAssignee(Integer.parseInt(employee_field.getText()));
				MR.setStatus(status_box.getValue());
				MR.setPriority(priority_field.getValue());
				MR.setCreated_at(Date.valueOf(create_at_field.getValue()));
				MR.setDeadline(Date.valueOf(dealine_field.getValue()));
				MR.setDescription(description_field.getText());

				boolean checkSave = dataDao.save(MR);
				if (checkSave == true) {
					alert.Success("Add ");
					clean();
				} else {
					alert.Error();
				}
			}

		} else {
			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Update");
			alert1.setHeaderText("Are you sure Update " + ID_field.getText());

			// option != null.
			Optional<ButtonType> option = alert1.showAndWait();

			if (option.get() == ButtonType.OK) {
				task MR = new task();
				MR.setTitle(title_field.getText());
				MR.setAssignee(Integer.parseInt(employee_field.getText()));
				MR.setStatus(status_box.getValue());
				MR.setPriority(priority_field.getValue());
				MR.setCreated_at(Date.valueOf(create_at_field.getValue()));
				MR.setDeadline(Date.valueOf(dealine_field.getValue()));
				MR.setDescription(description_field.getText());
				MR.setId(Integer.parseInt(ID_field.getText()));
				boolean checkSave = dataDao.update(MR);
				if (checkSave == true) {
					alert.Success("Update  ");
					clean();
				} else {
					alert.Error();
				}
			}

		}
	}

	@FXML
	void AddTaskPosition(ActionEvent event) {
		if (ID_TP.getText().equals("")) {
			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Delete File");
			alert1.setHeaderText("Are you sure Save " + ID_TP.getText() + ".");

			// option != null.
			Optional<ButtonType> option = alert1.showAndWait();
			if (option.get() == ButtonType.OK) {

				taskPosition MR = new taskPosition();
				MR.setPosition_id(Integer.parseInt(Po_ID.getText()));
				MR.setTask_id(Integer.parseInt(Task_iD.getText()));

				boolean checkSave = daTaskPosition.save(MR);
				if (checkSave == true) {
					alert.Success("Add ");
					cleanTE();
				} else {
					alert.Error();
				}
			}

		} else {
			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Update File");
			alert1.setHeaderText("Are you sure Update " + ID_TP.getText());

			// option != null.
			Optional<ButtonType> option = alert1.showAndWait();

			if (option.get() == ButtonType.OK) {
				taskPosition MR = new taskPosition();
				MR.setPosition_id(Integer.parseInt(Po_ID.getText()));
				MR.setTask_id(Integer.parseInt(Task_iD.getText()));
				MR.setId(Integer.parseInt(ID_TP.getText()));

				boolean checkSave = daTaskPosition.update(MR);
				if (checkSave == true) {
					alert.Success("Update  ");
					cleanTE();
				} else {
					alert.Error();
				}
			}

		}
	}

	@FXML
	void SearchEmployee(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getResource("../view/SearchEMploy.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Search employee.");
		stage.setScene(new Scene(root, 512, 472));
		stage.show();
	}

	@FXML
	void SeachPosition(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getResource("../view/SearchPosition.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Search employee.");
		stage.setScene(new Scene(root, 465, 472));
		stage.show();
	}

	@FXML
	void Print(ActionEvent event) {

	}

	@FXML
	void Refresh(ActionEvent event) {
		clean();
		getList();
		InserTableView();
	}

	@FXML
	void Refresh1(ActionEvent event) {
		cleanTE();
		getListTE();
		InsertTableTaskPosition();

	}
	
    @FXML
    void Refresh_task(ActionEvent event) {
    	cleanTask();
		getListTask();
		InserTableViewStaff();
    }

	public void clean() {
		ID_field.setText("");
		create_at_field.setValue(LocalDate.now());
		priority_field.setValue("prority");
		status_box.setValue("Status");
		dealine_field.setValue(LocalDate.now());
		description_field.setText("");
		title_field.setText("");
		employee_field.setText("");
		getList();
	}
	
	public void cleanTask() {
		getList();
	}

	public void cleanTE() {
		ID_TP.setText("");
		Po_ID.setText("");
		Task_iD.setText("");
		getListTE();
	}

	public void getList() {
		masterData = dataDao.getAll();
	}
	
	public void getListTask(){
		masterDataTask = dataDaoTask.getAllTask(usersession.getDepartment_id());
	}

	public void getListTE() {
		secondData = daTaskPosition.getAll();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(usersession.getRole_id() == 1 ) {
			status_box.setItems(statusBoxList);
			priority_field.setItems(priorituList);
			String pattern = "yyyy-MM-dd";
			StringConverter converter = new StringConverter<LocalDate>() {
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
	
				@Override
				public String toString(LocalDate date) {
					if (date != null) {
						return dateFormatter.format(date);
					} else {
						return "";
					}
				}
	
				@Override
				public LocalDate fromString(String string) {
					if (string != null && !string.isEmpty()) {
						return LocalDate.parse(string, dateFormatter);
					} else {
						return null;
					}
				}
			};
			create_at_field.setConverter(converter);
			dealine_field.setConverter(converter);
			getList();
			getListTE();
			InsertTableTaskPosition();
			InserTableView();
		}else{
			paneAdmin.setVisible(false);
			paneStaff.setVisible(true);
			InserTableViewStaff();
			
		}

	}

	public void InserTableView() {
		filteredData = new FilteredList<>(masterData, p -> true);
		// Search
		search_field.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(MR -> MR.getTitle().toLowerCase().contains(newValue.toLowerCase())
					|| MR.getNameString().toLowerCase().contains(newValue.toLowerCase())
					|| MR.getPriority().toLowerCase().contains(newValue.toLowerCase()));
			changeTableView(0, masterData.size());
			changeTableView(0, masterData.size());
		});
		// add value into cell

		ID_col.setCellValueFactory(new PropertyValueFactory<>("Id"));
		assignee_col.setCellValueFactory(new PropertyValueFactory<>("nameString"));
		title_col.setCellValueFactory(new PropertyValueFactory<>("title"));
		deadline_col.setCellValueFactory(new PropertyValueFactory<>("deadline"));
		create_col.setCellValueFactory(new PropertyValueFactory<>("created_at"));
		status_col.setCellValueFactory(new PropertyValueFactory<>("status"));
		priority_col.setCellValueFactory(new PropertyValueFactory<>("priority"));
		descrip_col.setCellValueFactory(new PropertyValueFactory<>("description"));

		// add cell of button edit

		Callback<TableColumn<task, String>, TableCell<task, String>> cellFoctory = (
				TableColumn<task, String> param) -> {
			// make cell containing buttons
			final TableCell<task, String> cell = new TableCell<task, String>() {
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
						deleteIcon.setOnMouseClicked((MouseEvent event) -> {
							// Alert delete
							Alert alert1 = new Alert(AlertType.CONFIRMATION);
							alert1.setTitle("Delete File");
							alert1.setHeaderText("Are you sure want to move this file to the Recycle Bin?");

							// option != null.
							Optional<ButtonType> option = alert1.showAndWait();

							if (option.get() == ButtonType.OK) {
								task MR = table_PandE.getSelectionModel().getSelectedItem();
								boolean checkDelete = dataDao.delete(MR);

								if (checkDelete == true) {
									alert.Success("Delete Module " + MR.getTitle() + " of " + MR.getId());
									clean();
								}
							}

						});

						// update event

						editIcon.setOnMouseClicked((MouseEvent event) -> {

							task MR = table_PandE.getSelectionModel().getSelectedItem();

							ID_field.setText(String.valueOf(MR.getId()));
							title_field.setText(MR.getTitle());
							status_box.setValue(MR.getStatus());
							priority_field.setValue(MR.getPriority());
							dealine_field.setValue(fomateDate(String.valueOf(MR.getDeadline())));
							create_at_field.setValue(fomateDate(String.valueOf(MR.getCreated_at())));
							employee_field.setText(String.valueOf(MR.getAssignee()));
							description_field.setText(MR.getDescription());

						});
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
		// customer color table column status
		Callback<TableColumn<task, String>, TableCell<task, String>> cellFoctory1 = (
				TableColumn<task, String> param) -> {
			// make cell containing buttons
			final TableCell<task, String> cell = new TableCell<task, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					// that cell created only on non-empty rows
					if (empty) {
						setText(null);

					} else {
						setText(item);
						setStyle("-fx-font-weight: bold;");
						if (item.equals("Complete")) {
							setTextFill(Color.DARKSEAGREEN);

						} else if (item.equals("Todo")) {
							setTextFill(Color.CHOCOLATE);
						} else {
							setTextFill(Color.DARKBLUE);
						}
					}
				}
			};

			return cell;
		};

		// customer color table column priority
		Callback<TableColumn<task, String>, TableCell<task, String>> cellFoctory2 = (
				TableColumn<task, String> param) -> {
			// make cell containing buttons
			final TableCell<task, String> cell = new TableCell<task, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					// that cell created only on non-empty rows
					if (empty) {
						setText(null);

					} else {
						setText(item);
						setStyle("-fx-font-weight: bold;");
						if (item.equals("High")) {
							setTextFill(Color.RED);

						} else if (item.equals("Medium")) {
							setTextFill(Color.YELLOWGREEN);
						} else {
							setTextFill(Color.BLUE);
						}
					}
				}
			};
			return cell;
		};
		priority_col.setCellFactory(cellFoctory2);
		status_col.setCellFactory(cellFoctory1);
		action_col.setCellFactory(cellFoctory);
		// set page in table view
		int totalPage = (int) (Math.ceil(masterData.size() * 1.0 / ROWS_PER_PAGE));
		pagination.setCurrentPageIndex(0);
		pagination.setPageCount(totalPage);
		changeTableView(0, ROWS_PER_PAGE);
		pagination.currentPageIndexProperty()
				.addListener((observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));

	}

	private void InserTableViewStaff() {
		getListTask();
		filteredData = new FilteredList<>(masterDataTask, p -> true);
		// Search
		search_field_task.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(MR -> MR.getTitle().toLowerCase().contains(newValue.toLowerCase())
					|| MR.getNameString().toLowerCase().contains(newValue.toLowerCase())
					|| MR.getPriority().toLowerCase().contains(newValue.toLowerCase()));
			changeTableViewTask(0, masterDataTask.size());
			changeTableViewTask(0, masterDataTask.size());
		});
		// add value into cell

		ID_col_task.setCellValueFactory(new PropertyValueFactory<>("id"));
		assignee_col_task.setCellValueFactory(new PropertyValueFactory<>("nameString"));
		title_col_task.setCellValueFactory(new PropertyValueFactory<>("title"));
//		department_col_task.setCellValueFactory(new PropertyValueFactory<>("department"));
		department_col_task.setCellValueFactory(cellData -> {
			String department_name = cellData.getValue().getDepartment().getDepartment_name();
			return new SimpleStringProperty(department_name);
		});
		deadline_col_task.setCellValueFactory(new PropertyValueFactory<>("deadline"));
		priority_col_task.setCellValueFactory(new PropertyValueFactory<>("priority"));
		status_col_task.setCellValueFactory(new PropertyValueFactory<>("status"));

		// customer color table column status
		Callback<TableColumn<task, String>, TableCell<task, String>> cellFoctory1 = (
				TableColumn<task, String> param) -> {
			// make cell containing buttons
			final TableCell<task, String> cell = new TableCell<task, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					// that cell created only on non-empty rows
					if (empty) {
						setText(null);

					} else {
						setText(item);
						setStyle("-fx-font-weight: bold;");
						if (item.equals("Complete")) {
							setTextFill(Color.DARKSEAGREEN);

						} else if (item.equals("Todo")) {
							setTextFill(Color.CHOCOLATE);
						} else {
							setTextFill(Color.DARKBLUE);
						}
					}
				}
			};

			return cell;
		};

		// customer color table column priority
		Callback<TableColumn<task, String>, TableCell<task, String>> cellFoctory2 = (
				TableColumn<task, String> param) -> {
			// make cell containing buttons
			final TableCell<task, String> cell = new TableCell<task, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					// that cell created only on non-empty rows
					if (empty) {
						setText(null);

					} else {
						setText(item);
						setStyle("-fx-font-weight: bold;");
						if (item.equals("High")) {
							setTextFill(Color.RED);

						} else if (item.equals("Medium")) {
							setTextFill(Color.YELLOWGREEN);
						} else {
							setTextFill(Color.BLUE);
						}
					}
				}
			};
			return cell;
		};
		priority_col_task.setCellFactory(cellFoctory2);
		status_col_task.setCellFactory(cellFoctory1);
		// set page in table view
		int totalPage = (int) (Math.ceil(masterDataTask.size() * 1.0 / ROWS_PER_PAGE));
		pagination_task.setCurrentPageIndex(0);
		pagination_task.setPageCount(totalPage);
		changeTableViewTask(0, ROWS_PER_PAGE);
		pagination_task.currentPageIndexProperty()
				.addListener((observable, oldValue, newValue) -> changeTableViewTask(newValue.intValue(), ROWS_PER_PAGE));

	}


	
	// Table TaskPosition
	
	private void InsertTableTaskPosition() {
		filteredDataTP = new FilteredList<>(secondData, p -> true);
		// Search
		search_field.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredDataTP.setPredicate(MR -> MR.getTaskString().toLowerCase().contains(newValue.toLowerCase())
					|| MR.getPositionString().toLowerCase().contains(newValue.toLowerCase()));
			changeTableView1(0, secondData.size());
			changeTableView1(0, secondData.size());
		});
		// add value into cell

		id_P_col.setCellValueFactory(new PropertyValueFactory<>("Id"));
		task_col.setCellValueFactory(new PropertyValueFactory<>("taskString"));
		position_col.setCellValueFactory(new PropertyValueFactory<>("positionString"));

		// add cell of button edit

		Callback<TableColumn<taskPosition, String>, TableCell<taskPosition, String>> cellFoctory = (
				TableColumn<taskPosition, String> param) -> {
			// make cell containing buttons
			final TableCell<taskPosition, String> cell = new TableCell<taskPosition, String>() {
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
						deleteIcon.setOnMouseClicked((MouseEvent event) -> {
							// Alert delete
							Alert alert1 = new Alert(AlertType.CONFIRMATION);
							alert1.setTitle("Delete File");
							alert1.setHeaderText("Are you sure want to move this file to the Recycle Bin?");

							// option != null.
							Optional<ButtonType> option = alert1.showAndWait();

							if (option.get() == ButtonType.OK) {
								taskPosition MR = table_Po.getSelectionModel().getSelectedItem();
								boolean checkDelete = daTaskPosition.delete(MR);

								if (checkDelete == true) {
									alert.Success("Delete Task Position " + MR.getTaskString() + " of "
											+ MR.getPositionString());
									clean();
								}
							}

						});

						// update event

						editIcon.setOnMouseClicked((MouseEvent event) -> {

							taskPosition MR = table_Po.getSelectionModel().getSelectedItem();

							ID_TP.setText(String.valueOf(MR.getId()));
							Po_ID.setText(String.valueOf(MR.getPosition_id()));
							Task_iD.setText(String.valueOf(MR.getTask_id()));

						});
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

		action_P_col.setCellFactory(cellFoctory);
		// set page in table view
		int totalPage = (int) (Math.ceil(secondData.size() * 1.0 / ROWS_PER_PAGE));
		pagination1.setCurrentPageIndex(0);
		pagination1.setPageCount(totalPage);
		changeTableView1(0, ROWS_PER_PAGE);
		pagination1.currentPageIndexProperty()
				.addListener((observable, oldValue, newValue) -> changeTableView1(newValue.intValue(), ROWS_PER_PAGE));

	}

	// change table view Method
	private void changeTableView(int index, int limit) {
		int fromIndex = index * limit;
		int toIndex = Math.min(fromIndex + limit, masterData.size());
		int minIndex = Math.min(toIndex, filteredData.size());
		SortedList<task> sortedData = new SortedList<>(
				FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
		sortedData.comparatorProperty().bind(table_PandE.comparatorProperty());
		table_PandE.setItems(sortedData);
	}
	
	// change table view Method
	private void changeTableViewTask(int index, int limit) {
		int fromIndex = index * limit;
		int toIndex = Math.min(fromIndex + limit, masterDataTask.size());
		int minIndex = Math.min(toIndex, filteredData.size());
		SortedList<task> sortedData = new SortedList<>(
				FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
		sortedData.comparatorProperty().bind(table_task_staff.comparatorProperty());
		table_task_staff.setItems(sortedData);
	}

	private void changeTableView1(int index, int limit) {
		int fromIndex = index * limit;
		int toIndex = Math.min(fromIndex + limit, secondData.size());
		int minIndex = Math.min(toIndex, filteredDataTP.size());
		SortedList<taskPosition> sortedData = new SortedList<>(
				FXCollections.observableArrayList(filteredDataTP.subList(Math.min(fromIndex, minIndex), minIndex)));
		sortedData.comparatorProperty().bind(table_Po.comparatorProperty());
		table_Po.setItems(sortedData);
	}

	private LocalDate fomateDate(String string) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(string, formatter);
		return localDate;
	}

}
