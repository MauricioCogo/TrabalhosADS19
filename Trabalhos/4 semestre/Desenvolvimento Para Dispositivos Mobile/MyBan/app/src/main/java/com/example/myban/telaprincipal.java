package com.example.myban;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class telaprincipal extends AppCompatActivity {

    Button btsair,btmovimentos,btextrato;
    TextView txtcorrentista,txtsaldo;
    usuario usr;
    int idusr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telaprincipal);

        txtcorrentista = findViewById(R.id.txtcorrentista);
        txtsaldo = findViewById(R.id.txtsaldo);

        usuariocontrole usrc = new usuariocontrole(telaprincipal.this);

        // Obtenha os extras do Intent
        try {
            Bundle extras = getIntent().getExtras();
            String nome = extras.getString("login");
            idusr = extras.getInt("id"); // Obter ID primeiro
            usr = usrc.retornaDadosUsuario(nome); // Obter os dados do usuário

            // Verifique se usr não é nulo antes de usar
            if (usr != null) {
                txtcorrentista.setText(usr.getLogin());
                txtsaldo.setText(String.valueOf(usr.getSaldo()));
            } else {
                Log.e("telaprincipal", "Usuário não encontrado para o login: " + nome);
            }
        } catch (Exception ex) {
            Log.e("telaprincipal", "Erro ao obter os dados do usuário", ex);
        }

        btextrato = findViewById(R.id.btextrato);
        btextrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), telaextrato.class);
                i.putExtra("id", idusr); // Enviar idusr corretamente
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });

        this.setTitle("Home Bank ADS - principal");
        btmovimentos = findViewById(R.id.btmovimentos);
        btmovimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), telamovimentos.class);
                i.putExtra("id", idusr);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });

        btsair = findViewById(R.id.btencerra);
        btsair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });
    }
}