package tony.workout.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import java.util.Calendar;

import mehdi.sakout.fancybuttons.FancyButton;
import tony.workout.R;
import tony.workout.helper.AnimationAddButton;
import tony.workout.helper.Constant;

public class DayActivity extends AppCompatActivity implements StartDialog.DialogListener {
    static final String TAG = "myLogs";
    static final int PAGE_COUNT = 7;

    private ViewPager pager;
    private MyFragmentPagerAdapter pagerAdapter;
    private FancyButton btnAdd;
    private NavigationSettingsFragment mNavigationSettingsFragment;
    private DrawerLayout mDrawerLayout;

//    private DayFragment dayFragment;
    private ViewGroup ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_activity);

        mNavigationSettingsFragment = (NavigationSettingsFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        mNavigationSettingsFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

//        dayFragment = (DayFragment) getFragmentManager().findFragmentById(R.id.rv);

        ll = (ViewGroup) findViewById(R.id.layout_in_day_activity);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.placeholder, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerSlide(View drawerView, float slideOffset) {
                float moveFactor = (getResources().getDimension(R.dimen.navdrawer_width) * slideOffset);
                ll.setTranslationX(moveFactor);
            }
        };

        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mDrawerLayout.setElevation(0);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        int msgDay = 0;
        Calendar c = Calendar.getInstance();
        msgDay = c.get(Calendar.DAY_OF_WEEK) - 2;

        pager = (ViewPager) findViewById(R.id.pager);
        btnAdd = (FancyButton) findViewById(R.id.btnAdd);

        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), btnAdd);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(msgDay);

//        btnAdd.attachToRecyclerView((RecyclerView) findViewById(R.id.rv));


        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                btnAdd.startAnimation(AnimationUtils.loadAnimation(DayActivity.this, R.anim.rotate_and_scale));
                StartDialog dialog = new StartDialog(DayActivity.this, DayActivity.this);
                dialog.show();
            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected, position = " + position);
//                pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
//                pager.setAdapter(pagerAdapter);
                if (!AnimationAddButton.btnAddShow) {
                    AnimationAddButton.showButton(btnAdd);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

//                Log.d(TAG, "onPageScrolled, position = " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                Log.d(TAG, "onPageScrollStateChanged, position = " + state);
            }
        });
    }

    @Override
    public void onAddPressed(String name, int reppetition, int approaches, int weight) {
        pagerAdapter.getRegisteredFragment(pager.getCurrentItem()).addNewItem(name, reppetition, approaches, weight);
        pagerAdapter.notifyDataSetChanged();
        pager.setCurrentItem(pager.getCurrentItem());
    }


    private class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

        SparseArray<DayFragment> registeredFragments = new SparseArray<DayFragment>();
        FancyButton btn;

        public MyFragmentPagerAdapter(FragmentManager fm, FancyButton btnAddInDayActivity) {
            super(fm);
            this.btn = btnAddInDayActivity;
        }

        private final String[] TITLES = {Constant.MONDAY, Constant.TUESDAY, Constant.WEDNESDAY, Constant.THURSDAY, Constant.FRIDAY, Constant.SATURDAY,
                Constant.SUNDAY};

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public DayFragment getItem(int position) {
            DayFragment day = DayFragment.newInstance(position, btn);
            return day;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            DayFragment fragment = (DayFragment) super.instantiateItem(container, position);
            registeredFragments.put(position, fragment);
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            registeredFragments.remove(position);
            super.destroyItem(container, position, object);
        }

        public DayFragment getRegisteredFragment(int position) {
            return registeredFragments.get(position);
        }

    }


}
