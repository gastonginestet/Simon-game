package gastonginestet.simon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FinDelJuego extends AppCompatActivity {
    int puntaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_del_juego);
        Bundle datos = getIntent().getExtras();
        if (datos != null) {
            puntaje = datos.getInt("puntaje");
        }
        ImageView mImageView;
        mImageView = (ImageView) findViewById(R.id.imagen);
        mImageView.setImageResource(R.drawable.imageview);
        TextView textView = (TextView) findViewById(R.id.textview3);
        textView.setText("Has hecho " + Integer.toString(puntaje) + " puntos");
    }

    public void reiniciarJuego(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        this.finish();

    }
}
