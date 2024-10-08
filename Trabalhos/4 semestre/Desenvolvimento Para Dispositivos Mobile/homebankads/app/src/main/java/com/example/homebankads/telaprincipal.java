package com.example.homebankads;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class telaprincipal extends AppCompatActivity {
    Button btsair,btmovimentos,btextrato;
    TextView txtcorrentista,txtsaldo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telaprincipal);
        txtcorrentista=(TextView) findViewById(R.id.txtcorrentista);
        txtsaldo=(TextView) findViewById(R.id.txtsaldo);
        try {
            Bundle extras = getIntent().getExtras();
            String nome = extras.getString("nome");
            String saldo = extras.getString("saldo");
            txtcorrentista.setText(nome);
            txtsaldo.setText(saldo);
        }catch(Exception ex){}
        btextrato = (Button)findViewById(R.id.btextrato);
        btextrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), telaextrato.class);
                startActivity(i);
            }
        });

        this.setTitle("Home Bank ADS - principal");
        btmovimentos=(Button) findViewById(R.id.btmovimentos);
        btmovimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), telamovimentos.class);
                startActivity(i);
            }
        });


        btsair=(Button) findViewById(R.id.btencerra);
        btsair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }
}