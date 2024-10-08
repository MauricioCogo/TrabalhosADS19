package com.example.homebankads;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class conectadb extends SQLiteOpenHelper {

    String tab_usr = "CREATE TABLE IF NOT EXISTS usuario (" +
            "id_usr INTEGER PRIMARY KEY AUTOINCREMENT,login TEXT,senha TEXT)";
    conectadb (Context contexto){
        super(contexto, "osdb",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(tab_usr);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String RECRIA="DROP TABLE IF EXISTS usuario";
        sqLiteDatabase.execSQL(RECRIA);
        onCreate(sqLiteDatabase);
    }
}
