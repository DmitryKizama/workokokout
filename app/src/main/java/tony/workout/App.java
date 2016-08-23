package tony.workout;

import android.app.Application;
import android.content.Context;

import com.activeandroid.ActiveAndroid;

import tony.workout.data.UsersSettings;
import tony.workout.helper.UIhelper;

public class App extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        appContext = getApplicationContext();
        UIhelper.init(appContext);

        UsersSettings.create();
        UIhelper.setConfigs();


//        Configuration.Builder config = new Configuration.Builder(this);
//        config.addModelClasses(InputData.class);

    }

    public static synchronized Context getAppContext() {
        return appContext;
    }
}
