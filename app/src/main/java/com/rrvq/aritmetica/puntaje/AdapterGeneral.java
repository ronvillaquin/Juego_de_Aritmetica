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

public class AdapterGeneral extends RecyclerView.Adapter<com.rrvq.aritmetica.puntaje.AdapterGeneral.ViewHolder> implements View.OnClickListener {

    private final LayoutInflater layoutInflater;
    private final List<PuntajeGeneral> dataGeneral;
    private final Context context;
    private final String idlite;


    private View.OnClickListener listener;

    public AdapterGeneral(Context context, List<PuntajeGeneral> dataGeneral, String idlite) {
        this.layoutInflater = LayoutInflater.from(context);
        this.dataGeneral = dataGeneral;
        this.context = context;
        this.idlite = idlite;
    }

    @NonNull
    @Override
    public com.rrvq.aritmetica.puntaje.AdapterGeneral.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.recyclerview_posiciones, parent, false);
        //con este escucha los envento de la lista
        view.setOnClickListener(this);

        return new com.rrvq.aritmetica.puntaje.AdapterGeneral.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final com.rrvq.aritmetica.puntaje.AdapterGeneral.ViewHolder holder, final int position) {


        final PuntajeGeneral puntajeGeneral = dataGeneral.get(position);

        //para no reciclar las vistas y que no se cambien de posicion los elementos
        //l desabilito
        holder.setIsRecyclable(false);
        String nombre = puntajeGeneral.getNombre_usu();
        holder.tvNombre.setText(nombre);

        holder.tvpuntaje.setText(puntajeGeneral.getPuntaje());

//        holder.tvPais.setText(puntajeGeneral.getPais());

        //asignar desde la actividad
        holder.tvPos.setText(puntajeGeneral.getPos());

        // para la bandera
        holder.imgIcono.setCountryCode(puntajeGeneral.getPais());

        if (idlite.equals(puntajeGeneral.getId())){
            holder.tvNombre.setTextColor(context.getResources().getColor(R.color.rojo));
            holder.tvPos.setTextColor(context.getResources().getColor(R.color.rojo));
//            holder.tvPais.setTextColor(context.getResources().getColor(R.color.rojo));
            holder.tvpuntaje.setTextColor(context.getResources().getColor(R.color.rojo));
        }

    }


    @Override
    public int getItemCount() {
        return dataGeneral.size();
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
