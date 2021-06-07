package com.rrvq.aritmetica;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase baseDeDatos) {

        baseDeDatos.execSQL("CREATE TABLE sesion(nombre_usu text, pais text, id text, cont text)");
//        baseDeDatos.execSQL("CREATE TABLE puntos(total text, sumas text, restas text, multiplicacion text, division text, combinados text)");
        baseDeDatos.execSQL("CREATE TABLE psuma(psn1 text, psn2 text, psn3 text, psn4 text, psn5 text, psn6 text, psn7 text, psn8 text, psn9 text, psn10 text)");
        baseDeDatos.execSQL("CREATE TABLE presta(psn1 text, psn2 text, psn3 text, psn4 text, psn5 text, psn6 text, psn7 text, psn8 text, psn9 text, psn10 text)");
        baseDeDatos.execSQL("CREATE TABLE pmultiplicacion(psn1 text, psn2 text, psn3 text, psn4 text, psn5 text, psn6 text, psn7 text, psn8 text, psn9 text, psn10 text)");
        baseDeDatos.execSQL("CREATE TABLE pdivicion(psn1 text, psn2 text, psn3 text, psn4 text, psn5 text, psn6 text, psn7 text, psn8 text, psn9 text, psn10 text)");
        baseDeDatos.execSQL("CREATE TABLE pcombinado(psn1 text, psn2 text, psn3 text, psn4 text, psn5 text, psn6 text, psn7 text, psn8 text, psn9 text, psn10 text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
