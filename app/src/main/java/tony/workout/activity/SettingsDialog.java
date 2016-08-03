package tony.workout.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import mehdi.sakout.fancybuttons.FancyButton;
import tony.workout.R;
import tony.workout.helper.UIhelper;

public class SettingsDialog extends Dialog {

    private MenuListener menuListener;

    interface MenuListener {
        void onApplyClicked(int position);
    }

    public SettingsDialog(Context context, MenuListener listener) {
        super(context, R.style.AppTheme);
        menuListener = listener;
    }

    private FancyButton cancel, apply;

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

        cancel = (FancyButton) findViewById(R.id.btnCancelInMenu);
        apply = (FancyButton) findViewById(R.id.btnApplyInMenu);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                menuListener.onApplyClicked(1);
                dismiss();
            }
        });

    }
}
