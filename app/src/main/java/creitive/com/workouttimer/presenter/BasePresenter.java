package creitive.com.workouttimer.presenter;

import android.content.Context;
import android.databinding.BaseObservable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

/**
 * Used for general stuff that all presenters need
 */
public abstract class BasePresenter<V> extends BaseObservable {
    protected WeakReference<V> mView;
    private Context mContext;

    public BasePresenter() {}

    /**
     * bindView binds View to Presenter
     */
    public void bindView(V view) {
        if (view instanceof AppCompatActivity) {
            mContext = (AppCompatActivity) view;
        } else {
            mContext = ((Fragment) view).getContext();
        }
        mView = new WeakReference<>(view);
    }

    /**
     * unbindView unbinds View from Presenter
     */
    public void unbindView() {
        mView = null;
    }

    /**
     * Presenter uses getView to get attached View
     */
    public V getView() {
        if (mView == null) {
            return null;
        } else {
            return mView.get();
        }
    }

    public Context getContext() {
        return mContext;
    }

    /**
     * Gets the string from resource provided
     *
     * @param resId string resource
     * @return String class read from resource
     */
    public String getString(int resId) {
        return getContext().getString(resId);
    }
}
