package com.example.controldegastosapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table facturas (\n" +
                " Nfactura text primary key, \n" +
                " descripcion text, \n" +
                " fecha text default (datetime('now', 'localtime')), \n" +
                " tipoGasto INTEGER, \n" +
                " chk1 INTEGER, \n" +
                " chk2 INTEGER, \n" +
                " chk3 INTEGER, \n" +
                " chk4 INTEGER, \n" +
                " chk5 INTEGER, \n" +
                " chk6 INTEGER, \n" +
                " maneraPago INTEGER, \n" +
                " monto real\t\n" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public int getRowCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT * FROM " + "facturas";
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}
