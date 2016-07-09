package tony.workout.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import tony.workout.R;
import tony.workout.helper.Input;
import tony.workout.helper.UIhelper;

public class MyPopupWindow extends Dialog {
    /**
     * Called when the activity is first created.
     */

    public interface DialogListener {
        void onAddPressed(Input in);
    }

    private DialogListener dialogListener;
    Button btnCancel, btnAdd;
    EditText etName, etApproaches, etRepetition;
    public static String INENT_NAME = "INTENT_INFO_INPUT_NAME";
    public static String INENT_APPR = "INTENT_INFO_INPUT_APPROACHES";
    public static String INENT_PEPE = "INTENT_INFO_INPUT_REPETITION";



    public MyPopupWindow(Context context, DialogListener dListener) {
        super(context, R.style.AppTheme);
        this.dialogListener = dListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup);

        ViewGroup parent = (ViewGroup) findViewById(R.id.parent);
//        parent.getLayoutParams().height = UIhelper.getH() / 2;
        parent.getLayoutParams().width = UIhelper.getW() - UIhelper.getPixel(30);


        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        etName = (EditText) findViewById(R.id.edtName);
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
                try{
                    appr = Integer.parseInt(etApproaches.getText().toString());
                    repp = Integer.parseInt(etRepetition.getText().toString());
                } catch (NumberFormatException e){
                    appr = 0;
                    repp = 0;
                }


                Input in = new Input(name, appr, repp);
                dialogListener.onAddPressed(in);
                dismiss();
            }
        });

    }
}