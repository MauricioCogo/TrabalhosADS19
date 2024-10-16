package com.example.homebankads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
    public List<movimentos> buscaMovimentos(String idUsuario, int ano, int mes) {
        List<movimentos> operacoes = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()); // Formato da data na base

        // Define a consulta SQL
        String BUSCA_MOVIMENTOS = "SELECT \n" +
                "    idmov AS id_movimento,\n" +
                "    idusr AS id_usuario,\n" +
                "    tipo_opera AS tipo_operacao,\n" +
                "    valor AS valor_operacao,\n" +
                "    destino AS destino_operacao,\n" +
                "    substr(dataopera, -4) AS ano,  -- Extrai os últimos 4 caracteres (o ano)\n" +
                "    CASE substr(dataopera, 5, 3)\n" +
                "        WHEN 'Jan' THEN '01'\n" +
                "        WHEN 'Feb' THEN '02'\n" +
                "        WHEN 'Mar' THEN '03'\n" +
                "        WHEN 'Apr' THEN '04'\n" +
                "        WHEN 'May' THEN '05'\n" +
                "        WHEN 'Jun' THEN '06'\n" +
                "        WHEN 'Jul' THEN '07'\n" +
                "        WHEN 'Aug' THEN '08'\n" +
                "        WHEN 'Sep' THEN '09'\n" +
                "        WHEN 'Oct' THEN '10'\n" +
                "        WHEN 'Nov' THEN '11'\n" +
                "        WHEN 'Dec' THEN '12'\n" +
                "    END AS mes\n" +
                "FROM movimento \n" +
                "WHERE idusr = ? AND mes = ? AND ano = ?";

        Log.d("teste",BUSCA_MOVIMENTOS);

        try (SQLiteDatabase db = banco.getReadableDatabase();
             Cursor cursor = db.rawQuery(BUSCA_MOVIMENTOS, new String[]{idUsuario, String.valueOf(ano), String.format("%02d", mes)})) {
            Log.d("teste", cursor.toString());
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Log.d("teste", "pego mais um");

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
                        Log.e("Erro", "Erro ao parsear a data: " + dataString, e);
                    }

                    // Adiciona o movimento à lista
                    operacoes.add(movimento);
                    Log.d("teste", "add mais um");

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("Erro", "Erro ao buscar movimentos", e);
        }

        return operacoes; // Retorna a lista de movimentos
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

