package tony.workout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import tony.workout.R;

public class MainActivity extends AppCompatActivity {

    public static final String DAY = "DAY";
    public static final int MONDAY = 0;
    public static final int TUESDAY = 1;
    public static final int WEDNESDAY = 2;
    public static final int THURSDAY = 3;
    public static final int FRIDAY = 4;
    public static final int SATURDAY = 5;
    public static final int SUNDAY = 6;

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
                dayIntent.putExtra(DAY, MONDAY);
                startActivity(dayIntent);
            }
        });

        tvTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayIntent.putExtra(DAY, TUESDAY);
                startActivity(dayIntent);
            }
        });

        tvWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayIntent.putExtra(DAY, WEDNESDAY);
                startActivity(dayIntent);
            }
        });

        tvThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayIntent.putExtra(DAY, THURSDAY);
                startActivity(dayIntent);
            }
        });

        tvFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayIntent.putExtra(DAY, FRIDAY);
                startActivity(dayIntent);
            }
        });

        tvSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayIntent.putExtra(DAY, SATURDAY);
                startActivity(dayIntent);
            }
        });

        tvSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayIntent.putExtra(DAY, SUNDAY);
                startActivity(dayIntent);
            }
        });
    }


    TextView findView(int id) {
        return (TextView) findViewById(id);
    }

}
