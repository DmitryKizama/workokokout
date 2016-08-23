package tony.workout.data;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "InputUserSettings")
public class UsersSettings extends Model {

    private static final String LANGUAGE = "language";
    private static final String RATE = "rate";

    @Column(name = LANGUAGE)
    private String language;

    @Column(name = RATE)
    private boolean rate;

    public static void create() {
        UsersSettings settings = getUsersSettings();
        if (settings == null) {
            settings = new UsersSettings();
            settings.language = "en_US";
            settings.rate = false;
            settings.save();
        }
    }

    public void setRate(boolean rate) {
        this.rate = rate;
        save();
    }

    public boolean getRate() {
        return rate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String lan) {
        this.language = lan;
        save();
    }

    public static UsersSettings getUsersSettings() {
        return new Select().from(UsersSettings.class).executeSingle();
    }

}
