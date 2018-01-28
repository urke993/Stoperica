package creitive.com.workouttimer.storage;

import android.databinding.BindingAdapter;
import android.databinding.InverseMethod;
import android.support.annotation.IntegerRes;
import android.view.View;
import android.widget.TextView;

import creitive.com.workouttimer.util.Utils;

/**
 * Created by Uros on 10/23/2017.
 */

public class BindingAdapters {

    @BindingAdapter({"formatDuration", "formatPreText"})
    public static void formatDuration(TextView textView, Integer millis, String preText) {
        if (millis == null) return;
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;
        textView.setText(preText + String.format("%02d:%02d", minutes, seconds));
    }

    @BindingAdapter({"formatDurationWithText", "formatPreText"})
    public static void formatDurationWithText(TextView textView, Integer millis, String preText) {
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;
        textView.setText(preText + (minutes > 0 ? String.format("%2dm %2ds", minutes, seconds) : String.format("%2ds", seconds)));
    }
}
