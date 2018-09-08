package com.example.dhdms.std_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBmanager extends SQLiteOpenHelper {
    public DBmanager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table sangpum(id integer primary key autoincrement,"+
                    "name text not null,"+
                    "price integer not null,"+
                    "su integer not null,"+
                    "totprice integer not null)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop table if EXISTS sangpum");
    onCreate(db);
    }
}
