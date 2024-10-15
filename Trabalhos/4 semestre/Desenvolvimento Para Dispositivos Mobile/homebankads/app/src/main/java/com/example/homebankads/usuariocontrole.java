package com.example.homebankads;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class usuariocontrole {

    private SQLiteDatabase db;
    private conectadb banco;

    public usuariocontrole(Context contexto) {

        this.banco = new conectadb(contexto);
    }
    public boolean insereUsuario(usuario usr) {

        if(usr.getLogin().isBlank() || usr.getSenha().isBlank()){
            return false;
        }

        String tabela = "usuario";
        ContentValues valores = new ContentValues();
        valores.put("login", usr.getLogin());
        valores.put("senha", usr.getSenha());
        valores.put("saldo", usr.getSaldo());

        try (SQLiteDatabase db = banco.getWritableDatabase()) {
            long resultado = db.insert(tabela, null, valores);
            return resultado != -1; // Retorna true se a inserção foi bem-sucedida
        } catch (Exception ex) {
            ex.printStackTrace(); // Log para fins de depuração
            return false;
        }
    }


    public void apaga_usuario(usuario usr){
        String APAGA_USUARIO = "DELETE FROM usuario WHERE id ="+usr.getId();
        try{
            SQLiteDatabase db=banco.getWritableDatabase();
            db.execSQL(APAGA_USUARIO);
            db.close();
        }catch(Exception ex){
            //("Erro (criação tabela)",ex.getMessage());
        }
    }
    public void atualiza_usuario(usuario usr){
        String ATUALIZA_USUARIO = "UPDATE TABLE usuario SET login = "+usr.getLogin()+", senha ="+usr.getSenha()+
                ",saldo="+usr.getSaldo()+" WHERE id = "+usr.getId();
        try{
            SQLiteDatabase db=banco.getWritableDatabase();
            db.execSQL(ATUALIZA_USUARIO);
            db.close();
        }catch(Exception ex){
            //("Erro (criação tabela)",ex.getMessage());
        }
    }

    public usuario retornaDadosUsuario(String login) {
        usuario usr = null;
        if (login.isBlank()){
            return usr;
        }
        String CONSULTA_USUARIO = "SELECT * FROM usuario WHERE login = ?";

        try (SQLiteDatabase bd = banco.getReadableDatabase();
             Cursor c = bd.rawQuery(CONSULTA_USUARIO, new String[]{login})) {

            if (c.moveToFirst()) {
                usr = new usuario();
                usr.setId(c.getInt(c.getColumnIndexOrThrow("id")));
                usr.setLogin(c.getString(c.getColumnIndexOrThrow("login")));
                usr.setSenha(c.getString(c.getColumnIndexOrThrow("senha")));
                usr.setSaldo(c.getDouble(c.getColumnIndexOrThrow("saldo")));
            }

        } catch (Exception ex) {
            // Aqui você pode logar o erro para fins de depuração
            ex.printStackTrace();
        }

        return usr;
    }

    public List<usuario> Consulta_todos_usuarios() {
        List<usuario> listaDeUsuarios = new ArrayList<usuario>();
        String CONSULTA_USUARIO ="SELECT id,login,senha,saldo FROM usuario ORDER BY login";
        try{
            SQLiteDatabase bd=banco.getReadableDatabase();
            Cursor c=bd.rawQuery(CONSULTA_USUARIO,null);
            if (c.moveToFirst()) {
                do {
                    usuario usr = new usuario();
                    usr.setId(Integer.parseInt(c.getString(0)));
                    usr.setLogin(c.getString(1));
                    usr.setSenha(c.getString(2));
                    usr.setSaldo(c.getDouble(3));
                    listaDeUsuarios.add(usr);
                } while (c.moveToNext());
            }
            c.close();
            bd.close();
            return listaDeUsuarios;
        }catch(Exception ex){
            //  ("Erro (criação tabela)",ex.getMessage());
            return null;
        }

    }

    public boolean testaUsuario(String nome) {
        if(!nome.isBlank()){
            return false;
        }
        String PESQUISA_POR_NOME="SELECT id FROM usuario WHERE login ='"+nome+"'";
        boolean ret = false;
        try{
            SQLiteDatabase bd=banco.getReadableDatabase();
            Cursor c=bd.rawQuery(PESQUISA_POR_NOME,null);
            int cursorCount = c.getCount();
            c.close();
            if (cursorCount > 0) {
                ret = true;
            }
            banco.close();
        }catch(Exception ex){
            //  ("Erro (criação tabela)",ex.getMessage());
            ret=false;
        }
        return ret;
    }


    public boolean checkusuario(String nome, String senha) {
        // Verifica se o nome ou senha estão vazios
        if (nome == null || nome.isBlank() || senha == null || senha.isBlank()) {
            return false;
        }

        String pesquisaPorNomeSenha = "SELECT id FROM usuario WHERE login = ? AND senha = ?";
        boolean ret = false;

        try (SQLiteDatabase bd = banco.getReadableDatabase();
             Cursor c = bd.rawQuery(pesquisaPorNomeSenha, new String[]{nome, senha})) {

            // Verifica se o cursor tem resultados
            ret = c.getCount() > 0;

        } catch (Exception ex) {
            ex.printStackTrace(); // Log para fins de depuração
            ret = false;
        }

        return ret;
    }

}
