package tony.workout.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

import tony.workout.R;
import tony.workout.helper.Constant;

public class DayFragment extends Fragment {
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    int backColor;
    String msg;

    static DayFragment newInstance(int page) {
        DayFragment pageFragment = new DayFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);

        switch (pageNumber){
            case 0:
                msg = Constant.MONDAY;
                break;
            case 1:
                msg = Constant.TUESDAY;
                break;
            case 2:
                msg = Constant.WEDNESDAY;
                break;
            case 3:
                msg = Constant.THURSDAY;
                break;
            case 4:
                msg = Constant.FRIDAY;
                break;
            case 5:
                msg = Constant.SATURDAY;
                break;
            case 6:
                msg = Constant.SUNDAY;
                break;
            default:
                msg = "ERROR, PLEASE TURN TO GOD FOR HELP";
                break;
        }

        Random rnd = new Random();
        backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day_fragment, null);

        TextView tvPage = (TextView) view.findViewById(R.id.tvPage);
        tvPage.setText(msg);
        tvPage.setBackgroundColor(backColor);

        return view;
    }


}
