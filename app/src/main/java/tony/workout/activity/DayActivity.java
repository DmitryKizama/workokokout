package tony.workout.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.astuetz.PagerSlidingTabStrip;
import com.melnykov.fab.FloatingActionButton;

import java.util.Calendar;

import tony.workout.R;
import tony.workout.data.InputData;
import tony.workout.helper.Constant;

public class DayActivity extends AppCompatActivity implements StartDialog.DialogListener, NavigationView.OnNavigationItemSelectedListener {
    static final String TAG = "myLogs";
    static final int PAGE_COUNT = 7;

    private ViewPager pager;
    private MyFragmentPagerAdapter pagerAdapter;
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_day_layout);


        int msgDay = 0;
        int progress = 50;
//        if (getIntent() != null) {
//            msgDay = getIntent().getIntExtra(MainActivity.DAY, 0);
//            progress = getIntent().getIntExtra(MainActivity.PROGRESS, 0);
//        } else {
//            msgDay = Calendar.DAY_OF_WEEK;
//        }

        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                msgDay = InputData.InputDay.MON.getIndex();
                break;
            case Calendar.TUESDAY:
                msgDay = InputData.InputDay.TUE.getIndex();
                break;
            case Calendar.WEDNESDAY:
                msgDay = InputData.InputDay.WED.getIndex();
                break;
            case Calendar.THURSDAY:
                msgDay = InputData.InputDay.THU.getIndex();
                break;
            case Calendar.FRIDAY:
                msgDay = InputData.InputDay.FRI.getIndex();
                break;
            case Calendar.SATURDAY:
                msgDay = InputData.InputDay.SUT.getIndex();
                break;
            case Calendar.SUNDAY:
                msgDay = InputData.InputDay.SUN.getIndex();
                break;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        pager = (ViewPager) findViewById(R.id.pager);
        btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);


        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(msgDay);

//        btnAdd.attachToRecyclerView((RecyclerView) findViewById(R.id.rv));


        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAdd.startAnimation(AnimationUtils.loadAnimation(DayActivity.this, R.anim.rotate_and_scale));
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
                btnAdd.show();
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onAddPressed(String name, int reppetition, int approaches, int weight) {
        pagerAdapter.getRegisteredFragment(pager.getCurrentItem()).addNewItem(name, reppetition, approaches, weight);
        pagerAdapter.notifyDataSetChanged();
        pager.setCurrentItem(pager.getCurrentItem());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

        SparseArray<DayFragment> registeredFragments = new SparseArray<DayFragment>();

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private final String[] TITLES = {Constant.MONDAY, Constant.TUESDAY, Constant.WEDNESDAY, Constant.THURSDAY, Constant.FRIDAY, Constant.SATURDAY,
                Constant.SUNDAY};

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public DayFragment getItem(int position) {
            DayFragment day = DayFragment.newInstance(position, btnAdd);
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
