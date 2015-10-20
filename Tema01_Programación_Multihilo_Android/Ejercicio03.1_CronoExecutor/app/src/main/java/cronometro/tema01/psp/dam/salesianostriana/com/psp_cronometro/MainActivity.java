package cronometro.tema01.psp.dam.salesianostriana.com.psp_cronometro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
(3.1) Cronómetro cuenta a atrás (con ExecutorService).

Modificar (en un nuevo proyecto) el ejercicio anterior para lanzar gestionar el cronómetro 
a través de un ExecutorService construido sobre un Thread Pool de 1 solo hilo.

 * */

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializamos los elementos de la IU
        final EditText editTiempoTotal = (EditText) findViewById(R.id.editTiempoTotal);
        final Button btn_empezar = (Button) findViewById(R.id.btn_empezar);
        final TextView txtTiempoRestante = (TextView) findViewById(R.id.txtTiempoRestante);

        //Asignamos al botón un manejador del envento click.
        btn_empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //se inicializa la clase ExecutorService con el número de hilos que tendrá el Pool.
                ExecutorService es = Executors.newFixedThreadPool(1);
                es.execute(new Runnable() {
                    @Override
                    public void run() {
                        //Se obtiene el tiempo introducido por el usuario
                        final int tiempo = Integer.parseInt(String.valueOf(editTiempoTotal.getText()));
                        //for regresivo con un Sleep que dormirá el hilo
                        // para que el bucle se retrase un segundo por repetición
                        for (int i = tiempo; i != -1; i--) {
                            actualizarTiempo(txtTiempoRestante, i);

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });
                //se liberan los recursos asociados al Pool
                es.shutdown();
            }
        });


    }

    /*
        Este método sirve para actualizar el texto que aparece en pantalla
        y que refleja el número de segundos restantes que le quedan al cronómetro

        Hay que invocarlo pasandole como primer argumento la variable txtTiempoRestante
     */
    public void actualizarTiempo(final TextView txt, final int tiempoRestante) {
        txt.post(new Runnable() {
            @Override
            public void run() {
                txt.setText(Integer.toString(tiempoRestante) + " s.");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
