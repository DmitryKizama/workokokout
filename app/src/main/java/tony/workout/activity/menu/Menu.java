package tony.workout.activity.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import tony.workout.R;
import tony.workout.activity.DayActivity;


public class Menu extends Fragment implements SettingsDialog.MenuListener {

    public static DrawerLayout mDrawerLayout;
    private View mFragmentContainerView;
    private View parentView;
    private LinearLayout pandaLayout;
    private TextView tvTip;

    private LinearLayout tvSettings, tvHowToUse, tvTrainings;
//    private LinearLayout tvProfile;

    private MenuCallback listener;

    public interface MenuCallback{
        void onChangeLang();
    }

    @Override
    public void onApplyClicked(int position) {

    }

    @Override
    public void onLangChanged() {
        listener.onChangeLang();
    }

    public Menu() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getFragmentManager();
//        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//            @Override
//            public void onBackStackChanged() {
//                mDrawerLayout.closeDrawer(GravityCompat.START);
//            }
//        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            listener = (MenuCallback) activity;
        } catch (ClassCastException e){
            Log.e("ERROR", "Main activity should implement " + MenuCallback.class);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.menu, container, false);

        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        pandaLayout = (LinearLayout) parentView.findViewById(R.id.linear_layout_in_menu);
        tvHowToUse = (LinearLayout) parentView.findViewById(R.id.tvInfoInMenu);
        tvSettings = (LinearLayout) parentView.findViewById(R.id.tvSettingsInMenu);
//        tvProfile = (LinearLayout) parentView.findViewById(R.id.tvProfileInMenu);
        tvTrainings = (LinearLayout) parentView.findViewById(R.id.tvTrainingInMenu);

        tvTip = (TextView) parentView.findViewById(R.id.text_view_tip);
        tvTip.setText(insertNewTip());

        pandaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GratitudeDialog dialog = new GratitudeDialog(getContext());
                dialog.show();
            }
        });

        tvHowToUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity().getClass() == ActivityHowToUse.class) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    Intent intent = new Intent(getContext(), ActivityHowToUse.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });

        tvSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsDialog settings = new SettingsDialog(getContext(), Menu.this);
                settings.show();
            }
        });

//        tvProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        tvTrainings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity().getClass() == DayActivity.class) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    Intent intent = new Intent(getContext(), DayActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });

        return parentView;
    }

    private void init() {
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    /**
     * Users of this fragment must call this method to set up the navigation drawer interactions.
     *
     * @param fragmentId   The android:id of this fragment in its activity's layout.
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     */
    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private String insertNewTip() {
//        String[] list = {getResources().getString(R.string.Never_say_no_to_panda), getResources().getString(R.string.Follow_the_schedule_go_to_sleep),
//                getResources().getString(R.string.Don_t_forget_about_dumplings_eat), getResources().getString(R.string.Transform_soft_and_fluffy_into_strong_and_fluffy)};
        ArrayList<String> list = new ArrayList();
        list.add(getResources().getString(R.string.never_say_no_to_panda));
        list.add(getResources().getString(R.string.follow_the_schedule_go_to_sleep));
        list.add(getResources().getString(R.string.don_t_forget_about_dumplings_eat));
        list.add(getResources().getString(R.string.transform_soft_and_fluffy_into_strong_and_fluffy));
        list.add(getResources().getString(R.string.it_s_a_high_time));
        list.add(getResources().getString(R.string.start_small));
        list.add(getResources().getString(R.string.why_so_serious));
        list.add(getResources().getString(R.string.why_do_we_fall));
        list.add(getResources().getString(R.string.do_penguins_have_knees));
        Random random = new Random();
        String strTip = list.get(random.nextInt(list.size()));
        return strTip;
    }

}
