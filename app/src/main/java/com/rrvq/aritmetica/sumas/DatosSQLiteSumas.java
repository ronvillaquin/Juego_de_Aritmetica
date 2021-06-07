package com.rrvq.aritmetica.sumas;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rrvq.aritmetica.AdminSQLiteOpenHelper;

public class DatosSQLiteSumas {

    Activity activity;
    int n1, n2, n3, n4, n5, n6, n7, n8, n9, n10;
    int res=0;
    String nombre_usu="", pais="", cont="", id="";
    Cursor fila;

    public DatosSQLiteSumas(Activity activity){
        this.activity = activity;
        RecuperaDatos();
    }

    public void RecuperaDatos(){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(activity, "BD", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        fila = bd.rawQuery("select nombre_usu, pais, id, cont from sesion WHERE rowid="+1, null);

        if (fila.moveToFirst()){

            nombre_usu = fila.getString(0);
            pais = fila.getString(1);
            id = fila.getString(2);
            cont = fila.getString(3);
        }

        /*fila = bd.rawQuery("select sumas from puntos WHERE rowid="+1, null);

        if (fila.moveToFirst()){

            sumas_i = Integer.parseInt(fila.getString(0));

        }*/

        fila = bd.rawQuery("select psn1,psn2,psn3,psn4,psn5,psn6,psn7,psn8,psn9,psn10 from psuma WHERE rowid="+1, null);

        if (fila.moveToFirst()){

            n1 = Integer.parseInt(fila.getString(0));
            n2 = Integer.parseInt(fila.getString(1));
            n3 = Integer.parseInt(fila.getString(2));
            n4 = Integer.parseInt(fila.getString(3));
            n5 = Integer.parseInt(fila.getString(4));
            n6 = Integer.parseInt(fila.getString(5));
            n7 = Integer.parseInt(fila.getString(6));
            n8 = Integer.parseInt(fila.getString(7));
            n9 = Integer.parseInt(fila.getString(8));
            n10 = Integer.parseInt(fila.getString(9));
        }

        bd.close();

    }

    public String setNombre_usu(){
//        RecuperaDatos();
        return nombre_usu;
    }

    public String setPais(){
        return pais;
    }

    public String setID(){return id;}

    public String setCont(){ return cont;
    }

    public int setResultado(){
        res = n1+n2+n3+n4+n5+n6+n7+n8+n9+n10;
        return res;
    }

    public int setn1(){
        return n1;
    }
    public int setn2(){
        return n2;
    }
    public int setn3(){
        return n3;
    }
    public int setn4(){
        return n4;
    }
    public int setn5(){
        return n5;
    }
    public int setn6(){
        return n6;
    }
    public int setn7(){
        return n7;
    }
    public int setn8(){
        return n8;
    }
    public int setn9(){
        return n9;
    }
    public int setn10(){
        return n10;
    }

}
