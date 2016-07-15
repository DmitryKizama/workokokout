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

import java.util.List;
import java.util.Random;

import tony.workout.R;
import tony.workout.adapters.TraningAdapter;
import tony.workout.data.InputData;

public class DayFragment extends Fragment implements TraningAdapter.AdapterListener {
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
//    static final String ARGUMENT_WORKOUT_NAME = "arg_name";
//    static final String ARGUMENT_WORKOUT_APPROACHES = "arg_approaches";
//    static final String ARGUMENT_WORKOUT_REPETITIONS = "arg_repetition";
//    static final String ARGUMENT_COUNT_INPUT = "arg_count_input";

    int backColor;
    int page;
    int countInputs;
    RecyclerView rv;
    TraningAdapter adapter;
    List<InputData> lst;
    private InputData inData;

    public static DayFragment newInstance(int page) {
        DayFragment d = new DayFragment();
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_PAGE_NUMBER, page);
        d.setArguments(args);
        return d;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        page = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
        lst = InputData.getAllByDay(page);

        Log.d("onCreateFragment", "get all by day" + lst.size());

        Random rnd = new Random();
        backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public void addNewItem(String name, int reppetition, int approaches) {
        if (inData == null) {
            inData = InputData.create(name, reppetition, approaches, page);
        } else {
            inData.setName(name);
            inData.setApproaches(approaches);
            inData.setRepetition(reppetition);
            inData.setDay(page);
            Log.d("PAGE", "page = " + page);
            inData.setIdNumber(inData.getIdNumber() + 1);
            inData.save();
        }
        adapter.onAdd(inData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day_fragment, container, false);

        Log.d("My", "Enter to creat view");
        Log.d("My", "count = " + countInputs);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        adapter = new TraningAdapter(lst, this);
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(mLayoutManager);


        return view;
    }


    @Override
    public void onDeletePressed(int position) {
//        lst.remove(position);
        InputData in = lst.get(position);
        Log.d("onDelete", "id Normal = " + in.getIdNumber());
        Log.d("onDelete", "id Special = " + in.getId());
        InputData in1 = InputData.findbyId(in.getIdNumber());

        in1.delete();
    }
}
