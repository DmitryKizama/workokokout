package tony.workout.helper;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import mehdi.sakout.fancybuttons.FancyButton;

public class AnimationAddButton {

    private FancyButton btn;
    private boolean btnAddShow = true;

    private AnimationAddButton(FancyButton btn) {
        this.btn = btn;
    }

    private static AnimationAddButton instance;

    public static synchronized AnimationAddButton getInstance(FancyButton btn) {
        if (instance == null) {
            instance = new AnimationAddButton(btn);
        }
        return instance;
    }

    public void hideButton() {
        int location[] = new int[2];
        btn.getLocationOnScreen(location);
        Log.d("addOnScrollListener", "IN HIDE METHOD, x = " + location[0] + " y = " + location[1]);

        TranslateAnimation anim = new TranslateAnimation(0, 0, 0, 300);
        anim.setDuration(350);
        anim.setFillAfter(true);
        btn.startAnimation(anim);
        btn.setEnabled(false);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                btn.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        btnAddShow = false;
    }

    public void showButton() {
        int location[] = new int[2];
        btn.getLocationOnScreen(location);
        btn.setVisibility(View.VISIBLE);
        btn.setGhost(false);
        Log.d("addOnScrollListener", "IN SHOW METHOD, x = " + location[0] + " y = " + location[1]);

        TranslateAnimation anim = new TranslateAnimation(0, 0, 300, 0);
        anim.setDuration(350);
        anim.setFillAfter(true);
        btn.startAnimation(anim);

        btn.setEnabled(true);
        btnAddShow = true;
    }

    public boolean isShown() {
        return btnAddShow;
    }
}
