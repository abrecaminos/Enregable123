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

public class PaintAdapter extends RecyclerView.Adapter<PaintAdapter.PaintViewHolder> {


    private List<Paint> listaPaints;

    public void setListaPaints(List<Paint> listaPaints) {
        this.listaPaints = listaPaints;
        notifyDataSetChanged();
    }

    public PaintAdapter() {
        this.listaPaints = new ArrayList<>();
    }

    public class PaintViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewtitulo;

        public PaintViewHolder(View itemView) {
            super(itemView);
            textViewtitulo = itemView.findViewById(R.id.celda_TextTitulo);
        }

        public void bind(Paint mPaint){
            textViewtitulo.setText(mPaint.getName());
        }
    }

    @NonNull
    @Override
    public PaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflador = LayoutInflater.from(parent.getContext());
        View viewCelda = inflador.inflate(R.layout.celda_paints, parent, false);
        PaintViewHolder paintViewHolder = new PaintViewHolder(viewCelda);

        return paintViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PaintViewHolder holder, int position) {
        PaintViewHolder paintViewHolder = (PaintViewHolder) holder;
        paintViewHolder.bind(listaPaints.get(position));
    }

    @Override
    public int getItemCount() {
        return listaPaints.size();
    }





}
