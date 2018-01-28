package creitive.com.workouttimer.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import creitive.com.workouttimer.activity.BaseActivity;
import creitive.com.workouttimer.model.ActionBar;
import creitive.com.workouttimer.presenter.ActionBarPresenter;
import creitive.com.workouttimer.storage.DataSource;


/**
 * Unites common functionality of all fragments
 */
public abstract class BaseFragment extends Fragment {
    public static final String ARGS_KEY = "com.timer.bundle";
    public BaseActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Initialize all stuff
        return init(inflater, container);
    }

    /**
     * What we do in this method is the same as what we do in the onCreateView method
     *
     * @param inflater  used to inflate views
     * @param container parent view
     */
    protected abstract View init(LayoutInflater inflater, ViewGroup container);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.setCurrentFragment(this);
        setActionBar();
    }

    protected ActionBar setActionBar() {
        ActionBar ab = DataSource.getInstance().getActionBar();
        ab.setTitle(null);
        return ab;
    }

    public ActionBarPresenter getPresenter() {
        return new ActionBarPresenter();
    }

    /**
     * Calls the same name method from BaseActivity class
     *
     * @param fragmentToLoad fragment that's going to be loaded instead of current one
     */
    public void loadFragment(BaseFragment fragmentToLoad) {
        mActivity.loadFragment(fragmentToLoad);
    }
}
