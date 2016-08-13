package tony.workout.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tony.workout.R;
import tony.workout.activity.UpdateDialog;
import tony.workout.data.InputData;


public class TraningAdapter extends RecyclerView.Adapter<MyViewHolder> implements UpdateDialog.DialogUpdateListener {

    private List<InputData> inputs;
    public MyViewHolder vHolder;
    public static boolean isShown = false;

    private AdapterListener adapterListener;
    private Context context;

    public interface AdapterListener {
        void onDeletePressed(int position);
    }


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
        InputData input = inputs.get(viewHolder.getAdapterPosition());
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

        viewHolder.btnCancelInItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.swipeLayout.close();
                isShown = false;
            }
        });

        viewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDialog dialog = new UpdateDialog(context, TraningAdapter.this, 0, viewHolder.getAdapterPosition(), inputs.get(viewHolder.getAdapterPosition()).getName());
                showDialog(dialog);
            }
        });

        viewHolder.weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDialog dialog = new UpdateDialog(context, TraningAdapter.this, 1, viewHolder.getAdapterPosition(), "" + inputs.get(viewHolder.getAdapterPosition()).getWeight());
                showDialog(dialog);
            }
        });

        viewHolder.approaches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDialog dialog = new UpdateDialog(context, TraningAdapter.this, 2, viewHolder.getAdapterPosition(), "" + inputs.get(viewHolder.getAdapterPosition()).getApproaches());
                showDialog(dialog);
            }
        });

        viewHolder.repetition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDialog dialog = new UpdateDialog(context, TraningAdapter.this, 3, viewHolder.getAdapterPosition(), "" + inputs.get(viewHolder.getAdapterPosition()).getRepetition());
                showDialog(dialog);
            }
        });

        // -------------------------------------------------------------------------------------

        viewHolder.name.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showSwipeLayout(viewHolder);
                return false;
            }
        });

        viewHolder.weight.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showSwipeLayout(viewHolder);
                return false;
            }
        });

        viewHolder.approaches.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showSwipeLayout(viewHolder);
                return false;
            }
        });

        viewHolder.repetition.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showSwipeLayout(viewHolder);
                return false;
            }
        });

        viewHolder.swipeLayout.setSwipeEnabled(false);
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

    private void showSwipeLayout(MyViewHolder viewHolder) {
        if (vHolder != null) {
            if (vHolder.swipeLayout.isShown()) {
                vHolder.swipeLayout.close();
            }
        }
        isShown = true;
        viewHolder.swipeLayout.open();
        vHolder = viewHolder;
    }

    private void showDialog(UpdateDialog dialog) {
        if (vHolder != null) {
            if (isShown) {
                vHolder.swipeLayout.close();
                isShown = false;
            } else {
                dialog.show();
            }
        } else {
            dialog.show();
        }
    }

    /**
     * Реализация класса ViewHolder, хранящего ссылки на виджеты.
     */

}
