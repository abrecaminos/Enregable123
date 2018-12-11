package com.example.federico.entregablemvc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.federico.entregablemvc.model.POJO.Paint;

import java.util.ArrayList;
import java.util.List;

public class PaintAdapter extends RecyclerView.Adapter {


    private List<Paint> listaPaints;
    private ListenerPaintAdapter listenerPaintAdapter;


//INTERFAZ LISTENER DEL ADAPTER
    public interface ListenerPaintAdapter{
        public void informarSeleccion(Paint Paint);
    }

    //PROVIDER,SETTER DEL ADAPTER
    public void setListaPaints(List<Paint> listaPaints) {
        this.listaPaints = listaPaints;
        notifyDataSetChanged();
    }


    //CONSTRUCTORES
    public PaintAdapter(List<Paint> listaPaints, ListenerPaintAdapter listenerPaintAdapter) {
        this.listaPaints = listaPaints;
        this.listenerPaintAdapter = listenerPaintAdapter;
    }

    public PaintAdapter() {
        this.listaPaints = new ArrayList<>();
        this.listenerPaintAdapter = listenerPaintAdapter;
    }


//VIEWHOLDER
    public class PaintViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewtitulo;
        private Paint paint;

        public PaintViewHolder(View itemView) {
            super(itemView);
            textViewtitulo = itemView.findViewById(R.id.celda_TextTitulo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerPaintAdapter.informarSeleccion(paint);
                }
            });
        }

        public void bind(Paint mPaint){
            this.paint = mPaint;

            textViewtitulo.setText(mPaint.getName());
        }
    }


    //3 METODOS DEL ADAPTER
    @NonNull
    @Override
    public PaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflador = LayoutInflater.from(parent.getContext());
        View viewCelda = inflador.inflate(R.layout.celda_paints, parent, false);
        PaintViewHolder paintViewHolder = new PaintViewHolder(viewCelda);

        return paintViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Paint receta = listaPaints.get(position);
        PaintViewHolder recetasViewHolder = (PaintViewHolder) holder;
        recetasViewHolder.bind(receta);
    }


    @Override
    public int getItemCount() {
        return listaPaints.size();
    }
}
