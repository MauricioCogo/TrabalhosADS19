package com.example.tarfas;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CriarCategoria extends AppCompatActivity {

    private EditText editTextNomeCategoria;
    private Spinner colorSpinner;
    private Button btnSalvarCategoria;
    private String categoriaCorHex = "#CCCCCC";  // Cor padrão em Hex (cinza claro)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_categoria);

        // Inicializando os componentes
        editTextNomeCategoria = findViewById(R.id.editTextNomeCategoria);
        colorSpinner = findViewById(R.id.colorSpinner);
        btnSalvarCategoria = findViewById(R.id.btnSalvarCategoria);

        // Configurar o Spinner com as cores
        String[] cores = {"Verde", "Azul", "Vermelho", "Amarelo", "Cinza"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cores);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);

        // Definir comportamento quando uma cor é selecionada
        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Atualizar a cor conforme a seleção
                switch (position) {
                    case 0:
                        categoriaCorHex = "#00796b";  // Verde
                        break;
                    case 1:
                        categoriaCorHex = "#1976D2";  // Azul
                        break;
                    case 2:
                        categoriaCorHex = "#C62828";  // Vermelho
                        break;
                    case 3:
                        categoriaCorHex = "#FFEB3B";  // Amarelo
                        break;
                    case 4:
                        categoriaCorHex = "#9E9E9E";  // Cinza
                        break;
                }

                // Atualizar o TextView para mostrar a cor hexadecimal
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Nenhuma cor foi selecionada
            }
        });

        // Salvar categoria ao clicar no botão
        btnSalvarCategoria.setOnClickListener(v -> salvarCategoria());
    }

    private void salvarCategoria() {
        String nomeCategoria = editTextNomeCategoria.getText().toString().trim();

        // Verificar se o nome da categoria não está vazio
        if (nomeCategoria.isEmpty()) {
            Toast.makeText(this, "Por favor, insira o nome da categoria", Toast.LENGTH_SHORT).show();
        } else {
            // Lógica para salvar a categoria com nomeCategoria e categoriaCorHex
            // Exemplo: Salvar em banco de dados, compartilhamento, ou outra operação
            Toast.makeText(this, "Categoria salva com sucesso!", Toast.LENGTH_SHORT).show();

            // Limpar os campos após salvar
            editTextNomeCategoria.setText("");
            categoriaCorHex = "#CCCCCC";  // Resetar cor para a inicial
        }
    }
}
