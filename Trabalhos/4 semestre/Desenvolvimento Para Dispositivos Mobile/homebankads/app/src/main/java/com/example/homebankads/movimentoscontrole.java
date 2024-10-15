package com.example.homebankads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class movimentoscontrole {

    private SQLiteDatabase db;
    private conectadb banco;

    public movimentoscontrole(Context contexto) {

        this.banco = new conectadb(contexto);
    }

    @SuppressLint("Range")
    public ArrayList<movimentos> buscaMovimentos(String idUsuario, int ano, int mes) {
        ArrayList<movimentos> listaMovimentos = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()); // Formato da data na base

        // Define a consulta SQL
        String BUSCA_MOVIMENTOS = "SELECT * FROM movimento WHERE idusr = ? AND " +
                "strftime('%Y', dataopera) = ? AND " +
                "strftime('%m', dataopera) = ?";

        SQLiteDatabase db = banco.getReadableDatabase();

        // Usa o método rawQuery para executar a consulta
        Cursor cursor = db.rawQuery(BUSCA_MOVIMENTOS, new String[]{idUsuario, String.valueOf(ano), String.format("%02d", mes)});

        // Processa o cursor e adiciona os movimentos à lista
        if (cursor != null) {
            while (cursor.moveToNext()) {
                // Cria um novo objeto Movimentos e preenche os dados
                movimentos movimento = new movimentos();
                movimento.setIdusr(cursor.getInt(cursor.getColumnIndex("idusr")));
                movimento.setTipo_opera(cursor.getString(cursor.getColumnIndex("tipo_opera")));
                movimento.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));
                movimento.setDestino(cursor.getString(cursor.getColumnIndex("destino")));

                // Converte a string da data para Date
                String dataString = cursor.getString(cursor.getColumnIndex("dataopera"));
                try {
                    Date dataOpera = dateFormat.parse(dataString);
                    movimento.setDataopera(dataOpera); // Assumindo que setDataopera aceita Date
                } catch (ParseException e) {
                    e.printStackTrace(); // Trate a exceção conforme necessário
                }

                // Adiciona o movimento à lista
                listaMovimentos.add(movimento);
            }
            cursor.close(); // Fecha o cursor
        }
        db.close(); // Fecha o banco de dados

        return listaMovimentos; // Retorna a lista de movimentos
    }



    public void insere_movimento(movimentos movi){

        String INSERE_MOVIMENTO = "INSERT INTO movimento (idusr,tipo_opera,valor,destino,dataopera)" +
                " VALUES ("+
                "'"+movi.getIdusr()+"','"+movi.getTipo_opera()+"','"+
                movi.getValor()+"','"+movi.getDestino()+"','"+movi.getDataopera()+"')";
        try{
            SQLiteDatabase db=banco.getWritableDatabase();
            db.execSQL(INSERE_MOVIMENTO);
            db.close();
        }catch(Exception ex){
            //("Erro (criação tabela)",ex.getMessage());
        }


    }

    public void apaga_movimento(movimentos movi){
        String APAGA_MOVIMENTO = "DELETE FROM movimento WHERE idmov ="+movi.getIdmov();
        try{
            SQLiteDatabase db=banco.getWritableDatabase();
            db.execSQL(APAGA_MOVIMENTO);
            db.close();
        }catch(Exception ex){
            //("Erro (criação tabela)",ex.getMessage());
        }
    }
    public void atualiza_movimento(movimentos movi){
        String ATUALIZA_MOVIMENTO = "UPDATE movimento SET " +
                "idusr = '"+movi.getIdusr()+"'," +
                "tipo_opera = '"+movi.getTipo_opera()+"',"+
                "valor = '"+movi.getValor()+"',"+
                "destino = '"+movi.getDestino()+"',"+
                "dataopera = '"+movi.getDataopera()+"'"+
                "WHERE idmov = '"+movi.getIdmov()+"'";
        try{
            SQLiteDatabase db=banco.getWritableDatabase();
            db.execSQL(ATUALIZA_MOVIMENTO);
            db.close();
        }catch(Exception ex){
            //("Erro (criação tabela)",ex.getMessage());
        }
    }

}
