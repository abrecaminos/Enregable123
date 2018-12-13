package com.example.federico.entregablemvc;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.federico.entregablemvc.model.POJO.Artist;
import com.example.federico.entregablemvc.model.POJO.Paint;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class FragmentDetalle extends Fragment {

    public static final String KEY_PAINT = "KEY_PAINT";
    public static final String ARTIST = "artists";
    private TextView textoRetrofit;
    private TextView textoFirebase;
    private ImageView imageViewDetalle;
    private FirebaseDatabase database;
    private StorageReference mStorageRef;
    private Paint paint;
    private Artist artist;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);
        // Inflate the layout for this fragment
        textoRetrofit = view.findViewById(R.id.textViewRetrofit);
        textoFirebase = view.findViewById(R.id.textViewDatabase);
        imageViewDetalle = view.findViewById(R.id.imageViewDetalle);

        mStorageRef = FirebaseStorage.getInstance().getReference();


        Bundle bundle = getArguments();
        paint = (Paint) bundle.getSerializable(KEY_PAINT);
        textoRetrofit.setText(paint.getName());


        traerPaintsDatabase();
        bajarFotoGlide();


        return view;
    }


    public void traerPaintsDatabase() {



        database = FirebaseDatabase.getInstance();


        //PARA TOMAR UN SOLO ELEMENTO
        DatabaseReference myRef = database.getReference().child(ARTIST);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //List<Artist> artistList = new ArrayList<>();
                // 1 String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);
                // 1 textoFirebase.setText(value);

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                artist = snapshot.getValue(Artist.class);

                if (paint.getArtistId().equals(artist.getArtistId())) {
                    textoFirebase.setText(artist.getName());
                }
                    //artistList.add(artist);
                    //  textoFirebase.setText(artist.getName());}
                    //textoFirebase.setText(artist.getName());
                }

                return ;
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }

    public void bajarFotoGlide(){
        final StorageReference paint_reference = mStorageRef.child(paint.getImage());
        //final StorageReference paint_reference = mStorageRef.child("artistpaints/keithharing_dreaming.png");
        //StorageReference bart_reference = mStorageRef.child("imagess/homero_bart.jpg");

        //List<Artist> artistList = new ArrayList<>();

        //for (artistList )
        //if (paint.getImage()).equals {
            Glide.with(this).load(paint_reference).into(imageViewDetalle);
        }



}
