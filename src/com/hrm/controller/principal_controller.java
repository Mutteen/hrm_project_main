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
import com.hrm.model.beans.employee;
import com.hrm.model.beans.employee_search;
import com.hrm.model.beans.principal;
import com.hrm.model.business_objects.bo_principal;
import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;

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
import javafx.scene.control.ScrollBar;
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

public class principal_controller implements Initializable {

	public principal_controller() {
		// TODO Auto-generated constructor stub
	}

	@FXML
	private TableView<principal> table_principal;

	@FXML
	private TableColumn<principal, Integer> ID_col;

	@FXML
	private TableColumn<principal, String> depart_col;

	@FXML
	private TableColumn<principal, String> emname_col;

	@FXML
	private TableColumn<principal, String> money_col;

	@FXML
	private TableColumn<principal, String> type_col;

	@FXML
	private TableColumn<principal, DatePicker> date_col;

	@FXML
	private TableColumn<principal, DatePicker> create_col;

	@FXML
	private TableColumn<principal, String> descrip_col;

	@FXML
	private TableColumn<principal, String> action_col;

	@FXML
	private Button save_btn;

	@FXML
	private Button print_btn;

	@FXML
	private Button refresh_btn;

	@FXML
	private Pagination pagination;

	@FXML
	private TextField money_ffield;

	@FXML
	private TextField type_field;

	@FXML
	private TextField id_field;

	@FXML
	private DatePicker date_field;

	@FXML
	private DatePicker creat_field;

	@FXML
	private TextField emname_field;

	@FXML
	private TextArea description_field;

	@FXML
	private TableView<employee_search> table_em_search;

	@FXML
	private TableColumn<employee_search, Integer> emID_col;

	@FXML
	private TableColumn<employee_search, String> employee_col;

	@FXML
	private TableColumn<employee_search, String> department_S_col;

	@FXML
	private TableColumn<employee_search, String> avatar_col;
	@FXML

	private TableColumn<employee_search, String> DOB_col;

	@FXML
	private TextField search_em_field;

	@FXML
	private Button edit_btn;

	@FXML
	private TextField search_field;

	private bo_principal dataDao = new bo_principal();
	private ObservableList<principal> masterData = FXCollections.observableArrayList();

	private ObservableList<employee_search> DataSeach = FXCollections.observableArrayList();

	private static int ROWS_PER_PAGE = 8;
	private FilteredList<principal> filteredData;
	private FilteredList<employee_search> filteredDataEm;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
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

		creat_field.setConverter(converter);
		date_field.setConverter(converter);

		//
		// 1
		getList();
		InserTableView();
		tableSearch();

	}

	public void getList() {
		masterData = dataDao.getList();
		DataSeach = dataDao.getSearchs();
	}

	private void clean() {
		creat_field.setValue(LocalDate.now());
		description_field.setText("");
		id_field.setText("");
		money_ffield.setText("");
		date_field.setValue(LocalDate.now());
		search_field.setText("");
		search_em_field.setText("");
		emname_field.setText("");
		type_field.setText("");

		getList();
		InserTableView();
		tableSearch();

	}

	@FXML
	void AddPrinciple(ActionEvent event) {
		if (id_field.getText().equals("")) {
			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Save File");
			alert1.setHeaderText("Are you sure Save " + money_ffield.getText() + ".");

			// option != null.
			Optional<ButtonType> option = alert1.showAndWait();

			if (option.get() == ButtonType.OK) {

				principal Principal = new principal();

				Principal.setEmployee_id(Integer.parseInt(emname_field.getText()));
				Principal.setValue_money(Integer.parseInt(money_ffield.getText()));
				Principal.setDate_principal(Date.valueOf(date_field.getValue()));
				Principal.setCreated_at(Date.valueOf(creat_field.getValue()));
				Principal.setDescription(description_field.getText());
				Principal.setType(type_field.getText());

				boolean checkSave = dataDao.save(Principal);
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
			alert1.setHeaderText("Are you sure Update " + " at " + id_field.getText());

			// option != null.
			Optional<ButtonType> option = alert1.showAndWait();

			if (option.get() == ButtonType.OK) {
				principal Principal = new principal();
				Principal.setId(Integer.parseInt(id_field.getText()));
				Principal.setEmployee_id(Integer.parseInt(emname_field.getText()));
				Principal.setValue_money(Integer.parseInt(money_ffield.getText()));
				Principal.setDate_principal(Date.valueOf(date_field.getValue()));
				Principal.setCreated_at(Date.valueOf(creat_field.getValue()));
				Principal.setDescription(description_field.getText());
				Principal.setType(type_field.getText());

				boolean checkSave = dataDao.update(Principal);
				if (checkSave == true) {
					alert.Success("Update Position ");
					clean();
				} else {
					alert.Error();
				}
			}

		}
	}

	@FXML
	void EditPrinciple(ActionEvent event) {

	}

	@FXML
	void Print(ActionEvent event) {

	}

	@FXML
	void Refresh(ActionEvent event) {
		clean();
	}

	public void tableSearch() {
		// Search Employee
		filteredDataEm = new FilteredList<>(DataSeach, p -> true);
		// Search
		search_em_field.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredDataEm.setPredicate(Em -> Em.getDepartment().toLowerCase().contains(newValue.toLowerCase())
					|| Em.getFullname().toLowerCase().contains(newValue.toLowerCase()));
			changeTableSearch(0, DataSeach.size());
		});
		emID_col.setCellValueFactory(new PropertyValueFactory<>("id"));
		employee_col.setCellValueFactory(new PropertyValueFactory<>("fullname"));
		department_S_col.setCellValueFactory(new PropertyValueFactory<>("department"));
		DOB_col.setCellValueFactory(new PropertyValueFactory<>("DOB"));
		avatar_col.setCellValueFactory(new PropertyValueFactory<>("avatar"));
		changeTableSearch(0, DataSeach.size());

	}

	public void InserTableView() {
		filteredData = new FilteredList<>(masterData, p -> true);
		// Search
		search_field.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(
					Principal -> Principal.getDepartment_name().toLowerCase().contains(newValue.toLowerCase())
							|| Principal.getEmployee_name().toLowerCase().contains(newValue.toLowerCase()));
			changeTableView(0, masterData.size());
			changeTableView(0, masterData.size());
		});
		// add value into cell

		ID_col.setCellValueFactory(new PropertyValueFactory<>("Id"));
		depart_col.setCellValueFactory(new PropertyValueFactory<>("department_name"));
		emname_col.setCellValueFactory(new PropertyValueFactory<>("employee_name"));
		money_col.setCellValueFactory(new PropertyValueFactory<>("getMoneyString"));
		type_col.setCellValueFactory(new PropertyValueFactory<>("type"));
		date_col.setCellValueFactory(new PropertyValueFactory<>("date_principal"));
		create_col.setCellValueFactory(new PropertyValueFactory<>("created_at"));
		descrip_col.setCellValueFactory(new PropertyValueFactory<>("description"));

		// add cell of button edit

		Callback<TableColumn<principal, String>, TableCell<principal, String>> cellFoctory = (
				TableColumn<principal, String> param) -> {
			// make cell containing buttons
			final TableCell<principal, String> cell = new TableCell<principal, String>() {
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
								principal Principal = table_principal.getSelectionModel().getSelectedItem();
								boolean checkDelete = dataDao.delete(Principal);

								if (checkDelete == true) {
									alert.Success("Delete Principal at  " + Principal.getId() + " ");
									clean();
								}
							}

						});

						// update event

						editIcon.setOnMouseClicked((MouseEvent event) -> {

							principal Principal = table_principal.getSelectionModel().getSelectedItem();

							int index$ = Principal.getGetMoneyString().indexOf("$");
							money_ffield.setText(Principal.getGetMoneyString().substring(0, index$));
							type_field.setText(String.valueOf(Principal.getType()));
							id_field.setText(String.valueOf(Principal.getId()));
							// setdatepciker fomat date sql
							date_field.setValue(formatDate(String.valueOf(Principal.getDate_principal())));
							creat_field.setValue(formatDate(String.valueOf(Principal.getCreated_at())));
							emname_field.setText(String.valueOf(Principal.getEmployee_id()));
							description_field.setText(Principal.getDescription());

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

	// change table view Method
	private void changeTableView(int index, int limit) {
		int fromIndex = index * limit;
		int toIndex = Math.min(fromIndex + limit, masterData.size());
		int minIndex = Math.min(toIndex, filteredData.size());
		SortedList<principal> sortedData = new SortedList<>(
				FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
		sortedData.comparatorProperty().bind(table_principal.comparatorProperty());
		table_principal.setItems(sortedData);
	}

	// change table search employee
	private void changeTableSearch(int index, int limit) {
		int fromIndex = index * limit;
		int toIndex = Math.min(fromIndex + limit, DataSeach.size());
		int minIndex = Math.min(toIndex, filteredDataEm.size());
		SortedList<employee_search> sortedData = new SortedList<>(
				FXCollections.observableArrayList(filteredDataEm.subList(Math.min(fromIndex, minIndex), minIndex)));
		sortedData.comparatorProperty().bind(table_em_search.comparatorProperty());
		table_em_search.setItems(sortedData);
	}

	private LocalDate formatDate(String string) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(string, formatter);
		return localDate;
	}

}
