package com.example.federico.entregablemvc;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.federico.entregablemvc.model.POJO.Paint;


public class FragmentDetalle extends Fragment {

    public static final String KEY_PAINT = "KEY_PAINT";
    private TextView pruebaText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);
        // Inflate the layout for this fragment
        pruebaText = view.findViewById(R.id.textViewPeueba);


        Bundle bundle = getArguments();
        Paint paint = (Paint) bundle.getSerializable(KEY_PAINT);
        pruebaText.setText(paint.getName());

        return view;
    }

    public FragmentDetalle() {
    }
}
