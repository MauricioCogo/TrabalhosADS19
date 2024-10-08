package com.example.homebankads;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class usuariocontrole {

    private SQLiteDatabase db;
    private conectadb banco;

    public usuariocontrole(Context contexto) {
        this.banco = new conectadb(contexto);
    }

    public void insere_usuario(usuario usr){
        String INSERE_USUARIO = "INSERT INTO usuario (login,senha) values ('"+usr.getLogin()+"','"+usr.getSenha()+"')";
        try{
            SQLiteDatabase db=banco.getWritableDatabase();
            db.execSQL(INSERE_USUARIO);
            db.close();
        }catch(Exception ex){
            //("Erro (criação tabela)",ex.getMessage());
        }


    }

    public void apaga_usuario(usuario usr){
        String APAGA_USUARIO = "DELETE FROM usuario WHERE id_usr ="+usr.getId();
        try{
            SQLiteDatabase db=banco.getWritableDatabase();
            db.execSQL(APAGA_USUARIO);
            db.close();
        }catch(Exception ex){
            //("Erro (criação tabela)",ex.getMessage());
        }
    }
    public void atualiza_usuario(usuario usr){
        String ATUALIZA_USUARIO = "UPDATE TABLE usuario SET login = "+usr.getLogin()+", senha ="+usr.getSenha()+" WHERE id_usr = "+usr.getId();
        try{
            SQLiteDatabase db=banco.getWritableDatabase();
            db.execSQL(ATUALIZA_USUARIO);
            db.close();
        }catch(Exception ex){
            //("Erro (criação tabela)",ex.getMessage());
        }
    }
    public List<usuario> Consulta_todos_usuarios() {
        List<usuario> listaDeUsuarios = new ArrayList<usuario>();
        String CONSULTA_USUARIO ="SELECT usuario_id,usuario_nome,usuario_email,usuario_senha FROM usuario ORDER BY usuario_nome";
        try{
            SQLiteDatabase bd=banco.getReadableDatabase();
            Cursor c=bd.rawQuery(CONSULTA_USUARIO,null);
            if (c.moveToFirst()) {
                do {
                    usuario usr = new usuario();
                    usr.setId(Integer.parseInt(c.getString(0)));
                    usr.setLogin(c.getString(1));
                    usr.setSenha(c.getString(2));
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
        String PESQUISA_POR_NOME="SELECT usuario_id FROM usuario WHERE usuario_nome ='"+nome+"'";
        boolean ret=false;
        try{
            SQLiteDatabase bd=banco.getReadableDatabase();
            Cursor c=bd.rawQuery(PESQUISA_POR_NOME,null);
            int cursorCount = c.getCount();
            c.close();
            if (cursorCount > 0) {
                ret =true;
            }
            banco.close();
        }catch(Exception ex){
            //  ("Erro (criação tabela)",ex.getMessage());
            ret=false;
        }
        return ret;
    }


    public boolean checkusuario(String nome, String senha) {
        String PESQUISA_POR_NOME_SENHA="SELECT usuario_id  FROM usuario WHERE usuario_nome ='"+nome+"' AND usuario_senha='"+senha+"'";
        boolean ret=false;
        try{
            SQLiteDatabase bd=banco.getReadableDatabase();
            Cursor c=bd.rawQuery(PESQUISA_POR_NOME_SENHA,null);
            int cursorCount = c.getCount();
            c.close();
            if (cursorCount > 0) {
                ret =true;
            }
            banco.close();
        }catch(Exception ex){
            //  ("Erro (criação tabela)",ex.getMessage());
            ret=false;
        }
       return ret;
    }
}
