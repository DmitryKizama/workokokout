package tony.workout.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tony.workout.R;
import tony.workout.base.Input;


public class TraningAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Input> inputs;

    public TraningAdapter(List<Input> records) {
        this.inputs = records;
        notifyDataSetChanged();
    }

    public void onAdd(Input input) {
        inputs.add(input);
        notifyItemInserted(inputs.size() - 1);
    }

    /**
     * Создание новых View и ViewHolder элемента списка, которые впоследствии могут переиспользоваться.
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup, false);
        return new MyViewHolder(v);
    }

    /**
     * Заполнение виджетов View данными из элемента списка с номером i
     */
    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        Input record = inputs.get(i);
        viewHolder.name.setText(record.getWorkoutName());
    }

    @Override
    public int getItemCount() {
        return inputs.size();
    }

    /**
     * Реализация класса ViewHolder, хранящего ссылки на виджеты.
     */

}