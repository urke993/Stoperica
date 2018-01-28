package creitive.com.workouttimer.activity;

import creitive.com.workouttimer.fragment.WorkoutListFragment;

/**
 * Created by Creitive 31 on 12-Oct-17.
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void init() {
        loadFragmentWithoutAnimation(new WorkoutListFragment());
    }
}
