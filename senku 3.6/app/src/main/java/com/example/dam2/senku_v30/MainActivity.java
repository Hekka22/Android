package com.example.dam2.senku_v30;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;



public class MainActivity extends Activity implements OnClickListener{
    Game game;

    static final int SIZE = 7;
    //Array con todos los identificadores de los botones
    private final int ids [][] = {
            {0, 0, R.id.f1, R.id.f2, R.id.f3, 0, 0},
            {0, 0, R.id.f4, R.id.f5, R.id.f6, 0, 0},
            {R.id.f7, R.id.f8, R.id.f9, R.id.f10, R.id.f11, R.id.f12, R.id.f13},
            {R.id.f14, R.id.f15, R.id.f16, R.id.f17, R.id.f18, R.id.f19, R.id.f20},
            {R.id.f21, R.id.f22, R.id.f23, R.id.f24, R.id.f25, R.id.f26, R.id.f27},
            {0, 0, R.id.f28, R.id.f29, R.id.f30, 0, 0},
            {0, 0, R.id.f31, R.id.f32, R.id.f33, 0, 0}};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerListeners();

        game = new Game();
        //Hago la primera llamada para que dibuje el talero inicial
        setFigureFromGrid();
    }
    //se encarga de registrar la actividad como escuchador para todos los botones
    private void registerListeners (){
        ImageView button;

        for (int i=0; i<SIZE; i++)
            for (int j=0; j<SIZE; j++)
                if (ids[i][j]!=0){
                    button = (ImageView) findViewById(ids[i][j]);
                    button.setOnClickListener(this);
                }
    }

    //identifica las coordenadas del botón pulsado por el jugador y se las pasa al método play()
    //Redibuja el tablero
    //si el método isGameFinished() devuelve true indica que no quedan mas movimientos posibles,
    // se comprueba si solo queda una bola en la posicion cental o si queda alguna mas
    // y muestra un mensaje acorde si se ha ganado o se ha perdido
    public void onClick (View v){
        int id = ((ImageView) v).getId();

        for (int i=0; i<SIZE; i++)
            for (int j=0; j<SIZE; j++)
                if (ids[i][j] == id) {

                    game.play(i, j);
                    break;
                }

        setFigureFromGrid();

        if (game.isGameFinished()) {
            if(countBall()==1 && game.getGrid(3,3)==1 ){
                Toast.makeText(this, R.string.gameWin, Toast.LENGTH_LONG).show();
            }

            Toast.makeText(this, R.string.gameOverTitle, Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"Te falto quitar "+countBall()+ " bolas", Toast.LENGTH_SHORT).show();
        }
    }

    //"dibuja" las fichas sobre el tablero
    private void setFigureFromGrid (){
        ImageView button;

        for (int i=0; i<SIZE; i++)
            for (int j=0; j<SIZE; j++)
                if (ids[i][j] != 0){
                    int value = game.getGrid(i, j);
                    button = (ImageView) findViewById(ids[i][j]);
                    if (value == 1)
                        button.setImageResource(R.drawable.radio);
                    else
                        button.setImageResource(R.drawable.radio_down);

                }
    }
    //Cuenta el numero de fichas que quedan en el tablero
    private int countBall()
    {
        int num =0;
        for (int i=0; i<SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                if (ids[i][j] != 0)
                {
                    int value = game.getGrid(i, j);
                    if (value == 1)
                    {
                        num++;
                    }
                }
            }
        }
        return num;
    }

    protected void onResume(){
        super.onResume();
      //  Music.play(this,R.raw.gardenmusic);
    }
    protected void onPause(){
        super.onPause();
      //  Music.stop(this);
    }

    //Almacena el grid en una cadena de caracteres
    public void onSaveInstanceState (Bundle outState) {
        outState.putString("GRID", game.gridToString());
        super.onSaveInstanceState(outState);
    }

    //Recupera el estado del tablero que esta guardado en la cadena de caracteres
    public void onRestoreInstanceState (Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        String grid = savedInstanceState.getString("GRID");
        game.stringToGrid(grid);
        setFigureFromGrid();
    }

}