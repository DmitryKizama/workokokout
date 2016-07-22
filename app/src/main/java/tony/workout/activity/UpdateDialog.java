package tony.workout.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mehdi.sakout.fancybuttons.FancyButton;
import tony.workout.R;
import tony.workout.helper.UIhelper;

public class UpdateDialog extends Dialog {


    public interface DialogUpdateListener {
        void onOkNameChange(String name, int position);

        void onOkApproachesChange(int approaches, int position);

        void onOkRepetitionChange(int repetition, int position);

        void onOkWeightChange(int weight, int position);
    }

    private DialogUpdateListener dialogListener;
    private int changegElement, position;
    private String msg;
    FancyButton btnCancel, btnOk;
    EditText etSom;
    TextView tvMain;


    public UpdateDialog(Context context, DialogUpdateListener dListener, int changedEl, int pos, String massage) {
        super(context, R.style.AppTheme);
        this.dialogListener = dListener;
        this.changegElement = changedEl;
        this.position = pos;
        this.msg = massage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.update_dialog);

        ViewGroup parent = (ViewGroup) findViewById(R.id.parentInUpdate);
//        parent.getLayoutParams().height = UIhelper.getH() / 2;
        parent.getLayoutParams().width = UIhelper.getW() - UIhelper.getPixel(30);

        tvMain = (TextView) findViewById(R.id.tvUpdate);

        switch (changegElement) {
            case 0:
                tvMain.setText("Update the name");
                break;
            case 1:
                tvMain.setText("Update weight");
                break;
            case 2:
                tvMain.setText("Update approaches");
                break;
            case 3:
                tvMain.setText("Update repetition");
                break;
        }

        btnCancel = (FancyButton) findViewById(R.id.btnCancelInUpdateDialog);
        btnOk = (FancyButton) findViewById(R.id.btnOkInUpdateDialog);
        etSom = (EditText) findViewById(R.id.edtSomethingInUpdateDialog);
        etSom.setText(msg);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (changegElement) {
                    case 0:
                        dialogListener.onOkNameChange(etSom.getText().toString(), position);
                        break;
                    case 1:
                        try {
                            dialogListener.onOkWeightChange(Integer.parseInt(etSom.getText().toString()), position);
                        } catch (NumberFormatException e) {
                            Toast.makeText(getContext(), "ONLY NUMBER!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        try {
                            dialogListener.onOkApproachesChange(Integer.parseInt(etSom.getText().toString()), position);
                        } catch (NumberFormatException e) {
                            Toast.makeText(getContext(), "ONLY NUMBER!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        try {
                            dialogListener.onOkRepetitionChange(Integer.parseInt(etSom.getText().toString()), position);
                        } catch (NumberFormatException e) {
                            Toast.makeText(getContext(), "ONLY NUMBER!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        dismiss();
                        break;
                }

                dismiss();
            }
        });

    }
}
