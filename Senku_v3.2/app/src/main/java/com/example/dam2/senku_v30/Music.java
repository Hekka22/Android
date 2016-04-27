package com.example.dam2.senku_v30;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by CRIS on 21/04/2016.
 */
public class Music {

    private static MediaPlayer player;
    //Metodo para iniiar o reanudar la reproduccion
    public static void play (Context context, int id){
        player = MediaPlayer.create(context, id);
        player.setLooping(true); //La pista se reproducira el bucle
        player.start();
    }
    //Metodo para parar la reproduccion
    public static void stop (Context context){
        if(player != null){
            player.pause();
            player.release(); //Liberamos todos los recursos asociados a la reproducion
            player = null;
        }
    }
}
