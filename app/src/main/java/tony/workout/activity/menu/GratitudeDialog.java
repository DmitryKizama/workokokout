package tony.workout.activity.menu;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;

import tony.workout.R;
import tony.workout.helper.UIhelper;

public class GratitudeDialog extends Dialog {


    public GratitudeDialog(Context context) {
        super(context, R.style.AppTheme);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.settings_dialog);

        ViewGroup parent = (ViewGroup) findViewById(R.id.parentInSettings);
//        parent.getLayoutParams().height = UIhelper.getH() / 2;
        parent.getLayoutParams().width = UIhelper.getW() - UIhelper.getPixel(30);

    }
}
