package com.example.amaremployeedirectory.database.table;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.amaremployeedirectory.database.BaseTable;
import com.example.amaremployeedirectory.model.EmployeeAuthModel;

/**
 * Created by Amar on 6/4/2016.
 */
public class EmployeeAuthTable extends BaseTable {

    public static String TABLE_NAME = "employee_auth";
    public static final String CN_EMP_ID = "id";
    public static final String CN_FIRST_NAME = "first_name";
    public static final String CN_LAST_NAME = "last_name";
    public static final String CN_EMAIL_ID = "email";


    public static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
            + " ( " + CN_EMP_ID + " text primary key, " + CN_FIRST_NAME + " text, " + CN_LAST_NAME + " text, "
            + CN_EMAIL_ID + " text )";

    public EmployeeAuthTable() {
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

    public void updateEmployeeDetails(EmployeeAuthModel user) {
        String[] args = {user.getEmpId()};
        mWritableDatabase.update(TABLE_NAME, getContentValues(user, false), CN_EMP_ID + "=?", args);
    }

    public void insertEmployeeDetails(EmployeeAuthModel user) {
        String[] args = {user.getEmpId()};
        mWritableDatabase.insert(TABLE_NAME, null, getContentValues(user, true));
    }

    private ContentValues getContentValues(EmployeeAuthModel user, boolean isInsert) {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put(CN_FIRST_NAME, user.getEmpFirstName());
        newValues.put(CN_LAST_NAME, user.getEmpLastName());
        newValues.put(CN_EMAIL_ID, user.getEmpEmailId());
        if (isInsert) {
            newValues.put(CN_EMP_ID, user.getEmpId());
        }

        return newValues;
    }

    public EmployeeAuthModel getAuthenticationModelById(String id) {
        EmployeeAuthModel employeeAuthModel = null;
        String arg[] = {id};
        Cursor cursor = mWritableDatabase.query(true, TABLE_NAME, null, CN_EMP_ID + "=?",
                arg, null, null, null, null);
        if (cursor.moveToFirst()) {
            employeeAuthModel = new EmployeeAuthModel();
            employeeAuthModel.setEmpId(cursor.getString(cursor
                    .getColumnIndex(CN_EMP_ID)));
            employeeAuthModel.setEmpLastName(cursor.getString(cursor
                    .getColumnIndex(CN_LAST_NAME)));
            employeeAuthModel.setEmpFirstName(cursor.getString(cursor
                    .getColumnIndex(CN_FIRST_NAME)));
            employeeAuthModel.setEmpEmailId(cursor.getString(cursor
                    .getColumnIndex(CN_EMAIL_ID)));
        }
        return employeeAuthModel;
    }

    public String getAuthenticationIdFromEmailId(String emailId) {
        String employeeAuthModelId = null;
        String arg[] = {emailId};
        Cursor cursor = mWritableDatabase.query(true, TABLE_NAME, null, CN_EMAIL_ID + "=?",
                arg, null, null, null, null);
        if (cursor.moveToFirst()) {
            employeeAuthModelId = cursor.getString(cursor
                    .getColumnIndex(CN_EMP_ID));
        }
        return employeeAuthModelId;
    }
}
