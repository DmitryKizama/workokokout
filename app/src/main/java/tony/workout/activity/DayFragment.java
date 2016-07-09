package tony.workout.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tony.workout.data.InputData;
import tony.workout.R;
import tony.workout.adapters.TraningAdapter;
import tony.workout.helper.Input;

public class DayFragment extends Fragment {
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    static final String ARGUMENT_WORKOUT_NAME = "arg_name";
    static final String ARGUMENT_WORKOUT_APPROACHES = "arg_approaches";
    static final String ARGUMENT_WORKOUT_REPETITIONS = "arg_repetition";
    static final String ARGUMENT_COUNT_INPUT = "arg_count_input";

    int backColor;
    String msg = "";
    int page;
    int countInputs;
    RecyclerView rv;
    TraningAdapter adapter;
    List<Input> lst;
    private InputData inData;

    public static DayFragment newInstance(int page, List<Input> dayListExercise) {
        DayFragment d = new DayFragment();
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_PAGE_NUMBER, page);

        int i = 0;
        for (Input in : dayListExercise) {
            args.putSerializable(ARGUMENT_WORKOUT_NAME + i, in);
            i++;
        }
        args.putInt(ARGUMENT_COUNT_INPUT, i);
        d.setArguments(args);
        return d;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lst = new ArrayList();
        for (int i = 0; i < getArguments().getInt(ARGUMENT_COUNT_INPUT); i++) {
            Input in = (Input) getArguments().getSerializable(ARGUMENT_WORKOUT_NAME + i);
            lst.add(in);
        }
        page = getArguments().getInt(ARGUMENT_PAGE_NUMBER);

        Random rnd = new Random();
        backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public void addNewItem(Input input) {
        if (inData == null) {
            inData = new InputData(input.getWorkoutName(), input.getNumber_of_approaches(), input.getNumber_of_repetitions());
        } else {
            inData.setName(input.getWorkoutName());
            inData.setApproaches(input.getNumber_of_approaches());
            inData.setRepetition(input.getNumber_of_repetitions());
            inData.save();
        }
        adapter.onAdd(input);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day_fragment, container, false);

        Log.d("My", "Enter to creat view");
        Log.d("My", "count = " + countInputs);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        adapter = new TraningAdapter(lst);
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(mLayoutManager);


        return view;
    }


}
