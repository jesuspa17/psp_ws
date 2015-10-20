package cronometro.tema01.psp.dam.salesianostriana.com.psp_cronometro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    boolean parado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializamos los elementos de la IU
        final EditText editTiempoTotal = (EditText) findViewById(R.id.editTiempoTotal);
        final Button btn_empezar = (Button) findViewById(R.id.btn_empezar);
        final Button btn_stop = (Button) findViewById(R.id.btn_stop);
        final TextView txtTiempoRestante = (TextView) findViewById(R.id.txtTiempoRestante);
        final EditText frecuencia = (EditText) findViewById(R.id.editFrecuencia);

        btn_stop.setOnClickListener(this);
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
                        double tiempo = Double.parseDouble(String.valueOf(editTiempoTotal.getText()));
                        double frecuencia_inicial = Double.parseDouble(String.valueOf(frecuencia.getText())) / 1000;
                        //for regresivo con un Sleep que dormirá el hilo según la frecuencia indicada
                        while (tiempo > 0) {

                            actualizarTiempo(txtTiempoRestante, tiempo);

                            while(parado){

                            }

                            tiempo = tiempo - frecuencia_inicial;

                            try {
                                if(frecuencia_inicial <= tiempo)
                                Thread.sleep((long) (frecuencia_inicial * 1000));
                                else
                                    Thread.sleep((long) tiempo * 1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (tiempo < 0) {
                                tiempo = 0;
                                actualizarTiempo(txtTiempoRestante, tiempo);
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
    public void actualizarTiempo(final TextView txt, final double tiempoRestante) {
        txt.post(new Runnable() {
            @Override
            public void run() {
                DecimalFormat format = new DecimalFormat("##.#");
                txt.setText(format.format(tiempoRestante) + " s.");
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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_stop){
            if(parado){
                parado = false;
            }else{
                parado = true;
            }
        }

    }
}
