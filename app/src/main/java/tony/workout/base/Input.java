package tony.workout.base;

public class Input {

    String workout_name;
    int number_of_approaches;
    int number_of_repetitions;

    public String getWorkout_name() {
        return workout_name;
    }

    public int getNumber_of_approaches() {
        return number_of_approaches;
    }

    public int getNumber_of_repetitions() {
        return number_of_repetitions;
    }

    public void setWorkout_name(String workout_name) {
        this.workout_name = workout_name;
    }

    public void setNumber_of_approaches(int number_of_approaches) {
        this.number_of_approaches = number_of_approaches;
    }

    public void setNumber_of_repetitions(int number_of_repetitions) {
        this.number_of_repetitions = number_of_repetitions;
    }

    @Override
    public String toString() {
        return (getWorkout_name() + " - " + getNumber_of_approaches() + " - " + getNumber_of_repetitions());
    }
}
