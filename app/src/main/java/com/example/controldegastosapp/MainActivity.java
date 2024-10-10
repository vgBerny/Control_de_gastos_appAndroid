package com.example.controldegastosapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText tvNoFactura, tvDescripcion, tvFecha, tvMonto;
    AdminSQLiteOpenHelper admin;
    Spinner spTipoDeGasto;
    CheckBox chkPrimeraNecesidad, chkSegundaNecesidad, chkOsio, chkSalud, chkHobby, chkPrestamo;
    RadioButton rbEfectivo, rbTarjetaCredito, rbTarjetaDebito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNoFactura = findViewById(R.id.tvNoFactura);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvFecha = findViewById(R.id.tvFecha);
        tvMonto = findViewById(R.id.tvMonto);

        chkPrimeraNecesidad = (CheckBox) findViewById(R.id.chkPrimeraNecesidad);
        chkSegundaNecesidad = (CheckBox) findViewById(R.id.chkSegundaNecesidad);
        chkOsio = (CheckBox) findViewById(R.id.chkOsio);
        chkSalud = (CheckBox) findViewById(R.id.chkSalud);
        chkHobby = (CheckBox) findViewById(R.id.chkHobby);
        chkPrestamo = (CheckBox) findViewById(R.id.chkPrestamo);

        rbEfectivo = (RadioButton) findViewById(R.id.rbEfectivo);
        rbTarjetaCredito = (RadioButton) findViewById(R.id.rbTarjetaCredito);
        rbTarjetaDebito = (RadioButton) findViewById(R.id.rbTarjetaDebito);

        spTipoDeGasto = (Spinner) findViewById(R.id.spTipoDeGasto);
        String []opciones={"Gasto periodico", "Gasto nuevo", "Gasto espontaneo", "Otro"};
        ArrayAdapter<String> ArregloUnidimencional =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spTipoDeGasto.setAdapter(ArregloUnidimencional);


        admin = new AdminSQLiteOpenHelper(this, "bd1", null, 1);

        TextView txtfecha = findViewById(R.id.txtfecha);
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        txtfecha.setText(currentDate);

    }

    public void Agregar(View v){
        boolean chkBool= false,radioBool=false;
        SQLiteDatabase bd= admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("Nfactura", tvNoFactura.getText().toString());
        registro.put("descripcion", tvDescripcion.getText().toString());
        registro.put("monto", tvMonto.getText().toString());

        String fechaActual = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        registro.put("fecha", fechaActual);

        String select = spTipoDeGasto.getSelectedItem().toString();
        registro.put("tipoGasto", select);

        boolean chk_PrimeraNecesidad= chkPrimeraNecesidad.isChecked();
        boolean chk_SegundaNecesidad= chkSegundaNecesidad.isChecked();
        boolean chk_Osio= chkOsio.isChecked();
        boolean chk_Salud= chkSalud.isChecked();
        boolean chk_Hobby= chkHobby.isChecked();
        boolean chk_Prestamo= chkPrestamo.isChecked();

        if (chk_PrimeraNecesidad==true){
            registro.put("chk1",1);
            chkBool=true;
        }else{
            registro.put("chk1",2);
        }

        if (chk_SegundaNecesidad==true){
            registro.put("chk2",1);
            chkBool=true;
        }else{
            registro.put("chk2",2);
        }

        if (chk_Osio==true){
            registro.put("chk3",1);
            chkBool=true;
        }else{
            registro.put("chk3",2);
        }

        if (chk_Salud==true){
            registro.put("chk4",1);
            chkBool=true;
        }else{
            registro.put("chk4",2);
        }

        if (chk_Hobby==true){
            registro.put("chk5",1);
            chkBool=true;
        }else{
            registro.put("chk5",2);
        }

        if (chk_Prestamo==true){
            registro.put("chk6",1);
            chkBool=true;
        }else{
            registro.put("chk6",2);
        }

        if(rbEfectivo.isChecked()){
            registro.put("maneraPago",1);
            radioBool=true;
        }else if (rbTarjetaCredito.isChecked()) {
            registro.put("maneraPago",2);
            radioBool=true;
        }else if (rbTarjetaDebito.isChecked()) {
            registro.put("maneraPago",3);
            radioBool=true;
        }

        if(chkBool == false){
            Toast notificacion = Toast.makeText(MainActivity.this, "Debe rellenar los espacios vacios para guardar la factura..", Toast.LENGTH_SHORT);
            notificacion.show();
            bd.close();
        } else if( radioBool == false){
            Toast notificacion = Toast.makeText(MainActivity.this, "Debe rellenar los espacios vacios para guardar la factura.", Toast.LENGTH_SHORT);
            notificacion.show();
        } else if(tvNoFactura.getText().toString().trim().isEmpty() || tvDescripcion.getText().toString().trim().isEmpty() || tvMonto.getText().toString().trim().isEmpty()) {
            Toast notificacion = Toast.makeText(MainActivity.this, "Debe rellenar los espacios vacios para guardar la factura.", Toast.LENGTH_SHORT);
            notificacion.show();
        } else {
            bd.insert("facturas", null,registro);
            tvNoFactura.setText("");
            tvDescripcion.setText("");
            tvMonto.setText("");
            chkPrimeraNecesidad.setChecked(false);
            chkSegundaNecesidad.setChecked(false);
            chkOsio.setChecked(false);
            chkSalud.setChecked(false);
            chkHobby.setChecked(false);
            chkPrestamo.setChecked(false);
            rbEfectivo.setChecked(false);
            rbTarjetaCredito.setChecked(false);
            rbTarjetaDebito.setChecked(false);
            bd.close();
            Toast.makeText(this, "Se almaceno la factura con exito", Toast.LENGTH_SHORT).show();
        }
    }

    public void ActivityMonto(View v){
        Intent intent = new Intent(MainActivity.this, MainActivity3.class);
        startActivity(intent);

    }

    public void ActivityConsulta(View v){
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);

    }
}