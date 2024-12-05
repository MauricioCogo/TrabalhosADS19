package com.example.tarefas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarefas.Adapter.TarefaAdapter;
import com.example.tarefas.Entity.Subtarefa;
import com.example.tarefas.Entity.Tarefa;
import com.example.tarefas.Entity.Usuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TelaPrincipal extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private List<Tarefa> tarefaList;
    private List<Subtarefa> subtarefasList;
    private Button btnCriarTarefa;
    private Button btnCriarCategoria;
    private TextView txtNome;
    private Usuario u = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        recyclerView = findViewById(R.id.recycler_view_tarefas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        txtNome = findViewById(R.id.nomeusuario);

        // Criar lista de tarefas
        tarefaList = new ArrayList<>();
        subtarefasList = new ArrayList<>();  // Inicializando a lista de subtarefas

        Intent i = getIntent();
        String id = i.getStringExtra("id");
        String nome = i.getStringExtra("nome");

        if (nome != null) {
            txtNome.setText(nome);
        }

        if (id != null && !id.isEmpty()) {
            try {
                u.setId(Integer.parseInt(id));
            } catch (NumberFormatException e) {
            }
        }

        System.out.println(id);
        System.out.println(nome);

        // Referenciar botões
        btnCriarTarefa = findViewById(R.id.btn_criar_tarefa);
        btnCriarCategoria = findViewById(R.id.btn_criar_categoria);

        new Enviajsonpost().execute();

        // Configurar o listener para o botão Criar Tarefa
        btnCriarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar para a tela de criação de tarefa
                Intent intent = new Intent(TelaPrincipal.this, CriarTarefa.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        // Configurar o listener para o botão Criar Categoria
        btnCriarCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar para a tela de criação de categoria (criar a nova tela quando necessário)
                Intent intent = new Intent(TelaPrincipal.this, CriarCategoria.class);
                startActivity(intent);
            }
        });
    }

    class Enviajsonpost extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... arg0) {
            try {
                String url = "read/consulta_tarefas.php";
                JSONObject jsonValores = new JSONObject();

                jsonValores.put("id", u.getId());

                conexao mandar = new conexao();

                String mensagem = mandar.postJSONObject(url, jsonValores);

                // Verifica a resposta
                if (mensagem.contains("SUCCESS")) {
                    try {
                        // Convertendo a resposta para um JSONObject
                        JSONObject jsonResponse = new JSONObject(mensagem);
                        JSONObject tarefas = jsonResponse.getJSONObject("tarefas");

                        // Iterando pelas tarefas usando as chaves exatas (tarefa27, tarefa28, etc.)
                        Iterator<String> keys = tarefas.keys(); // Obtém as chaves das tarefas dinamicamente

                        while (keys.hasNext()) {
                            String chaveTarefa = keys.next();  // Exemplo: "tarefa27", "tarefa28"
                            JSONObject tarefa = tarefas.getJSONObject(chaveTarefa);

                            // Extraindo dados da tarefa
                            String id = tarefa.getString("id");
                            String titulo = tarefa.getString("titulo");
                            String descricao = tarefa.getString("descricao");
                            String dataVencimento = tarefa.getString("data_vencimento");
                            String situacao = tarefa.getString("situacao");
                            String status = tarefa.getString("status");
                            String prioridade = tarefa.getString("prioridade");

                            System.out.println(titulo+"           aaaaa");

                            // Extraindo categoria
                            JSONObject categoria = tarefa.getJSONObject("categoria");
                            String nomeCategoria = categoria.getString("nome");
                            String corCategoria = categoria.getString("cor");

                            // Exibindo os dados da tarefa no log

                            // Subtarefas
                            JSONArray subtarefas = tarefa.getJSONArray("subtarefas");

                            subtarefasList.clear();  // Limpa a lista de subtarefas antes de adicionar novas
                            for (int j = 0; j < subtarefas.length(); j++) {
                                JSONObject subtarefa = subtarefas.getJSONObject(j);
                                String subtarefaId = subtarefa.getString("id");
                                String subtarefaTitulo = subtarefa.getString("titulo");
                                String subtarefaDescricao = subtarefa.getString("descricao");
                                String subtarefaStatus = subtarefa.getString("status");
                                Subtarefa s = new Subtarefa(subtarefaId, subtarefaTitulo, subtarefaStatus);
                                subtarefasList.add(s);
                            }
                            Tarefa t = new Tarefa(id, titulo, descricao, dataVencimento, prioridade, subtarefasList, status, nomeCategoria, corCategoria);

                            System.out.println("aaaaaaaaaaaaaaaaa" + t.toString());
                            tarefaList.add(t);
                            tarefaAdapter = new TarefaAdapter(TelaPrincipal.this, tarefaList);
                            recyclerView.setAdapter(tarefaAdapter);
                        }

                    } catch (Exception e) {
                        System.out.println("deu erro ai fi" + e.getMessage());
                    }
                }

                return mensagem;

            } catch (Exception e) {
                e.printStackTrace();
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

