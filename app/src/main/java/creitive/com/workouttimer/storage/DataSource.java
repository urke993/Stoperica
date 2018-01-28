package creitive.com.workouttimer.storage;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import creitive.com.workouttimer.model.ActionBar;
import creitive.com.workouttimer.model.Settings;
import creitive.com.workouttimer.model.Workout;
import creitive.com.workouttimer.util.Utils;

/**
 * Used for caching
 */

public class DataSource {

    private static final String WORKOUTS = "com.creitive.timer.workouts";
    private static final String SETTINGS = "com.creitive.timer.settings";
    private static final String PREFS_KEY = "com.creitive.craftmaster.prefs";

    private static DataSource sInstance;
    private ActionBar mActionBar;
    private SharedPreferences mPrefs;
    private Context mContext;

    private List<Workout> workoutsList;
    private Settings mSettings;

    /**
     * @return Current action bar that's linked with the Activity's toolbar or initializes one if
     * there isn't a current action bar
     */
    public ActionBar getActionBar() {
        if (mActionBar == null)
            mActionBar = new ActionBar();
        return mActionBar;
    }

    public static DataSource getInstance() {
        if (sInstance == null)
            sInstance = new DataSource();
        return sInstance;
    }

    public void setContext(Context context) {
        mContext = context;
        mPrefs = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
    }

    /**
     * Returns list of workouts from shared prefs.
     * There are few predefined workouts when user install application for the first time.
     */
    public List<Workout> getWorkoutsList() {
        if (workoutsList == null) {
            workoutsList = Utils.General.deserializeFromJson(mPrefs.getString(WORKOUTS, ""),
                    new TypeToken<ArrayList<Workout>>() {
                    }.getType());
            if (workoutsList == null) {
                workoutsList = Workout.getPredefinedWorkouts();
                saveWorkouts();
            }
        }
        return workoutsList;
    }

    /**
     * For now this method is unused
     */
    public void setWorkoutsList(List<Workout> workoutsList) {
        this.workoutsList = workoutsList;
    }

    /**
     * Add and save workout into list of workouts
     * @param workout
     */
    public void addWorkout(Workout workout) {
        workoutsList.add(workout);
        saveWorkouts();
    }

    public void deleteWorkout(Workout workout){
        workoutsList.remove(workout);
        saveWorkouts();
    }

    /**
     * Save workouts in shared prefs
     */
    public void saveWorkouts(){
        mPrefs.edit().putString(WORKOUTS, Utils.General.serializeToJson(workoutsList)).apply();
    }

    /**
     * Returns app settings
     */
    public Settings getSettings() {
        if (mSettings == null) {
            Settings settings = Utils.General.deserializeFromJson(mPrefs.getString(SETTINGS, ""), Settings.class);
            if (settings == null) {
                mSettings = new Settings(true, true, true, true);
            } else mSettings = settings;
        }
        return mSettings;
    }

    /**
     * Saves the app settings
     */
    public void saveSettings(Settings settings) {
        this.mSettings = settings;
        mPrefs.edit().putString(SETTINGS, Utils.General.serializeToJson(mSettings)).apply();
    }
}
