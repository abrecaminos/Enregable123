package com.example.federico.entregablemvc;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.example.federico.entregablemvc.controller.PaintController;
import com.example.federico.entregablemvc.model.POJO.MoMA;
import com.example.federico.entregablemvc.util.ResultListener;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPaints;
    private PaintAdapter paintAdapter;
    private LinearLayoutManager linearLayoutManager;
    private CallbackManager callbackManager;
    private LoginButton loginButtonFacebook;


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
                Toast.makeText(MainActivity.this, "LOGIN CORRECTO", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "Login cancelado", Toast.LENGTH_SHORT).show();
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



}
