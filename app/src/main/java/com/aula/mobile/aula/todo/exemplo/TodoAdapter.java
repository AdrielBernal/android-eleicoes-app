package com.aula.mobile.aula.todo.exemplo;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aula.mobile.aula.R;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.RecyclerViewHolder> {

    private List<Tarefa> mList;
    private Context mContext;
    private TodoActivity activity;

    public TodoAdapter(Context context, List<Tarefa> list) {
        this.mContext = context;
        this.mList = list;
        this.activity = (TodoActivity) context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Tarefa tarefa = mList.get(position);
        holder.tvTarefa.setText(tarefa.getTarefa());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected class RecyclerViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvTarefa;
        private LinearLayout llRow;
        private AppCompatImageView ivDelete;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tvTarefa = itemView.findViewById(R.id.tvTarefa);
            llRow = itemView.findViewById(R.id.llRow);
            llRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Tarefa item = mList.get(getAdapterPosition());
                    activity.onEdit(item);
                }
            });
            ivDelete = itemView.findViewById(R.id.ivDelete);
            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Tarefa item = mList.get(getAdapterPosition());
                    activity.onRemove(item);
                }
            });

        }
    }
}
