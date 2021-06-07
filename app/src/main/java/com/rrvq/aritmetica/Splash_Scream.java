package com.rrvq.aritmetica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_Scream extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    Animation animacion1, animacion2;

    int aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash__scream);

        datosSQLite();

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        // animaciones de logo y texto
        animacion1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        animacion2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        textView.setAnimation(animacion2);
        imageView.setAnimation(animacion1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (aux==1) {
                    Intent intent = new Intent(Splash_Scream.this, MainActivity2_Principal.class);
                    startActivity(intent);
                    finish();
                } else if (aux==0){
                    Intent intent = new Intent(Splash_Scream.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 1000);

    }

    public void datosSQLite(){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BD", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor fila = bd.rawQuery("SELECT * FROM sesion WHERE rowid="+1, null);

        if (fila.moveToFirst()){

            aux = 1;

        }else {

            aux = 0;
        }

        bd.close();

    }

}