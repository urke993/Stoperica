package creitive.com.workouttimer.model;

/**
 * Used by presenters to handle recyclerview item clicks
 */

public interface OnListItemClickListener {
    void onItemClicked(Workout item);
    void onDeleteClicked(Workout workout);
}
