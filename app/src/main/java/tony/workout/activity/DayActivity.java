package tony.workout.activity;

import android.os.Bundle;
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

import tony.workout.R;
import tony.workout.helper.Constant;
import tony.workout.helper.Input;
import tony.workout.helper.UIhelper;

public class DayActivity extends FragmentActivity implements MyPopupWindow.DialogListener {
    static final String TAG = "myLogs";
    static final int PAGE_COUNT = 7;
    public final static int REQUEST_CODE_OJ_INPUT = 1;

    private ViewPager pager;
    private MyFragmentPagerAdapter pagerAdapter;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_activity);

        int msgDay = 0;
        if (getIntent() != null) {
            msgDay = getIntent().getIntExtra(MainActivity.DAY, 0);
        }

        pager = (ViewPager) findViewById(R.id.pager);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        //TODO: MAKE DOWNLOAD FROM THE BASE
        ArrayList<ArrayList<Input>> listOLists = new ArrayList<ArrayList<Input>>();
        ArrayList<Input> mon = new ArrayList<Input>();
        mon.add(new Input("mon", 0, 0));
        listOLists.add(mon);

        ArrayList<Input> tue = new ArrayList<Input>();
        tue.add(new Input("tue", 1, 1));
        listOLists.add(tue);

        ArrayList<Input> wed = new ArrayList<Input>();
        wed.add(new Input("wed", 2, 2));
        listOLists.add(wed);

        ArrayList<Input> thu = new ArrayList<Input>();
        thu.add(new Input("thu", 3, 3));
        listOLists.add(thu);

        ArrayList<Input> fri = new ArrayList<Input>();
        fri.add(new Input("fri", 4, 4));
        listOLists.add(fri);

        ArrayList<Input> sun = new ArrayList<Input>();
        sun.add(new Input("sun", 5, 5));
        listOLists.add(sun);

        ArrayList<Input> sut = new ArrayList<Input>();
        sut.add(new Input("sut", 6, 6));
        listOLists.add(sut);

        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), listOLists);

        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(msgDay);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIhelper.init(DayActivity.this);
                MyPopupWindow dialog = new MyPopupWindow(DayActivity.this, DayActivity.this);
                dialog.show();
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

    @Override
    public void onAddPressed(Input in) {
        pagerAdapter.getRegisteredFragment(pager.getCurrentItem()).addNewItem(in);
        pagerAdapter.notifyDataSetChanged();
        pager.setCurrentItem(pager.getCurrentItem());
    }


    private class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

        SparseArray<DayFragment> registeredFragments = new SparseArray<DayFragment>();

        private ArrayList<ArrayList<Input>> programs;

        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<ArrayList<Input>> programs) {
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


}
