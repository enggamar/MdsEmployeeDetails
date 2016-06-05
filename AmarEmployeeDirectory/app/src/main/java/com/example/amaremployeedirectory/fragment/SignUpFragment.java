package com.example.amaremployeedirectory.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amaremployeedirectory.R;
import com.example.amaremployeedirectory.activity.BaseActivity;
import com.example.amaremployeedirectory.application.MdsApplication;
import com.example.amaremployeedirectory.database.table.EmployeeAuthTable;
import com.example.amaremployeedirectory.model.EmployeeAuthModel;
import com.example.amaremployeedirectory.model.ModelManager;
import com.example.amaremployeedirectory.util.MyTextWatcher;
import com.example.amaremployeedirectory.util.UAction;
import com.example.amaremployeedirectory.util.Utility;

/**
 * Created by Amar on 6/4/2016.
 */
public class SignUpFragment extends BaseFragment {

    public static final String TAG = "SignUpFragment";
    EditText empFName, empLName, empEmail;
    TextInputLayout layoutEmpFName, layoutEmpLName, layoutEmpEmail;
    Button btnSignUp;
    Boolean flag = true;

    public SignUpFragment() {
        layoutId = R.layout.signup;
    }

    @Override
    protected void setupUi(Bundle savedInstanceState) {
        initialization();
    }

    private void initialization() {
        ((ImageView) rootView.findViewById(R.id.btn_logout)).setVisibility(View.GONE);
        ((ImageView) rootView.findViewById(R.id.back)).setVisibility(View.VISIBLE);
        ((TextView) rootView.findViewById(R.id.title)).setText("Sign up");

        empFName = (EditText) rootView.findViewById(R.id.input_first_name);
        empLName = (EditText) rootView.findViewById(R.id.last_name);
        empEmail = (EditText) rootView.findViewById(R.id.input_email);
        layoutEmpFName = (TextInputLayout) rootView.findViewById(R.id.input_layout_first_name);
        layoutEmpLName = (TextInputLayout) rootView.findViewById(R.id.input_last_name);
        layoutEmpEmail = (TextInputLayout) rootView.findViewById(R.id.input_layout_email);
        empFName.addTextChangedListener(new MyTextWatcher(empFName, layoutEmpFName));
        empLName.addTextChangedListener(new MyTextWatcher(empLName, layoutEmpLName));
        empEmail.addTextChangedListener(new MyTextWatcher(empEmail, layoutEmpEmail));
        btnSignUp = (Button) rootView.findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(this);
        ((ImageView) rootView.findViewById(R.id.back)).setOnClickListener(this);
        initiateAnimations();

    }

    private void initiateAnimations() {
        ((BaseActivity) getActivity()).animateToVisible(rootView.findViewById(R.id.btn_signup), -1);
        ((BaseActivity) getActivity()).animateToVisible(rootView.findViewById(R.id.input_layout_first_name), -1);
        ((BaseActivity) getActivity()).animateToVisible(rootView.findViewById(R.id.input_last_name), -1);
        ((BaseActivity) getActivity()).animateToVisible(rootView.findViewById(R.id.input_layout_email), -1);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_signup:
                String firstName = empFName.getText().toString();
                String lastName = empLName.getText().toString();
                String email = empEmail.getText().toString();
                if (validate()) {
                    EmployeeAuthTable employeeAuthTable = new EmployeeAuthTable();
                    try {
                        int numberOfRecords = employeeAuthTable.getCount();
                        String id = Utility.generateId(numberOfRecords);
                        EmployeeAuthModel employeeAuthModel = new EmployeeAuthModel();
                        employeeAuthModel.setEmpEmailId(email);
                        employeeAuthModel.setEmpFirstName(firstName);
                        employeeAuthModel.setEmpLastName(lastName);
                        employeeAuthModel.setEmpId(id);
                        Utility.insertEmployeeAuthInDatabase(employeeAuthModel);
                        ModelManager.getIntance().setEmployeeAuthModel(employeeAuthModel);
                        MdsApplication.setLoggedUserId(id);
//                        registerDataBaseAdapter.insertEntry(id, firstName, lastName, email);
//                        registerDataBaseAdapter.close();
                        mUserActionListener.performUserAction(UAction.ACTION_PROFILE, null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                break;
            case R.id.back:
                getActivity().onBackPressed();
                break;
        }
    }

    private boolean validate() {
        String firstName = empFName.getText().toString();
        String lastName = empLName.getText().toString();
        String email = empEmail.getText().toString();
        if (firstName.equalsIgnoreCase("") || firstName.length() < 1) {
            layoutEmpFName.setError("Enter the First Name");
            flag = false;

        }
        if (lastName.equalsIgnoreCase("") || lastName.length() < 1) {
            layoutEmpLName.setError("Enter the Last Name");
            flag = false;

        }
        if (email.equalsIgnoreCase("")) {
            layoutEmpEmail.setError("Invalid Email");
            flag = false;

        }
        return flag;
    }

}
