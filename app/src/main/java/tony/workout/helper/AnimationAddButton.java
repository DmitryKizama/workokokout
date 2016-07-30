package tony.workout.helper;

import android.util.Log;
import android.view.animation.TranslateAnimation;

import mehdi.sakout.fancybuttons.FancyButton;

public final class AnimationAddButton {

    public static boolean btnAddShow = true;

    public static void hideButton(FancyButton btn) {
        int location[] = new int[2];
        btn.getLocationOnScreen(location);
        Log.d("addOnScrollListener", "IN HIDE METHOD, x = " + location[0] + " y = " + location[1]);
        TranslateAnimation anim = new TranslateAnimation(0, 0, 0, 300);
        anim.setDuration(350);
        anim.setFillAfter(true);
        btn.startAnimation(anim);
        btn.setEnabled(false);
        btnAddShow = false;
    }

    public static void showButton(FancyButton btn) {
        int location[] = new int[2];
        btn.getLocationOnScreen(location);
        Log.d("addOnScrollListener", "IN SHOW METHOD, x = " + location[0] + " y = " + location[1]);
        TranslateAnimation anim = new TranslateAnimation(0, 0, 300, 0);
        anim.setDuration(350);
        anim.setFillAfter(true);
        btn.startAnimation(anim);
        btn.setEnabled(true);
        btnAddShow = true;
    }
}
