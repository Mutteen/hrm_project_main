package com.hrm.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.Normalizer.Form;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.management.loading.PrivateClassLoader;

import com.hrm.assets.lib.alert;
import com.hrm.model.beans.department;
import com.hrm.model.business_objects.bo_department;
import com.hrm.model.data_access_object.departmentDAO;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class department_controller implements Initializable {

	public department_controller() {
		// TODO Auto-generated constructor stub
	}

	private bo_department dataDao = new bo_department();
	private ObservableList<department> masterData = FXCollections.observableArrayList();

	private static int ROWS_PER_PAGE = 8;
	private FilteredList<department> filteredData;
	@FXML
	private TableView<department> table_department;

	@FXML
	private TableColumn<department, Integer> ID_col;

	@FXML
	private TableColumn<department, String> department_name_col;

	@FXML
	private TableColumn<department, DatePicker> create_at_col;

	@FXML
	private TableColumn<department, String> des_col;

	@FXML
	private TableColumn<department, String> action_col;

	@FXML
	private Button save_btn;

	@FXML
	private Button print_btn;

	@FXML
	private Button refresh_btn;

	@FXML
	private Pagination pagination;

	@FXML
	private TextArea description_field;

	@FXML
	private TextField status_field;

	@FXML
	private TextField depast_name;
	@FXML
	private TextField depast_name1;

	@FXML
	private DatePicker create_at_field;

	@FXML
	private TextField search_field;

	@FXML
	private Button search_btn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		// format Datepicker to Dale.sql
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
		//
		// 1
		getList();
		InserTableView();

	}

	@FXML
	void Print(ActionEvent event) {

	}

	@FXML
	void Refresh(ActionEvent event) {
		clean();

	}

	private void clean() {
		create_at_field.setValue(LocalDate.now());
		description_field.setText("");
		depast_name.setText("");
		depast_name1.setText("");
		search_field.setText("");
		getList();
		InserTableView();
	}

	public void getList() {
		masterData = dataDao.getAll();

	}

	public void InserTableView() {
		filteredData = new FilteredList<>(masterData, p -> true);
		// Search
		search_field.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(
					Department -> Department.getDepartment_name().toLowerCase().contains(newValue.toLowerCase()));
			changeTableView(0, masterData.size());
		});
		// add value into cell

		ID_col.setCellValueFactory(new PropertyValueFactory<>("id"));

		department_name_col.setCellValueFactory(new PropertyValueFactory<>("department_name"));
		create_at_col.setCellValueFactory(new PropertyValueFactory<>("created_at"));
		des_col.setCellValueFactory(new PropertyValueFactory<>("description"));

		// add cell of button edit

		Callback<TableColumn<department, String>, TableCell<department, String>> cellFoctory = (
				TableColumn<department, String> param) -> {
			// make cell containing buttons
			final TableCell<department, String> cell = new TableCell<department, String>() {
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
								department Department = table_department.getSelectionModel().getSelectedItem();
								boolean checkDelete = dataDao.delete(Department);

								if (checkDelete == true) {

									alert.Success("Delete Department " + Department.getId() + " ");

									clean();
								}
							}

						});

						// update event

						editIcon.setOnMouseClicked((MouseEvent event) -> {

							department Department = table_department.getSelectionModel().getSelectedItem();
							depast_name.setText(Department.getDepartment_name());

							depast_name1.setText(String.valueOf(Department.getId()));

							// setdatepciker fomat date sql
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
							LocalDate localDate = LocalDate.parse(String.valueOf(Department.getCreated_at()),
									formatter);

							create_at_field.setValue(formatDate(String.valueOf(Department.getCreated_at())));
							description_field.setText(Department.getDescription());

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

		action_col.setCellFactory(cellFoctory);
		// set page in table view
		int totalPage = (int) (Math.ceil(masterData.size() * 1.0 / ROWS_PER_PAGE));
		pagination.setCurrentPageIndex(0);
		pagination.setPageCount(totalPage);
		changeTableView(0, ROWS_PER_PAGE);
		pagination.currentPageIndexProperty()
				.addListener((observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));
	}

	@FXML
	void SaveDepartment(ActionEvent event) {

		if (depast_name1.getText().equals("")) {
			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Delete File");
			alert1.setHeaderText("Are you sure Save " + depast_name.getText() + ".");

			// option != null.
			Optional<ButtonType> option = alert1.showAndWait();

			if (option.get() == ButtonType.OK) {
				department Department = new department();
				Department.setDepartment_name(depast_name.getText());
				Department.setCreated_at(Date.valueOf(create_at_field.getValue()));
				Department.setDescription(description_field.getText());

				boolean checkSave = dataDao.save(Department);
				if (checkSave == true) {
					alert.Success("Add Department ");
					clean();
				} else {
					alert.Error();
				}
			}

		} else {
			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Delete File");
			alert1.setHeaderText("Are you sure Update " + depast_name.getText() + " at " + depast_name1.getText());

			// option != null.
			Optional<ButtonType> option = alert1.showAndWait();

			if (option.get() == ButtonType.OK) {
				department Department = new department();

				Department.setId(Integer.parseInt(depast_name1.getText()));

				Department.setDepartment_name(depast_name.getText());
				Department.setCreated_at(Date.valueOf(create_at_field.getValue()));
				Department.setDescription(description_field.getText());

				boolean checkSave = dataDao.update(Department);
				if (checkSave == true) {
					alert.Success("Update Department ");
					clean();
				} else {
					alert.Error();
				}
			}

		}
	}

	@FXML
	void SearchDepartment(ActionEvent event) {

	}

	// change table view Method
	private void changeTableView(int index, int limit) {
		int fromIndex = index * limit;
		int toIndex = Math.min(fromIndex + limit, masterData.size());
		int minIndex = Math.min(toIndex, filteredData.size());
		SortedList<department> sortedData = new SortedList<>(
				FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
		sortedData.comparatorProperty().bind(table_department.comparatorProperty());
		table_department.setItems(sortedData);
	}

	private LocalDate formatDate(String string) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(string, formatter);
		return localDate;
	}

}
