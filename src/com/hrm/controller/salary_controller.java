package com.hrm.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
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
import com.hrm.model.beans.salary;
import com.hrm.model.business_objects.bo_principal;
import com.hrm.model.business_objects.bo_salary;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class salary_controller implements Initializable {

	public salary_controller() {
		// TODO Auto-generated constructor stub
	}

	private bo_salary dataDAO = new bo_salary();
	private static int ROWS_PER_PAGE = 8;
	private ObservableList<salary> masterData = FXCollections.observableArrayList();
	private FilteredList<salary> filteredData;

	@FXML
	private TableView<salary> table_salary;

	@FXML
	private TableColumn<salary, Integer> ID_col;

	@FXML
	private TableColumn<salary, String> name_col;

	@FXML
	private TableColumn<salary, String> money_col;

	@FXML
	private TableColumn<salary, String> reward_col;

	@FXML
	private TableColumn<salary, Integer> work_col;

	@FXML
	private TableColumn<salary, DatePicker> timepay_col;

	@FXML
	private TableColumn<salary, String> whopay_col;

	@FXML
	private TableColumn<salary, DatePicker> createat_col;

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
	private Button search_btn;

	@FXML
	private TextField ID_field;

	@FXML
	private TextField search_field;

	@FXML
	void Print(ActionEvent event) {

	}

	@FXML
	void Refresh(ActionEvent event) {
		clean();
	}

	@FXML
	void Save(ActionEvent event) {
		if (ID_field.getText().equals("")) {
			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Save File");
			alert1.setHeaderText("Are you sure Save .");

			// option != null.
			Optional<ButtonType> option = alert1.showAndWait();

			if (option.get() == ButtonType.OK) {

				salary Salary = new salary();
				Salary.setEmployee_id(Integer.parseInt(name_field.getText()));
				Salary.setWho_pay(whopay_field.getText());
				Salary.setCreated_at(Date.valueOf(createat_field.getValue()));
				Salary.setTime_to_pay(Date.valueOf(timepay_fileld.getValue()));
				Salary.setDescription(descip_field.getText());
				Salary.setValue_money(Integer.parseInt(money_field.getText()));
				Salary.setValue_money_reward(Integer.parseInt(reward_field.getText()));
				Salary.setWorking_day(Integer.parseInt(work_field.getText()));

				boolean checkSave = dataDAO.save(Salary);
				if (checkSave == true) {
					alert.Success("Add Salary ");
					clean();
				} else {
					alert.Error();
				}
			}

		} else {
			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Delete File");
			alert1.setHeaderText("Are you sure Update " + " at " + ID_field.getText());

			// option != null.
			Optional<ButtonType> option = alert1.showAndWait();

			if (option.get() == ButtonType.OK) {
				salary Salary = new salary();

				Salary.setEmployee_id(Integer.parseInt(name_field.getText()));
				Salary.setWho_pay(whopay_field.getText());
				Salary.setCreated_at(Date.valueOf(createat_field.getValue()));
				Salary.setTime_to_pay(Date.valueOf(timepay_fileld.getValue()));
				Salary.setDescription(descip_field.getText());
				Salary.setValue_money(Integer.parseInt(money_field.getText()));
				Salary.setValue_money_reward(Integer.parseInt(reward_field.getText()));
				Salary.setWorking_day(Integer.parseInt(work_field.getText()));
				Salary.setId(Integer.parseInt(ID_field.getText()));

				boolean checkSave = dataDAO.update(Salary);
				if (checkSave == true) {
					alert.Success("Update Salary ");
					clean();
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

		createat_field.setConverter(converter);
		timepay_fileld.setConverter(converter);
		getList();
		InserTableView();
	}

	public void clean() {
		search_field.setText("");
		descip_field.setText(" ");
		whopay_field.setText("");
		reward_field.setText("");
		timepay_fileld.setValue(LocalDate.now());
		money_field.setText("");
		name_field.setText("");
		ID_field.setText("");
		createat_field.setValue(LocalDate.now());
		work_field.setText("");

		createat_field.setValue(LocalDate.now());
		getList();
		InserTableView();

	}

	public void InserTableView() {
		filteredData = new FilteredList<>(masterData, p -> true);
		// Search
		search_field.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Salary -> Salary.getEmployee().toLowerCase().contains(newValue.toLowerCase())
					|| Salary.getWho_pay().toLowerCase().contains(newValue.toLowerCase()));
			changeTableView(0, masterData.size());
			changeTableView(0, masterData.size());
		});
		// add value into cell

		ID_col.setCellValueFactory(new PropertyValueFactory<>("id"));
		name_col.setCellValueFactory(new PropertyValueFactory<>("employee"));
		money_col.setCellValueFactory(new PropertyValueFactory<>("moneyString"));
		reward_col.setCellValueFactory(new PropertyValueFactory<>("rewardString"));
		work_col.setCellValueFactory(new PropertyValueFactory<>("working_day"));
		timepay_col.setCellValueFactory(new PropertyValueFactory<>("time_to_pay"));
		whopay_col.setCellValueFactory(new PropertyValueFactory<>("who_pay"));
		createat_col.setCellValueFactory(new PropertyValueFactory<>("created_at"));
		descip_col.setCellValueFactory(new PropertyValueFactory<>("description"));

		// add cell of button edit

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
						deleteIcon.setOnMouseClicked((MouseEvent event) -> {
							// Alert delete
							Alert alert1 = new Alert(AlertType.CONFIRMATION);
							alert1.setTitle("Delete File");
							alert1.setHeaderText("Are you sure want to move this file to the Recycle Bin?");

							// option != null.
							Optional<ButtonType> option = alert1.showAndWait();

							if (option.get() == ButtonType.OK) {
								salary Salary = table_salary.getSelectionModel().getSelectedItem();
								boolean checkDelete = dataDAO.delete(Salary);

								if (checkDelete == true) {
									alert.Success("Delete Salary at  " + Salary.getId() + " ");
									clean();
								}
							}

						});

						// update event

						editIcon.setOnMouseClicked((MouseEvent event) -> {

							salary Salary = table_salary.getSelectionModel().getSelectedItem();

							int index$ = Salary.getMoneyString().indexOf("$");
							int index$1 = Salary.getRewardString().indexOf("$");

							ID_field.setText(String.valueOf(Salary.getId()));
							name_field.setText(String.valueOf(Salary.getEmployee_id()));
							descip_field.setText(Salary.getDescription());
							work_field.setText(String.valueOf(Salary.getWorking_day()));
							timepay_fileld.setValue(formatDate(String.valueOf(Salary.getTime_to_pay())));
							createat_field.setValue(formatDate(String.valueOf(Salary.getCreated_at())));
							money_field.setText(Salary.getMoneyString().substring(0, index$));
							reward_field.setText(Salary.getRewardString().substring(0, index$1));
							whopay_field.setText(Salary.getWho_pay());

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
		pagiaton.setCurrentPageIndex(0);
		pagiaton.setPageCount(totalPage);
		changeTableView(0, ROWS_PER_PAGE);
		pagiaton.currentPageIndexProperty()
				.addListener((observable, oldValue, newValue) -> changeTableView(newValue.intValue(), ROWS_PER_PAGE));
	}

	public void getList() {
		masterData = dataDAO.getAll();
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

	private LocalDate formatDate(String string) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(string, formatter);
		return localDate;
	}

}