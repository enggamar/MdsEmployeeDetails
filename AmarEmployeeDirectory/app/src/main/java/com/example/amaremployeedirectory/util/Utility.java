package com.example.amaremployeedirectory.util;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

import com.example.amaremployeedirectory.database.table.EmployeeAuthTable;
import com.example.amaremployeedirectory.database.table.EmployeeDetailTable;
import com.example.amaremployeedirectory.model.EmployeeAuthModel;
import com.example.amaremployeedirectory.model.EmployeeMainModel;

import java.util.regex.Pattern;

/**
 * Created by Amar on 6/4/2016.
 */
public class Utility {
    public static boolean isNotLowerVersion(int versionCode) {
        return Build.VERSION.SDK_INT >= versionCode;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static Animator[] getAnimators(View view, boolean isIn) {
        if (isIn) {
            return new Animator[]{ /*ObjectAnimator.ofFloat(view,
                    "translationY", Utilities.deviceHeight - view.getY(), 0),*/
                    ObjectAnimator.ofFloat(view, "alpha", 0f, 1f),
                    ObjectAnimator.ofFloat(view, "scaleX", 0, 1),
                    ObjectAnimator.ofFloat(view, "scaleY", 0, 1),
             /*ObjectAnimator.ofFloat(view, "translationX",
             view.getRootView().getWidth(), 0)*/
            };
        } else {
            return new Animator[]{ /*ObjectAnimator.ofFloat(
                    view,
					"translationY",
					view.getY()>view.getHeight()?-view.getY():-view.getHeight() , 0),*/
                    ObjectAnimator.ofFloat(view, "alpha", 1f, 0f),
                    ObjectAnimator.ofFloat(view, "scaleX", 1, 0),
                    ObjectAnimator.ofFloat(view, "scaleY", 1, 0),
			/* ObjectAnimator.ofFloat(view, "translationX",
			 0, -view.getRootView().getWidth())*/
            };
        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Pattern.compile(
                    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
                            + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
                            + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
            ).matcher(target)
                    .matches();
        }
    }

    public static String generateId(int incremnt) {

        int i = 1 + incremnt;
        String id = "Mds-" + i + "-se";
        return id;
    }
    public static void insertEmployeeAuthInDatabase(EmployeeAuthModel employeeAuthModel){
        if(employeeAuthModel!=null) {
            EmployeeAuthTable employeeAuthTable = new EmployeeAuthTable();
            employeeAuthTable.insertEmployeeDetails(employeeAuthModel);
        }
    }
    public static void updateEmployeeDetailInDatabase(EmployeeMainModel employeeAuthModel){
        if(employeeAuthModel!=null) {
            EmployeeDetailTable employeeAuthTable = new EmployeeDetailTable();
            if(employeeAuthTable.isIdExist(employeeAuthModel.getEmpId())){
                employeeAuthTable.updateEmployeeDetails(employeeAuthModel);
            } else {
                employeeAuthTable.insertEmployeeDetails(employeeAuthModel);
            }
        }
    }
}
