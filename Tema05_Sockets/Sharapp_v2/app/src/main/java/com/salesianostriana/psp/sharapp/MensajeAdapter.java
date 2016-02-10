package com.salesianostriana.psp.sharapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Jesus Pallares on 09/02/2016.
 */
public class MensajeAdapter extends RecyclerView.Adapter<MensajeAdapter.ViewHolder> {

    private List<CuerpoMensaje> lista_datos;
    Context contexto;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView usuario,fecha;
        ImageView imgRecibida;
        LinearLayout linearLayout;
        View v;


        public ViewHolder(View v) {
            super(v);

            usuario = (TextView) v.findViewById(R.id.textViewUsuario);
            fecha = (TextView) v.findViewById(R.id.textViewFechaRecibo);
            imgRecibida = (ImageView) v.findViewById(R.id.imageViewRecibida);
            linearLayout = (LinearLayout) v.findViewById(R.id.linearLayoutImage);
            this.v = v;
        }
    }

    public MensajeAdapter(List<CuerpoMensaje> lista_datos) {
        this.lista_datos = lista_datos;
    }

    @Override
    public MensajeAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = null;

        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item_layout, viewGroup, false);

        contexto = v.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MensajeAdapter.ViewHolder holder, int i) {

        CuerpoMensaje userActual = lista_datos.get(i);

        Utils.initializePreferences(contexto);

        String nom_prefs = Utils.preferences.getString("nom_user", null);

        if(userActual.getNombre().equals(nom_prefs)){

            holder.linearLayout.setGravity(Gravity.RIGHT);
            holder.usuario.setGravity(Gravity.LEFT);
            holder.fecha.setGravity(Gravity.LEFT);
            holder.usuario.setText("Enviado por mí");
        }else{
            holder.usuario.setText(userActual.getNombre());
        }


        Date fecha = userActual.getFecha();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        holder.fecha.setText(sdf.format(fecha));
        holder.imgRecibida.setImageBitmap(userActual.getImagen());

    }

    @Override
    public int getItemCount() {
        return lista_datos.size();
    }



}