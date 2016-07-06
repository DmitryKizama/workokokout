package tony.workout.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tony.workout.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public ImageView icon;

    public MyViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.recyclerViewItemName);
        icon = (ImageView) itemView.findViewById(R.id.recyclerViewItemIcon);
    }
}
