package creitive.com.workouttimer.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import creitive.com.workouttimer.R;
import creitive.com.workouttimer.databinding.FragmentTimerBinding;
import creitive.com.workouttimer.model.ActionBar;
import creitive.com.workouttimer.model.Workout;
import creitive.com.workouttimer.presenter.TimerPresenter;
import creitive.com.workouttimer.storage.DataSource;
import creitive.com.workouttimer.util.TimerManager;
import creitive.com.workouttimer.util.Utils;

/**
 * Created by Creitive 31 on 12-Oct-17.
 */

public class TimerFragment extends BaseFragment {

    private TextView tvSeconds;
    private Workout mWorkout;

    public TimerFragment() {
    }

    @Override
    protected View init(LayoutInflater inflater, ViewGroup container) {
        FragmentTimerBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timer, container, false);
        View view = binding.getRoot();

        TimerPresenter mPresenter = new TimerPresenter();
        mPresenter.bindView(this);
        binding.setPresenter(mPresenter);

        tvSeconds = view.findViewById(R.id.tvSeconds);
        mWorkout =  Utils.General.deserializeFromJson(getArguments().getString(ARGS_KEY), Workout.class);
        binding.setWorkout(mWorkout);

        TimerManager timerManager = new TimerManager(mWorkout, tvSeconds);
        mPresenter.setTimerManager(timerManager);

     /*   btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerManager.startStopwatch();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerManager.stopStopwatch();
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerManager.pauseStopwatch();
            }
        });
*/
        return view;
    }

    public static BaseFragment getInstance(Workout workout) {
        Bundle arg = new Bundle();
        arg.putString(ARGS_KEY, Utils.General.serializeToJson(workout));
        TimerFragment instance = new TimerFragment();
        instance.setArguments(arg);
        return instance;
    }
    @Override
    protected ActionBar setActionBar() {
        ActionBar ab = DataSource.getInstance().getActionBar();
        ab.setBackButton(true);
        ab.setTitle(null);
        return ab;
    }
}
