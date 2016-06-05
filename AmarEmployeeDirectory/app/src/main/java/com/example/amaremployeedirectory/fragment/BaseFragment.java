package com.example.amaremployeedirectory.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amaremployeedirectory.util.UserActionListener;

/**
 * Created by Amar on 6/4/2016.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    protected UserActionListener mUserActionListener;
    protected int layoutId=-1;
    protected boolean isActionBarVisible;
    protected View rootView;

    protected abstract void setupUi(Bundle savedInstanceState);

    @Override
    public void onAttach(Activity activity) {
        if (activity instanceof UserActionListener) {
            mUserActionListener = (UserActionListener) activity;
        }
        super.onAttach(activity);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(layoutId, container, false);
        setupUi(savedInstanceState);
        return rootView;//super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onClick(View v) {

    }
}
