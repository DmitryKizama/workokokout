package tony.workout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tony.workout.R;
import tony.workout.helper.Constant;

public class DayActivity extends FragmentActivity {
    static final String TAG = "myLogs";
    static final int PAGE_COUNT = 7;

    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private Button btnAdd;
    private Intent day;
    private int msgDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_activity);

        day = getIntent();
        getIntentInfo();

        pager = (ViewPager) findViewById(R.id.pager);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());

        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(msgDay);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String freshmsg = "New msg";
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

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String msg;
            switch (position){
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
        public Fragment getItem(int position) {
            return DayFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

    }

    private void getIntentInfo() {
        if (day != null) {
            msgDay = day.getIntExtra(MainActivity.DAY, 0);
        }
    }

}
