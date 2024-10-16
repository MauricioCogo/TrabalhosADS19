package com.example.homebankads;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

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

        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mesSelecionado = spMeses.getSelectedItemPosition() + 1; // Ajusta para 1-12
                int anoSelecionado = Integer.parseInt(spAnos.getSelectedItem().toString());
                buscarMovimentos(anoSelecionado,mesSelecionado);
            }
        });
    }

    private void buscarMovimentos(int ano, int mes) {
        // Obtém o ID do usuário
        Bundle extras = getIntent().getExtras();
        int idusr = extras.getInt("id", -1); // Obter ID primeiro

        movimentoscontrole banco = new movimentoscontrole(this);

        // Busca os movimentos no banco de dados
        List<movimentos> movimentos = banco.buscaMovimentos(String.valueOf(idusr), ano, mes);

        // Cria um ArrayAdapter para o ListView
        ArrayAdapter<movimentos> adapter = new ArrayAdapter<movimentos>(this, R.layout.layout_movimentos, movimentos) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // Verifica se a view já foi criada ou reutiliza uma existente
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_movimentos, parent, false);
                }

                // Obtém o item atual
                movimentos movimento = getItem(position);

                // Preenche os TextViews com os dados
                TextView txtTipoOpera = convertView.findViewById(R.id.txt_tipo_opera);
                TextView txtValor = convertView.findViewById(R.id.txt_valor);
                TextView txtDestino = convertView.findViewById(R.id.txt_destino);
                TextView txtDataOpera = convertView.findViewById(R.id.txt_dataopera);

                // Define o texto nos TextViews
                txtTipoOpera.setText(movimento.getTipo_opera());
                txtValor.setText(String.format("Valor: %.2f", movimento.getValor()));
                txtDestino.setText("Destino: " + movimento.getDestino());
                txtDataOpera.setText("Data: " + movimento.getDataopera().toString()); // Converta para string conforme necessário

                return convertView;
            }
        };

        listViewMovimentos.setAdapter(adapter);
    }
}
