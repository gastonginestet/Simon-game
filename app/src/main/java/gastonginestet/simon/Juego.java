package gastonginestet.simon;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Juego extends AppCompatActivity {
    boolean termino;
    long duracionF = 3000;
    long duracionM = 2000;
    long duracionD = 1000;
    long duracionB;
    View v1, v2, v3, v4;
    int pos, modo, cant;
    public List<Integer> secuencia = new ArrayList<>();
    public List<Integer> secuenciajugador = new ArrayList<>();
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        Bundle datos = getIntent().getExtras();
        if (datos != null) {
            modo = datos.getInt("modo");
        }
        v1 = (View) findViewById(R.id.rojo);
        v2 = (View) findViewById(R.id.verde);
        v3 = (View) findViewById(R.id.azul);
        v4 = (View) findViewById(R.id.amarillo);
        inicioJuego(modo);
        textview = (TextView) findViewById(R.id.textView2);
        cant = Integer.parseInt(textview.getText().toString());

    }

    public void inicioJuego(int dificultad) {
        secuencia.add((int) (Math.random() * 4) + 1);


        switch (dificultad) {
            case 1:
                imprimeColores(duracionF, 2000);
                break;
            case 2:
                imprimeColores(duracionM, 1000);
                break;
            case 3:
                imprimeColores(duracionD, 500);
                break;

        }


    }

    //Metodos para deshabilitar y habilitar la opcion de "presionar" los botones
    public void deshabilitarColores() {
        v1.setEnabled(false);
        v2.setEnabled(false);
        v3.setEnabled(false);
        v4.setEnabled(false);
    }

    public void habilitarColores() {
        v1.setEnabled(true);
        v2.setEnabled(true);
        v3.setEnabled(true);
        v4.setEnabled(true);
    }


    public void imprimeColores(final long duracion, final long duracionB) {
        pos = 0;
        termino = false;
        deshabilitarColores();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (termino != true) {
                    if (pos < secuencia.size()) {
                        switch (secuencia.get(pos)) {
                            case 1:
                                encenderRojo(duracionB);
                                break;
                            case 2:
                                encenderVerde(duracionB);
                                break;
                            case 3:
                                encenderAzul(duracionB);
                                break;
                            case 4:
                                encenderAmarillo(duracionB);
                                break;
                        }

                        pos++;
                        handler.postDelayed(this, duracion);

                    } else {
                        termino = true;
                        habilitarColores();
                    }
                }

            }
        }, 1000);

    }


    public void compararSecuencia() {


        if (secuencia.equals(secuenciajugador)) {
            secuenciajugador.clear();
            cant++;
            textview.setText(Integer.toString(cant));
            inicioJuego(modo);
        } else {
            for (int y = 0; y <= secuenciajugador.size() - 1; y++) {
                if (!(secuenciajugador.get(y).equals(secuencia.get(y)))) {
                    Intent i = new Intent(this, FinDelJuego.class);
                    i.putExtra("puntaje", cant);
                    startActivity(i);
                    this.finish();
                }
            }
        }
    }

    public void presionaUnColor(View v) {
        switch (v.getId()) {
            case R.id.rojo:
                secuenciajugador.add(1);
                encenderRojo(100);
                compararSecuencia();
                break;

            case R.id.verde:
                secuenciajugador.add(2);
                encenderVerde(100);
                compararSecuencia();
                break;

            case R.id.azul:
                secuenciajugador.add(3);
                encenderAzul(100);
                compararSecuencia();
                break;

            case R.id.amarillo:
                secuenciajugador.add(4);
                encenderAmarillo(100);
                compararSecuencia();
                break;
        }


    }

    public void encenderRojo(long duracionB) {
        v1.setBackgroundResource(R.color.rojoClaro);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                v1.setBackgroundResource(R.color.rojoOscuro);
            }
        }, duracionB);

    }

    public void encenderVerde(long duracionB) {
        v2.setBackgroundResource(R.color.verdeClaro);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                v2.setBackgroundResource(R.color.verdeOscuro);
            }
        }, duracionB);
    }

    public void encenderAzul(long duracionB) {
        v3.setBackgroundResource(R.color.azulClaro);
        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                v3.setBackgroundResource(R.color.azulOscuro);
            }
        }, duracionB);
    }

    public void encenderAmarillo(long duracionB) {
        v4.setBackgroundResource(R.color.amarilloClaro);
        final Handler handler4 = new Handler();
        handler4.postDelayed(new Runnable() {
            @Override
            public void run() {
                v4.setBackgroundResource(R.color.amarilloOscuro);
            }
        }, duracionB);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList("Lista Juego", (ArrayList<Integer>) secuencia);
        outState.putIntegerArrayList("Lista Jugador", (ArrayList<Integer>) secuenciajugador);
        outState.putInt("Posicion", pos);
        outState.putBoolean("Termino", termino);
        outState.putInt("Puntaje", cant);
        outState.putInt("Velocidad", modo);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pos = savedInstanceState.getInt("Puntero");
        termino = savedInstanceState.getBoolean("Termino");
        modo = savedInstanceState.getInt("Velocidad");
        cant = savedInstanceState.getInt("Puntaje");
        secuencia = savedInstanceState.getIntegerArrayList("Lista Juego");
        secuenciajugador = savedInstanceState.getIntegerArrayList("Lista Jugador");
        textview.setText(Integer.toString(cant));
        habilitarColores();
        //Si se gira el celular en plena secuencia se vuelve a repetir
    }

    @Override
    public void onBackPressed() {
        this.finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
}



