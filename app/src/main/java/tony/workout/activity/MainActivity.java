package tony.workout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import tony.workout.R;

public class MainActivity extends AppCompatActivity {

    TextView tvMonday, tvTuesday, tvWednesday, tvThursday, tvFriday, tvSaturday, tvSunday;

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
        tvMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sspa = new Intent(MainActivity.this, DayActivity.class);
                startActivity(sspa);
            }
        });
    }


    TextView findView(int id) {
        return (TextView) findViewById(id);
    }

}
