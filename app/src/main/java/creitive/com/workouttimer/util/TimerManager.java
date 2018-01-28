package creitive.com.workouttimer.util;

import android.os.Handler;
import android.widget.TextView;

import creitive.com.workouttimer.model.Workout;

/**
 * This class is used for manipulation with exercise time.
 * TimerManager must be used on the main thread.
 */

public class TimerManager {

    private Workout workout;
    private TextView textView;
    private long millisInTotal = 0;
    private long millisOnPause = 0;

    long startTime = 0;

    private Handler timerHandler;
    Runnable timerRunnable;

    public TimerManager(Workout workout, TextView textView) {
        this.workout = workout;
        this.textView = textView;
        timerHandler = new Handler();
        timerRunnable = new Runnable() {

            @Override
            public void run() {
                long millis = System.currentTimeMillis() - startTime;
                millisInTotal = millisOnPause + millis;

                calculateCurrentWorkoutStatus(millisInTotal);


                timerHandler.postDelayed(this, 500);
            }
        };
    }

    public void startStopwatch() {
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
    }

    public void stopStopwatch() {
        timerHandler.removeCallbacks(timerRunnable);
        millisOnPause = 0;
        millisInTotal = 0;
        workout.reloadWorkout();
    }

    public void pauseStopwatch() {
        millisOnPause = millisInTotal;
        timerHandler.removeCallbacks(timerRunnable);
    }

    private void calculateCurrentWorkoutStatus(long millisInTotal) {

        //Workout is finished
        if (workout.getTotalWorkoutTime() <= millisInTotal) {
            stopStopwatch();
            workout.reloadWorkout();
            //And set finish sound
        } else {
            if (millisInTotal > workout.getCurrentWorkoutCycle().getCycleEndTimeInMillis()) {
                workout.nextCycle();
                Utils.SingleToast.show(textView.getContext(), workout.getCurrentWorkoutCycle().getCycleName());
            }
            //only what is shown to user
            int seconds = (int) (((workout.getCurrentWorkoutCycle().getCycleEndTimeInMillis() - millisInTotal)-100) / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            textView.setText(String.format("%02d:%02d", minutes, seconds));
        }
    }

}
