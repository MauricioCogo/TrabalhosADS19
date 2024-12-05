package com.example.tarfas.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tarfas.Entity.Subtarefa;
import com.example.tarfas.R;

import java.util.List;

public class SubtarefaAdapter extends RecyclerView.Adapter<SubtarefaAdapter.SubtarefaViewHolder> {

    private final List<Subtarefa> subtarefas;

    public SubtarefaAdapter(List<Subtarefa> subtarefas) {
        this.subtarefas = subtarefas;
    }

    @NonNull
    @Override
    public SubtarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subtarefa, parent, false);
        return new SubtarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubtarefaViewHolder holder, int position) {
        Subtarefa subtarefa = subtarefas.get(position);

        holder.checkSubtarefa.setText(subtarefa.getTitulo());
        holder.checkSubtarefa.setChecked("Concluída".equals(subtarefa.getStatus()));

        // Listeners para editar e excluir
        holder.btnEditar.setOnClickListener(v -> {

        });

        holder.btnExcluir.setOnClickListener(v -> {
            // Lógica para excluir subtarefa
        });

        holder.checkSubtarefa.setOnCheckedChangeListener((buttonView, isChecked) -> {
            subtarefa.setStatus(isChecked ? "Concluída" : "Pendente");
        });
    }

    @Override
    public int getItemCount() {
        return subtarefas.size();
    }

    public static class SubtarefaViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkSubtarefa;
        Button btnEditar, btnExcluir;

        public SubtarefaViewHolder(@NonNull View itemView) {
            super(itemView);
            checkSubtarefa = itemView.findViewById(R.id.check_subtarefa);
            btnEditar = itemView.findViewById(R.id.btn_editar_subtarefa);
            btnExcluir = itemView.findViewById(R.id.btn_excluir_subtarefa);
        }
    }
}
