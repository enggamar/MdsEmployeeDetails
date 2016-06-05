package com.example.amaremployeedirectory.database.table;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.amaremployeedirectory.database.BaseTable;
import com.example.amaremployeedirectory.model.EmployeeAuthModel;
import com.example.amaremployeedirectory.model.EmployeeMainModel;

/**
 * Created by Amar on 6/4/2016.
 */
public class EmployeeDetailTable extends BaseTable {

    public static String TABLE_NAME = "employee_detail";
    public static final String EMP_ID = "id";
    public static final String ADDRESS = "address";
    public static final String DESIGNATION = "designation";
    public static final String DEPARTMENT = "department";
    public static final String INCOME = "income";
    public static final String PHONE_NUMBER = "phone_number";


    public static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
            + " ( " + EMP_ID + " text ," + ADDRESS + " text, " +
            PHONE_NUMBER + " text, " + DESIGNATION + " text, " +
            DEPARTMENT + " text, " + INCOME + " text, FOREIGN KEY (" + EMP_ID + ") REFERENCES " +
            EmployeeAuthTable.TABLE_NAME + " (" + EmployeeAuthTable.CN_EMP_ID + ") )";

    public EmployeeDetailTable() {
        super(TABLE_NAME);
    }

    /**
     * @param pPrimaryKey
     * @return true if row with pPrimaryKey is found and deleted
     */
    @Override
    public boolean deleteData(Object pPrimaryKey) {
        return false;
    }

    public void updateEmployeeDetails(EmployeeMainModel user) {
        String[] args = {user.getEmpId()};
        if (isIdExist(user.getEmpId())) {
            mWritableDatabase.update(TABLE_NAME, getContentValues(user,false), EMP_ID + "=?", args);
        } else {
            insertEmployeeDetails(user);
        }
    }

    public void insertEmployeeDetails(EmployeeMainModel user) {
        String[] args = {user.getEmpId()};
        mWritableDatabase.insert(TABLE_NAME, null, getContentValues(user,true));
    }

    private ContentValues getContentValues(EmployeeMainModel user, boolean isInsert) {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put(ADDRESS, user.getEmpAddress());
        newValues.put(DESIGNATION, user.getEmpDesignation());
        newValues.put(DEPARTMENT, user.getEmpDepartment());
        newValues.put(INCOME, user.getEmpIncome());
        if (isInsert) {
            newValues.put(EMP_ID, user.getEmpId());
        }
        return newValues;
    }

    public boolean isIdExist(String id) {
        return checkIsDataAlreadyInDBorNot(TABLE_NAME, EMP_ID, id);
    }

    public EmployeeMainModel getAuthenticationModelById(String id) {
        EmployeeAuthModel employeeAuthModel = null;
        EmployeeMainModel employeeMainModel = null;
        String arg[] = {id};
        Cursor cursor = mWritableDatabase.query(true, TABLE_NAME, null, EMP_ID + "=?",
                arg, null, null, null, null);
        if (cursor.moveToFirst()) {
            EmployeeAuthTable employeeAuthTable = new EmployeeAuthTable();
            employeeAuthModel = employeeAuthTable.getAuthenticationModelById(id);
            employeeMainModel = new EmployeeMainModel();
            employeeMainModel.setEmpId(employeeAuthModel.getEmpId());
            employeeMainModel.setEmpFirstName(employeeAuthModel.getEmpFirstName());
            employeeMainModel.setEmpLastName(employeeAuthModel.getEmpLastName());
            employeeMainModel.setEmpEmailId(employeeAuthModel.getEmpEmailId());

            employeeMainModel.setEmpDepartment(cursor.getString(cursor
                    .getColumnIndex(DEPARTMENT)));
            employeeMainModel.setEmpAddress(cursor.getString(cursor
                    .getColumnIndex(ADDRESS)));
            employeeMainModel.setEmpDesignation(cursor.getString(cursor
                    .getColumnIndex(DESIGNATION)));
            employeeMainModel.setEmpIncome(cursor.getString(cursor
                    .getColumnIndex(INCOME)));
            employeeMainModel.setEmpPhone(cursor.getString(cursor
                    .getColumnIndex(PHONE_NUMBER)));
        }
        return employeeMainModel;
    }
}
