
package com.hrm.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputFilter.Status;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import com.hrm.assets.lib.alert;
import com.hrm.assets.lib.path_image;
import com.hrm.model.beans.department;
import com.hrm.model.beans.employee;
import com.hrm.model.business_objects.bo_department;
import com.hrm.model.business_objects.bo_employee;
import com.hrm.model.business_objects.bo_modulerole;
import com.hrm.model.business_objects.bo_roles;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

import com.hrm.model.beans.role;

public class editEmployee_controller implements Initializable {

	public editEmployee_controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

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
		hire_date.setConverter(converter);
//			createat_field.setConverter(converter);
		//
		// 1
	}

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
	private BorderPane image_pane;
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
	private TextField user_name1;

	@FXML
	private TextArea address_field;

	@FXML
	private DatePicker hire_date;

	@FXML
	private TextField on_leave;
	@FXML
	private TextField ID_field;

	@FXML
	private TextField password;
	@FXML
	private BorderPane deomo;

	private static String imagepath;
	private static String imagepathold;
	private static File fileimage;
	private bo_roles dataRoles = new bo_roles();
	private bo_department dataDepartment = new bo_department();
	private boolean update;

	@FXML
	void AddAvatar(ActionEvent event) throws IOException {
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
		//
		fileimage = file;

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
//	            person_image = bos.toByteArray();

		} catch (IOException ex) {
			Logger.getLogger("ss");
		}

		// demo addimage

	}

	@FXML
	void Refresh(ActionEvent event) {
		clean();
		getList();
	}

	@FXML
	void SaveEmployee(ActionEvent event) throws IOException {
		// option != null.
		Alert alert1 = new Alert(AlertType.CONFIRMATION);
		alert1.setTitle("Save File");
		alert1.setHeaderText("Are you sure Update " + ID_field.getText() + ".");

		// option != null.
		Optional<ButtonType> option = alert1.showAndWait();

		if (option.get() == ButtonType.OK) {
			// setimage
			/// hrm_project_main/src/com/hrm/assets/avatar
			String path = path_image.pathString();
			if (fileimage != null) {
				Files.copy(fileimage.toPath(), (new File(path + fileimage.getName())).toPath(),
						StandardCopyOption.REPLACE_EXISTING);
				imagepath = "./com/hrm/assets/avatar/" + fileimage.getName();
			}
			employee EM = new employee();
			EM.setFirst_name(first_name.getText());
			EM.setLast_name(last_name.getText());
			EM.setMiddle_name(middle_name.getText());
			EM.setDob(Date.valueOf(DOB.getValue()));
			EM.setDepartment_id(depart_id);
			EM.setRole_id(role_id);
			if (status_box.getValue().equals("enable")) {
				EM.setStatus(0);
			} else {
				EM.setStatus(1);
			}

			EM.setEmail(gmail.getText());
			EM.setAddress(address_field.getText());
			EM.setDescription(descip_field.getText());
			EM.setHire_date(Date.valueOf(hire_date.getValue()));
			EM.setOn_leave(Integer.parseInt(on_leave.getText()));
			EM.setUsername(user_name1.getText());
			EM.setPassword(password.getText());
			EM.setTelephone(phone.getText());
			if (imagepath == null) {
				EM.setAvatar(imagepathold);
			} else {
				EM.setAvatar(imagepath);
			}
			EM.setId(Integer.parseInt(ID_field.getText()));

			boolean update = getEmployee.update(EM);
			if (update == true) {
				alert.Success("Update Employee ");
				clean();
			} else {
				alert.Error();
			}

		}
	}

	void setTextField(employee EE) {
		ID_field.setText(String.valueOf(EE.getId()));
		first_name.setText(EE.getFirst_name());
		last_name.setText(EE.getLast_name());
		middle_name.setText(EE.getMiddle_name());
		DOB.setValue(formatDate(String.valueOf(EE.getDob())));
//		depart_id);
//		role_id);
//		status_id);
//		module_field.setValue(datBo_modulerole.getRole());
//			if
		if (EE.getStatus() == 1) {
			status_box.setValue("disable");
		} else if (EE.getStatus() == 0) {
			status_box.setValue("enable");

		}
		department_file.setValue(dataDepartment.getDepartment(EE.getDepartment_id()));
		role.setValue(dataRoles.getRole(EE.getRole_id()));
		gmail.setText(EE.getEmail());
		address_field.setText(EE.getAddress());
		descip_field.setText(EE.getDescription());
		hire_date.setValue(formatDate(String.valueOf(EE.getHire_date())));
		on_leave.setText(String.valueOf(EE.getOn_leave()));
		user_name1.setText(EE.getUsername());
		password.setText(EE.getPassword());
		phone.setText(EE.getTelephone());
		//
		if (EE.getAvatar() == null) {
			ImageView imgImageView1 = new ImageView();
			imagepathold = "./com/hrm/assets/avatar/avatarnul.png";
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
		} else {
			ImageView imgImageView1 = new ImageView();
			imagepathold = EE.getAvatar();
			// demo addimage
			Image image1 = new Image(EE.getAvatar());
			imgImageView1.setImage(image1);
			imgImageView1.setFitWidth(150);
			imgImageView1.setFitHeight(150);
			imgImageView1.scaleXProperty();
			imgImageView1.scaleYProperty();
			imgImageView1.setSmooth(true);
			imgImageView1.setCache(true);
			image_pane.setCenter(imgImageView1);
		}

	}

	public void getList() {
		roleList = getRole.getAll();
		departmentList = getDepartment.getAll();

	}

	void setUpdate(boolean b) {
		this.update = b;

	}

	public void clean() {
		ImageView imgImageView1 = new ImageView();

		// demo addimage
		Image image1 = new Image(imagepathold);
		imgImageView1.setImage(image1);
		imgImageView1.setFitWidth(150);
		imgImageView1.setFitHeight(150);
		imgImageView1.scaleXProperty();
		imgImageView1.scaleYProperty();
		imgImageView1.setSmooth(true);
		imgImageView1.setCache(true);
		image_pane.setCenter(imgImageView1);

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
		user_name1.setText("");
		address_field.setText("");
		hire_date.setValue(LocalDate.now());
		on_leave.setText("");
		password.setText("");
		getList();

	}

	private LocalDate formatDate(String string) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(string, formatter);
		return localDate;
	}

}
