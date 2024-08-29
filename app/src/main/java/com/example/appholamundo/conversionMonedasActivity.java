package com.example.appholamundo;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class conversionMonedasActivity extends AppCompatActivity {

    private Spinner spinnerMonedaOrigen;
    private EditText editTextMonto;
    private Button botonCalcular;
    private Button botonLimpiar;
    private Button botonCerrar;
    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conversion_monedas);

        spinnerMonedaOrigen = findViewById(R.id.spinnerMonedaOrigen);
        editTextMonto = findViewById(R.id.editTextMonto);
        botonCalcular = findViewById(R.id.botonCalcular);
        botonLimpiar = findViewById(R.id.botonLimpiar);
        botonCerrar = findViewById(R.id.botonCerrar);
        textViewResultado = findViewById(R.id.textViewResultado);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.monedas_origen, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonedaOrigen.setAdapter(adapter);

        botonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularConversion();
            }
        });

        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos();
            }
        });

        botonCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarApp();
            }
        });
    }
    private void calcularConversion() {
        String monedaOrigen = spinnerMonedaOrigen.getSelectedItem().toString();
        if (editTextMonto.getText().toString().isEmpty()) {
            textViewResultado.setText("Ingrese la cantidad");
            return;
        }
        double monto = Double.parseDouble(editTextMonto.getText().toString());
        double resultado = 0;
        double tasaDolar = 0.06;
        double tasaEuro = 0.055;
        double tasaDolarCanadiense = 0.064;
        double tasaLibraEsterlina = 0.047;

        switch (monedaOrigen) {
            case "USA":
                resultado = monto * tasaDolar;
                break;
            case "EURO":
                resultado = monto * tasaEuro;
                break;
            case "CAD":
                resultado = monto * tasaDolarCanadiense;
                break;
            case "LIBRA":
                resultado = monto * tasaLibraEsterlina;
                break;
            default:
                textViewResultado.setText("La moneda seleccionada no es válida.");
                return;
        }

        textViewResultado.setText(String.format("%f", resultado));
    }

    private void limpiarCampos() {
        editTextMonto.setText("");
        textViewResultado.setText("");
    }

    private void cerrarApp() {
        finish();
    }
}
