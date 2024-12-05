package com.example.tarfas.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarfas.Entity.Tarefa;
import com.example.tarfas.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {

    private Context context;  // Variável para armazenar o contexto
    private List<Tarefa> tarefaList;

    public TarefaAdapter(Context context, List<Tarefa> tarefaList) {
        this.context = context;
        this.tarefaList = tarefaList;
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tarefa, parent, false);
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd"); // Formato da data que você recebe
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy"); // Formato desejado para exibição

        Tarefa tarefa = tarefaList.get(position);

        holder.titulo.setText(tarefa.getTitulo());
        holder.descricao.setText(tarefa.getDescricao());

        try {
            // Converte a data de vencimento
            Date date = inputFormat.parse(tarefa.getDataVencimento());
            String formattedDate = outputFormat.format(date);
            holder.dataVencimento.setText(formattedDate);

            // Compara a data de vencimento com a data atual
            Date currentDate = new Date(); // Data atual
            if (date.after(currentDate)) { // Se a data de vencimento for maior que a data atual
                holder.dataVencimento.setTextColor(Color.RED); // Muda a cor para vermelho
            } else {
                holder.dataVencimento.setTextColor(Color.BLACK); // Muda a cor para preto (ou outra cor desejada)
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        String prioridade = tarefa.getPrioridade(); // Pega o valor da prioridade

// Definir cor dependendo da prioridade
        switch (prioridade) {
            case "Alta":
                holder.prioridade.setTextColor(ContextCompat.getColor(context, R.color.vermelho)); // cor vermelha
                break;
            case "Média":
                holder.prioridade.setTextColor(ContextCompat.getColor(context, R.color.amarelo)); // cor amarela
                break;
            case "Baixa":
                holder.prioridade.setTextColor(ContextCompat.getColor(context, R.color.verde)); // cor verde
                break;
            default:
                holder.prioridade.setTextColor(ContextCompat.getColor(context, R.color.preto)); // cor padrão, se necessário
                break;
        }

        holder.prioridade.setText(prioridade); // Exibe a prioridade no TextView
        holder.status.setChecked("Concluída".equals(tarefa.getStatus()));

        // Configurando subtarefas
        holder.recyclerSubtarefas.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        SubtarefaAdapter subtarefaAdapter = new SubtarefaAdapter(tarefa.getSubtarefas());
        holder.recyclerSubtarefas.setAdapter(subtarefaAdapter);

        // Listener para expandir/ocultar subtarefas
        holder.btnExpandirSubtarefas.setOnClickListener(v -> {
            if (holder.recyclerSubtarefas.getVisibility() == View.GONE) {
                holder.recyclerSubtarefas.setVisibility(View.VISIBLE);
            } else {
                holder.recyclerSubtarefas.setVisibility(View.GONE);
            }
        });

        // Listener para marcar tarefa como concluída
        holder.status.setOnCheckedChangeListener((buttonView, isChecked) -> {
            tarefa.setStatus(isChecked ? "Concluída" : "Pendente");
        });

        // Listeners para editar/excluir tarefa
        holder.btnEditar.setOnClickListener(v -> {
            // Lógica para editar tarefa
        });

        holder.btnExcluir.setOnClickListener(v -> {
            // Lógica para excluir tarefa
        });
    }

    @Override
    public int getItemCount() {
        return tarefaList.size();
    }

    public static class TarefaViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, descricao, dataVencimento, prioridade;
        CheckBox status;
        Button btnEditar, btnExcluir, btnExpandirSubtarefas;
        RecyclerView recyclerSubtarefas;

        public TarefaViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo_tarefa);
            descricao = itemView.findViewById(R.id.descricao_tarefa);
            dataVencimento = itemView.findViewById(R.id.data_vencimento);
            prioridade = itemView.findViewById(R.id.prioridade_tarefa);
            status = itemView.findViewById(R.id.status_tarefa_principal);
            btnEditar = itemView.findViewById(R.id.btn_editar_tarefa);
            btnExcluir = itemView.findViewById(R.id.btn_excluir_tarefa);
            btnExpandirSubtarefas = itemView.findViewById(R.id.btn_exibir_subtarefas);
            recyclerSubtarefas = itemView.findViewById(R.id.recycler_subtarefas);
        }
    }
}
