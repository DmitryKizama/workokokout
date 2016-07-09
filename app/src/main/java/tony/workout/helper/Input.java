package tony.workout.helper;

import java.io.Serializable;

public class Input implements Serializable{

    String workoutName;
    int number_of_approaches;
    int number_of_repetitions;

    public Input(String name, int approaches, int repetition){
        workoutName = name;
        number_of_approaches = approaches;
        number_of_repetitions = repetition;
    }

    public Input(){}

    public String getWorkoutName() {
        return workoutName;
    }

    public int getNumber_of_approaches() {
        return number_of_approaches;
    }

    public int getNumber_of_repetitions() {
        return number_of_repetitions;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public void setNumber_of_approaches(int number_of_approaches) {
        this.number_of_approaches = number_of_approaches;
    }

    public void setNumber_of_repetitions(int number_of_repetitions) {
        this.number_of_repetitions = number_of_repetitions;
    }

    @Override
    public String toString() {
        return (getWorkoutName() + " - " + getNumber_of_approaches() + " - " + getNumber_of_repetitions());
    }
}
