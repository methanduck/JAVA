package com.example.dhdms.std_all;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class MoneyData extends SQLiteOpenHelper {
    public MoneyData(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Income = "CREATE TABLE Income ("+
                    "Seq INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "Month INTEGER,"+
                    "Date INTEGER,"+
                    "IncomeWay TEXT,"+
                    "Memo TEXT,"+
                    "Income INTEGER)";
        db.execSQL(Income);                                                                         //수입 내용을 저장할 테이블

        String Withdraw = "CREATE TABLE Withdraw("+
                            "Seq INTEGER PRIMARY KEY AUTOINCREMENT,"+
                            "Month INTEGER,"+
                            "Date INTEGER,"+
                            "WithdrawWay TEXT,"+
                            "Memo TEXT,"+
                            "Withdraw INTEGER)";
        db.execSQL(Withdraw);                                                                       //지출 내용을 저장할 테이블
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Income");
        db.execSQL("DROP TABLE IF EXISTS Withdraw");
        onCreate(db);
    }
}
