package com.example.homebankads;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btlogar;
    TextView linkregistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Home Bank ADS");
        btlogar=(Button) findViewById(R.id.buttonlogar);
        btlogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), telaprincipal.class);
                i.putExtra("nome", "Daniel Boemo");
                i.putExtra("saldo", "R$ 5000,00");
                startActivity(i);
            }
        });
        linkregistro =(TextView) findViewById(R.id.linkCriaConta);
        linkregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), telaregistro.class);

                startActivity(i);
            }
        });


    }
}