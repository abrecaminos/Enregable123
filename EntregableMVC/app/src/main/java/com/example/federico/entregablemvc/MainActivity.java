package com.example.federico.entregablemvc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.federico.entregablemvc.controller.PaintController;
import com.example.federico.entregablemvc.model.POJO.MoMA;
import com.example.federico.entregablemvc.util.ResultListener;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPaints;
    private PaintAdapter paintAdapter;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewPaints = findViewById(R.id.recyclerview_paints);

        paintAdapter = new PaintAdapter();
        recyclerViewPaints.setAdapter(paintAdapter);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewPaints.setLayoutManager(linearLayoutManager);

        PaintController paintController = new PaintController();
        paintController.getSpecificPaint2(new ResultListener<MoMA>() {
            @Override
            public void finish(MoMA result) {
                paintAdapter.setListaPaints(result.getPaints());
            }
        });



    }


}
