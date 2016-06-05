package com.example.amaremployeedirectory.model;

/**
 * Created by Amar on 6/4/2016.
 */
public class EmployeeMainModel extends EmployeeAuthModel {

    private String empAddress;
    private String empPhone;
    private String empDesignation;
    private String empDepartment;
    private String empIncome;

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getEmpDesignation() {
        return empDesignation;
    }

    public void setEmpDesignation(String empDesignation) {
        this.empDesignation = empDesignation;
    }

    public String getEmpDepartment() {
        return empDepartment;
    }

    public void setEmpDepartment(String empDepartment) {
        this.empDepartment = empDepartment;
    }

    public String getEmpIncome() {
        return empIncome;
    }

    public void setEmpIncome(String empIncome) {
        this.empIncome = empIncome;
    }
}
