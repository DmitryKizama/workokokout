package tony.workout.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.SwipeLayout;

import java.util.List;

import tony.workout.R;
import tony.workout.activity.UpdateDialog;
import tony.workout.data.InputData;


public class TraningAdapter extends RecyclerView.Adapter<MyViewHolder> implements UpdateDialog.DialogUpdateListener {

    private List<InputData> inputs;

    public interface AdapterListener {
        void onDeletePressed(InputData data);
    }

    private AdapterListener adapterListener;
    private Context context;

    public TraningAdapter(List<InputData> records, AdapterListener listener, Context con) {
        this.inputs = records;
        notifyDataSetChanged();
        this.adapterListener = listener;
        this.context = con;
    }

    public void onAdd(InputData input) {
        inputs.add(input);
        notifyItemInserted(inputs.size() - 1);
    }

    /**
     * Создание новых View и ViewHolder элемента списка, которые впоследствии могут переиспользоваться.
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new MyViewHolder(v);
    }

    /**
     * Заполнение виджетов View данными из элемента списка с номером i
     */
    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int i) {
        final InputData input = inputs.get(i);
        viewHolder.tvName.setText(context.getResources().getString(R.string.item_name));
        viewHolder.tvWeight.setText(context.getResources().getString(R.string.item_weight));
        viewHolder.tvApproaches.setText(context.getResources().getString(R.string.item_approaches));
        viewHolder.tvRepetition.setText(context.getResources().getString(R.string.item_repetition));
        viewHolder.name.setText(input.getName());
        viewHolder.weight.setText("" + input.getWeight());
        viewHolder.repetition.setText("" + input.getRepetition());
        viewHolder.approaches.setText("" + input.getApproaches());

        Log.d("TYU", "Create " + input.getName() + " I : " + i);

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewHolder.getAdapterPosition();

                adapterListener.onDeletePressed(inputs.get(pos));
                inputs.get(pos).delete();
                inputs.remove(pos);
                notifyItemRemoved(pos);

            }
        });

        viewHolder.linearLayoutNAME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewHolder.getAdapterPosition();
                UpdateDialog dialog = new UpdateDialog(context, TraningAdapter.this, 0, pos);
                dialog.show();
            }
        });

        viewHolder.linearLayoutWEIGHT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewHolder.getAdapterPosition();
                UpdateDialog dialog = new UpdateDialog(context, TraningAdapter.this, 1, pos);
                dialog.show();
            }
        });

        viewHolder.linearLayoutAPPR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewHolder.getAdapterPosition();
                UpdateDialog dialog = new UpdateDialog(context, TraningAdapter.this, 2, pos);
                dialog.show();
            }
        });

        viewHolder.linearLayoutREPP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewHolder.getAdapterPosition();
                UpdateDialog dialog = new UpdateDialog(context, TraningAdapter.this, 3, pos);
                dialog.show();
            }
        });


        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
    }

    @Override
    public void onOkNameChange(String name, int position) {
        inputs.get(position).setName(name);
        notifyItemChanged(position);
        inputs.get(position).save();
    }

    @Override
    public void onOkApproachesChange(int approaches, int position) {
        inputs.get(position).setApproaches(approaches);
        notifyItemChanged(position);
        inputs.get(position).save();
    }

    @Override
    public void onOkRepetitionChange(int repetition, int position) {
        inputs.get(position).setRepetition(repetition);
        notifyItemChanged(position);
        inputs.get(position).save();
    }

    @Override
    public void onOkWeightChange(int weight, int position) {
        inputs.get(position).setWeight(weight);
        notifyItemChanged(position);
        inputs.get(position).save();
    }

    @Override
    public int getItemCount() {
        return inputs.size();
    }

    /**
     * Реализация класса ViewHolder, хранящего ссылки на виджеты.
     */

}
