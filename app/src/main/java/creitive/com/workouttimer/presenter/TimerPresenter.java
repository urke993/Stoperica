package creitive.com.workouttimer.presenter;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import creitive.com.workouttimer.BR;
import creitive.com.workouttimer.fragment.TimerFragment;
import creitive.com.workouttimer.util.TimerManager;

/**
 * Created by Creitive 31 on 12-Oct-17.
 */

public class TimerPresenter extends ActionBarPresenter<TimerFragment> {
    private TimerManager mTimerManager;
    private boolean playing = false;

    public void startTimer() {
        mTimerManager.startStopwatch();
        setPlaying(true);
    }

    public void pauseTimer() {
        mTimerManager.pauseStopwatch();
        setPlaying(false);
    }

    public void stopTimer() {
        mTimerManager.stopStopwatch();
        setPlaying(false);
    }

    public void refreshTimer() {
        mTimerManager.stopStopwatch();
        setPlaying(false);
    }

    public void setTimerManager(TimerManager timerManager) {
        mTimerManager = timerManager;
    }

    @Bindable
    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
        notifyPropertyChanged(BR.playing);
    }

}
