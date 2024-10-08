package com.example.homebankads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.bd.R;

public class telaextrato extends AppCompatActivity {
    Button btextratoret;
    Spinner spmeses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telaextrato);
        btextratoret=(Button) findViewById(R.id.btextratret);
        btextratoret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), telaprincipal.class);
                startActivity(i);

                spmeses = (Spinner)findViewById(R.id.spmes);


            }
        });
    }
}