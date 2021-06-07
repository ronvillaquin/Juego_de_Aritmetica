package com.rrvq.aritmetica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    AdView mAdView;
    int num_aleatorio = (int) (Math.random() * 5);

    ImageView img_cambio;
    TextInputEditText etNombre;
    Button btnComenzar;
    String locale = "otro";

    String[] idImg = {"inicio_uno","inicio_dos","inicio_tres","inicio_cuatro","inicio_cinco","inicio_seis","inicio_siete","inicio_ocho","inicio_nueve"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        casting();
        BANNER();
        secuenciaImg();
        getbtnComenzar();

        TelephonyManager tm = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        locale = tm.getNetworkCountryIso();

    }

    public void casting(){

        img_cambio = findViewById(R.id.img_cambio_inicio);
        etNombre = findViewById(R.id.etNombre);
        btnComenzar = findViewById(R.id.btnComenzar);

    }

    public void getbtnComenzar(){

        btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cajaNombre = etNombre.getText().toString();

                if (!cajaNombre.equals("")){

                    Toast.makeText(MainActivity.this, getResources().getString(R.string.bienvenido), Toast.LENGTH_LONG).show();

                    guardarSQLite(cajaNombre);

                    Intent intent = new Intent(getApplicationContext(), MainActivity2_Principal.class);
//                    intent.putExtra("nombre", cajaNombre);
                    startActivity(intent);
                    finish();

                }else {

                    Toast.makeText(MainActivity.this, getResources().getString(R.string.ingresenombre), Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    public void guardarSQLite(String cajaNombre) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BD", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor fila = bd.rawQuery("SELECT * FROM sesion WHERE rowid="+1, null);

        if (fila.moveToFirst()){
            //aqui colocr todos los datos en 0 reiniciarlos

            if (locale.equals("")){
                locale = "otro";
            }

            ContentValues update = new ContentValues();
            update.put("nombre_usu", cajaNombre);
            update.put("pais", locale);
            update.put("id", "0");
            update.put("cont","0");
            bd.update("sesion", update, "rowid="+1, null);

            ContentValues updatePNSuma = new ContentValues();
            ContentValues updatePNResta = new ContentValues();
            ContentValues updatePNMultiplicacion = new ContentValues();
            ContentValues updatePNDivicion = new ContentValues();
            ContentValues updatePNCombinado = new ContentValues();
            for (int i=0; i<=9; i++){
                String aux = "psn"+(i+1);
                updatePNSuma.put(aux, "0");
                updatePNResta.put(aux, "0");
                updatePNMultiplicacion.put(aux, "0");
                updatePNDivicion.put(aux, "0");
                updatePNCombinado.put(aux, "0");
            }
            bd.update("psuma", updatePNSuma, "rowid="+1, null);
            bd.update("presta", updatePNResta, "rowid="+1, null);
            bd.update("pmultiplicacion", updatePNMultiplicacion, "rowid="+1, null);
            bd.update("pdivicion", updatePNDivicion, "rowid="+1, null);
            bd.update("pcombinado", updatePNCombinado, "rowid="+1, null);

        }else {

            if (locale.equals("")){
                locale = "otro";
            }

            ContentValues insertar = new ContentValues();
            insertar.put("nombre_usu", cajaNombre);
            insertar.put("pais", locale);
            insertar.put("id", "0");
            insertar.put("cont", "0");
            bd.insert("sesion", null, insertar);

            ContentValues insertPNsuma = new ContentValues();
            ContentValues insertPNResta = new ContentValues();
            ContentValues insertPNMultiplicacion = new ContentValues();
            ContentValues insertPNDivicion = new ContentValues();
            ContentValues insertPNCombinado = new ContentValues();
            for (int i=0; i<=9; i++){
                String aux = "psn"+(i+1);
                insertPNsuma.put(aux, "0");
                insertPNResta.put(aux, "0");
                insertPNMultiplicacion.put(aux, "0");
                insertPNDivicion.put(aux, "0");
                insertPNCombinado.put(aux, "0");
            }
            bd.insert("psuma", null, insertPNsuma);
            bd.insert("presta", null, insertPNResta);
            bd.insert("pmultiplicacion", null, insertPNMultiplicacion);
            bd.insert("pdivicion", null, insertPNDivicion);
            bd.insert("pcombinado", null, insertPNCombinado);

        }

        bd.close();

    }

    public void secuenciaImg(){

        int id;
        for (int i=0; i<idImg.length; i++){
            if (num_aleatorio == i){
                id = getResources().getIdentifier(idImg[i], "drawable", getPackageName());
                img_cambio.setImageResource(id);
            }
        }

    }

    public void BANNER(){

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }
}