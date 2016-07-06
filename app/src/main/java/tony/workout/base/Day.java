package tony.workout.base;

import java.util.ArrayList;
import java.util.List;

abstract public class Day {
    List<Input> list_exercise = new ArrayList();

    public void setList(List<Input> list_exercise) {
        this.list_exercise = list_exercise;
    }

    public List<Input> getList() {
        return list_exercise;
    }

    public Input getOneInput(int num){
        return list_exercise.get(num);
    }

}
