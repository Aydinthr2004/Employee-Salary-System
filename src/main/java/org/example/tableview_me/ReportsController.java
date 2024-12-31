package org.example.tableview_me;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReportsController {

    @FXML
    private Button readFinancialButton;

    @FXML
    private Button readEngineeringButton;

    @FXML
    private Button readCommerceButton;

    @FXML
    private TableView<Employee> table_usersFinancial;

    @FXML
    private TableColumn<Employee, String> col_idFinancial;

    @FXML
    private TableColumn<Employee, String> col_usernameFinancial;

    @FXML
    private TableColumn<Employee, Double> col_monthlyWageFinancial;

    @FXML
    private TableColumn<Employee, Double> col_overtimeHoursFinancial;

    @FXML
    private TableColumn<Employee, Boolean> col_isManagerFinancial;

    @FXML
    private TableColumn<Employee, Boolean> col_commissionFinancial;

    @FXML
    private TableColumn<Employee, String> col_departmentFinancial;

    @FXML
    private TableColumn<Employee, String> col_statueFinancial;

    @FXML
    private TableColumn<Employee, Double> col_totalSalaryFinancial;

    @FXML
    private TableColumn<Employee, String> col_timeFinancial;

    @FXML
    private TableView<Employee> table_usersEngineering;

    @FXML
    private TableColumn<Employee, String> col_idEngineering;

    @FXML
    private TableColumn<Employee, String> col_usernameEngineering;

    @FXML
    private TableColumn<Employee, Double> col_monthlyWageEngineering;

    @FXML
    private TableColumn<Employee, Double> col_overtimeHoursEngineering;

    @FXML
    private TableColumn<Employee, Boolean> col_isManagerEngineering;

    @FXML
    private TableColumn<Employee, Boolean> col_commissionEngineering;

    @FXML
    private TableColumn<Employee, String> col_departmentEngineering;

    @FXML
    private TableColumn<Employee, String> col_statueEngineering;

    @FXML
    private TableColumn<Employee, Double> col_totalSalaryEngineering;

    @FXML
    private TableColumn<Employee, String> col_timeEngineering;

    @FXML
    private TableView<Employee> table_usersCommerce;

    @FXML
    private TableColumn<Employee, String> col_idCommerce;

    @FXML
    private TableColumn<Employee, String> col_usernameCommerce;

    @FXML
    private TableColumn<Employee, Double> col_monthlyWageCommerce;

    @FXML
    private TableColumn<Employee, Double> col_overtimeHoursCommerce;

    @FXML
    private TableColumn<Employee, Boolean> col_isManagerCommerce;

    @FXML
    private TableColumn<Employee, Boolean> col_commissionCommerce;

    @FXML
    private TableColumn<Employee, String> col_departmentCommerce;

    @FXML
    private TableColumn<Employee, String> col_statueCommerce;

    @FXML
    private TableColumn<Employee, Double> col_totalSalaryCommerce;

    @FXML
    private TableColumn<Employee, String> col_timeCommerce;

    private List<Employee> employeeDataFinancial = new ArrayList<>();
    private List<Employee> employeeDataEngineering = new ArrayList<>();
    private List<Employee> employeeDataCommerce = new ArrayList<>();

    private int currentEmployeeIndexFinancial = 0;
    private int currentEmployeeIndexEngineering = 0;
    private int currentEmployeeIndexCommerce = 0;

    @FXML
    void readEmployeeDataFinancial(ActionEvent event) {
        readEmployeeData("Financial.txt", employeeDataFinancial, currentEmployeeIndexFinancial, table_usersFinancial);
    }

    @FXML
    void readEmployeeDataEngineering(ActionEvent event) {
        readEmployeeData("Engineering.txt", employeeDataEngineering, currentEmployeeIndexEngineering, table_usersEngineering);
    }

    @FXML
    void readEmployeeDataCommerce(ActionEvent event) {
        readEmployeeData("Commerce.txt", employeeDataCommerce, currentEmployeeIndexCommerce, table_usersCommerce);
    }

    private void readEmployeeData(String fileName, List<Employee> employeeData, int currentEmployeeIndex, TableView<Employee> tableView) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            List<String> employeeInfo = new ArrayList<>();
            String line;

            // Read the next 10 lines for the current employee
            for (int i = 0; i < 10; i++) {
                line = reader.readLine();
                if (line != null) {
                    employeeInfo.add(line);
                } else {
                    // End of file reached
                    System.out.println("End of file reached. No more employees to read.");
                    return;
                }
            }

            // Create and add employee object to data
            Employee employee = createEmployeeFromData(employeeInfo);
            employeeData.add(employee);

            // Update table view with the newly added employee
            tableView.getItems().setAll(employeeData.subList(currentEmployeeIndex, currentEmployeeIndex + 1));
            currentEmployeeIndex++; // Move to the next employee

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private Employee createEmployeeFromData(List<String> employeeInfo) {
        // Assuming the file data is in the same order as the columns in the table view
        String id = employeeInfo.get(0);
        String username = employeeInfo.get(1);
        double monthlyWage = Double.parseDouble(employeeInfo.get(2));
        double overtimeHours = Double.parseDouble(employeeInfo.get(3));
        boolean isManager = Boolean.parseBoolean(employeeInfo.get(4));
        boolean commission = Boolean.parseBoolean(employeeInfo.get(5));
        String department = employeeInfo.get(6);
        String statue = employeeInfo.get(7);
        double totalSalary = Double.parseDouble(employeeInfo.get(8));
        LocalDateTime time = LocalDateTime.parse(employeeInfo.get(9), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return new Employee(id, username, monthlyWage, overtimeHours, isManager, commission, department, statue, totalSalary, time);
    }

    @FXML
    public void initialize() {
        // Set up the table columns for Financial
        col_idFinancial.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_usernameFinancial.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_monthlyWageFinancial.setCellValueFactory(new PropertyValueFactory<>("monthlyWage"));
        col_overtimeHoursFinancial.setCellValueFactory(new PropertyValueFactory<>("overtimeHours"));
        col_isManagerFinancial.setCellValueFactory(new PropertyValueFactory<>("isManager"));
        col_commissionFinancial.setCellValueFactory(new PropertyValueFactory<>("commission"));
        col_departmentFinancial.setCellValueFactory(new PropertyValueFactory<>("department"));
        col_statueFinancial.setCellValueFactory(new PropertyValueFactory<>("statue"));
        col_totalSalaryFinancial.setCellValueFactory(new PropertyValueFactory<>("totalSalary"));
        col_timeFinancial.setCellValueFactory(new PropertyValueFactory<>("time"));

        // Set up the table columns for Engineering
        col_idEngineering.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_usernameEngineering.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_monthlyWageEngineering.setCellValueFactory(new PropertyValueFactory<>("monthlyWage"));
        col_overtimeHoursEngineering.setCellValueFactory(new PropertyValueFactory<>("overtimeHours"));
        col_isManagerEngineering.setCellValueFactory(new PropertyValueFactory<>("isManager"));
        col_commissionEngineering.setCellValueFactory(new PropertyValueFactory<>("commission"));
        col_departmentEngineering.setCellValueFactory(new PropertyValueFactory<>("department"));
        col_statueEngineering.setCellValueFactory(new PropertyValueFactory<>("statue"));
        col_totalSalaryEngineering.setCellValueFactory(new PropertyValueFactory<>("totalSalary"));
        col_timeEngineering.setCellValueFactory(new PropertyValueFactory<>("time"));

        // Set up the table columns for Commerce
        col_idCommerce.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_usernameCommerce.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_monthlyWageCommerce.setCellValueFactory(new PropertyValueFactory<>("monthlyWage"));
        col_overtimeHoursCommerce.setCellValueFactory(new PropertyValueFactory<>("overtimeHours"));
        col_isManagerCommerce.setCellValueFactory(new PropertyValueFactory<>("isManager"));
        col_commissionCommerce.setCellValueFactory(new PropertyValueFactory<>("commission"));
        col_departmentCommerce.setCellValueFactory(new PropertyValueFactory<>("department"));
        col_statueCommerce.setCellValueFactory(new PropertyValueFactory<>("statue"));
        col_totalSalaryCommerce.setCellValueFactory(new PropertyValueFactory<>("totalSalary"));
        col_timeCommerce.setCellValueFactory(new PropertyValueFactory<>("time"));
    }

    // Employee class to hold the employee data
    public class Employee {
        private String id;
        private String username;
        private double monthlyWage;
        private double overtimeHours;
        private boolean isManager;
        private boolean commission;
        private String department;
        private String statue;
        private double totalSalary;
        private LocalDateTime time;

        public Employee(String id, String username, double monthlyWage, double overtimeHours,
                        boolean isManager, boolean commission, String department, String statue,
                        double totalSalary, LocalDateTime time) {
            this.id = id;
            this.username = username;
            this.monthlyWage = monthlyWage;
            this.overtimeHours = overtimeHours;
            this.isManager = isManager;
            this.commission = commission;
            this.department = department;
            this.statue = statue;
            this.totalSalary = totalSalary;
            this.time = time;
        }

        // Getters and Setters
        public String getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public double getMonthlyWage() {
            return monthlyWage;
        }

        public double getOvertimeHours() {
            return overtimeHours;
        }

        public boolean isManager() {
            return isManager;
        }

        public boolean isCommission() {
            return commission;
        }

        public String getDepartment() {
            return department;
        }

        public String getStatue() {
            return statue;
        }

        public double getTotalSalary() {
            return totalSalary;
        }

        public LocalDateTime getTime() {
            return time;
        }
    }
}