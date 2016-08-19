package tony.workout.activity.menu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import tony.workout.R;
import tony.workout.activity.DayActivity;

public class ActivityHowToUse extends FragmentActivity implements Menu.MenuCallback{
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 3;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    private Menu mMenu;
    private DrawerLayout mDrawerLayout;
    private Animation anim;
    private ViewGroup ll;
    private ImageView ivSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_how_to_use);

        mMenu = (Menu) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer_htu);

        mMenu.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout_htu));

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_htu);
        ll = (ViewGroup) findViewById(R.id.layout_htu);

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
        anim = AnimationUtils.loadAnimation(ActivityHowToUse.this, R.anim.rotate_hor_menu);
        ivSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivSettings.startAnimation(anim);
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pagerInActivity);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, DayActivity.class);
        finish();
        startActivity(intent);

    }

    @Override
    public void onChangeLang() {
        Intent intent = new Intent(this, ActivityHowToUse.class);
        startActivity(intent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        } else {
            finish();
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ScreenSlidePageFragmentInHowToUse sc = ScreenSlidePageFragmentInHowToUse.newInstance(position);
            return sc;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}