package com.example.amaremployeedirectory;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.amaremployeedirectory.activity.BaseActivity;
import com.example.amaremployeedirectory.application.MdsApplication;
import com.example.amaremployeedirectory.database.table.EmployeeAuthTable;
import com.example.amaremployeedirectory.database.table.EmployeeDetailTable;
import com.example.amaremployeedirectory.fragment.LoginActivityFragment;
import com.example.amaremployeedirectory.fragment.ProfileFragment;
import com.example.amaremployeedirectory.fragment.SignUpFragment;
import com.example.amaremployeedirectory.model.ModelManager;
import com.example.amaremployeedirectory.util.UAction;

public class LoginActivity extends BaseActivity {

    private long backPressedTime;
    static CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        /**
         * If User loggedIn perform Profile Action otherwise Login Action
         */

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
       String id=MdsApplication.getLoggedUserId();
        if(id!=null){
            EmployeeAuthTable employeeAuthTable=new EmployeeAuthTable();
            ModelManager.getIntance().setEmployeeAuthModel(employeeAuthTable.getAuthenticationModelById(id));
            EmployeeDetailTable employeeDetailTable=new EmployeeDetailTable();
            ModelManager.getIntance().setEmployeeMainModel(employeeDetailTable.getAuthenticationModelById(id));
            performUserAction(UAction.ACTION_PROFILE, null, null);
        } else {
            performUserAction(UAction.ACTION_LOGIN, null, null);
        }
    }

    public static void showError(String str) {

        Snackbar snackbar = Snackbar.make(coordinatorLayout, " " + str, Snackbar.LENGTH_SHORT);
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void performUserAction(int pActionType, View pView, Object pData) {
        Fragment fragment;
        Bundle bundle;
        switch (pActionType) {

            case UAction.ACTION_LOGIN:

                if (isFragmentExistsInBackStack(LoginActivityFragment.TAG)) {
                    if (getTopFragment() instanceof LoginActivityFragment)
                        return;
                    popBackStack(LoginActivityFragment.TAG, 0);

                } else {
                    fragment = new LoginActivityFragment();
                    bundle = new Bundle();
                    addFragment(R.id.content_frame, fragment,
                            LoginActivityFragment.TAG);
                }
                break;

            case UAction.ACTION_SIGN_UP:

                if (isFragmentExistsInBackStack(SignUpFragment.TAG)) {
                    if (getTopFragment() instanceof SignUpFragment)
                        return;
                    popBackStack(SignUpFragment.TAG, 0);

                } else {
                    fragment = new SignUpFragment();
                    bundle = new Bundle();
                    addFragment(R.id.content_frame, fragment,
                            SignUpFragment.TAG);
                }
                break;

            case UAction.ACTION_PROFILE:

                if (isFragmentExistsInBackStack(ProfileFragment.TAG)) {
                    if (getTopFragment() instanceof ProfileFragment)
                        return;
                    popBackStack(ProfileFragment.TAG, 0);

                } else {
                    fragment = new ProfileFragment();
                    bundle = new Bundle();
                    addFragment(R.id.content_frame, fragment,
                            ProfileFragment.TAG);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getTopFragment();
        if (fragment != null
                && (fragment instanceof LoginActivityFragment
                || fragment instanceof ProfileFragment)) {
            long t = System.currentTimeMillis();
            if (t - backPressedTime > 2000) {
                backPressedTime = t;
                Toast.makeText(this, "Press back again to exit",
                        Toast.LENGTH_SHORT).show();
            } else {
                finish();
                // super.onBackPressed();
            }

        } else {
            super.onBackPressed();
        }
    }
}
