package tony.workout;

import android.app.Application;
import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

import tony.workout.data.InputData;
import tony.workout.helper.UIhelper;

public class App extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();

        UIhelper.init(appContext);
//        Configuration.Builder config = new Configuration.Builder(this);
//        config.addModelClasses(InputData.class);
        ActiveAndroid.initialize(this);


    }

    public static synchronized Context getAppContext() {
        return appContext;
    }
}
