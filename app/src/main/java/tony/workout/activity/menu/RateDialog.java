package tony.workout.activity.menu;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RatingBar;
import android.widget.Toast;

import mehdi.sakout.fancybuttons.FancyButton;
import tony.workout.R;
import tony.workout.helper.UIhelper;

public class RateDialog extends Dialog {

    private FancyButton btnCancel, btnRate;
    private RatingBar ratingBar;
    private Context con;

    public RateDialog(Context context) {
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

        setContentView(R.layout.rate);

        ViewGroup parent = (ViewGroup) findViewById(R.id.parentInRate);
//        parent.getLayoutParams().height = UIhelper.getH() / 2;
        parent.getLayoutParams().width = UIhelper.getW() - UIhelper.getPixel(30);

        btnCancel = (FancyButton) findViewById(R.id.btnCancelInRate);
        btnRate = (FancyButton) findViewById(R.id.btnOkInRate);
        ratingBar = (RatingBar) findViewById(R.id.ratingbarInRate);

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ratingBar.getRating() > 3) {
                    final String appPackageName = con.getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        con.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        con.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }
                } else {
                    Toast.makeText(con, con.getResources().getString(R.string.thanks), Toast.LENGTH_SHORT).show();
                }
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
