package com.rrvq.aritmetica;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class GuardaRecuperaVolley {

    Activity activity;

    public GuardaRecuperaVolley(Activity activity){
        this.activity = activity;
    }

    // hacer y las q necesite de la actividadlas recibo arriba o en el metodo q estoy creando
    // solo para la primera iniciada
    public void guardarDatos(String nombre_usu, String puntos, String pais) {

        String url = activity.getResources().getString(R.string.urlGuardarDatos);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (!response.isEmpty()) {
                    guardaID(response);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(activity, activity.getResources().getString(R.string.conexion), Toast.LENGTH_SHORT).show();


            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> parametros = new HashMap<String, String>();

                parametros.put("nombre_usu", nombre_usu);
                parametros.put("puntos", puntos);
                parametros.put("pais", pais);

                return parametros;
            }
        };

        // para cuando hago peticiones con requesstring solamente ************
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(stringRequest);

    }
    public void guardaID(String id){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(activity, "BD", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor fila = bd.rawQuery("SELECT * FROM sesion WHERE rowid="+1, null);

        if (fila.moveToFirst()){
            ContentValues update = new ContentValues();
            update.put("id", id);
            update.put("cont","1");
            bd.update("sesion", update, "rowid="+1, null);

        }

    }

    // con esta actualizo solamente el puntaje mediante el id q ya tengo de nube
    public void actualizaDatos(String id, String puntos) {

        String url = activity.getResources().getString(R.string.urlActualizaDatos);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

               /* if (!response.isEmpty()) {
                    String id = response;
                    guardaID(id);
                }*/


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(activity, activity.getResources().getString(R.string.conexion), Toast.LENGTH_SHORT).show();


            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> parametros = new HashMap<String, String>();

                parametros.put("id", id);
                parametros.put("puntos", puntos);

                return parametros;
            }
        };

        // el tiempo se coloca para que este mucho haciendo la peticion o nada
        //para cuando hago peticiones con jsonreques  ***********
        /*jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/

        // para cuando hago peticiones con requesstring solamente ************
        /*stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/



        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(stringRequest);

    }


    public boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

}
