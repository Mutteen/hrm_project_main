package com.hrm.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.hrm.model.usersession;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;

public class home implements Initializable {
	@FXML
	public TreeView<String> menuTreeView;
	@FXML
	public Button btnInfo;
	@FXML
	private Label name_user;
	@FXML
	private Label timenow;
	@FXML
	public Button btnDashboard;
	@FXML
	public Button minimize;
	@FXML
	public Button btnEmployee;
	@FXML
	public Button btnDepartment;
	@FXML
	public BorderPane mainPane;

	@FXML
	public Button btnPrincipal;
	@FXML
	private AnchorPane main_pane;
	@FXML
	public Button btnSalary;
	@FXML
	public Button btnPosition;
	@FXML
	private Button logout_btn;
	@FXML
	private Button arrow_btn;
	@FXML
	private Button bars_btn;
	@FXML
	private AnchorPane nav_form;

	@FXML
	private AnchorPane mainCenter_form;
	@FXML
	private BorderPane minizius;

	@FXML
	private MenuItem profile_btn;

	@FXML
	private MenuItem signout_btn;

	@FXML
	private Text notifi_text;

	@FXML
	private Button btnSetting;

	public home() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initClock();
		name_user.setText("Hi " + usersession.getUserName() + "  ");
	}

	private void initClock() {

		Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			timenow.setText(LocalDateTime.now().format(formatter));
		}), new KeyFrame(Duration.seconds(1)));
		clock.setCycleCount(Animation.INDEFINITE);
		clock.play();

	}

	public void sliderArrow() {

		TranslateTransition slide = new TranslateTransition();

		slide.setDuration(Duration.seconds(.5));
		slide.setNode(nav_form);
		slide.setToX(-224);

		TranslateTransition slide1 = new TranslateTransition();

		slide1.setDuration(Duration.seconds(.5));
		slide1.setNode(mainCenter_form);
		slide1.setToX(-224 + 90);

//        TranslateTransition slide2 = new TranslateTransition();
//        slide2.setDuration(Duration.seconds(.5));
//        slide2.setNode(halfNav_form);
//        slide2.setToX(0);

		slide.setOnFinished((ActionEvent event) -> {

			arrow_btn.setVisible(false);
			bars_btn.setVisible(true);

		});

//        slide2.play();
		slide1.play();
		slide.play();

	}
//    Sugggoooiiii!! : ) 

	public void sliderBars() {

		TranslateTransition slide = new TranslateTransition();

		slide.setDuration(Duration.seconds(.5));
		slide.setNode(nav_form);
		slide.setToX(0);

		TranslateTransition slide1 = new TranslateTransition();

		slide1.setDuration(Duration.seconds(.5));
		slide1.setNode(mainCenter_form);
		slide1.setToX(0);

//        TranslateTransition slide2 = new TranslateTransition();
//        slide2.setDuration(Duration.seconds(.5));
//        slide2.setNode(halfNav_form);
//        slide2.setToX(-77);

		slide.setOnFinished((ActionEvent event) -> {

			arrow_btn.setVisible(true);
			bars_btn.setVisible(false);

		});
//
//        slide2.play();
		slide1.play();
		slide.play();
	}

	public void btnInfoOnAction(ActionEvent event) {

	}

	@FXML
	public void logout(ActionEvent event) {
		try {

			// TO SWAP FROM DASHBOARD TO LOGIN FORM

			Parent root = FXMLLoader.load(this.getClass().getResource("../view/login.fxml"));

			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

			Scene scene = new Scene(root);

			stage.setScene(scene);
			stage.show();
			usersession.cleanUserSession();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void Dashboard(ActionEvent event) {
		FxmlLoader oblectFxmlLoader = new FxmlLoader();
		AnchorPane viewAnchorPane = oblectFxmlLoader.getPane("dashboad");
		mainPane.setCenter(viewAnchorPane);

	}

	@FXML
	public void Employee(ActionEvent event) {
		FxmlLoader oblectFxmlLoader = new FxmlLoader();
		AnchorPane viewAnchorPane = oblectFxmlLoader.getPane("employee");
		mainPane.setCenter(viewAnchorPane);

	}

	@FXML
	public void Department(ActionEvent event) {
		FxmlLoader oblectFxmlLoader = new FxmlLoader();
		AnchorPane viewAnchorPane = oblectFxmlLoader.getPane("department");
		mainPane.setCenter(viewAnchorPane);
	}

	@FXML
	public void Position(ActionEvent event) {
		FxmlLoader oblectFxmlLoader = new FxmlLoader();
		AnchorPane viewAnchorPane = oblectFxmlLoader.getPane("postion");
		mainPane.setCenter(viewAnchorPane);
	}

	@FXML
	public void Principal(ActionEvent event) {
		FxmlLoader oblectFxmlLoader = new FxmlLoader();
		AnchorPane viewAnchorPane = oblectFxmlLoader.getPane("principal");
		mainPane.setCenter(viewAnchorPane);
	}

	@FXML
	public void ProfieView(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getResource("../view/profile.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML
	public void Salary(ActionEvent event) {
		FxmlLoader oblectFxmlLoader = new FxmlLoader();
		AnchorPane viewAnchorPane = oblectFxmlLoader.getPane("salary");
		mainPane.setCenter(viewAnchorPane);
	}

	@FXML
	public void Setting(ActionEvent event) {
		FxmlLoader oblectFxmlLoader = new FxmlLoader();
		AnchorPane viewAnchorPane = oblectFxmlLoader.getPane("setting");
		mainPane.setCenter(viewAnchorPane);
	}

	@FXML
	public void SignOut(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getResource("../view/login.fxml"));
		Stage owner = (Stage) signout_btn.getParentPopup().getOwnerWindow();
		Scene scene = new Scene(root);
		owner.setScene(scene);
		owner.show();
		usersession.cleanUserSession();
	}

	@FXML
	void sliderArrow(ActionEvent event) {

	}

}