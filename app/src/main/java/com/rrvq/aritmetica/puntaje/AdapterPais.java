package com.rrvq.aritmetica.puntaje;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.haipq.android.flagkit.FlagImageView;
import com.rrvq.aritmetica.R;

import java.util.List;

public class AdapterPais extends RecyclerView.Adapter<com.rrvq.aritmetica.puntaje.AdapterPais.ViewHolder> implements View.OnClickListener {

    private final LayoutInflater layoutInflater;
    private final List<PuntajesPais> dataPais;
    private final Context context;
    private final String idlite;


    private View.OnClickListener listener;

    public AdapterPais(Context context, List<PuntajesPais> dataPais, String idlite) {
        this.layoutInflater = LayoutInflater.from(context);
        this.dataPais = dataPais;
        this.context = context;
        this.idlite = idlite;
    }

    @NonNull
    @Override
    public com.rrvq.aritmetica.puntaje.AdapterPais.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.recyclerview_posiciones, parent, false);
        //con este escucha los envento de la lista
        view.setOnClickListener(this);

        return new com.rrvq.aritmetica.puntaje.AdapterPais.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final com.rrvq.aritmetica.puntaje.AdapterPais.ViewHolder holder, final int position) {


        final PuntajesPais puntajesPais = dataPais.get(position);
        //para no reciclar las vistas y que no se cambien de posicion los elementos
        //l desabilito
        holder.setIsRecyclable(false);
        String nombre = puntajesPais.getNombre_usu();
        holder.tvNombre.setText(nombre);

        holder.tvpuntaje.setText(puntajesPais.getPuntaje());

//        holder.tvPais.setText(puntajesPais.getPais());

        //asignar desde la actividad
        holder.tvPos.setText(puntajesPais.getPos());

        // para la bandera
        holder.imgIcono.setCountryCode(puntajesPais.getPais());

        if (idlite.equals(puntajesPais.getId())){
            holder.tvNombre.setTextColor(context.getResources().getColor(R.color.rojo));
            holder.tvPos.setTextColor(context.getResources().getColor(R.color.rojo));
//            holder.tvPais.setTextColor(context.getResources().getColor(R.color.rojo));
            holder.tvpuntaje.setTextColor(context.getResources().getColor(R.color.rojo));
        }

    }

    @Override
    public int getItemCount() {
        return dataPais.size();
    }

    public void setOnClickListener(View.OnClickListener listener){

        this.listener = listener;

    }

    @Override
    public void onClick(View v) {

        if (listener != null){
            listener.onClick(v);
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombre, tvpuntaje, tvPos, tvPais;
//        ImageView imgIcono;
        FlagImageView imgIcono;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvpuntaje = itemView.findViewById(R.id.tvpuntaje);
            tvPos = itemView.findViewById(R.id.tvPos);
//            tvPais = itemView.findViewById(R.id.tvPais);
            imgIcono = itemView.findViewById(R.id.imgIcono);


        }
    }



}
