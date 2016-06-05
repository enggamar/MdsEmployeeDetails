package com.example.amaremployeedirectory.model;

import com.example.amaremployeedirectory.application.MdsApplication;

/**
 * Created by Amar on 6/4/2016.
 */
public class ModelManager {
    private static ModelManager modelManager;
    private EmployeeAuthModel employeeAuthModel;
    private EmployeeMainModel employeeMainModel;
    public static ModelManager getIntance() {
        if (modelManager == null) {
            modelManager = new ModelManager();
        }
        return modelManager;
    }

    public EmployeeAuthModel getEmployeeAuthModel() {
        return employeeAuthModel;
    }

    public void setEmployeeAuthModel(EmployeeAuthModel employeeAuthModel) {
        this.employeeAuthModel = employeeAuthModel;
    }

    public EmployeeMainModel getEmployeeMainModel() {
        return employeeMainModel;
    }

    public void setEmployeeMainModel(EmployeeMainModel employeeMainModel) {
        this.employeeMainModel = employeeMainModel;
    }
    public void logOut(){
        employeeAuthModel=null;
        employeeMainModel=null;
        MdsApplication.setLoggedUserId(null);
    }
}
