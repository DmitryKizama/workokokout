package tony.workout.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import tony.workout.R;


public class Menu extends Fragment implements SettingsDialog.MenuListener{

    private DrawerLayout mDrawerLayout;
    private View mFragmentContainerView;
    private View parentView;

    private LinearLayout tvSettings, tvProfile, tvHowToUse;

    @Override
    public void onApplyClicked(int position) {

    }

    public Menu() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.menu, container, false);
        tvHowToUse = (LinearLayout) parentView.findViewById(R.id.tvInfoInMenu);
        tvSettings = (LinearLayout) parentView.findViewById(R.id.tvSettingsInMenu);
        tvProfile = (LinearLayout) parentView.findViewById(R.id.tvProfileInMenu);

        tvHowToUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsDialog settings = new SettingsDialog(getContext(), Menu.this);
                settings.show();
            }
        });

        tvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
