package creitive.com.workouttimer.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import creitive.com.workouttimer.BR;


/**
 * ActionBar represents an object that corresponds to the actionbar look while the app is running
 */
public class ActionBar extends BaseObservable {

    private String mTitle;
    private boolean backButtonVisible;

    @Bindable
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public boolean isBackButtonVisible() {
        return backButtonVisible;
    }

    public void setBackButton(boolean backButtonVisible) {
        this.backButtonVisible = backButtonVisible;
        notifyPropertyChanged(BR.backButtonVisible);
    }

}
