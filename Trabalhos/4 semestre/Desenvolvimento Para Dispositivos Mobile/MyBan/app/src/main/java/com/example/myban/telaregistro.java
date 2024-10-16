package com.example.myban;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class telaregistro extends AppCompatActivity {
    Button btretorna, btregistra;
    EditText edtlogin, edtsenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telaregistro);
        btretorna = (Button) findViewById(R.id.btregretornar);
        btregistra = (Button) findViewById(R.id.btregregistrar);
        edtlogin = (EditText) findViewById(R.id.edtreglogin);
        edtsenha = (EditText) findViewById(R.id.edtregsenha);

        btregistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario usrtemp = new usuario(edtlogin.getText().toString(), edtsenha.getText().toString(), 10);
                usuariocontrole usrcontrol = new usuariocontrole(telaregistro.this);
                if (usrcontrol.insereUsuario(usrtemp)) {
                    showAlertDialog("Parabens!", "Conta criada com sucesso!");
                }else{
                    showAlertDialog("Error", "Não foi possivel criar a conta!");
                }
            }
        });
        btretorna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });
    }

    public void showAlertDialog(String title, String body) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(body);

        // Botão OK
        builder.setPositiveButton("Voltar ao inicio", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Ação ao clicar em Cancelar
                dialog.dismiss();
            }
        });

        // Mostrar o AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}