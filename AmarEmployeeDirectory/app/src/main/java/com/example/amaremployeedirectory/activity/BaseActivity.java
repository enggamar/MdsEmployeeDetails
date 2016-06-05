package com.example.amaremployeedirectory.activity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.amaremployeedirectory.R;
import com.example.amaremployeedirectory.util.UserActionListener;
import com.example.amaremployeedirectory.util.Utility;

/**
 * Created by Amar on 6/4/2016.
 */
public abstract class BaseActivity extends AppCompatActivity implements UserActionListener {

    private static long LIST_ANIM_OUT_TIME=300;


    @SuppressLint("NewApi")
    public static void animateToGone(final View view, long time) {
        if (time == -1) {
            time = LIST_ANIM_OUT_TIME;
        }
        if (Utility.isNotLowerVersion(Build.VERSION_CODES.HONEYCOMB)) {

            for (Animator anim : Utility.getAnimators(view, false)) {
                if (anim.isRunning()) {
                } else {
                    anim.setDuration(LIST_ANIM_OUT_TIME).start();
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            if (view != null)
                                view.setVisibility(View.GONE);

                        }
                    }, time);
                }
            }
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @SuppressLint("NewApi")
    public static void animateToVisible(final View view, long time) {
        if (time == -1) {
            time = LIST_ANIM_OUT_TIME;
        }
        if (Utility.isNotLowerVersion(Build.VERSION_CODES.HONEYCOMB)) {

            if (view != null)
                view.setVisibility(View.VISIBLE);
            for (Animator anim : Utility.getAnimators(view, true)) {
                if (anim.isRunning()) {
                } else {
                    anim.setDuration(time).start();
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            if (view != null)
                                view.setVisibility(View.VISIBLE);

                        }
                    }, time);
                }
            }
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }



    /**
     * utility method to find fragment existence in back stack.
     *
     * @param tag
     *            tag of fragment which is to be searched.same as provided
     *            during adding
     * @return true if exist.
     */
    protected boolean isFragmentExistsInBackStack(String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) != null)
            return true;
        else
            return false;
    }

    /**
     * utility method to pop top fragment
     */
    private void popBackStack() {
        getSupportFragmentManager().popBackStack();
    }

    /**
     * utility method for poping back provided tag fragment with
     * inclusive/exclusive flag
     *
     * @param tag
     *            tag to identify fragment.
     * @param flag
     *            inclusive/exclusive flag
     */
    public void popBackStack(String tag, int flag) {
        getSupportFragmentManager().popBackStack(tag, flag);
    }

    /**
     * helper method to retrieve top fragment
     *
     * @return top fragment
     */
    public Fragment getTopFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.content_frame);

    }

    /**
     *
     * utility method for adding fragment
     *
     * @param containerId
     *            viewgroup id to add fragment in.
     * @param fragment
     *            fragment instance to be added.
     * @param tag
     *            tag to be associated with this transaction.
     */
    public void addFragment(final int containerId, final Fragment fragment,
                            final String tag) {
        // fragment.setRetainInstance(true);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment topFragment = getTopFragment();
		/*
		 * if (topFragment instanceof OffersFragment) { FragmentTransaction
		 * trans = fragmentManager.beginTransaction();
		 * trans.remove(topFragment); trans.commit();
		 * fragmentManager.popBackStack(); }
		 */

        try {
            final FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            setFragmentCustomAnimations(fragmentTransaction);
            fragmentTransaction.replace(containerId, fragment, tag);
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();

        } catch (Exception e) {
            try {
                final FragmentTransaction fragmentTransaction = fragmentManager
                        .beginTransaction();
                fragmentTransaction.commitAllowingStateLoss();
            } catch (Exception exception) {
                // exception.printStackTrace();
            }

        }
    }

    protected void setFragmentCustomAnimations(
            FragmentTransaction pFragmentTransaction) {

		/*
		 * pFragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,
		 * android.R.anim.slide_out_right, android.R.anim.slide_in_left,
		 * android.R.anim.slide_out_right);
		 */

		/*
		 * pFragmentTransaction.setCustomAnimations(
		 * R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit,
		 * R.anim.fragment_slide_right_enter, R.anim.fragment_slide_right_exit);
		 */
    }

}
