package com.example.amaremployeedirectory.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.amaremployeedirectory.LoginActivity;
import com.example.amaremployeedirectory.R;
import com.example.amaremployeedirectory.activity.BaseActivity;
import com.example.amaremployeedirectory.application.MdsApplication;
import com.example.amaremployeedirectory.database.table.EmployeeAuthTable;
import com.example.amaremployeedirectory.database.table.EmployeeDetailTable;
import com.example.amaremployeedirectory.model.ModelManager;
import com.example.amaremployeedirectory.util.MyTextWatcher;
import com.example.amaremployeedirectory.util.UAction;
import com.example.amaremployeedirectory.util.Utility;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginActivityFragment extends BaseFragment implements View.OnClickListener {

    public static final String TAG = "LoginActivityFragment";
    private EditText inputEmail, inputPassword;
    private TextInputLayout layoutEmail, layoutPassword;
    ArrayList<String> record = new ArrayList<String>();

    public LoginActivityFragment() {
        layoutId = R.layout.fragment_login;
    }

    @Override
    protected void setupUi(Bundle savedInstanceState) {

        Button btnLogin = (Button) rootView.findViewById(R.id.btn_login);
        Button btnSignUp = (Button) rootView.findViewById(R.id.btn_signup);
        layoutEmail = (TextInputLayout) rootView.findViewById(R.id.input_layout_email);
        layoutPassword = (TextInputLayout) rootView.findViewById(R.id.input_layout_password);
        inputEmail = (EditText) rootView.findViewById(R.id.input_email);
        inputPassword = (EditText) rootView.findViewById(R.id.input_password);

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail, layoutEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword, layoutPassword));
        initiateAnimations();

    }


    private void initiateAnimations() {
        ((BaseActivity) getActivity()).animateToVisible(rootView.findViewById(R.id.layout_btn_container), -1);
        ((BaseActivity) getActivity()).animateToVisible(rootView.findViewById(R.id.img_icon_login), -1);
        ((BaseActivity) getActivity()).animateToVisible(rootView.findViewById(R.id.input_layout_password), -1);
        ((BaseActivity) getActivity()).animateToVisible(rootView.findViewById(R.id.input_layout_email), -1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String email = inputEmail.getText().toString();
                if (email.length() > 0 && Utility.isValidEmail(email)) {
//                    RegisterDataBaseAdapter registerDataBaseAdapter = new RegisterDataBaseAdapter(getActivity());
                    try {
//                        registerDataBaseAdapter.open();
//                        record = registerDataBaseAdapter.getSingleRecordFromDB(email);
                        EmployeeAuthTable employeeAuthTable = new EmployeeAuthTable();
                        String empId = employeeAuthTable.getAuthenticationIdFromEmailId(email);
                        String pass = inputPassword.getText().toString();
                        if (empId != null && !empId.equalsIgnoreCase("") && pass.equalsIgnoreCase("mds123")) {
                            MdsApplication.setLoggedUserId(empId);
                            ModelManager.getIntance().setEmployeeAuthModel(employeeAuthTable.getAuthenticationModelById(empId));
                            EmployeeDetailTable employeeDetailTable = new EmployeeDetailTable();
                            ModelManager.getIntance().setEmployeeMainModel(employeeDetailTable.getAuthenticationModelById(empId));
                            mUserActionListener.performUserAction(UAction.ACTION_PROFILE, null, null);
                        } else {
                            String msg = "Username or Passw" +
                                    "or is incorrect";
                            LoginActivity.showError(msg);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    String msg = "Username or Passwor is incorrect";
                    LoginActivity.showError(msg);

                }
                break;
            case R.id.btn_signup:
                mUserActionListener.performUserAction(UAction.ACTION_SIGN_UP, v, null);
                break;

        }

    }

}

