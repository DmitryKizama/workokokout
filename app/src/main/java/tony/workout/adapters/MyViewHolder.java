package tony.workout.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import tony.workout.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView name, repetition, weight, approaches, tvName, tvWeight, tvApproaches, tvRepetition;
    public Button btnDelete;

    public MyViewHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tvName);
        tvWeight = (TextView) itemView.findViewById(R.id.tvWeight);
        tvApproaches = (TextView) itemView.findViewById(R.id.tvApproaches);
        tvRepetition = (TextView) itemView.findViewById(R.id.tvRepetition);
        weight = (TextView) itemView.findViewById(R.id.recyclerViewItemWeight);
        name = (TextView) itemView.findViewById(R.id.recyclerViewItemName);
        repetition = (TextView) itemView.findViewById(R.id.recyclerViewItemRepetition);
        approaches = (TextView) itemView.findViewById(R.id.recyclerViewItemApproaches);
        btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
    }
}
