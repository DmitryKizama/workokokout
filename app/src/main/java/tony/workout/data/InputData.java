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
    public static int idCounter;
//    static {
//        InputData inputData = new Select().from(InputData.class).orderBy(ID + " DESC").executeSingle();
//        if (inputData != null) {
//            idCounter = inputData.idNumber;
//        } else {
//            idCounter = 0;
//        }
//    }

//    public static enum DAY{
//        int MONDAY;
//    }

    @Column(name = NAME)
    private String name;

    @Column(name = APPROACHES)
    private int approaches;

    @Column(name = REPETITION)
    private int repetition;

    @Column(name = ID)
    private int idNumber;


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

    public static InputData create(String name, int apr, int rep){
        InputData data = new InputData();
        data.name = name;
        data.approaches = apr;
        data.repetition = rep;
        data.idNumber = ++idCounter;
        data.save();

        return data;
    }


//    public static List<InputData> getAllByCategory(String catery){
//        InputData data = new InputData();
//    }

    public static InputData findbyId(int id) {
        return new Select().from(InputData.class).where(ID + "= ?", id).executeSingle();
    }

    public static List<InputData> getAll() {
        return new Select().from(InputData.class).execute();
    }

}