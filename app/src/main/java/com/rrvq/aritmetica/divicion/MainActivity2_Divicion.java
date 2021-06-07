package com.rrvq.aritmetica.divicion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.rrvq.aritmetica.Flavor;
import com.rrvq.aritmetica.GuardaRecuperaVolley;
import com.rrvq.aritmetica.MainActivity2_Principal;
import com.rrvq.aritmetica.R;

public class MainActivity2_Divicion extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    AdView mAdView;
    InterstitialAd mInterstitialAd;
    String idCompra;

    LinearLayout linear1, linear2, linear3, linear4, linear5, linear6, linear7, linear8, linear9, linear10;
    ImageView iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9, iv10;
    ProgressBar p1,p2,p3,p4,p5,p6,p7,p8,p9,p10;
    int maxProgressBar=10;


    String nombre_usu;
    int n1, n2, n3, n4, n5, n6, n7, n8, n9, n10;
    int puntajeTotal;
    TextView tvPuntaje;

    int condicionCandado = 8;

    TextView tvDesbloquea;
    int condicionCandadoOperacion = 60;

    GuardaRecuperaVolley guardaRecuperaVolley = new GuardaRecuperaVolley(this);
    Boolean Online;

    int countPublicidad=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__divicion);

        Online = guardaRecuperaVolley.isOnline(getApplicationContext());
        datosSQLite();
        casting();
        toolbarMenu();
        // en el metodo verificar esta contenido el banner
        verificarPublicidad();
//        BANNER();
        desbloqueoNiveles();
        datosProgressBar();

        tvPuntaje.setText(String.valueOf(puntajeTotal));
        if (puntajeTotal >= condicionCandadoOperacion){
            tvDesbloquea.setText(getResources().getString(R.string.combinadas));
        }else {
            int re = condicionCandadoOperacion - puntajeTotal;
            tvDesbloquea.setText(String.valueOf(re));
        }


    }


    public void casting(){

        toolbar = findViewById(R.id.toolbar);

        linear1 = findViewById(R.id.linear1);
        linear1.setOnClickListener(this);
        linear2 = findViewById(R.id.linear2);
        linear2.setOnClickListener(this);
        linear3 = findViewById(R.id.linear3);
        linear3.setOnClickListener(this);
        linear4 = findViewById(R.id.linear4);
        linear4.setOnClickListener(this);
        linear5 = findViewById(R.id.linear5);
        linear5.setOnClickListener(this);
        linear6 = findViewById(R.id.linear6);
        linear6.setOnClickListener(this);
        linear7 = findViewById(R.id.linear7);
        linear7.setOnClickListener(this);
        linear8 = findViewById(R.id.linear8);
        linear8.setOnClickListener(this);
        linear9 = findViewById(R.id.linear9);
        linear9.setOnClickListener(this);
        linear10 = findViewById(R.id.linear10);
        linear10.setOnClickListener(this);

        iv2 = findViewById(R.id.ivCandado2);
        iv3 = findViewById(R.id.ivCandado3);
        iv4 = findViewById(R.id.ivCandado4);
        iv5 = findViewById(R.id.ivCandado5);
        iv6 = findViewById(R.id.ivCandado6);
        iv7 = findViewById(R.id.ivCandado7);
        iv8 = findViewById(R.id.ivCandado8);
        iv9 = findViewById(R.id.ivCandado9);
        iv10 = findViewById(R.id.ivCandado10);

        p1 = findViewById(R.id.p1);
        p2 = findViewById(R.id.p2);
        p3 = findViewById(R.id.p3);
        p4 = findViewById(R.id.p4);
        p5 = findViewById(R.id.p5);
        p6 = findViewById(R.id.p6);
        p7 = findViewById(R.id.p7);
        p8 = findViewById(R.id.p8);
        p9 = findViewById(R.id.p9);
        p10 = findViewById(R.id.p10);

        tvPuntaje = findViewById(R.id.tvPuntaje);
        tvDesbloquea = findViewById(R.id.tvDesbloquea);
    }

    //*****************Desbloquear niveles ***************//
    public void desbloqueoNiveles(){


        if (n1>=condicionCandado){

            linear2.setEnabled(true);
            iv2.setVisibility(View.GONE);

        }else {

            linear2.setEnabled(false);
            iv2.setVisibility(View.VISIBLE);

        }

        if (n2>=condicionCandado){

            linear3.setEnabled(true);
            iv3.setVisibility(View.GONE);

        }else {

            linear3.setEnabled(false);
            iv3.setVisibility(View.VISIBLE);

        }

        if (n3>=condicionCandado){

            linear4.setEnabled(true);
            iv4.setVisibility(View.GONE);

        }else {

            linear4.setEnabled(false);
            iv4.setVisibility(View.VISIBLE);

        }

        if (n4>=condicionCandado){

            linear5.setEnabled(true);
            iv5.setVisibility(View.GONE);

        }else {

            linear5.setEnabled(false);
            iv5.setVisibility(View.VISIBLE);

        }

        if (n5>=condicionCandado){

            linear6.setEnabled(true);
            iv6.setVisibility(View.GONE);

        }else {

            linear6.setEnabled(false);
            iv6.setVisibility(View.VISIBLE);

        }

        if (n6>=condicionCandado){

            linear7.setEnabled(true);
            iv7.setVisibility(View.GONE);

        }else {

            linear7.setEnabled(false);
            iv7.setVisibility(View.VISIBLE);

        }

        if (n7>=condicionCandado){

            linear8.setEnabled(true);
            iv8.setVisibility(View.GONE);

        }else {

            linear8.setEnabled(false);
            iv8.setVisibility(View.VISIBLE);

        }

        if (n8>=condicionCandado){

            linear9.setEnabled(true);
            iv9.setVisibility(View.GONE);

        }else {

            linear9.setEnabled(false);
            iv9.setVisibility(View.VISIBLE);

        }

        if (n9>=condicionCandado){

            linear10.setEnabled(true);
            iv10.setVisibility(View.GONE);

        }else {

            linear10.setEnabled(false);
            iv10.setVisibility(View.VISIBLE);

        }


    }

    //***************  LLENADO DE PROGRESSBAR  ***************//
    public void datosProgressBar(){

        p1.setProgress(n1);
        p1.setMax(maxProgressBar);

        p2.setProgress(n2);
        p2.setMax(maxProgressBar);

        p3.setProgress(n3);
        p3.setMax(maxProgressBar);

        p4.setProgress(n4);
        p4.setMax(maxProgressBar);

        p5.setProgress(n5);
        p5.setMax(maxProgressBar);

        p6.setProgress(n6);
        p6.setMax(maxProgressBar);

        p7.setProgress(n7);
        p7.setMax(maxProgressBar);

        p8.setProgress(n8);
        p8.setMax(maxProgressBar);

        p9.setProgress(n9);
        p9.setMax(maxProgressBar);

        p10.setProgress(n10);
        p10.setMax(maxProgressBar);


    }


    //***************** Recuperar datos SQLite  ****************//
    public void datosSQLite(){

        DatosSQLiteDivicion datosSQLite = new DatosSQLiteDivicion(this);
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
        puntajeTotal = datosSQLite.setResultado();

    }

    //******************* TOOLBAR MENU  ***********************//
    public void toolbarMenu() {
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>" + getResources().getString(R.string.division) + "</font>"));
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
            Intent intent = new Intent(getApplicationContext(), MainActivity2_Principal.class);
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
                        Intent intent = new Intent(getApplicationContext(), MainActivity2_Principal.class);
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

    //*******************   BOTONES DE LOS NIVELES  ***********************//
    public void btnNiveles(String nv){

        Intent intent = new Intent(MainActivity2_Divicion.this, NivelesDivicion.class);
        // colocar parametro del nivel que esta pulsando
//                String nv = "1";
        intent.putExtra("nivel", nv);
        startActivity(intent);


    }


    //********************* MENU CONTEXTUAL   *************************//
    public boolean onCreateOptionsMenu(Menu vista){

        getMenuInflater().inflate(R.menu.cerrar, vista);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem vista){

        int id = vista.getItemId();


        if (id == R.id.itemCerrar){

            if (Online && countPublicidad<=1) {
                INTERSTICIAL();
            }else {
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity2_Principal.class);
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
        if (Online && countPublicidad<=1){
            INTERSTICIAL();
        }else {
            finish();
            Intent intent = new Intent(getApplicationContext(), MainActivity2_Principal.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            // con la de arriba se elimian todas todas menos la que se llamo
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);  // con esta elimina solo las q estan delante de la que se llamo
            startActivity(intent);
        }
    }


    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){
            case R.id.linear1:
                btnNiveles("1");
                break;
            case R.id.linear2:
                btnNiveles("2");
                break;
            case R.id.linear3:
                btnNiveles("3");
                break;
            case R.id.linear4:
                btnNiveles("4");
                break;
            case R.id.linear5:
                btnNiveles("5");
                break;
            case R.id.linear6:
                btnNiveles("6");
                break;
            case R.id.linear7:
                btnNiveles("7");
                break;
            case R.id.linear8:
                btnNiveles("8");
                break;
            case R.id.linear9:
                btnNiveles("9");
                break;
            case R.id.linear10:
                btnNiveles("10");
                break;
        }

    }

}