package creitive.com.workouttimer.fragment;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import creitive.com.workouttimer.R;
import creitive.com.workouttimer.adapter.ListItemAdapter;
import creitive.com.workouttimer.databinding.FragmentWorkoutListBinding;
import creitive.com.workouttimer.model.ActionBar;
import creitive.com.workouttimer.model.OnListItemClickListener;
import creitive.com.workouttimer.model.Workout;
import creitive.com.workouttimer.presenter.WorkoutListPresenter;
import creitive.com.workouttimer.storage.DataSource;

/**
 * Created by Creitive 31 on 17-Oct-17.
 */

public class WorkoutListFragment extends BaseFragment {

    private RecyclerView rvWorkouts;
    private WorkoutListPresenter mPresenter;

    public WorkoutListFragment() {
        // Required empty public constructor
    }

    @Override
    protected View init(LayoutInflater inflater, ViewGroup container) {
        FragmentWorkoutListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_workout_list, container, false);
        rvWorkouts = binding.getRoot().findViewById(R.id.rvWorkouts);

        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        rvWorkouts.setLayoutManager(lm);

        mPresenter = new WorkoutListPresenter();
        mPresenter.bindView(this);
        mPresenter.loadData();

        binding.setPresenter(mPresenter);

        return binding.getRoot();
    }

    public void setAdapter(List<Workout> workouts, OnListItemClickListener listener) {
        ListItemAdapter adapter = new ListItemAdapter(R.layout.list_item, workouts, listener);
        rvWorkouts.setAdapter(adapter);
    }

    @Override
    protected ActionBar setActionBar() {
        ActionBar ab = DataSource.getInstance().getActionBar();
        ab.setBackButton(false);
        ab.setTitle(getString(R.string.workouts));
        return ab;
    }
}
