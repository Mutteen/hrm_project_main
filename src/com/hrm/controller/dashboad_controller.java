package com.hrm.controller;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.hrm.model.beans.employee;
import com.hrm.model.beans.salary;
import com.hrm.model.data_access_object.employeeDao;
import com.hrm.model.data_access_object.salaryDao;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class dashboad_controller implements Initializable {
	 @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private Label lb_total_employee;

    @FXML
    private Label lb_avg_dob;

    @FXML
    private Label lb_total_female;

    @FXML
    private Label lb_total_male;
    
    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private PieChart pieChart;
	
	public dashboad_controller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lb_total_employee.setText("Total employee: \n           "+employeeDao.getTotal());
		lb_avg_dob.setText("Average age: \n    "+ employeeDao.getAvgDob());
		lb_total_male.setText("Total male: \n       "+ employeeDao.getTotalMale());
		lb_total_female.setText("Total female: \n         "+ employeeDao.getTotalFemale());
		initlineChart(); 
		initBarChart();
		initPieChart();

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initlineChart() {
		employeeDao employeeDao = new employeeDao();
		ObservableList<employee> listHire = employeeDao.getHireDate();
		ObservableList<employee> listTer =  employeeDao.getTerDate();
		
		
		lineChart.setTitle("Hire and Terminations");
		

		XYChart.Series new_hire = new XYChart.Series();
		new_hire.setName("New hire");
		
		for (employee employee : listHire) {
			new_hire.getData().add(new XYChart.Data(employee.getHire_month(), employee.getQuantity_employee()));
		}

		
		XYChart.Series termination = new XYChart.Series();
		termination.setName("Termination");
		
		for (employee employee : listTer) {
			termination.getData().add(new XYChart.Data(employee.getTermination_month(), employee.getQuantity_employee()));
		}
		
		lineChart.getData().addAll(new_hire, termination);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initBarChart() {
		ObservableList<salary> listData = salaryDao.getData();
		
		barChart.setTitle("Salary to be paid in a month");
		XYChart.Series series = new XYChart.Series();
		series.setName("Monthly Salary");
		
		for (salary salary: listData) {
			series.getData().add(new XYChart.Data(salary.getMonth_to_pay(), salary.getTotal_salary()));		
		}
		

		barChart.getData().add(series);

	}

	public void initPieChart(){
		ObservableList<employee> listData = employeeDao.getData(); 
		pieChart.setTitle("Quatity of department");
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

		for (employee employee : listData) {
			pieChartData.add(new PieChart.Data(employee.getDepartment().getDepartment_name(), employee.getDepartment_id()));
		}
					
		pieChartData.forEach(data ->
			data.nameProperty().bind(
					Bindings.concat(
							data.getName(), ": ", data.getPieValue()
					)
			)
		);
		
		pieChart.getData().addAll(pieChartData);
	}


	
}
