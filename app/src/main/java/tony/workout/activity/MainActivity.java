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
                dayIntent.putExtra(DAY, InputData.MONDAY);
                startActivity(dayIntent);
            }
        });

        tvTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayIntent.putExtra(DAY, InputData.TUESDAY);
                startActivity(dayIntent);
            }
        });

        tvWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayIntent.putExtra(DAY, InputData.WEDNESDAY);
                startActivity(dayIntent);
            }
        });

        tvThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayIntent.putExtra(DAY, InputData.THURSDAY);
                startActivity(dayIntent);
            }
        });

        tvFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayIntent.putExtra(DAY, InputData.FRIDAY);
                startActivity(dayIntent);
            }
        });

        tvSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayIntent.putExtra(DAY, InputData.SATURDAY);
                startActivity(dayIntent);
            }
        });

        tvSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayIntent.putExtra(DAY, InputData.SUNDAY);
                startActivity(dayIntent);
            }
        });
    }


    FancyButton findView(int id) {
        return (FancyButton) findViewById(id);
    }

}