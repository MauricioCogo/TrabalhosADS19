package com.example.tarefas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tarefas.Entity.Usuario;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Iterator;

public class Cadastrar extends AppCompatActivity {

    private EditText editTextNome, editTextEmail, editTextSenha;
    private Button btnCadastrar;
    private TextView loginLink;
    private Usuario u = new Usuario();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        editTextNome = findViewById(R.id.none);
        editTextEmail = findViewById(R.id.email);
        editTextSenha = findViewById(R.id.senha);
        btnCadastrar = findViewById(R.id.cadastrar_button);
        loginLink = findViewById(R.id.login_link);

        btnCadastrar.setOnClickListener(v -> cadastrarUsuario());

        loginLink.setOnClickListener(v -> navegarParaLogin());
    }

    private void cadastrarUsuario() {
        String nome = editTextNome.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String senha = editTextSenha.getText().toString().trim();

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            u.setNome(nome);
            u.setSenha(senha);
            u.setEmail(email);
            new Enviajsonpost().execute();

            editTextNome.setText("");
            editTextEmail.setText("");
            editTextSenha.setText("");
        }
    }

    private void navegarParaLogin() {
        Intent intent = new Intent(Cadastrar.this, MainActivity.class);
        startActivity(intent);
    }

    class Enviajsonpost extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... arg0) {
            try {
                String url = "create/cad_usuario.php";
                JSONObject jsonValores = new JSONObject();

                jsonValores.put("nome", u.getNome());
                jsonValores.put("senha", u.getSenha());
                jsonValores.put("email", u.getEmail());

                conexao mandar = new conexao();

                String mensagem = mandar.postJSONObject(url, jsonValores);

                if (mensagem.contains("SUCCESS")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Cadastrar.this);
                            builder.setTitle("Cadastro Realizado");
                            builder.setMessage("Usuário cadastrado com sucesso!");

                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            });

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

            while(itr.hasNext()){

                String key= itr.next();
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
