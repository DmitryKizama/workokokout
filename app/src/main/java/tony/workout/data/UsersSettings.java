package tony.workout.data;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "InputUserSettings")
public class UsersSettings extends Model {

    @Column(name = DAY)
    private int day;

}
