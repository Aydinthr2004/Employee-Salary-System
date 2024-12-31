package org.example.tableview_me;

import java.time.LocalDateTime;

public class EmployeeData extends employee{
    public EmployeeData(String id, String username, String department, String statue, double monthly, double hourly, boolean isManager, boolean commission) {
        super(id, username, department, statue, monthly, hourly, isManager, commission);
        this.id = Integer.parseInt(id);
        this.name = name;
        this.monthlyWage = monthlyWage;
        this.overtimeHours = overtimeHours;
        this.isManager = isManager;
        this.commission = commission;
        this.department = department;
        this.status = status;
        this.totalSalary = totalSalary;
        this.time = time;
    }
    private int id;
    private String name;
    private double monthlyWage;
    private double overtimeHours;
    private boolean isManager;

    public EmployeeData(int i, String username, double v, double v1, boolean b, boolean b1, String s, String s1, double v2, LocalDateTime parse) {
        super(i , username , v , v1 ,b ,b1,s,s1,v2,parse);
    }

    public String getId() {
        return String.valueOf(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMonthlyWage() {
        return monthlyWage;
    }

    public void setMonthlyWage(double monthlyWage) {
        this.monthlyWage = monthlyWage;
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public boolean isCommission() {
        return commission;
    }

    public void setCommission(boolean commission) {
        this.commission = commission;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    private boolean commission;
    private String department;
    private String status;
    private double totalSalary;
    private LocalDateTime time;


    // Getters and Setters (omitted for brevity)
    // ...
}
