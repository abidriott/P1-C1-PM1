package com.example.appholamundo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class IMCActivity extends AppCompatActivity {
    private TextView lblResultado;
    private EditText txtWeight;
    private EditText txtHeight;
    private Button btnCalcular;
    private Button btnLimpiar;

    private Button btnCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imcactivity);

        btnCerrar = (Button) findViewById(R.id.btnCerrar);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
        txtHeight = (EditText) findViewById(R.id.txtAltura);
        txtWeight = (EditText) findViewById(R.id.txtPeso);
        lblResultado = (TextView) findViewById(R.id.lblResultado);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double height = Float.parseFloat(txtHeight.getText().toString());
                    double weight = Double.parseDouble(txtWeight.getText().toString());

                    if (height <= 0 || weight <= 0) {
                        throw new IllegalArgumentException("Los valores no pueden ser iguales o menores a 0");
                    }

                    double imc = weight / Math.pow(height, 2);

                    DecimalFormat df = new DecimalFormat("#.##");
                    String imcStr =  df.format(imc);

                    lblResultado.setText("IMC: " + imcStr);
                }
                catch (NumberFormatException e) {
                    Toast.makeText(IMCActivity.this, "Inserta un valor numérico para altura y peso",
                            Toast.LENGTH_SHORT).show();
                }
                catch (IllegalArgumentException e) {
                    Toast.makeText(IMCActivity.this, "Error: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblResultado.setText("IMC: ");
                txtHeight.setText(" ");
                txtWeight.setText(" ");

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
}