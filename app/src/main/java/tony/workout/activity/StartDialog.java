package tony.workout.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import mehdi.sakout.fancybuttons.FancyButton;
import tony.workout.R;
import tony.workout.helper.UIhelper;

public class StartDialog extends Dialog {


    public interface DialogListener {
        void onAddPressed(String name, int reppetition, int approaches, int weight);
    }

    private DialogListener dialogListener;
    FancyButton btnCancel, btnAdd;
    EditText etName, etApproaches, etRepetition, etWeight;

    public StartDialog(Context context, DialogListener dListener) {
        super(context, R.style.AppTheme);
        this.dialogListener = dListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.start_dialog);

        ViewGroup parent = (ViewGroup) findViewById(R.id.parent);
//        parent.getLayoutParams().height = UIhelper.getH() / 2;
        parent.getLayoutParams().width = UIhelper.getW() - UIhelper.getPixel(30);

        UIhelper.hideKeyboard(getOwnerActivity());

        btnCancel = (FancyButton) findViewById(R.id.btnCancel);
        btnAdd = (FancyButton) findViewById(R.id.btnAdd);
        etName = (EditText) findViewById(R.id.edtName);
        etWeight = (EditText) findViewById(R.id.edtWeight);
        etApproaches = (EditText) findViewById(R.id.edtApproaches);
        etRepetition = (EditText) findViewById(R.id.edtRepetition);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                int appr;
                int repp;
                int weight;
                try {
                    appr = Integer.parseInt(etApproaches.getText().toString());
                } catch (NumberFormatException e) {
                    appr = 0;
                }
                try {
                    repp = Integer.parseInt(etRepetition.getText().toString());
                } catch (NumberFormatException e) {
                    repp = 0;
                }
                try {
                    weight = Integer.parseInt(etWeight.getText().toString());
                } catch (NumberFormatException e) {
                    weight = 0;
                }

                dialogListener.onAddPressed(name, repp, appr, weight);
                dismiss();
            }
        });

    }
}