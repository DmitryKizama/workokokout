package tony.workout.data;

import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "InputUserSettings")
public class UsersSettings extends Model {

    private static final String LANGUAGE = "language";

    @Column(name = LANGUAGE)
    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String lan) {
        language = lan;
    }

    public static UsersSettings getUsersSettings() {
        UsersSettings settings;
        try {
            settings = new Select().from(Model.class).executeSingle();
            Log.d("ENTER", "YES");
        } catch (Exception e) {
            Log.d("ENTER", "NO");
            settings = new UsersSettings();
            settings.setLanguage("en_US");
            settings.save();
        }
        return settings;
    }

}
