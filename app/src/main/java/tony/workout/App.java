package tony.workout;

import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;

import tony.workout.helper.UIhelper;

public class App extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();

        UIhelper.init(appContext);
        ActiveAndroid.initialize(this);
    }

    public static synchronized Context getAppContext(){
        return appContext;
    }
}
