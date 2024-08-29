package com.example.appholamundo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class cotizacionmainActivity extends AppCompatActivity {

    private EditText txtPregunta;
    private Button btnCotizacion, btnCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cotizacionmain);
        iniciarcomponentes();

        btnCotizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pregunta = txtPregunta.getText().toString();

                if (pregunta.isEmpty()) {
                    Toast.makeText(cotizacionmainActivity.this, "Inserta el nombre",
                            Toast.LENGTH_SHORT).show();
                } else if (pregunta.matches(".*\\d.*")) {
                    Toast.makeText(cotizacionmainActivity.this, "Valor numerico no aceptado",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(cotizacionmainActivity.this, cotizacionActivity.class);

                    intent.putExtra("PREGUNTA", pregunta);

                    startActivity(intent);
                }
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void iniciarcomponentes() {
        txtPregunta = (EditText) findViewById(R.id.editTextNombreCliente);
        btnCotizacion = (Button) findViewById(R.id.btnIrCotizacion);
        btnCerrar = (Button) findViewById(R.id.btnCerrar);
    }

}
