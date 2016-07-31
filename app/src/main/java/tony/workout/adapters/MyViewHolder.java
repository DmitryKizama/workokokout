package tony.workout.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

import tony.workout.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView name, repetition, weight, approaches, btnCancelInItem;
    //    public TextView tvName, tvWeight, tvApproaches, tvRepetition;
    public ImageView btnDelete;
    public SwipeLayout swipeLayout;
    public LinearLayout bottom_wrapper, topLayout;
//    public LinearLayout linearLayoutNAME, linearLayoutREPP, linearLayoutAPPR, linearLayoutWEIGHT;


    public MyViewHolder(View itemView) {
        super(itemView);
        topLayout = (LinearLayout) itemView.findViewById(R.id.top_layout_in_item);
        bottom_wrapper = (LinearLayout) itemView.findViewById(R.id.bottom_wrapper);
//        linearLayoutNAME = (LinearLayout) itemView.findViewById(R.id.linearNAME);
//        linearLayoutREPP = (LinearLayout) itemView.findViewById(R.id.linearREPET);
//        linearLayoutAPPR = (LinearLayout) itemView.findViewById(R.id.linearAPPR);
//        linearLayoutWEIGHT = (LinearLayout) itemView.findViewById(R.id.linearWEIGHT);

        swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipelayout);
//        tvName = (TextView) itemView.findViewById(R.id.tvName);
//        tvWeight = (TextView) itemView.findViewById(R.id.tvWeight);
//        tvApproaches = (TextView) itemView.findViewById(R.id.tvApproaches);
//        tvRepetition = (TextView) itemView.findViewById(R.id.tvRepetition);
        weight = (TextView) itemView.findViewById(R.id.recyclerViewItemWeight);
        name = (TextView) itemView.findViewById(R.id.recyclerViewItemName);
        repetition = (TextView) itemView.findViewById(R.id.recyclerViewItemRepetition);
        approaches = (TextView) itemView.findViewById(R.id.recyclerViewItemApproaches);
        btnDelete = (ImageView) itemView.findViewById(R.id.btnDelete);
        btnCancelInItem = (TextView) itemView.findViewById(R.id.btnCancelInItem);
    }
}
