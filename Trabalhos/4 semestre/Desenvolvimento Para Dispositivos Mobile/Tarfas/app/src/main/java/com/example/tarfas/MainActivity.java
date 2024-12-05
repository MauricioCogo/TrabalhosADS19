package com.example.tarfas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tarfas.Entity.Usuario;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private Usuario u = new Usuario();
    private EditText editTextEmail, editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialize os campos de entrada
        editTextEmail = findViewById(R.id.email);
        editTextSenha = findViewById(R.id.senha);

        Button loginButton = findViewById(R.id.login_button);
        TextView cadastroLink = findViewById(R.id.cadastro_link);

        // Setando a ação de clicar no link para cadastro
        cadastroLink.setOnClickListener(v -> {
            // Ao clicar no link "Cadastre-se", abre a tela de cadastro
            Intent intent = new Intent(MainActivity.this, Cadastrar.class);
            startActivity(intent);
        });

        // Ação do botão de login
        loginButton.setOnClickListener(v -> {
            // Pega os valores dos campos email e senha
            String email = editTextEmail.getText().toString().trim();
            String senha = editTextSenha.getText().toString().trim();

            if (!email.isEmpty() && !senha.isEmpty()) {
                u.setEmail(email);
                u.setSenha(senha);

                // Executa a tarefa de login
                new Enviajsonpost().execute();
            } else {
                // Caso os campos não sejam preenchidos
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Erro");
                builder.setMessage("Por favor, preencha todos os campos.");
                builder.setPositiveButton("OK", null);
                builder.create().show();
            }
        });
    }

    // Classe para enviar os dados via POST para o servidor
    class Enviajsonpost extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... arg0) {
            try {
                String url = "read/consulta_login.php";
                JSONObject jsonValores = new JSONObject();

                // Enviando email e senha para o servidor
                jsonValores.put("senha", u.getSenha());
                jsonValores.put("email", u.getEmail());

                conexao mandar = new conexao();

                String mensagem = mandar.postJSONObject(url, jsonValores);

                JSONObject resposta = new JSONObject(mensagem);

                if (resposta.getString("status").equals("SUCCESS")) {
                    // Acessa o objeto "usuario" na resposta
                    JSONObject usuarioObject = resposta.getJSONObject("usuario");

                    // Pegando os valores dentro do objeto "usuario"
                    String id = usuarioObject.getString("id");
                    String nome = usuarioObject.getString("nome");
                    String email = usuarioObject.getString("email");
                    String senha = usuarioObject.getString("senha");



                    // Redireciona para a Tela Principal
                    Intent i = new Intent(getApplicationContext(), TelaPrincipal.class);
                    i.putExtra("id", id);
                    i.putExtra("nome", nome);
                    startActivity(i);
                    finish();
                } else {
                    // Exibe um erro se o status não for "SUCCESS"
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Erro");
                            builder.setMessage("Email ou senha incorreto!");
                            builder.setPositiveButton("OK", null);
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    });
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
