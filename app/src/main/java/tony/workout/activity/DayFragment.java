package tony.workout.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import tony.workout.R;
import tony.workout.adapters.TraningAdapter;
import tony.workout.data.InputData;

public class DayFragment extends Fragment implements TraningAdapter.AdapterListener {
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
//    static final String ARGUMENT_WORKOUT_NAME = "arg_name";
//    static final String ARGUMENT_WORKOUT_APPROACHES = "arg_approaches";
//    static final String ARGUMENT_WORKOUT_REPETITIONS = "arg_repetition";
//    static final String ARGUMENT_COUNT_INPUT = "arg_count_input";

    int page;
    int countInputs;
    RecyclerView rv;
    TraningAdapter adapter;
    List<InputData> lst;
    private InputData inData;
    private static FloatingActionButton btn;
    private int idPreviouse;

    public static DayFragment newInstance(int page, FloatingActionButton btnAdd) {
        DayFragment d = new DayFragment();
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_PAGE_NUMBER, page);
        btn = btnAdd;
        d.setArguments(args);
        return d;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        page = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
        lst = InputData.getAllByDay(page);
        Log.d("onCreateFragment", "get all by day" + lst.size());

    }

    public void addNewItem(String name, int reppetition, int approaches, int weight) {
        if (inData == null) {
            idPreviouse = 0;
        } else {
            inData.setIdNumber(idPreviouse + 1);
        }
        inData = InputData.create(name, reppetition, approaches, weight, page);
        if (idPreviouse == 0) {// IT WORKS, I DON'T CARE!
            inData.setIdNumber(0);
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
        btn.attachToRecyclerView(rv);
        adapter = new TraningAdapter(lst, this, getContext());
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
//        Log.d("onDelete", "id Normal = " + in.getIdNumber());
//        Log.d("onDelete", "id Special = " + in.getId());
        InputData in1 = InputData.findbyId(in.getId());

        in1.delete();
    }
}
