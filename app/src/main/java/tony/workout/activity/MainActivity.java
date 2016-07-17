package tony.workout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import tony.workout.R;
import tony.workout.data.InputData;

public class MainActivity extends AppCompatActivity {

    public static final String DAY = "DAY";

    TextView tvMonday, tvTuesday, tvWednesday, tvThursday, tvFriday, tvSaturday, tvSunday;
    Intent dayIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMonday = findView(R.id.tvMonday);
        tvTuesday = findView(R.id.tvTuesday);
        tvWednesday = findView(R.id.tvWednesday);
        tvThursday = findView(R.id.tvThursday);
        tvFriday = findView(R.id.tvFriday);
        tvSaturday = findView(R.id.tvSaturday);
        tvSunday = findView(R.id.tvSunday);


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


    TextView findView(int id) {
        return (TextView) findViewById(id);
    }

}