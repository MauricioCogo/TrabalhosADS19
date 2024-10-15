package com.example.homebankads;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class telaextrato extends AppCompatActivity {
    Button btextratoret, btBuscar;
    Spinner spMeses, spAnos;
    ListView listViewMovimentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telaextrato);

        // Inicializa os componentes da interface
        btextratoret = findViewById(R.id.btextratret);
        btBuscar = findViewById(R.id.btBuscar);
        spMeses = findViewById(R.id.spmes);
        spAnos = findViewById(R.id.spano);
        listViewMovimentos = findViewById(R.id.listViewMovimentos); // ID para o ListView que você deve adicionar


        btextratoret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), telaprincipal.class);
                i.putExtra("login", getIntent().getStringExtra("login")); // Passar o login corretamente
                i.putExtra("id", getIntent().getIntExtra("id", -1)); // Passar o ID também
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });

        // Configura o botão de busca
        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarMovimentos();
            }
        });
    }

    private void buscarMovimentos() {
        // Obtém o ID do usuário
        int idUsuario = getIntent().getIntExtra("id", -1);

        movimentoscontrole banco = new movimentoscontrole(this);

        // Obtém os valores selecionados
        int mesSelecionado = spMeses.getSelectedItemPosition() + 1; // Ajusta para 1-12
        int anoSelecionado = Integer.parseInt(spAnos.getSelectedItem().toString());

        // Busca os movimentos no banco de dados
        ArrayList<movimentos> movimentos = banco.buscaMovimentos(String.valueOf(idUsuario), anoSelecionado, mesSelecionado);

        // Cria um ArrayAdapter para o ListView
        ArrayAdapter<movimentos> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movimentos);
        listViewMovimentos.setAdapter(adapter);
    }
}