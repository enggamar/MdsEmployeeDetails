package com.example.amaremployeedirectory.util;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.amaremployeedirectory.R;

/**
 * Created by Amar on 6/4/2016.
 */
public class MyTextWatcher implements TextWatcher {

    private EditText editText;
    private TextInputLayout txtInputLayout;

    public MyTextWatcher(EditText editText, TextInputLayout txtInputLayout) {

        this.editText = editText;
        this.txtInputLayout = txtInputLayout;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void afterTextChanged(Editable editable) {
        switch (editText.getId()) {

            case R.id.input_email:

                String email = editText.getText().toString();
                boolean isEmailValid = Utility.isValidEmail(email);
                if (isEmailValid) {
                    txtInputLayout.setErrorEnabled(false);
                } else {
                    txtInputLayout.setErrorEnabled(true);
                    txtInputLayout.setError("Invalid Email");
                }
                break;
            case R.id.input_password:

                break;
            case R.id.input_address:
                String address = editText.getText().toString();
                if (!address.equalsIgnoreCase("") && address.length() > 0) {
                    txtInputLayout.setErrorEnabled(false);
                } else {
                    txtInputLayout.setErrorEnabled(true);
                    txtInputLayout.setError("Please enter the address");
                }
                break;
//            case R.id.input_phone_number:
//                String phoneNumber = editText.getText().toString();
//                if (!phoneNumber.equalsIgnoreCase("") && phoneNumber.length() == 12) {
//                    txtInputLayout.setErrorEnabled(false);
//                } else {
//                    txtInputLayout.setErrorEnabled(true);
//                    txtInputLayout.setError("Please enter the phone number");
//                }
//                break;
            case R.id.input_department:
                String department = editText.getText().toString();
                if (!department.equalsIgnoreCase("") && department.length() > 0) {
                    txtInputLayout.setErrorEnabled(false);
                } else {
                    txtInputLayout.setErrorEnabled(true);
                    txtInputLayout.setError("Please enter the department");
                }
                break;
            case R.id.input_income:
                String income = editText.getText().toString();
                if (!income.equalsIgnoreCase("") && income.length() > 0) {
                    txtInputLayout.setErrorEnabled(false);
                } else {
                    txtInputLayout.setErrorEnabled(true);
                    txtInputLayout.setError("Please enter the address");
                }
                break;
            case R.id.input_designation:
                String designation = editText.getText().toString();
                if (!designation.equalsIgnoreCase("") && designation.length() > 0) {
                    txtInputLayout.setErrorEnabled(false);
                } else {
                    txtInputLayout.setErrorEnabled(true);
                    txtInputLayout.setError("Please enter the designation");
                }
                break;
            case R.id.input_first_name:
                String fName = editText.getText().toString();
                if (!fName.equalsIgnoreCase("") && fName.length() > 0) {
                    txtInputLayout.setErrorEnabled(false);
                } else {
                    txtInputLayout.setErrorEnabled(true);
                    txtInputLayout.setError("Please enter the first name");
                }
                break;
            case R.id.last_name:
                String lName = editText.getText().toString();
                if (!lName.equalsIgnoreCase("") && lName.length() > 0) {
                    txtInputLayout.setErrorEnabled(false);
                } else {
                    txtInputLayout.setErrorEnabled(true);
                    txtInputLayout.setError("Please enter the last name");
                }
                break;


        }
    }

}