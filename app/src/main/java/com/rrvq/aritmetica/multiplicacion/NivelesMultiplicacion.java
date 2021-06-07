package com.rrvq.aritmetica.multiplicacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.rrvq.aritmetica.AdminSQLiteOpenHelper;
import com.rrvq.aritmetica.Flavor;
import com.rrvq.aritmetica.GuardaRecuperaVolley;
import com.rrvq.aritmetica.R;

public class NivelesMultiplicacion extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    AdView mAdView;
    InterstitialAd mInterstitialAd;
    String idCompra;

    ProgressBar p1;
    int totalSegundos = 15;
    TextView tvPuntaje, tvaciertos, tvfallidos, tvnumero1, tvsigno1, tvnumero2, tvsigno2, tvnumero3, tvresultado;
    String nombre_usu;
    int n1, n2, n3, n4, n5, n6, n7, n8, n9, n10;
    LinearLayout linearNumeros, linearFinal;

    Button[] castingNumeros;

    Cursor fila;

    int ip=0;

    TextView tvparasuperarlo, tv90omas, tvnivelSuperado, tvpuntosanteriores;
    Button btn_continuar;

    MediaPlayer mp_great, mp_bad;
    CountDownTimer countDownTimer;

    String ns;
    int nivelSelect;

    int numAleatorio1, numAleatorio2, numAleatorio3;
    int resultado;
    int countTurnos = 0;
    int puntos = 0;
    int aciertos = 0, fallidos = 0;

    TableLayout tableLayout;

    GuardaRecuperaVolley guardaRecuperaVolley = new GuardaRecuperaVolley(this);
    Boolean Online;

    int countPublicidad=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveles_multiplicacion);

        ns = getIntent().getStringExtra("nivel");
        nivelSelect = Integer.parseInt(ns);

        Online = guardaRecuperaVolley.isOnline(getApplicationContext());
        datosSQLite();
        casting();
        toolbarMenu();
        // en el metodo verificar esta contenido el banner
        verificarPublicidad();
//        BANNER();


        mostrarProblema();

    }

    //************ gUARDADO SQLITE PUNTOS Y NIVEL FINALIZADO   ************//
    public void guardadoSQLITE(int n, String psn){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BD", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        fila = bd.rawQuery("select * from pmultiplicacion WHERE rowid="+1, null);

        if (fila.moveToFirst()) {

            ContentValues update = new ContentValues();
            update.put(psn, n);
            bd.update("pmultiplicacion", update, "rowid=" + 1, null);
        }

    }

    public void saveSQLiteyPuntos(int nivelSelect){

        tableLayout.setVisibility(View.GONE);
        linearNumeros.setVisibility(View.GONE);
        linearFinal.setVisibility(View.VISIBLE);
        p1.setProgress(totalSegundos);
        countDownTimer.cancel();

        // tambien llamar la base de datos para guardar los puntos si son mayores a los que ya estaban o si son primera ves

        int tvpuntos = Integer.parseInt(tvPuntaje.getText().toString());
        if (tvpuntos >= 8){
            tvparasuperarlo.setVisibility(View.GONE);
            tv90omas.setVisibility(View.GONE);
            tvnivelSuperado.setText(getResources().getString(R.string.nivelSuperado));
        }else {
            tvnivelSuperado.setTextColor(getResources().getColor(R.color.rojo));
            tvnivelSuperado.setText(getResources().getString(R.string.nivelPerdido));
            tvparasuperarlo.setText(getResources().getString(R.string.parasuperalo));
            tv90omas.setText(getResources().getString(R.string.noventaomas));
        }

        switch (nivelSelect){
            case 1:
                tvpuntosanteriores.setText(String.valueOf(n1));
                if (tvpuntos > n1){
                    guardadoSQLITE(tvpuntos, "psn1");
                }
                break;
            case 2:
                tvpuntosanteriores.setText(String.valueOf(n2));
                if (tvpuntos > n2){
                    guardadoSQLITE(tvpuntos, "psn2");
                }
                break;
            case 3:
                tvpuntosanteriores.setText(String.valueOf(n3));
                if (tvpuntos > n3){
                    guardadoSQLITE(tvpuntos, "psn3");
                }
                break;
            case 4:
                tvpuntosanteriores.setText(String.valueOf(n4));
                if (tvpuntos > n4){
                    guardadoSQLITE(tvpuntos, "psn4");
                }
                break;
            case 5:
                tvpuntosanteriores.setText(String.valueOf(n5));
                if (tvpuntos > n5){
                    guardadoSQLITE(tvpuntos, "psn5");
                }
                break;
            case 6:
                tvpuntosanteriores.setText(String.valueOf(n6));
                if (tvpuntos > n6){
                    guardadoSQLITE(tvpuntos, "psn6");
                }
                break;
            case 7:
                tvpuntosanteriores.setText(String.valueOf(n7));
                if (tvpuntos > n7){
                    guardadoSQLITE(tvpuntos, "psn7");
                }
                break;
            case 8:
                tvpuntosanteriores.setText(String.valueOf(n8));
                if (tvpuntos > n8){
                    guardadoSQLITE(tvpuntos, "psn8");
                }
                break;
            case 9:
                tvpuntosanteriores.setText(String.valueOf(n9));
                if (tvpuntos > n9){
                    guardadoSQLITE(tvpuntos, "psn9");
                }
                break;
            case 10:
                tvpuntosanteriores.setText(String.valueOf(n10));
                if (tvpuntos > n10){
                    guardadoSQLITE(tvpuntos, "psn10");
                }
                break;
        }

    }

    //**************** MOSTRAR PROBLEMAS *************************//
    public void numAleatoriosdeNivel(int num1, int num2, int num3, int condicion, String sifras){

        int score = Integer.parseInt(tvPuntaje.getText().toString());

        // revisar que nivel va y  colocar una condicion para que no muestre valores debajo de esos
        if (score <= 10 && countTurnos <= 10){

            do {
                numAleatorio1 = (int) (Math.random()*num1);
                numAleatorio2 = (int) (Math.random()*num2);
                numAleatorio3 = (int) (Math.random()*num3);

                if (sifras.equals("dos")){
                    resultado = numAleatorio1 * numAleatorio2;
                    if (resultado >= condicion && resultado<=1000){
                        tvnumero1.setText(String.valueOf(numAleatorio1));
                        tvnumero2.setText(String.valueOf(numAleatorio2));

                    }
                }else if (sifras.equals("tres")){
                    resultado = numAleatorio1 * numAleatorio2 * numAleatorio3;
                    if (resultado >= condicion && resultado<=1000) {
                        tvsigno2.setText(getResources().getString(R.string.por));
                        tvnumero1.setText(String.valueOf(numAleatorio1));
                        tvnumero2.setText(String.valueOf(numAleatorio2));
                        tvnumero3.setText(String.valueOf(numAleatorio3));

                    }
                }
            } while (resultado<condicion || resultado>1000);

            progressTime();
        }else {

            saveSQLiteyPuntos(nivelSelect);
        }

    }

    public void mostrarProblema(){

        countTurnos++;
        String dos="dos";
        String tres="tres";

        switch (nivelSelect){
            case 1:
                numAleatoriosdeNivel(2,10,0,0, dos);  // cndicionar para q el numero menor a mostrar sea de compeljidad depende del ivel
                break;
            case 2:
                numAleatoriosdeNivel(4,10,0,5, dos);
                break;
            case 3:
                numAleatoriosdeNivel(6,10,0,10, dos);
                break;
            case 4:
                numAleatoriosdeNivel(8,10,0,15, dos);
                break;
            case 5:
                numAleatoriosdeNivel(10,10,0,20, dos);
                break;
            case 6:
                numAleatoriosdeNivel(2,2,10,0, tres);
                break;
            case 7:
                numAleatoriosdeNivel(4, 4,10, 5, tres);
                break;
            case 8:
                numAleatoriosdeNivel(6,6,10,10, tres);
                break;
            case 9:
                numAleatoriosdeNivel(8,8,10,15, tres);
                break;
            case 10:
                numAleatoriosdeNivel(10,10,10,20, tres);
                break;
        }


    }


    //************* CASTIN VIEW   **************************//
    public void casting(){

        toolbar = findViewById(R.id.toolbar);

        // declarand para poder reproducir los sonidos de correcto y incorrecto
        mp_great = MediaPlayer.create(this, R.raw.wonderful);
        mp_bad = MediaPlayer.create(this, R.raw.bad);

        p1 = findViewById(R.id.p1);

        tableLayout = findViewById(R.id.tableLayout1);
        linearNumeros = findViewById(R.id.linearNumeros);
        linearFinal = findViewById(R.id.linearFinalNivel);

        tvPuntaje = findViewById(R.id.tvPuntaje);
        tvaciertos = findViewById(R.id.tvAciertos);
        tvfallidos = findViewById(R.id.tvFallidos);
        tvnumero1 = findViewById(R.id.tvnumero1);
        tvnumero2 = findViewById(R.id.tvnumero2);
        tvnumero3 = findViewById(R.id.tvnumero3);
        tvsigno1 = findViewById(R.id.tvsigno1);
        tvsigno2 = findViewById(R.id.tvsigno2);
        tvresultado = findViewById(R.id.tvResultado);


        castingNumeros = new Button[]{
                findViewById(R.id.btn_uno), findViewById(R.id.btn_dos),
                findViewById(R.id.btn_tres), findViewById(R.id.btn_cuatro),
                findViewById(R.id.btn_cinco), findViewById(R.id.btn_seis),
                findViewById(R.id.btn_siete), findViewById(R.id.btn_ocho),
                findViewById(R.id.btn_nueve), findViewById(R.id.btn_cero),
                findViewById(R.id.btn_comprobar), findViewById(R.id.btn_borrar)};

        for (int i = 0; i < 12; i++) {
            castingNumeros[i].setOnClickListener(this);
        }

        tvparasuperarlo = findViewById(R.id.tvparasuperarlo);
        tv90omas = findViewById(R.id.tv90omas);
        tvnivelSuperado = findViewById(R.id.tvnivelSuperado);
        tvpuntosanteriores = findViewById(R.id.tvpuntosanteriores);

        btn_continuar = findViewById(R.id.btn_continuar);
        btn_continuar.setOnClickListener(this);


    }


    // *****************  FALLIDOS POR TIEMPO Y ERRADO   **********************//
    public void setFallidos(){

        fallidos++;
        countDownTimer.cancel();
        ip=0;
        tvresultado.setText("");
        mp_bad.start();
        mostrarProblema();
        tvfallidos.setText(String.valueOf(fallidos));

    }

    //****************  PROGRESS CON TIEMPO PARA RESPONDER  ********************//

    public void progressTime(){

        countDownTimer = new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                ip++;
                p1.setProgress(ip);

            }

            @Override
            public void onFinish() {


                setFallidos();

            }
        }.start();

    }

    //******************* TOOLBAR MENU  ***********************//
    public void toolbarMenu() {
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>" + getResources().getString(R.string.nivel) +" : "+ns+ "</font>"));
    }

    //***************** Recuperar datos SQLite  ****************//
    public void datosSQLite(){

        DatosSQLiteMultiplicacion datosSQLite = new DatosSQLiteMultiplicacion(this);
        nombre_usu = datosSQLite.setNombre_usu();
        n1 = datosSQLite.setn1();
        n2 = datosSQLite.setn2();
        n3 = datosSQLite.setn3();
        n4 = datosSQLite.setn4();
        n5 = datosSQLite.setn5();
        n6 = datosSQLite.setn6();
        n7 = datosSQLite.setn7();
        n8 = datosSQLite.setn8();
        n9 = datosSQLite.setn9();
        n10 = datosSQLite.setn10();

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
        idCompra = flavor.getId();
        Boolean b = getBoolFromPref(this, "myPref", idCompra);

        if (b){


        }else {

            BANNER();

        }

    }
    public void BANNER(){

        // para el intersticial
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.intersticial));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

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
    public void INTERSTICIAL(){
        Flavor flavor = new Flavor();
        idCompra = flavor.getId();
        Boolean b = getBoolFromPref(this, "myPref", idCompra);

        if (b){

            // Load the next interstitial.
            finish();
            Intent intent = new Intent(getApplicationContext(), MainActivity2_Multiplicacion.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            // con la de arriba se elimian todas todas menos la que se llamo
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);  // con esta elimina solo las q estan delante de la que se llamo
            startActivity(intent);

        }else {

            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Load the next interstitial.
                        finish();
                        Intent intent = new Intent(getApplicationContext(), MainActivity2_Multiplicacion.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        // con la de arriba se elimian todas todas menos la que se llamo
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);  // con esta elimina solo las q estan delante de la que se llamo
                        startActivity(intent);
                    }

                });

            } else {
                countPublicidad++;
                Toast.makeText(this, getResources().getString(R.string.precionadenuevo), Toast.LENGTH_SHORT).show();
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }

        }
    }

    //********************* MENU CONTEXTUAL   *************************//
    public boolean onCreateOptionsMenu(Menu vista){

        getMenuInflater().inflate(R.menu.cerrar, vista);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem vista){

        int id = vista.getItemId();


        if (id == R.id.itemCerrar){
            countDownTimer.cancel();
            if (Online && countPublicidad<=1) {
                INTERSTICIAL();
            }else {
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity2_Multiplicacion.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                // con la de arriba se elimian todas todas menos la que se llamo
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);  // con esta elimina solo las q estan delante de la que se llamo
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(vista);
    }


    //*****************  BTN PARA REGRESAR  **********************//

    @Override
    public void onBackPressed() {
        countDownTimer.cancel();
        if (Online && countPublicidad<=1) {
            INTERSTICIAL();
        }else {
            finish();
            Intent intent = new Intent(getApplicationContext(), MainActivity2_Multiplicacion.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            // con la de arriba se elimian todas todas menos la que se llamo
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);  // con esta elimina solo las q estan delante de la que se llamo
            startActivity(intent);
        }
    }


    //*************** ESTADOS PARA DETENER LA SECUENCIA SI CIERRO EL APP *******//

    @Override
    protected void onPause() {
        super.onPause();
        // para cuadno cierre o pause el app
        countDownTimer.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // para cuadno cierre o pause el app
        countDownTimer.cancel();
    }

    //*****************  TECLADO  **********************//

    public void mostrarNPrecionado(String numero){

        String etNumero = tvresultado.getText().toString();

        etNumero = etNumero+ numero.charAt(0);

        tvresultado.setText(etNumero);

    }

    @Override
    public void onClick(View v) {



        //Calculadora con iconos seleccionables
        int id = v.getId();
        switch (id) {
            case R.id.btn_borrar:

                String borrarNumero = tvresultado.getText().toString();
                String muestra = "";

                if (borrarNumero.equals("")){
                    tvresultado.setText("");
                }else {

                    for (int i=0; i<borrarNumero.length()-1; i++){

                        muestra = muestra + borrarNumero.charAt(i);

                    }

                    tvresultado.setText(muestra);
                }
                break;
            case R.id.btn_comprobar:


                if (tvresultado.getText().toString().equals("")) {
                    Toast.makeText(this, getResources().getString(R.string.ingreseNumero), Toast.LENGTH_SHORT).show();
                }else {

                    int tvi = Integer.parseInt(tvresultado.getText().toString());

                    if (resultado == tvi){
                        aciertos++;
                        puntos++;
                        countDownTimer.cancel();
                        ip=0;
                        tvresultado.setText("");
                        mp_great.start();
                        tvaciertos.setText(String.valueOf(aciertos));
                        tvPuntaje.setText(String.valueOf(puntos));
                        mostrarProblema();
                    }else {

                        setFallidos();
                    }

                }

                break;
            case R.id.btn_cero:
                mostrarNPrecionado("0");
                break;
            case R.id.btn_uno:
                mostrarNPrecionado("1");
                break;
            case R.id.btn_dos:
                mostrarNPrecionado("2");
                break;
            case R.id.btn_tres:
                mostrarNPrecionado("3");
                break;
            case R.id.btn_cuatro:
                mostrarNPrecionado("4");
                break;
            case R.id.btn_cinco:
                mostrarNPrecionado("5");
                break;
            case R.id.btn_seis:
                mostrarNPrecionado("6");
                break;
            case R.id.btn_siete:
                mostrarNPrecionado("7");
                break;
            case R.id.btn_ocho:
                mostrarNPrecionado("8");
                break;
            case R.id.btn_nueve:
                mostrarNPrecionado("9");
                break;
            case R.id.btn_continuar:
                countDownTimer.cancel();
                if (Online && countPublicidad<=1) {
                    INTERSTICIAL();
                }else {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), MainActivity2_Multiplicacion.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    // con la de arriba se elimian todas todas menos la que se llamo
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);  // con esta elimina solo las q estan delante de la que se llamo
                    startActivity(intent);
                }

                break;
        }

    }


}