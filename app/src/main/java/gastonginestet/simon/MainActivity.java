package gastonginestet.simon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Segun lo que se elija se envia un integer con el valor de la dificultad
    public void modoFacil(View view) {
        Intent i = new Intent(this, Juego.class);
        i.putExtra("modo", 1);
        startActivity(i);
        this.finish();

    }

    public void modoMedio(View view) {
        Intent i = new Intent(this, Juego.class);
        i.putExtra("modo", 2);
        startActivity(i);
        this.finish();

    }

    public void modoDificil(View view) {
        Intent i = new Intent(this, Juego.class);
        i.putExtra("modo", 3);
        startActivity(i);
        this.finish();

    }

    @Override
    public void onBackPressed(){
        this.finish();
    }
}
