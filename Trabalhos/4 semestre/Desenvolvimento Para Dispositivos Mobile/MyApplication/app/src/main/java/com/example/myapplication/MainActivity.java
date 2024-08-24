package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button bnt_localiza;
    EditText edt_localizacao;
    WebView web_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnt_localiza = (Button) findViewById(R.id.bnt_local);
        edt_localizacao = (EditText) findViewById(R.id.edt_text);
        web_map = (WebView) findViewById(R.id.web_map);

        bnt_localiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                web_map.getSettings().setJavaScriptEnabled(true);
                web_map.loadUrl("https://www.google.com/maps/place");
            }
        });
    }
}