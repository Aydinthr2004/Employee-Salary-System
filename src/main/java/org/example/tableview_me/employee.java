package org.example.tableview_me;


import java.time.LocalDateTime;

public class employee {
    String id , username, department , statue ;



    private boolean isManager  , commission ;

    public employee(int i, String username, double v, double v1, boolean b, boolean b1, String s, String s1, double v2, LocalDateTime parse) {
    }

    public String getId() {
        return id;
    }

    public employee(String id, String username, String department, String statue, double monthly, double hourly , boolean isManager , boolean commission) {
        this.id = id;
        this.username = username;
        this.department = department;
        this.statue = statue;
        this.monthly = monthly;
        this.hourly = hourly;
        this.isManager = isManager;
        this.commission = commission;
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

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }

    public double getMonthly() {
        return monthly;
    }

    public void setMonthly(double monthly) {
        this.monthly = monthly;
    }

    public double getHourly() {
        return hourly;
    }

    public void setHourly(double hourly) {
        this.hourly = hourly;
    }

    double monthly , hourly;

    private double salary;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean getIsManager() {
        return isManager;
    }

    public boolean getCommission() {
        return commission;
    }

    public static abstract class salary{


        public salary(String id, String username, String department, String statue, double monthly, double hourly, boolean isManager, boolean commission) {

        }
        double wage;

        public double getWage() {
            return wage;
        }

        public void setWage(double wage) {
            this.wage = wage;
        }



        public abstract void Calculate();
    }





}
