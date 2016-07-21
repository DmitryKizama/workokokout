package tony.workout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;


import mehdi.sakout.fancybuttons.FancyButton;
import tony.workout.R;
import tony.workout.data.InputData;

public class MainActivity extends AppCompatActivity {


    public static final String DAY = "DAY";
    public static final String PROGRESS = "PROGRESS";

    FancyButton tvMonday, tvTuesday, tvWednesday, tvThursday, tvFriday, tvSaturday, tvSunday;
    Intent dayIntent;
    SeekBar seekBar;
    private int progres = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMonday = findView(R.id.btnMonday);
        tvTuesday = findView(R.id.btnTuesday);
        tvWednesday = findView(R.id.btnWednesday);
        tvThursday = findView(R.id.btnThursday);
        tvFriday = findView(R.id.btnFriday);
        tvSaturday = findView(R.id.btnSaturday);
        tvSunday = findView(R.id.btnSunday);

        seekBar = (SeekBar) findViewById(R.id.seek_bar);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progres = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        dayIntent = new Intent(MainActivity.this, DayActivity.class);

        tvMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayIntent.putExtra(MainActivity.PROGRESS, progres);
                dayIntent.putExtra(DAY, InputData.InputDay.MON.getIndex());
                startActivity(dayIntent);
            }
        });

        tvTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayIntent.putExtra(MainActivity.PROGRESS, progres);
                dayIntent.putExtra(DAY, InputData.InputDay.TUE.getIndex());
                startActivity(dayIntent);
            }
        });

        tvWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayIntent.putExtra(MainActivity.PROGRESS, progres);
                dayIntent.putExtra(DAY, InputData.InputDay.WED.getIndex());
                startActivity(dayIntent);
            }
        });

        tvThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayIntent.putExtra(MainActivity.PROGRESS, progres);
                dayIntent.putExtra(DAY, InputData.InputDay.THU.getIndex());
                startActivity(dayIntent);
            }
        });

        tvFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayIntent.putExtra(MainActivity.PROGRESS, progres);
                dayIntent.putExtra(DAY, InputData.InputDay.FRI.getIndex());
                startActivity(dayIntent);
            }
        });

        tvSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayIntent.putExtra(MainActivity.PROGRESS, progres);
                dayIntent.putExtra(DAY, InputData.InputDay.SUT.getIndex());
                startActivity(dayIntent);
            }
        });

        tvSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayIntent.putExtra(MainActivity.PROGRESS, progres);
                dayIntent.putExtra(DAY, InputData.InputDay.SUN.getIndex());
                startActivity(dayIntent);
            }
        });
    }


    FancyButton findView(int id) {
        return (FancyButton) findViewById(id);
    }

}