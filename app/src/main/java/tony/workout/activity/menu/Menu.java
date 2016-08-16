package tony.workout.activity.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import tony.workout.R;
import tony.workout.activity.DayActivity;


public class Menu extends Fragment implements SettingsDialog.MenuListener {

    public static DrawerLayout mDrawerLayout;
    private View mFragmentContainerView;
    private View parentView;
    private LinearLayout pandaLayout;

    private LinearLayout tvSettings, tvHowToUse, tvTrainings;
//    private LinearLayout tvProfile;

    @Override
    public void onApplyClicked(int position) {

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
//        listener = (OnSettingsListener) activity;
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

}
