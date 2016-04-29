package com.example.dam2.senku_v30;

/**
 * Created by usuario on 22/04/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class Initial extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial);
    }

    //Crea un intent para el lanzamineto del juego
    public void onButtonGame(View view) {
        startActivity(new Intent("com.example.dam2.senku_v30.MAINACTIVITY"));

    }
}
