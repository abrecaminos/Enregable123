package com.example.federico.entregablemvc;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.federico.entregablemvc.model.POJO.Artist_;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FragmentDetalle2 extends Fragment {

    FirebaseDatabase myRef;
    public static final String ARTISTA = "Artista";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalle2, container, false);

                // Inflate the layout for this fragment
        return view;
    }

    private void traerTodosLosContactos() {

        DatabaseReference reference = myRef.getReference().child(ARTISTA);

    reference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
          //  List<Artist_> artistList = new ArrayList<>();
            String value = dataSnapshot.getValue(String.class);
            //Log.d(TAG, "Value is: " + value);


            //for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
              //  Artist_ artist_ = snapshot.getValue(Artist_.class);
                //artistList.add(artist_);}
        }

        @Override
        public void onCancelled(DatabaseError error) {

        }
    });

} }