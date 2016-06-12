package tony.workout.base;

import java.util.ArrayList;

abstract public class Day {
    ArrayList<Input> list_exercise = new ArrayList();

    public void setList_exercise(ArrayList<Input> list_exercise) {
        this.list_exercise = list_exercise;
    }

    public ArrayList<Input> getList_exercise() {
        return list_exercise;
    }

    public Input getExerciseAbout(int num){
        return list_exercise.get(num);
    }

}
