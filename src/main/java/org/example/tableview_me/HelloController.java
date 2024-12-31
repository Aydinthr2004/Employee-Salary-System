package org.example.tableview_me;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HelloController {


    @FXML
    private TableView<employee> table_users;

    @FXML
    private TableColumn<employee, String> col_id;

    @FXML
    private TableColumn<employee, String> col_username;

    @FXML
    private TableColumn<employee, String> col_salary;

    @FXML
    private TableColumn<employee, String> col_department;

    @FXML
    private TableColumn<employee, String> col_statue;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_username;

    @FXML
    private TextField txt_monthly;

    @FXML
    private ComboBox<String> cbo_department;

    @FXML
    private ComboBox<String> cbo_statue; // Use ComboBox instead of TextField
    ;

    @FXML
    private TextField txt_hourly;

    @FXML
    private CheckBox check_isManager;

    @FXML
    private CheckBox check_commission;

    @FXML
    private TextField addRandom;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField searchField;

    @FXML
    private Button randomButton;





    ObservableList<employee> employeeData = FXCollections.observableArrayList();
    private List<String> randomEmployeeData = new ArrayList<>();
    private int randomEmployeeIndex = 0;

    public void initialize() {
        // Set up TableView columns
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        col_department.setCellValueFactory(new PropertyValueFactory<>("department"));
        ObservableList<String> departmentOptions = FXCollections.observableArrayList(
                "Engineering", "Commerce", "Financial"
        );
        cbo_department.setItems(departmentOptions);
        cbo_department.getSelectionModel().selectFirst(); // Set the default selection




        col_statue.setCellValueFactory(new PropertyValueFactory<>("statue"));
        ObservableList<String> statueOptions = FXCollections.observableArrayList(
                "Active", "Fired", "Unpaid Vacation", "Retirement", "Paid Vacation"
        );
        cbo_statue.setItems(statueOptions);
        cbo_statue.getSelectionModel().selectFirst(); // Set the default selection

        table_users.setItems(employeeData);
        loadRandomEmployeeData();




    }
    private void loadRandomEmployeeData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("random.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                randomEmployeeData.add(line);
            } } catch (IOException e) {
            System.err.println("Error loading random employee data: " + e.getMessage());
        }
    }


    public void Add_users(ActionEvent actionEvent) {
        try {
            // Get values from input fields
            String id = txt_id.getText();
            String username = txt_username.getText();
            double monthly = Double.parseDouble(txt_monthly.getText());
            double hourly = Double.parseDouble(txt_hourly.getText());
            boolean isManager = check_isManager.isSelected();
            boolean commission = check_commission.isSelected();
            String department = cbo_department.getSelectionModel().getSelectedItem();
            String statue = cbo_statue.getSelectionModel().getSelectedItem(); // Corrected




            // Create new Employee object
            employee employee = new employee(id, username, department, statue, monthly, hourly , isManager , commission);


            int i=0;

            if(isManager && commission){
                employee.setSalary(monthly + hourly*10 + 500 + 0.20*monthly);
                i=i+1;
            }

            if(i==0 && isManager){
                employee.setSalary(monthly + hourly*10 + 500); // Calculate total salary and set it
                i=i+1;

            }
           if(i==0 && commission){
               employee.setSalary(monthly + hourly*10 + 0.20*monthly);
               i=i+1;

           }

           if(i==0)
               employee.setSalary(monthly + hourly*10);


            // Add to ObservableList
            employeeData.add(employee);

            // Save to txt file
            saveEmployeeToFile(employee);





            // Clear input fields
            txt_id.clear();
            txt_username.clear();
            txt_monthly.clear();
            txt_hourly.clear();
            check_isManager.setSelected(false);
            check_commission.setSelected(false);
            cbo_department.getSelectionModel().clearSelection();
            cbo_statue.getSelectionModel().clearSelection();







        } catch (NumberFormatException e) {
            System.err.println("Invalid ID format. Please enter a valid number.");
        }
    }

    public void Edit(ActionEvent actionEvent) {
        // Get the selected employee from the TableView
        employee selectedEmployee = table_users.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {
            // Get updated values from input fields
            String id = txt_id.getText();
            String username = txt_username.getText();
            double monthly = Double.parseDouble(txt_monthly.getText());
            double hourly = Double.parseDouble(txt_hourly.getText());
            boolean isManager = check_isManager.isSelected();
            boolean commission = check_commission.isSelected();
            String department = String.valueOf(cbo_department.getItems());
            String statue = String.valueOf(cbo_statue.getItems());
            cbo_department.getItems();
            cbo_statue.getItems();
            // Save to txt file


            // Update the selected employee's data
            selectedEmployee.setId(id); // Make sure to update the ID if necessary
            selectedEmployee.setUsername(username);
            selectedEmployee.setMonthly(monthly);
            selectedEmployee.setHourly(hourly);
            selectedEmployee.setManager(isManager);
            selectedEmployee.setCommission(commission);
            selectedEmployee.setDepartment(cbo_department.getSelectionModel().getSelectedItem());
            selectedEmployee.setStatue(cbo_statue.getSelectionModel().getSelectedItem()); // Update statue

            // Recalculate salary based on updated values
            // You should update your salary calculation logic here
            if (isManager) {
                selectedEmployee.setSalary(monthly + hourly*10 + 500);
                if (commission) {
                    selectedEmployee.setSalary(selectedEmployee.getSalary() + 0.20 * monthly);
                }
            } else if (commission) {
                selectedEmployee.setSalary(monthly + hourly*10 + 0.20 * monthly);
            } else {
                selectedEmployee.setSalary(monthly + hourly*10);
            }

            // Update the table view data
            table_users.refresh();

            // Clear input fields
            txt_id.clear();
            txt_username.clear();
            txt_monthly.clear();
            txt_hourly.clear();
            cbo_department.getSelectionModel().selectFirst();
            cbo_statue.getSelectionModel().selectFirst();

            // Update the file with edited information
            saveEmployeeToFile(selectedEmployee);

        } else {
            // Display a message if no employee is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Employee Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select an employee to edit.");
            alert.showAndWait();
        }

    }

    private void updateEmployeeFile(employee updatedEmployee) {
        try {
            // Read the content of the file
            List<String> fileContent = new ArrayList<>();
            String departmentFileName = updatedEmployee.getDepartment() + ".txt";
            try (BufferedReader reader = new BufferedReader(new FileReader(departmentFileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.add(line);
                }
            }

            // Update the employee's information in the fileContent list
            for (int i = 0; i < fileContent.size(); i++) {
                String line = fileContent.get(i);
                if (line.startsWith("ID: " + updatedEmployee.getId() + "\n")) {
                    fileContent.set(i, "ID: " + updatedEmployee.getId() + "\n");
                    fileContent.set(i + 1, "Name: " + updatedEmployee.getUsername() + "\n");
                    fileContent.set(i + 2, "Monthly Wage: " + updatedEmployee.getMonthly() + "\n");
                    fileContent.set(i + 3, "Hourly Wage: " + updatedEmployee.getHourly() + "\n");
                    fileContent.set(i + 4, "Department: " + updatedEmployee.getDepartment() + "\n");
                    fileContent.set(i + 5, "Statue: " + updatedEmployee.getStatue() + "\n");
                }
            }

            // Overwrite the file with the updated content
            try (FileWriter writer = new FileWriter(departmentFileName)) {
                for (String line : fileContent) {
                    writer.write(line + "n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error updating employee data in file: " + e.getMessage());
        }
    }

// ... (Rest of your HelloController class code)


    public void Delete(ActionEvent actionEvent) {
        // Get the selected employee from the TableView
        employee selectedEmployee = table_users.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {
            // Remove the employee from the ObservableList
            employeeData.remove(selectedEmployee);

            // Refresh the TableView
            table_users.refresh();

            // Delete the corresponding entry from the file (optional)
            // deleteEmployeeFromFile(selectedEmployee);
            deleteEmployee(); // Call the delete method
        } else {
            System.err.println("Please select an employee to delete.");
        }
    }


    private void deleteEmployee() {
        // Get selected employee from the table view
        employee selectedEmployee = table_users.getSelectionModel().getSelectedItem();

        // If an employee is selected
        if (selectedEmployee != null) {
            // Remove the selected employee from the table view data
            employeeData.remove(selectedEmployee);

            // Save deletion information to the file
            try (FileWriter writer = new FileWriter(selectedEmployee.getDepartment() + ".txt", true)) {
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = now.format(formatter);
                writer.write("Employee with ID: " + selectedEmployee.getId() + " was deleted on: " + formattedDateTime + "\n");
            } catch (IOException e) {
                System.err.println("Error saving deletion information to file: " + e.getMessage());
            }
        } else {
            // Display a message if no employee is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Employee Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select an employee to delete.");
            alert.showAndWait();
        }
    }

    // ... your existing Add_random method ...
    // ... your existing search_user method ...

    public void getSelected(MouseEvent mouseEvent) {
        // Get the selected employee from the TableView
        employee selectedEmployee = table_users.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {
            // Populate input fields with selected employee data
            txt_id.setText(String.valueOf(selectedEmployee.getId()));
            txt_username.setText(selectedEmployee.getUsername());
            txt_monthly.setText(String.valueOf(selectedEmployee.getMonthly()));
            txt_hourly.setText(String.format("%.2f", selectedEmployee.getHourly()));
            check_isManager.setSelected(selectedEmployee.getIsManager());
            check_commission.setSelected(selectedEmployee.getCommission());
            cbo_department.getSelectionModel().select(selectedEmployee.getDepartment());
            cbo_statue.getSelectionModel().select(selectedEmployee.getStatue());
        }
    }

    private void saveEmployeeToFile(employee employee) {
        try (FileWriter writer = new FileWriter(employee.getDepartment() + ".txt", true)) { // append to file
            writer.write(employee.getId() + "\n");
            writer.write(employee.getUsername() + "\n");
            writer.write(employee.getMonthly() + "\n");
            writer.write(employee.getHourly() + "\n");
            writer.write(employee.getIsManager() + "\n");
            writer.write(employee.getCommission() + "\n");
            writer.write(employee.getDepartment() + "\n");
            writer.write(employee.getStatue() + "\n");

            // Calculate total salary
            double totalSalary = employee.getMonthly() + employee.getHourly() * 10;
            if (employee.getIsManager()) {
                totalSalary += 500;
                if (employee.getCommission()) {
                    totalSalary += 0.20 * employee.getMonthly();
                }
            } else if (employee.getCommission()) {
                totalSalary += 0.20 * employee.getMonthly();
            }

            writer.write("Total Salary: " + totalSalary + "\n");

            // Get current date and time
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            writer.write(formattedDateTime + "\n"); // Add date and time
        } catch (IOException e) {
            System.err.println("Error saving employee data to file: " + e.getMessage());
        }
    }




    // ... (Your existing code for the HelloController class)









    @FXML
    void Add_random(ActionEvent event) {

        int numberOfEmployeesToAdd = 0;
        try {
            numberOfEmployeesToAdd = Integer.parseInt(addRandom.getText());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return; // Stop execution if input is not a valid number
        }

        for (int j=0 ; j<numberOfEmployeesToAdd ; j++) {
            if (randomEmployeeIndex < randomEmployeeData.size()) {
                String id = randomEmployeeData.get(randomEmployeeIndex++);
                String username = randomEmployeeData.get(randomEmployeeIndex++);
                double salary = Double.parseDouble(randomEmployeeData.get(randomEmployeeIndex++));
                String department = randomEmployeeData.get(randomEmployeeIndex++);
                String statue = randomEmployeeData.get(randomEmployeeIndex++);

                employee newEmployee = new employee(id, username, department, statue, 0, 0, false, false); // Assuming no monthly, hourly, manager, or commission
                newEmployee.setSalary(salary);

                employeeData.add(newEmployee);
                table_users.refresh();
            } else {
                System.out.println("All random employees have been added.");
                break; // Exit the loop if no more random employees to add
            }
        }
    }

    public void search_user(KeyEvent keyEvent) {
    }
}