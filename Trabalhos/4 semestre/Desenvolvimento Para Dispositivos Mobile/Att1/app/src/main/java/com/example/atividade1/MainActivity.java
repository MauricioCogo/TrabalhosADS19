package com.example.atividade1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button botao;
    TextView txt_result;
    EditText edit_texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = findViewById(R.id.bnt_botao);
        txt_result = findViewById(R.id.txt_result);
        edit_texto = findViewById(R.id.edit_texto);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t = edit_texto.getText().toString();
                t = t.replace(",",".");
                double nota = Double.parseDouble(t);

                if (nota>=7){
                    txt_result.setText("Aprovado!");
                }else if (nota<7){
                    txt_result.setText("Reprovado, pode fazer exame!");
                }else if(nota<1.4){
                    txt_result.setText("Reprovado e não pode fazer exame!");
                }

            }
        });
    }
}