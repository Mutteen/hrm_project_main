package com.hrm.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;

import com.hrm.assets.lib.alert;
import com.hrm.assets.lib.path_image;
import com.hrm.model.beans.department;
import com.hrm.model.beans.employee;
import com.hrm.model.beans.module;
import com.hrm.model.beans.principal;
import com.hrm.model.beans.role;
import com.hrm.model.business_objects.bo_department;
import com.hrm.model.business_objects.bo_employee;
import com.hrm.model.business_objects.bo_roles;
import com.mysql.cj.protocol.a.result.ResultsetRowsStatic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

public class createEmployee_controller implements Initializable {

	public createEmployee_controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// add image null
		ImageView imgImageView1 = new ImageView();

		// demo addimage
		Image image1 = new Image("./com/hrm/assets/avatar/avatarnul.png");
		imgImageView1.setImage(image1);
		imgImageView1.setFitWidth(150);
		imgImageView1.setFitHeight(150);
		imgImageView1.scaleXProperty();
		imgImageView1.scaleYProperty();
		imgImageView1.setSmooth(true);
		imgImageView1.setCache(true);
		image_pane.setCenter(imgImageView1);
		getList();
		role.setConverter(new StringConverter<role>() {
			@Override
			public String toString(role object) {
				return object.getRole_name();
			}

			@Override
			public role fromString(String string) {
				return null;
			}
		});
		department_file.setConverter(new StringConverter<department>() {
			@Override
			public String toString(department object) {
				return object.getDepartment_name();
			}

			@Override
			public department fromString(String string) {
				return null;
			}
		});

		// add data combo box module and role
		role.setItems(roleList);
		department_file.setItems(departmentList);
		status_box.setItems(statuslList);
		role.valueProperty().addListener((obs, oldVal, newVal) -> {
			int selectionText = newVal.getId();
			role_id = selectionText;
		});
		department_file.valueProperty().addListener((obs, oldVal, newVal) -> {
			int selectionText = newVal.getDepartment_Id();
			depart_id = selectionText;
		});
		status_box.valueProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal.equals("disable")) {
				status_id = 1;
			} else {
				status_id = 0;
			}
		});

		//
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
		DOB.setConverter(converter);
		create_at.setConverter(converter);
//			createat_field.setConverter(converter);
		//
		// 1

	}

	static String imagepath;
	static File imagefileString;
	static int role_id;
	static int depart_id;
	static int status_id;
	private ObservableList<department> departmentList = FXCollections.observableArrayList();
	private ObservableList<role> roleList = FXCollections.observableArrayList();
	private ObservableList<String> statuslList = FXCollections.observableArrayList("disable ", "enable");
	private ObservableList<employee> employees = FXCollections.observableArrayList();
	private bo_department getDepartment = new bo_department();
	private bo_roles getRole = new bo_roles();
	private bo_employee getEmployee = new bo_employee();
	@FXML
	private Button save_btn;

	@FXML
	private Button refresh_btn;

	@FXML
	private Label Title;

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
	private ComboBox<department> department_file;

	@FXML
	private TextField gmail;

	@FXML
	private TextArea descip_field;

	@FXML
	private ComboBox<String> status_box;

	@FXML
	private ComboBox<role> role;

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
	private TextField password;
	@FXML
	private BorderPane image_pane;

	@FXML
	void AddAvatar(ActionEvent event) {
		ImageView imgImageView = new ImageView();
		FileChooser fileChooser = new FileChooser();
		// Set extension filter
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
		FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
		FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);
		imagefileString = file;

		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);

			imgImageView.setImage(image);
			imgImageView.setFitWidth(150);
			imgImageView.setFitHeight(150);
			imgImageView.scaleXProperty();
			imgImageView.scaleYProperty();
			imgImageView.setSmooth(true);
			imgImageView.setCache(true);
			image_pane.setCenter(imgImageView);
			FileInputStream fin = new FileInputStream(file);
			System.out.println(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];

			for (int readNum; (readNum = fin.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
			}
//            person_image = bos.toByteArray();

		} catch (IOException ex) {
			Logger.getLogger("ss");
		}
		/// hrm_project_main/src/com/hrm/assets/avatar
		String path = path_image.pathString();

		try {
			Files.copy(file.toPath(), (new File(path + file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("./com/hrm/assets/avatar/" + file.getName());
	}

	@FXML
	void Refresh(ActionEvent event) {
		clean();

	}

	public void clean() {
		first_name.setText("");
		last_name.setText("");
		middle_name.setText("");
		DOB.setValue(LocalDate.now());
		department_file.setValue(null);
		gmail.setText("");
		descip_field.setText("");
		status_box.setValue(null);
		role.setValue(null);
		phone.setText("");
		user_name.setText("");
		address_field.setText("");
		create_at.setValue(LocalDate.now());
		on_leave.setText("");
		password.setText("");
		ImageView imgImageView1 = new ImageView();

		// demo addimage
		Image image1 = new Image("./com/hrm/assets/avatar/avatarnul.png");
		imgImageView1.setImage(image1);
		imgImageView1.setFitWidth(150);
		imgImageView1.setFitHeight(150);
		imgImageView1.scaleXProperty();
		imgImageView1.scaleYProperty();
		imgImageView1.setSmooth(true);
		imgImageView1.setCache(true);
		image_pane.setCenter(imgImageView1);
		getList();

	}

	public void getList() {
		roleList = getRole.getAll();
		departmentList = getDepartment.getAll();

	}

	@FXML
	void SaveEmployee(ActionEvent event) throws IOException {

		// option != null.
		Alert alert1 = new Alert(AlertType.CONFIRMATION);
		alert1.setTitle("Save File");
		alert1.setHeaderText("Are you sure Save " + first_name.getText() + ".");

		// option != null.
		Optional<ButtonType> option = alert1.showAndWait();

		if (option.get() == ButtonType.OK) {
			System.out.print(imagefileString);
			// save image
			String path = "D:\\project\\hrm_project_main\\src\\com\\hrm\\assets\\avatar\\";
			Files.copy(imagefileString.toPath(), (new File(path + imagefileString.getName())).toPath(),
					StandardCopyOption.REPLACE_EXISTING);
			imagepath = "./com/hrm/assets/avatar/" + imagefileString.getName();

			employee EM = new employee();
			EM.setAvatar(imagepath);
			EM.setFirst_name(first_name.getText());
			EM.setLast_name(last_name.getText());
			EM.setMiddle_name(middle_name.getText());
			EM.setDob(Date.valueOf(DOB.getValue()));
			EM.setDepartment_id(depart_id);
			EM.setRole_id(role_id);
			EM.setStatus(status_id);
			EM.setEmail(gmail.getText());
			EM.setAddress(address_field.getText());
			EM.setDescription(descip_field.getText());
			EM.setHire_date(Date.valueOf(create_at.getValue()));
			EM.setOn_leave(Integer.parseInt(on_leave.getText()));
			EM.setUsername(user_name.getText());
			EM.setPassword(password.getText());
			EM.setTelephone(phone.getText());

			boolean checkSave = getEmployee.save(EM);
			if (checkSave == true) {

				alert.Success("Add Employee ");
				clean();
			} else {
				alert.Error();
			}

		}
	}
}