package tony.workout.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
        void onDeletePressed(int position);
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
        InputData input = inputs.get(i);
//        viewHolder.tvName.setText("Workout name:");
//        viewHolder.tvWeight.setText("Weight:");
//        viewHolder.tvApproaches.setText("Amount:");
//        viewHolder.tvRepetition.setText("Repeat:");
        viewHolder.name.setText(input.getName());
        viewHolder.weight.setText("" + input.getWeight());
        viewHolder.repetition.setText("" + input.getRepetition());
        viewHolder.approaches.setText("" + input.getApproaches());
        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewHolder.getAdapterPosition();
//                adapterListener.onDeletePressed(inputs.get(pos).getId());
                inputs.get(pos).delete();
                inputs.remove(pos);
                notifyItemRemoved(pos);
            }
        });

        viewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDialog dialog = new UpdateDialog(context, TraningAdapter.this, 0, viewHolder.getAdapterPosition(), inputs.get(viewHolder.getAdapterPosition()).getName());
                dialog.show();
            }
        });

        viewHolder.weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDialog dialog = new UpdateDialog(context, TraningAdapter.this, 1, viewHolder.getAdapterPosition(), "" + inputs.get(viewHolder.getAdapterPosition()).getWeight());
                dialog.show();
            }
        });

        viewHolder.approaches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDialog dialog = new UpdateDialog(context, TraningAdapter.this, 2, viewHolder.getAdapterPosition(), "" + inputs.get(viewHolder.getAdapterPosition()).getApproaches());
                dialog.show();
            }
        });

        viewHolder.repetition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDialog dialog = new UpdateDialog(context, TraningAdapter.this, 3, viewHolder.getAdapterPosition(), "" + inputs.get(viewHolder.getAdapterPosition()).getRepetition());
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
