package com.example.tarefas;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tarefas.Adapter.TarefaAdapter;
import com.example.tarefas.Entity.Subtarefa;
import com.example.tarefas.Entity.Tarefa;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CriarTarefa extends AppCompatActivity {

    private EditText editTextTitulo, editTextDescricao;
    private Spinner spinnerPrioridade, spinnerCategoria;
    private LinearLayout subtarefasContainer;
    private List<Subtarefa> subtarefas = new ArrayList<>();
    private Button btnAdicionarSubtarefa, btnSalvarTarefa;
    private DatePicker datePicker;
    private Tarefa tarefa;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_tarefa);
        Intent i = getIntent();
        id = i.getStringExtra("id");

        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextDescricao = findViewById(R.id.descricao_tarefa);
        spinnerPrioridade = findViewById(R.id.spinnerPrioridade);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        subtarefasContainer = findViewById(R.id.subtarefasContainer);
        btnAdicionarSubtarefa = findViewById(R.id.btnAdicionarSubtarefa);
        btnSalvarTarefa = findViewById(R.id.btnSalvarTarefa);
        datePicker = findViewById(R.id.date_picker);

        List<String> prioridades = new ArrayList<>();
        prioridades.add("Baixa");
        prioridades.add("Média");
        prioridades.add("Alta");
        ArrayAdapter<String> adapterPrioridade = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, prioridades);
        adapterPrioridade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrioridade.setAdapter(adapterPrioridade);

        List<String> categorias = new ArrayList<>();
        categorias.add("Padrão");
        categorias.add("Trabalho");
        categorias.add("Estudo");
        categorias.add("Pessoal");
        categorias.add("Saúde");
        categorias.add("Financeiro");
        categorias.add("Casa");
        categorias.add("Lazer");
        categorias.add("Compras");
        categorias.add("Projetos");
        ArrayAdapter<String> adapterCategoria = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerCategoria.setAdapter(adapterCategoria);

        btnAdicionarSubtarefa.setOnClickListener(view -> addSubtarefa());

        btnSalvarTarefa.setOnClickListener(view -> salvarTarefa());
    }

    private void addSubtarefa() {
        LinearLayout subtarefaLayout = new LinearLayout(this);
        subtarefaLayout.setOrientation(LinearLayout.HORIZONTAL);
        subtarefaLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        EditText subtarefaTitulo = new EditText(this);
        subtarefaTitulo.setHint("Título da Subtarefa");
        subtarefaLayout.addView(subtarefaTitulo);

        Spinner subtarefaStatus = new Spinner(this);
        List<String> statusList = new ArrayList<>();
        statusList.add("Pendente");
        statusList.add("Concluída");
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statusList);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subtarefaStatus.setAdapter(statusAdapter);
        subtarefaLayout.addView(subtarefaStatus);

        subtarefasContainer.addView(subtarefaLayout);
    }

    private void salvarTarefa() {
        String titulo = editTextTitulo.getText().toString();
        String descricao = editTextDescricao.getText().toString();
        String categoria = spinnerCategoria.getSelectedItem().toString();
        String prioridade = spinnerPrioridade.getSelectedItem().toString();

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();
        String dataVencimento = year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);

        subtarefas.clear();

        for (int i = 0; i < subtarefasContainer.getChildCount(); i++) {
            LinearLayout subtarefaLayout = (LinearLayout) subtarefasContainer.getChildAt(i);

            EditText subtarefaTitulo = (EditText) subtarefaLayout.getChildAt(0);
            String subtarefaTituloText = subtarefaTitulo.getText().toString();

            Spinner subtarefaStatus = (Spinner) subtarefaLayout.getChildAt(1);
            String subtarefaStatusText = subtarefaStatus.getSelectedItem().toString();

            if (!subtarefaTituloText.isEmpty()) {
                subtarefas.add(new Subtarefa(subtarefaTituloText, subtarefaStatusText));
            }
        }

        tarefa = new Tarefa(titulo, descricao, dataVencimento, prioridade, subtarefas, "Pendente",categoria);

        Toast.makeText(this, "Tarefa criada com sucesso!", Toast.LENGTH_SHORT).show();

        new Enviajsonpost().execute();
        finish();
    }

    class Enviajsonpost extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... arg0) {
            try {
                String url = "create/cad_tarefa.php";
                JSONObject jsonValores = new JSONObject();


                jsonValores.put("titulo", tarefa.getTitulo());
                jsonValores.put("descricao", tarefa.getDescricao());
                jsonValores.put("data_vencimento", tarefa.getDataVencimento());
                jsonValores.put("status", tarefa.getStatus());
                jsonValores.put("prioridade", tarefa.getPrioridade());
                jsonValores.put("categoria_id", 1);
                jsonValores.put("usuario_id", id);
                jsonValores.put("subtarefas", tarefa.getSubtarefas());

                System.out.println(jsonValores.toString());

                conexao mandar = new conexao();

                String mensagem = mandar.postJSONObject(url, jsonValores);

                System.out.println(mensagem);
                if (mensagem.contains("SUCCESS")) {
                    System.out.println("OIJGFDSOIJFDG FOI PORRA");
                }

                return mensagem;

            } catch (Exception e) {
                System.out.println(e.getMessage()+"OI");
            }

            return null;
        }

        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while (itr.hasNext()) {
                String key = itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));
            }
            return result.toString();
        }
    }
}
