package com.example.amaremployeedirectory.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amaremployeedirectory.R;
import com.example.amaremployeedirectory.activity.BaseActivity;
import com.example.amaremployeedirectory.database.table.EmployeeDetailTable;
import com.example.amaremployeedirectory.model.EmployeeAuthModel;
import com.example.amaremployeedirectory.model.EmployeeMainModel;
import com.example.amaremployeedirectory.model.ModelManager;
import com.example.amaremployeedirectory.util.MyTextWatcher;
import com.example.amaremployeedirectory.util.UAction;

/**
 * Created by Amar on 6/4/2016.
 */
public class ProfileFragment extends BaseFragment {
    public static final String TAG = "ProfileFragment";
    EditText empDesignation, empDepartment, empIncome, empAddress, phoneNumber;
    TextInputLayout lytEmpDesignation, lytEmpDepartment, lytEmpIncome, lytEmpAddress, lytPhoneNumber;
    private FloatingActionButton fab;
    private boolean editable = false;

    public ProfileFragment() {
        layoutId = R.layout.profilesceen;
    }

    @Override
    protected void setupUi(Bundle savedInstanceState) {

        initialization();
        initiateAnimations();

    }

    private void setUpData() {
        EmployeeMainModel mainModel = ModelManager.getIntance().getEmployeeMainModel();
        EmployeeAuthModel employeeAuthModel = ModelManager.getIntance().getEmployeeAuthModel();
        fab.setImageResource((mainModel == null || editable) ? R.drawable.img_ok : android.R.drawable.ic_menu_edit);
        empDesignation.setEnabled(mainModel == null || editable);
        empDepartment.setEnabled(mainModel == null || editable);
        phoneNumber.setEnabled(mainModel == null || editable);
        empIncome.setEnabled(mainModel == null || editable);
        empAddress.setEnabled(mainModel == null || editable);
        if (employeeAuthModel != null) {
            ((TextView) rootView.findViewById(R.id.emp_id)).setText(employeeAuthModel.getEmpId());
            ((TextView) rootView.findViewById(R.id.emp_email)).setText(employeeAuthModel.getEmpEmailId());
            ((TextView) rootView.findViewById(R.id.emp_name)).setText(employeeAuthModel.getEmpFirstName() + " " + employeeAuthModel.getEmpLastName());
        }
        if (mainModel != null) {
            empDepartment.setText(mainModel.getEmpDepartment());
            empDesignation.setText(mainModel.getEmpDesignation());
            empAddress.setText(mainModel.getEmpAddress());
            empIncome.setText(mainModel.getEmpIncome());
            phoneNumber.setText(mainModel.getEmpPhone());
        } else {
            editable = true;
        }
    }

    private void initialization() {
        empDesignation = (EditText) rootView.findViewById(R.id.input_designation);
        empDepartment = (EditText) rootView.findViewById(R.id.input_department);
        empIncome = (EditText) rootView.findViewById(R.id.input_income);
        empAddress = (EditText) rootView.findViewById(R.id.input_address);
        phoneNumber = (EditText) rootView.findViewById(R.id.input_phone_number);
        lytEmpDesignation = (TextInputLayout) rootView.findViewById(R.id.input_layout_designation);
        lytEmpDepartment = (TextInputLayout) rootView.findViewById(R.id.input_layout_department);
        lytEmpIncome = (TextInputLayout) rootView.findViewById(R.id.input_layout_income);
        lytEmpAddress = (TextInputLayout) rootView.findViewById(R.id.input_layout_address);
        lytPhoneNumber = (TextInputLayout) rootView.findViewById(R.id.input_layout_phone_number);
        empDesignation.addTextChangedListener(new MyTextWatcher(empDesignation, lytEmpDesignation));
        empDepartment.addTextChangedListener(new MyTextWatcher(empDepartment, lytEmpDepartment));
        empIncome.addTextChangedListener(new MyTextWatcher(empIncome, lytEmpIncome));
        empAddress.addTextChangedListener(new MyTextWatcher(empAddress, lytEmpAddress));
        phoneNumber.addTextChangedListener(new MyTextWatcher(phoneNumber, lytPhoneNumber));
        ((ImageView) rootView.findViewById(R.id.btn_logout)).setOnClickListener(this);
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        setUpData();

    }

    private void initiateAnimations() {
        ((BaseActivity) getActivity()).animateToVisible(rootView.findViewById(R.id.input_layout_designation), -1);
        ((BaseActivity) getActivity()).animateToVisible(rootView.findViewById(R.id.input_layout_department), -1);
        ((BaseActivity) getActivity()).animateToVisible(rootView.findViewById(R.id.input_layout_income), -1);
        ((BaseActivity) getActivity()).animateToVisible(rootView.findViewById(R.id.input_layout_address), -1);
        ((BaseActivity) getActivity()).animateToVisible(rootView.findViewById(R.id.input_layout_phone_number), -1);
        ((BaseActivity) getActivity()).animateToVisible(rootView.findViewById(R.id.lyt_details), -1);
    }


    public void updateData(EmployeeMainModel mainModel) {
        EmployeeDetailTable employeeDetailTable = new EmployeeDetailTable();
        employeeDetailTable.updateEmployeeDetails(mainModel);
        ModelManager.getIntance().setEmployeeMainModel(mainModel);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.fab:
                if (validate()) {
                    if (editable) {

                        EmployeeMainModel mainModel = ModelManager.getIntance().getEmployeeMainModel();
                        if (mainModel == null) {
                            mainModel = new EmployeeMainModel();
                        }
                        EmployeeAuthModel employeeDetailTable = ModelManager.getIntance().getEmployeeAuthModel();
                        mainModel.setEmpId(employeeDetailTable.getEmpId());
                        mainModel.setEmpEmailId(employeeDetailTable.getEmpEmailId());
                        mainModel.setEmpFirstName(employeeDetailTable.getEmpFirstName());
                        mainModel.setEmpLastName(employeeDetailTable.getEmpLastName());
                        mainModel.setEmpIncome(empIncome.getText().toString());
                        mainModel.setEmpDesignation(empDesignation.getText().toString());
                        mainModel.setEmpDepartment(empDepartment.getText().toString());
                        mainModel.setEmpAddress(empAddress.getText().toString());
                        mainModel.setEmpPhone(phoneNumber.getText().toString());
                        updateData(mainModel);
                    }
                    editable = editable == false;
                    setUpData();
                    initiateAnimations();
                }
                break;
            case R.id.btn_logout:
                ModelManager.getIntance().logOut();
                mUserActionListener.performUserAction(UAction.ACTION_LOGIN, null, null);
                break;
        }
    }

    private boolean validate() {
        String designtn = empDesignation.getText().toString();
        String depart = empDepartment.getText().toString();
        String income = empIncome.getText().toString();
        String addrs = empAddress.getText().toString();
        String phoneNu = phoneNumber.getText().toString();
        boolean flag = true;
        if (designtn.equalsIgnoreCase("") || designtn.length() < 1) {
            lytEmpDesignation.setError("Enter the Designation");
            flag = false;
        }
        if (depart.equalsIgnoreCase("") || depart.length() < 1) {
            lytEmpDepartment.setError("Enter the Department");
            flag = false;
        }
        if (income.equalsIgnoreCase("") || income.length() < 1) {
            lytEmpIncome.setError("Enter the Income");
            flag = false;
        }



        if (addrs.equalsIgnoreCase("") || addrs.length() < 1) {
            lytEmpAddress.setError("Enter the address");
            flag = false;
        }

        return flag;
    }
}
