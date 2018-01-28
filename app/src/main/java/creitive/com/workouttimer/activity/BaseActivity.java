package creitive.com.workouttimer.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import creitive.com.workouttimer.BR;
import creitive.com.workouttimer.R;

import creitive.com.workouttimer.databinding.ActivityBaseBinding;
import creitive.com.workouttimer.fragment.BaseFragment;
import creitive.com.workouttimer.presenter.ActionBarPresenter;
import creitive.com.workouttimer.storage.DataSource;
import creitive.com.workouttimer.util.Utils;


/**
 * Activity that unites common functionality
 */
public abstract class BaseActivity extends AppCompatActivity {
    private BaseFragment mCurrentFragment;
    private ActivityBaseBinding mBinding;
    protected static final String ARGS_KEY = "com.creitive.timer.args";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mBinding.setVariable(BR.actionBar, DataSource.getInstance().getActionBar());
        init();
    }

    /**
     * Sets the layout id to the baseActivity
     *
     * @return layout that will be displayed
     */
    protected int getLayoutId() {
        return R.layout.activity_base;
    }

    /**
     * Method implemented in child activity representing the content of onCreate method
     */
    protected abstract void init();

    public void loadFragment(BaseFragment fragmentToLoad) {
        loadFragment(fragmentToLoad, true);
    }

    /**
     * Loads fragments without animation
     **/
    public void loadFragmentWithoutAnimation(Fragment fragmentToLoad) {
        //If we are trying to load the same fragment that is being currently displayed don't let it
        if (mCurrentFragment != null && Utils.General.compareClasses(mCurrentFragment, fragmentToLoad)) {
            return;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flContent, fragmentToLoad)
                .addToBackStack(fragmentToLoad.getClass().getName())
                .commit();
    }
    /**
     * Loads fragments with animation going forward or backward dependent on loadForward
     **/
    public void loadFragment(Fragment fragmentToLoad, boolean loadForward) {
        int animationInId, animationOutId;
        int animationInIdOut, animationOutIdOut;
        if (loadForward) {
            animationInId = R.anim.right_in;
            animationOutId = R.anim.left_out;

            animationInIdOut = R.anim.in_from_left;
            animationOutIdOut = R.anim.out_to_right;
        } else {
            animationInId = R.anim.in_from_left;
            animationOutId = R.anim.out_to_right;

            animationInIdOut = R.anim.right_in;
            animationOutIdOut = R.anim.left_out;
        }

        //If we are trying to load the same fragment that is being currently displayed don't let it
        if (mCurrentFragment != null && Utils.General.compareClasses(mCurrentFragment, fragmentToLoad)) {
            return;
        }
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(animationInId, animationOutId, animationInIdOut, animationOutIdOut)
                .replace(R.id.flContent, fragmentToLoad)
                .addToBackStack(fragmentToLoad.getClass().getName())
                .commit();
    }

    /**
     * Loads a new Activity
     **/
    public void loadActivity(Class<? extends Activity> activityToLoad) {
        Intent i = new Intent(this, activityToLoad);
        activityStart(i, true);
        this.finish();
    }

    /**
     * Used to start new Activity with slide in transition
     **/
    public void activityStart(Intent i, boolean loadForward) {
        startActivity(i);
        if (loadForward)
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        else
            overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

    public ViewDataBinding getBinding() {
        return mBinding;
    }

    public void setPresenter(int presenterId, ActionBarPresenter presenter) {
        if (presenter != null)
            mBinding.setVariable(presenterId, presenter);
    }

    public void setCurrentFragment(BaseFragment loadedFragment) {
        mCurrentFragment = loadedFragment;
        /*if (loadedFragment.getPresenter() != null)
            mBinding.setPresenter(loadedFragment.getPresenter());*/
    }
    @Override
    public void onBackPressed() {
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntryCount == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

}


