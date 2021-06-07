package com.rrvq.aritmetica;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.snackbar.Snackbar;
import com.rrvq.aritmetica.combinados.DatosSQLiteCombinados;
import com.rrvq.aritmetica.combinados.MainActivity2_Combinados;
import com.rrvq.aritmetica.divicion.DatosSQLiteDivicion;
import com.rrvq.aritmetica.divicion.MainActivity2_Divicion;
import com.rrvq.aritmetica.multiplicacion.DatosSQLiteMultiplicacion;
import com.rrvq.aritmetica.multiplicacion.MainActivity2_Multiplicacion;
import com.rrvq.aritmetica.puntaje.MainActivity2_Puntajes;
import com.rrvq.aritmetica.restas.DatosSQLiteRestas;
import com.rrvq.aritmetica.restas.MainActivity2_Restas;
import com.rrvq.aritmetica.sumas.DatosSQLiteSumas;
import com.rrvq.aritmetica.sumas.MainActivity2_Sumas;


public class MainActivity2_Principal extends AppCompatActivity implements View.OnClickListener{

    Toolbar toolbar;
    AdView mAdView;

    View view;

    LinearLayout linearS, linearR, linearM, linearD, linearC;
    ImageView ivCR, ivCM, ivCD, ivCC;

    ProgressBar progressBarS, progressBarR, progressBarM, progressBarD, progressBarC;
    int maxProgressBar=100;

    String nombre_usu, pais, cont, id;
    int total_i, sumas_i, restas_i, multiplicacion_i, division_i, combinados_i;
    TextView tvPuntaje;

    int condicionCandado = 80;

    GuardaRecuperaVolley guardaRecuperaVolley = new GuardaRecuperaVolley(this);
    Boolean Online;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__principal);

        Online = guardaRecuperaVolley.isOnline(getApplicationContext());
        datosSQLite();
        casting();
        toolbarMenu();
        // en el metodo verificar esta contenido el banner
        verificarPublicidad();
//        BANNER();
        desbloqueoNiveles();
        datosProgressBar();


        tvPuntaje.setText(String.valueOf(total_i));
        guardaVolley();

    }


    public void casting(){

        toolbar = findViewById(R.id.toolbar);
        view = findViewById(R.id.MainActivity_Principal);

        linearS = findViewById(R.id.linearS);
        linearS.setOnClickListener(this);
        linearR = findViewById(R.id.linearR);
        linearR.setOnClickListener(this);
        linearM = findViewById(R.id.linearM);
        linearM.setOnClickListener(this);
        linearD = findViewById(R.id.linearD);
        linearD.setOnClickListener(this);
        linearC = findViewById(R.id.linearC);
        linearC.setOnClickListener(this);

        ivCR = findViewById(R.id.ivCandadoR);
        ivCM = findViewById(R.id.ivCandadoM);
        ivCD = findViewById(R.id.ivCandadoD);
        ivCC = findViewById(R.id.ivCandadoC);

        progressBarS = findViewById(R.id.progressBarS);
        progressBarR = findViewById(R.id.progressBarR);
        progressBarM = findViewById(R.id.progressBarM);
        progressBarD = findViewById(R.id.progressBarD);
        progressBarC = findViewById(R.id.progressBarC);

        tvPuntaje = findViewById(R.id.tvPuntaje);
    }

    //*****************Desbloquear niveles ***************//
    public void desbloqueoNiveles(){

        if (sumas_i>=condicionCandado){

            linearR.setEnabled(true);
            ivCR.setVisibility(View.GONE);

        }else {

            linearR.setEnabled(false);
            ivCR.setVisibility(View.VISIBLE);

        }

        if (restas_i>=condicionCandado){

            linearM.setEnabled(true);
            ivCM.setVisibility(View.GONE);

        }else {

            linearM.setEnabled(false);
            ivCM.setVisibility(View.VISIBLE);

        }

        if (multiplicacion_i>=condicionCandado){

            linearD.setEnabled(true);
            ivCD.setVisibility(View.GONE);

        }else {

            linearD.setEnabled(false);
            ivCD.setVisibility(View.VISIBLE);

        }

        if (division_i>=60){

            linearC.setEnabled(true);
            ivCC.setVisibility(View.GONE);

        }else {

            linearC.setEnabled(false);
            ivCC.setVisibility(View.VISIBLE);

        }

    }

    //***************  LLENADO DE PROGRESSBAR  ***************//
    public void datosProgressBar(){

        progressBarS.setProgress(sumas_i);
        progressBarS.setMax(maxProgressBar);

        progressBarR.setProgress(restas_i);
        progressBarR.setMax(maxProgressBar);

        progressBarM.setProgress(multiplicacion_i);
        progressBarM.setMax(maxProgressBar);

        progressBarD.setProgress(division_i);
        progressBarD.setMax(maxProgressBar);

        progressBarC.setProgress(combinados_i);
        progressBarC.setMax(maxProgressBar);


    }

    //***************** Recuperar datos SQLite  ****************//
    public void datosSQLite(){


        DatosSQLiteSumas datosSQLiteSumas = new DatosSQLiteSumas(this);
        nombre_usu = datosSQLiteSumas.setNombre_usu();
        pais = datosSQLiteSumas.setPais();
        cont = datosSQLiteSumas.setCont();
        id = datosSQLiteSumas.setID();
        sumas_i = datosSQLiteSumas.setResultado();

        DatosSQLiteRestas datosSQLiteRestas = new DatosSQLiteRestas(this);
        restas_i = datosSQLiteRestas.setResultado();

        DatosSQLiteMultiplicacion datosSQLiteMultiplicacion = new DatosSQLiteMultiplicacion(this);
        multiplicacion_i = datosSQLiteMultiplicacion.setResultado();

        DatosSQLiteDivicion datosSQLiteDivicion = new DatosSQLiteDivicion(this);
        division_i = datosSQLiteDivicion.setResultado();

        DatosSQLiteCombinados datosSQLiteCombinados = new DatosSQLiteCombinados(this);
        combinados_i = datosSQLiteCombinados.setResultado();

        // para poder llamar el banner tengo que pasarle  el madview ya casteado al lado de this
//        datosSQLiteRestas.BANNER();


        total_i = sumas_i+restas_i+multiplicacion_i+division_i+combinados_i;


    }

    //******************* TOOLBAR MENU  ***********************//
    public void toolbarMenu() {
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>" + getResources().getString(R.string.bienvenido) +" : "+nombre_usu+ "</font>"));
    }
    //********************* MENU CONTEXTUAL   *************************//
    public boolean onCreateOptionsMenu(Menu vista){

        getMenuInflater().inflate(R.menu.opciones, vista);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem vista){

        int id = vista.getItemId();
        Online = guardaRecuperaVolley.isOnline(getApplicationContext());

        if (id == R.id.itemEliminar){

            if (Online) {
                Flavor flavor = new Flavor();
                String iid = flavor.getId();
                Boolean b = getBoolFromPref(this, "myPref", iid);

                if (b){
                    Snackbar.make(view, getResources().getString(R.string.erespremium), Snackbar.LENGTH_SHORT).show();

                }else {

                    Intent intent = new Intent(this, MainActivity2_Premium.class);
                    startActivity(intent);

                }
            }else {
                Toast.makeText(this, getResources().getString(R.string.conexion), Toast.LENGTH_SHORT).show();
            }
        }else
        if (id == R.id.itemPuntaje){
            if (Online) {
                Intent intent = new Intent(this, MainActivity2_Puntajes.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, getResources().getString(R.string.conexion), Toast.LENGTH_SHORT).show();
            }
        }else
        if (id == R.id.itemCerrar){

            confirmarCerrarSesion();

        }
        return super.onOptionsItemSelected(vista);
    }

    //***************** CONFIRMAR CERRAR SESION *******************/
    public void confirmarCerrarSesion(){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity2_Principal.this);
        dialog.setTitle(getResources().getString(R.string.cerrarsesion));

        dialog.setMessage(getResources().getString(R.string.segurodecerrar) +
                "\n\n" + getResources().getString(R.string.perderaprogreso) );

        dialog.setPositiveButton(getResources().getString(R.string.si), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(MainActivity2_Principal.this, "BD", null, 1);
                SQLiteDatabase bd = admin.getWritableDatabase();

                bd.delete("sesion", "rowid="+ 1,null);
                bd.delete("psuma", "rowid="+ 1,null);
                bd.delete("presta", "rowid="+ 1,null);
                bd.delete("pmultiplicacion", "rowid="+ 1,null);
                bd.delete("pdivicion", "rowid="+ 1,null);
                bd.delete("pcombinado", "rowid="+ 1,null);
                bd.close();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            }
        });

        dialog.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialog.show();

    }

    //********************  BANNER *******************//
    //************************  Para verificar si ya la compro o no ***************//
    private Boolean getBoolFromPref(Context context, String prefName, String constantName){

        SharedPreferences pref = context.getSharedPreferences(prefName, 0);
        return pref.getBoolean(constantName, false);

    }
    public void verificarPublicidad(){

        //******************** Verificar la compra para mostrar anuncios   **********************//
        // se verifica desde una clase aparte en los dosflavors

            Flavor flavor = new Flavor();
            String id = flavor.getId();
            Boolean b = getBoolFromPref(this, "myPref", id);

            if (b){


            }else {

                BANNER();

            }

    }
    public void BANNER(){

        // genera para todos los anuncios
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        // para el banner
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    //*************************  GUARDADO DE VOLLEY EN LA NUBE  *******************//
    public void guardaVolley(){

        String puntos = String.valueOf(total_i);

        if (Online) {
            if (cont.equals("0")){
                guardaRecuperaVolley.guardarDatos(nombre_usu,puntos,pais);
            }else {
                // editar solo puntaje enviando el id para verificar en la nube
                guardaRecuperaVolley.actualizaDatos(id,puntos);
            }
        }
    }

    //*****************  BTN PARA REGRESAR  **********************//

    @Override
    public void onBackPressed() {
        finish();
    }


    @Override
    public void onClick(View v) {

        int id = v.getId();
        Intent intent;
        switch (id) {
            case R.id.linearS:
                finish();
                intent = new Intent(MainActivity2_Principal.this, MainActivity2_Sumas.class);
                startActivity(intent);
                break;
            case R.id.linearR:
                finish();
                intent = new Intent(MainActivity2_Principal.this, MainActivity2_Restas.class);
                startActivity(intent);
                break;
            case R.id.linearM:
                finish();
                intent = new Intent(MainActivity2_Principal.this, MainActivity2_Multiplicacion.class);
                startActivity(intent);
                break;
            case R.id.linearD:
                finish();
                intent = new Intent(MainActivity2_Principal.this, MainActivity2_Divicion.class);
                startActivity(intent);
                break;
            case R.id.linearC:
                finish();
                intent = new Intent(MainActivity2_Principal.this, MainActivity2_Combinados.class);
                startActivity(intent);
                break;
        }
    }
}