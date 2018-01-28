package creitive.com.workouttimer.adapter;

import java.util.List;

import creitive.com.workouttimer.model.OnListItemClickListener;
import creitive.com.workouttimer.model.Workout;

/**
 * Used for handling loading of simple list items
 */

public class ListItemAdapter extends BaseAdapter {

    private int mLayoutId;
    private List<Workout> mList;
    private OnListItemClickListener mListener;

    public ListItemAdapter(int layoutId, List<Workout> list, OnListItemClickListener listener) {
        mLayoutId = layoutId;
        mList = list;
        mListener = listener;
    }

    @Override
    protected Object getItemForPosition(int position) {
        return mList.get(position);
    }

    @Override
    protected OnListItemClickListener getListener() {
        return mListener;
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return mLayoutId;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
