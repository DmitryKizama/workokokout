package tony.workout.activity.menu;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Locale;

import mehdi.sakout.fancybuttons.FancyButton;
import tony.workout.R;
import tony.workout.helper.UIhelper;

public class SettingsDialog extends Dialog {

    private MenuListener menuListener;
    private FancyButton cancel, apply;
    private Switch switch_in_menu;
    private Spinner spinner_language;

    interface MenuListener {
        void onApplyClicked(int position);
        void onLangChanged();
    }

    public SettingsDialog(Context context, MenuListener listener) {
        super(context, R.style.AppTheme);
        menuListener = listener;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount = 0.7f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        setContentView(R.layout.settings_dialog);

        ViewGroup parent = (ViewGroup) findViewById(R.id.parentInSettings);
//        parent.getLayoutParams().height = UIhelper.getH() / 2;
        parent.getLayoutParams().width = UIhelper.getW() - UIhelper.getPixel(30);

        cancel = (FancyButton) findViewById(R.id.btnCancelInMenu);
        apply = (FancyButton) findViewById(R.id.btnApplyInMenu);
        switch_in_menu = (Switch) findViewById(R.id.switch_in_menu);
        spinner_language = (Spinner) findViewById(R.id.language);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_language.setAdapter(adapter);

        switch_in_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), getContext().getResources().getString(R.string.its_not_working), Toast.LENGTH_SHORT).show();
            }
        });

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
                Log.d("SPINNER", "position = " + spinner_language.getSelectedItemPosition());
                Locale locale;
                switch (spinner_language.getSelectedItemPosition()) {
                    case 0:
                        locale = new Locale("en_US");
                        break;
                    case 1:
                        locale = new Locale("ru");
                        break;
                    case 2:
                        locale = new Locale("uk");
                        break;
                    default:
                        locale = new Locale("en_US");
                        break;
                }
                Resources res = getContext().getResources();
                // Change locale settings in the app.
                DisplayMetrics dm = res.getDisplayMetrics();
                android.content.res.Configuration conf = res.getConfiguration();
                conf.locale = locale;
                res.updateConfiguration(conf, dm);
//                getContext().startActivity(intent);
                menuListener.onLangChanged();
                dismiss();
            }
        });

    }
}
