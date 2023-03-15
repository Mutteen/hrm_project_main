package com.hrm.controller;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class home implements Initializable {
	@FXML
	public TreeView<String> menuTreeView;

	@FXML
	public Button btnInfo;

	@FXML
	public Button btnDashboard;

	@FXML
	public Button minimize;
	@FXML

	public Button btnEmployee;

	@FXML
	public Button btnDepartment;

	@FXML
	public Button btnPrincipal;

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

	public home() {
		// TODO Auto-generated constructor stub
	}

	public void btnInfoOnAction(ActionEvent event) {

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

	@FXML
	public void logout(ActionEvent event) {
		try {

			// TO SWAP FROM DASHBOARD TO LOGIN FORM

			Parent root = FXMLLoader.load(this.getClass().getResource("../view/login.fxml"));

			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

			Scene scene = new Scene(root);

			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
