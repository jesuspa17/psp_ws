package com.salesianostriana.psp.sharapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        View v;


        public ViewHolder(View v) {
            super(v);

            usuario = (TextView) v.findViewById(R.id.textViewUsuario);
            fecha = (TextView) v.findViewById(R.id.textViewFechaRecibo);
            imgRecibida = (ImageView) v.findViewById(R.id.imageViewRecibida);
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

        holder.usuario.setText(userActual.getNombre());
        holder.fecha.setText(userActual.getFecha());

    }

    @Override
    public int getItemCount() {
        return lista_datos.size();
    }



}