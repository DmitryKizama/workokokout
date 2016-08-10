package tony.workout.activity.menu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tony.workout.R;

public class ScreenSlidePageFragmentInHowToUse extends Fragment {

    private static String ARGUMENT_PAGE_NUMBER = "ARGUMENT_PAGE_NUMBER";
    private int page = 0;

    public static ScreenSlidePageFragmentInHowToUse newInstance(int pos) {
        ScreenSlidePageFragmentInHowToUse howToUse = new ScreenSlidePageFragmentInHowToUse();
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_PAGE_NUMBER, pos);
        howToUse.setArguments(args);
        return howToUse;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView;
        switch (page) {
            case 0:
                rootView = (ViewGroup) inflater.inflate(R.layout.how_to_use_one, container, false);
                break;
            case 1:
                rootView = (ViewGroup) inflater.inflate(R.layout.how_to_use_two, container, false);
                break;
            case 2:
                rootView = (ViewGroup) inflater.inflate(R.layout.how_to_use_three, container, false);
                break;
            default:
                rootView = (ViewGroup) inflater.inflate(R.layout.how_to_use_one, container, false);
                break;
        }

        return rootView;
    }
}