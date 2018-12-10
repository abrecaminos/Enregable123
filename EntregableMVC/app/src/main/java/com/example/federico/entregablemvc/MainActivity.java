package com.example.federico.entregablemvc;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.federico.entregablemvc.controller.PaintController;
import com.example.federico.entregablemvc.model.POJO.MoMA;
import com.example.federico.entregablemvc.model.POJO.Paint;
import com.example.federico.entregablemvc.util.ResultListener;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity implements FragmentRecycler.ListenerFragmentRecycler{


    private PaintAdapter paintAdapter;
    private LinearLayoutManager linearLayoutManager;
    private CallbackManager callbackManager;
    private LoginButton loginButtonFacebook;

    private FirebaseAuth mAuth;


    private Fragment fragmentDetalle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


               mAuth = FirebaseAuth.getInstance();


        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        callbackManager = CallbackManager.Factory.create();

        loginButtonFacebook = (LoginButton) findViewById(R.id.login_button);
        loginButtonFacebook.setReadPermissions("email");



        // Callback registration
        // Callback registration
        loginButtonFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contenedorRecycler,new FragmentRecycler()).commit();

                Toast.makeText(MainActivity.this, "LOGIN CORRECTO", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "Login cancelado", Toast.LENGTH_SHORT).show();

                //volver al Main, cerrar el contenedor
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, "Ha Ocurrido un Horror", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        }

    @Override
    public void informarSeleccion(Paint paint) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentDetalle fragmentDetalle = new FragmentDetalle();


        Bundle bundle = new Bundle();
        bundle.putSerializable(FragmentDetalle.KEY_PAINT,paint);
        fragmentManager.beginTransaction().replace(R.id.contenedorRecycler,new FragmentDetalle()).addToBackStack(null).commit();
        Toast.makeText(this, paint.getName(), Toast.LENGTH_SHORT).show();
    }
}


