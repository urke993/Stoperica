package creitive.com.workouttimer.presenter;

import creitive.com.workouttimer.fragment.TimerFragment;
import creitive.com.workouttimer.fragment.WorkoutListFragment;
import creitive.com.workouttimer.model.OnListItemClickListener;
import creitive.com.workouttimer.model.Workout;
import creitive.com.workouttimer.storage.DataSource;
import creitive.com.workouttimer.util.Utils;

/**
 * Created by Creitive 31 on 17-Oct-17.
 */

public class WorkoutListPresenter extends ActionBarPresenter<WorkoutListFragment> implements OnListItemClickListener {
    @Override
    public void onItemClicked(Workout workout) {
        getView().loadFragment(TimerFragment.getInstance(workout));
    }

    @Override
    public void onDeleteClicked(Workout workout){
        DataSource.getInstance().deleteWorkout(workout);
        loadData();
    }

    public void loadData() {
        getView().setAdapter(DataSource.getInstance().getWorkoutsList(), this);
    }
}
