package creitive.com.workouttimer.app;

import android.app.Application;

import creitive.com.workouttimer.storage.DataSource;

/**
 * General application class that's used to initialize some important things
 */

public class ApplicationController extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        /*//Set font
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/montserrat-regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );*/


        //Set context of data source to be application context
        //This context will be used for initializing shared prefs
        DataSource.getInstance().setContext(this);
    }
}
