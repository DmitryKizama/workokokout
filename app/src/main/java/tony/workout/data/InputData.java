package tony.workout.data;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "InputDataTR")
public class InputData extends Model {

    public static final String NAME = "NAME_OF_INPUT";
    public static final String ID = "ID_OF_INPUT";
    public static final String APPROACHES = "APPROACHES_OF_INPUT";
    public static final String REPETITION = "REPETITION_OF_INPUT";
    public static final String DAY = "DAY_OF_INPUT";
    public static int idCounter;

    public static final int MONDAY = 0;
    public static final int TUESDAY = 1;
    public static final int WEDNESDAY = 2;
    public static final int THURSDAY = 3;
    public static final int FRIDAY = 4;
    public static final int SATURDAY = 5;
    public static final int SUNDAY = 6;

    @Column(name = NAME)
    private String name;

    @Column(name = APPROACHES)
    private int approaches;

    @Column(name = REPETITION)
    private int repetition;

    @Column(name = ID)
    private int idNumber;

    @Column(name = DAY)
    private int day;


    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getApproaches() {
        return approaches;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setApproaches(int approaches) {
        this.approaches = approaches;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }


    public int getIdNumber() {
        return idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public InputData() {
        super();
    }

    public static InputData create(String name, int rep, int apr, int day) {
        InputData data = new InputData();
        data.name = name;
        data.approaches = apr;
        data.repetition = rep;
        data.day = day;
        data.idNumber = 0;
        data.save();

        return data;
    }

    public static InputData findbyId(long id) {
        return new Select().from(InputData.class).where("Id = ?", id).executeSingle();
    }

    public static List<InputData> getAll() {
        return new Select().from(InputData.class).execute();
    }

    public static List<InputData> getAllByDay(int day) {
        return new Select().from(InputData.class).where(DAY + "= ?", day).execute();
    }

}