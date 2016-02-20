package com.salesianostriana.psp.sharapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistroActivity extends AppCompatActivity {

    Button btnEntrar;
    EditText nomUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nomUser = (EditText) findViewById(R.id.editTextNomUser);
        btnEntrar = (Button) findViewById(R.id.btn_entrar);
        Utils.initializePreferences(this);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nom = nomUser.getText().toString();

                Utils.editor.putString("nom_user",nom);
                Utils.editor.apply();

                startActivity(new Intent(RegistroActivity.this,MainActivity.class));
                RegistroActivity.this.finish();
            }
        });
    }
}
