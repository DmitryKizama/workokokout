package tony.workout.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;
import com.melnykov.fab.FloatingActionButton;

import java.util.Calendar;

import tony.workout.R;
import tony.workout.activity.menu.Menu;

public class DayActivity extends AppCompatActivity implements StartDialog.DialogListener, Menu.MenuCallback {
    static final String TAG = "myLogs";
    static final int PAGE_COUNT = 7;

    private ViewPager pager;
    private MyFragmentPagerAdapter pagerAdapter;
    private FloatingActionButton btnAdd;
    private Menu mMenu;
    private DrawerLayout mDrawerLayout;
    private ImageView ivSettings;

    private Animation anim;
    //    private DayFragment dayFragment;
    private ViewGroup ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_activity);

//        Resources res = getResources();
//        DisplayMetrics dm = res.getDisplayMetrics();
//        android.content.res.Configuration conf = res.getConfiguration();
//        conf.locale = new Locale(UsersSettings.getUsersSettings().getLanguage());
//        res.updateConfiguration(conf, dm);

        mMenu = (Menu)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        mMenu.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

//        dayFragment = (DayFragment) getFragmentManager().findFragmentById(R.id.rv);

        ll = (ViewGroup) findViewById(R.id.layout_in_day_activity);

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pagerAdapter.getRegisteredFragment(pager.getCurrentItem()).adapter.vHolder != null) {
                    if (pagerAdapter.getRegisteredFragment(pager.getCurrentItem()).adapter.isShown) {
                        pagerAdapter.getRegisteredFragment(pager.getCurrentItem()).adapter.vHolder.swipeLayout.close();
                        pagerAdapter.getRegisteredFragment(pager.getCurrentItem()).adapter.isShown = false;
                    }
                }
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.placeholder, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerSlide(View drawerView, float slideOffset) {
//                ivSettings.startAnimation(anim);
                float moveFactor = (getResources().getDimension(R.dimen.navdrawer_width) * slideOffset);
                ll.setTranslationX(moveFactor);
            }
        };

        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mDrawerLayout.setElevation(0);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        ivSettings = (ImageView) findViewById(R.id.ivSettings);
        anim = AnimationUtils.loadAnimation(DayActivity.this, R.anim.rotate_hor_menu);
        ivSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pagerAdapter.getRegisteredFragment(pager.getCurrentItem()).adapter.vHolder != null) {
                    if (pagerAdapter.getRegisteredFragment(pager.getCurrentItem()).adapter.isShown) {
                        pagerAdapter.getRegisteredFragment(pager.getCurrentItem()).adapter.vHolder.swipeLayout.close();
                        pagerAdapter.getRegisteredFragment(pager.getCurrentItem()).adapter.isShown = false;
                    }
                }
                ivSettings.startAnimation(anim);
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        int msgDay;
        Calendar c = Calendar.getInstance();
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                msgDay = 0;
                break;
            case Calendar.TUESDAY:
                msgDay = 1;
                break;
            case Calendar.WEDNESDAY:
                msgDay = 2;
                break;
            case Calendar.THURSDAY:
                msgDay = 3;
                break;
            case Calendar.FRIDAY:
                msgDay = 4;
                break;
            case Calendar.SATURDAY:
                msgDay = 5;
                break;
            case Calendar.SUNDAY:
                msgDay = 6;
                break;
            default:
                msgDay = 0;
                break;
        }

        pager = (ViewPager) findViewById(R.id.pager);
        btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);

        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), btnAdd);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(msgDay);

//        btnAdd.attachToRecyclerView((RecyclerView) findViewById(R.id.rv));


        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);

        btnAdd.setColorNormal(getResources().getColor(R.color.primary));
        btnAdd.setColorPressed(getResources().getColor(R.color.primary_pressed));
        btnAdd.setColorRipple(getResources().getColor(R.color.primary_pressed));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                btnAdd.startAnimation(AnimationUtils.loadAnimation(DayActivity.this, R.anim.rotate_and_scale));
                StartDialog dialog = new StartDialog(DayActivity.this, DayActivity.this);
                if (pagerAdapter.getRegisteredFragment(pager.getCurrentItem()).adapter.vHolder != null) {
                    if (pagerAdapter.getRegisteredFragment(pager.getCurrentItem()).adapter.isShown) {
                        pagerAdapter.getRegisteredFragment(pager.getCurrentItem()).adapter.vHolder.swipeLayout.close();
                        pagerAdapter.getRegisteredFragment(pager.getCurrentItem()).adapter.isShown = false;
                    } else {
                        dialog.show();
                    }
                } else {
                    dialog.show();
                }
            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected, position = " + position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                if (pagerAdapter.getRegisteredFragment(pager.getCurrentItem()).adapter.vHolder != null) {
                    if (pagerAdapter.getRegisteredFragment(pager.getCurrentItem()).adapter.isShown) {
                        pagerAdapter.getRegisteredFragment(pager.getCurrentItem()).adapter.vHolder.swipeLayout.close();
                    }
                }
                btnAdd.show();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Menu.mDrawerLayout != null) {
            if (Menu.mDrawerLayout.isShown()) {
                Menu.mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        }
    }

    @Override
    public void onChangeLang() {
        Intent intent = new Intent(this, DayActivity.class);
        startActivity(intent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        } else {
            finish();
        }
    }

    private class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

        SparseArray<DayFragment> registeredFragments = new SparseArray<DayFragment>();
        FloatingActionButton btn;

        public MyFragmentPagerAdapter(FragmentManager fm, FloatingActionButton btnAddInDayActivity) {
            super(fm);
            this.btn = btnAddInDayActivity;
        }

        private final String[] TITLES = {getResources().getString(R.string.mon), getResources().getString(R.string.tue),
                getResources().getString(R.string.wed), getResources().getString(R.string.thu),
                getResources().getString(R.string.fri), getResources().getString(R.string.sut),
                getResources().getString(R.string.sun)};

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
