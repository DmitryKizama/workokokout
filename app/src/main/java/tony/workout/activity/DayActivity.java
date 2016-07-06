package tony.workout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import tony.workout.R;
import tony.workout.base.Day;
import tony.workout.base.Friday;
import tony.workout.base.Input;
import tony.workout.base.Monday;
import tony.workout.base.Saturday;
import tony.workout.base.Sunday;
import tony.workout.base.Thursday;
import tony.workout.base.Tuesday;
import tony.workout.base.Wednesday;
import tony.workout.helper.Constant;

public class DayActivity extends FragmentActivity {
    static final String TAG = "myLogs";
    static final int PAGE_COUNT = 7;

    private ViewPager pager;
    private MyFragmentPagerAdapter pagerAdapter;
    private Button btnAdd;
    private Intent day;
    private int msgDay;
    private List<Day> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_activity);

        day = getIntent();
        getIntentInfo();
        list = new ArrayList();
        final Monday mon = new Monday();
        Tuesday tue = new Tuesday();
        Wednesday wed = new Wednesday();
        Thursday thu = new Thursday();
        Friday fri = new Friday();
        Saturday sut = new Saturday();
        Sunday sun = new Sunday();
        list.add(mon);
        list.add(tue);
        list.add(wed);
        list.add(thu);
        list.add(fri);
        list.add(sut);
        list.add(sun);

        pager = (ViewPager) findViewById(R.id.pager);
        btnAdd = (Button) findViewById(R.id.btnAdd);


        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), list);

        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(msgDay);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Input in = new Input("huy", 2, 1);
                Log.d("My", "courent item = " + pager.getCurrentItem());
                pagerAdapter.getRegisteredFragment(pager.getCurrentItem()).addNewItem(in);
                Log.d("My", "notify data changed");

                Log.d("My", "list size = " + list.get(pager.getCurrentItem()).getList().size());
                pagerAdapter.notifyDataSetChanged();
//                pager.setCurrentItem(pager.getCurrentItem());
            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected, position = " + position);
//                pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
//                pager.setAdapter(pagerAdapter);
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

    private class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

        SparseArray<DayFragment> registeredFragments = new SparseArray<DayFragment>();
        private List<Day> programs;

        public Day justGetItem(int i) {
            return programs.get(i);
        }

        public MyFragmentPagerAdapter(FragmentManager fm, List<Day> programs) {
            super(fm);
            this.programs = programs;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String msg;
            switch (position) {
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
            return msg;
        }

        @Override
        public DayFragment getItem(int position) {
            DayFragment day = DayFragment.newInstance(position, programs.get(position));
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

    private void getIntentInfo() {
        if (day != null) {
            msgDay = day.getIntExtra(MainActivity.DAY, 0);
        }
    }

}
