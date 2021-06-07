package com.rrvq.aritmetica.puntaje;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.rrvq.aritmetica.R;
import com.rrvq.aritmetica.sumas.DatosSQLiteSumas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity2_Puntajes extends AppCompatActivity {

    TextView tvPuntaje, tvPosPais, tvPosGeneral;
    Button btnPais, btnGeneral;
    TextView linearPais, linearGeneral;
    RecyclerView recyclerPais, recyclerGeneral;
    View view;

    AdapterPais adapterPais;
    ArrayList<PuntajesPais> dataPais = new ArrayList<>();

    AdapterGeneral adapterGeneral;
    ArrayList<PuntajeGeneral> dataGeneral = new ArrayList<>();

    String paisLite, idLite;
    int countPOSPais=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_puntajes);

        castingView();
        obtenerSQLite();
        obtenerDatos();
        botonesView();


    }

    public void castingView(){

        view = findViewById(R.id.MainActivity_Puntajes);

        tvPuntaje = findViewById(R.id.tvPuntaje);
        tvPosPais = findViewById(R.id.tvPosPais);
        tvPosGeneral = findViewById(R.id.tvPosGeneral);

        btnPais = findViewById(R.id.btn_pais);
        linearPais = findViewById(R.id.linearPais);
        btnGeneral = findViewById(R.id.btn_general);
        linearGeneral = findViewById(R.id.linearGeneral);

        recyclerPais = findViewById(R.id.recyclerviewPais);
        recyclerGeneral = findViewById(R.id.recyclerviewGeneral);
    }

    public void setRecyclerViewPAis(){

        recyclerPais.setLayoutManager(new LinearLayoutManager(this));
        adapterPais = new AdapterPais(this, dataPais, idLite);


        adapterPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //para usar solo listas y referenciasr con get listas.getIdLista() recibo de listas
                PuntajesPais puntajesPais = dataPais.get(recyclerPais.getChildAdapterPosition(v));

                /*Toast.makeText(getApplicationContext(), "Seleccion: "+
                        data.get(recyclerView.getChildAdapterPosition(v)).getNombre_usu()+
                        " ID: "+data.get(recyclerView.getChildAdapterPosition(v)).getId_usuario(), Toast.LENGTH_SHORT).show();*/

            }
        });


        recyclerPais.setAdapter(adapterPais);

    }
    public void setRecyclerViewGeneral(){

        recyclerGeneral.setLayoutManager(new LinearLayoutManager(this));
        adapterGeneral = new AdapterGeneral(this, dataGeneral, idLite);


        adapterGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //para usar solo listas y referenciasr con get listas.getIdLista() recibo de listas
                PuntajeGeneral puntajeGeneral = dataGeneral.get(recyclerGeneral.getChildAdapterPosition(v));

                /*Toast.makeText(getApplicationContext(), "Seleccion: "+
                        data.get(recyclerView.getChildAdapterPosition(v)).getNombre_usu()+
                        " ID: "+data.get(recyclerView.getChildAdapterPosition(v)).getId_usuario(), Toast.LENGTH_SHORT).show();*/

            }
        });


        recyclerGeneral.setAdapter(adapterGeneral);

    }


    public void obtenerDatos(){


        String url = getResources().getString(R.string.urlBuscarDatos);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

//                String resString = response.toString();

                JSONObject jsonObject = null;
                dataPais.clear();
                dataGeneral.clear();

                for (int i = 0; i < response.length(); i++) {


                    try {

                        jsonObject = response.getJSONObject(i);

                        if (jsonObject.getString("id").equalsIgnoreCase("No")) {

                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.sindatos), Toast.LENGTH_SHORT).show();


                        } else {

                            dataGeneral.add(new PuntajeGeneral(
                                    jsonObject.getString("nombre_usu"),
                                    jsonObject.getString("puntos"),
                                    jsonObject.getString("pais"),
                                    jsonObject.getString("id"),
                                    String.valueOf(i+1)
                            ));

                            if (jsonObject.getString("id").equals(idLite)){
//                                countPOSGeneral = i+1;
//                                puntajeTotal = jsonObject.getString("puntos");
                                tvPuntaje.setText(jsonObject.getString("puntos"));
                                tvPosGeneral.setText(String.valueOf(i+1));
                            }

                            if (jsonObject.getString("pais").equals(paisLite)){
                                countPOSPais++;
                                dataPais.add(new PuntajesPais(
                                        jsonObject.getString("nombre_usu"),
                                        jsonObject.getString("puntos"),
                                        jsonObject.getString("pais"),
                                        jsonObject.getString("id"),
                                        String.valueOf(countPOSPais)
                                ));
                                if (jsonObject.getString("id").equals(idLite)){
                                    tvPosPais.setText(String.valueOf(countPOSPais));
                                }
                            }

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                setRecyclerViewPAis();
                setRecyclerViewGeneral();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // esto se puede dar mensaje de error de conexion
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.conexion), Toast.LENGTH_SHORT).show();
            }
        }) ;

        //Podria ir setretrypoliciti para que solo envie una peticion y no se repitan las peticiones

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    public void obtenerSQLite(){

        DatosSQLiteSumas datosSQLiteSumas = new DatosSQLiteSumas(this);
        paisLite = datosSQLiteSumas.setPais();
        idLite = datosSQLiteSumas.setID();

    }

    public void botonesView() {

        btnPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearPais.setBackgroundColor(getResources().getColor(R.color.purple_500));
                linearGeneral.setBackgroundColor(getResources().getColor(R.color.white));
                btnGeneral.setTextColor(getResources().getColor(R.color.letras));
                btnPais.setTextColor(getResources().getColor(R.color.purple_500));
                recyclerGeneral.setVisibility(View.GONE);
                recyclerPais.setVisibility(View.VISIBLE);
            }
        });

        btnGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearGeneral.setBackgroundColor(getResources().getColor(R.color.purple_500));
                linearPais.setBackgroundColor(getResources().getColor(R.color.white));
                btnPais.setTextColor(getResources().getColor(R.color.letras));
                btnGeneral.setTextColor(getResources().getColor(R.color.purple_500));
                recyclerGeneral.setVisibility(View.VISIBLE);
                recyclerPais.setVisibility(View.GONE);
            }
        });

    }


}