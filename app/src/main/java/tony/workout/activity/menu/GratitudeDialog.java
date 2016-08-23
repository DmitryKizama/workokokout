package tony.workout.activity.menu;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import mehdi.sakout.fancybuttons.FancyButton;
import tony.workout.R;
import tony.workout.data.UsersSettings;
import tony.workout.helper.UIhelper;

public class GratitudeDialog extends Dialog {

    private FancyButton btnCancel, btnRate;
    private Context con;

    public GratitudeDialog(Context context) {
        super(context, R.style.AppTheme);
        con = context;
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

        setContentView(R.layout.gratitude_dialog);

        ViewGroup parent = (ViewGroup) findViewById(R.id.parentInGratitude);
//        parent.getLayoutParams().height = UIhelper.getH() / 2;
        parent.getLayoutParams().width = UIhelper.getW() - UIhelper.getPixel(30);

        btnCancel = (FancyButton) findViewById(R.id.btnCancelInGratitude);
        btnRate = (FancyButton) findViewById(R.id.btnRateInMenu);

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!UsersSettings.getUsersSettings().getRate()) {
                    RateDialog dialog = new RateDialog(con);
                    dialog.show();
                } else {
                    Toast.makeText(con, con.getResources().getString(R.string.already), Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
