package com.example.controldegastosapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    AdminSQLiteOpenHelper admin;
    EditText tvNoFactura, tvDescripcion, tvFecha, tvMonto;
    TextView txtTgasto;
    String t1, t2, t3,t4,t5,t6, tRadio;
    CheckBox chkPrimeraNecesidad, chkSegundaNecesidad, chkOsio, chkSalud, chkHobby, chkPrestamo;
    RadioButton rbEfectivo, rbTarjetaCredito, rbTarjetaDebito;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvNoFactura = findViewById(R.id.tvNoFactura);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvFecha = findViewById(R.id.tvFecha);
        tvMonto = findViewById(R.id.tvMonto);
        txtTgasto = findViewById(R.id.txtTgasto);

        chkPrimeraNecesidad = (CheckBox) findViewById(R.id.chkPrimeraNecesidad);
        chkSegundaNecesidad = (CheckBox) findViewById(R.id.chkSegundaNecesidad);
        chkOsio = (CheckBox) findViewById(R.id.chkOsio);
        chkSalud = (CheckBox) findViewById(R.id.chkSalud);
        chkHobby = (CheckBox) findViewById(R.id.chkHobby);
        chkPrestamo = (CheckBox) findViewById(R.id.chkPrestamo);

        rbEfectivo = (RadioButton) findViewById(R.id.rbEfectivo);
        rbTarjetaCredito = (RadioButton) findViewById(R.id.rbTarjetaCredito);
        rbTarjetaDebito = (RadioButton) findViewById(R.id.rbTarjetaDebito);

        admin = new AdminSQLiteOpenHelper(this, "bd1", null,1);

    }
    public void Consulta(View v){
        try {
            chkPrimeraNecesidad.setChecked(false);
            chkSegundaNecesidad.setChecked(false);
            chkOsio.setChecked(false);
            chkSalud.setChecked(false);
            chkHobby.setChecked(false);
            chkPrestamo.setChecked(false);
            SQLiteDatabase bd= admin.getWritableDatabase();
            String Nfactura = tvNoFactura.getText().toString();
            Cursor fila = bd.rawQuery("select descripcion,fecha,tipoGasto,chk1,chk2,chk3,chk4,chk5,chk6,maneraPago,monto from facturas where Nfactura= '" + Nfactura + "'",null);
            if(fila.moveToFirst()){
                tvDescripcion.setText(fila.getString(0));
                tvFecha.setText(fila.getString(1));
                txtTgasto.setText(fila.getString(2));
                t1 = fila.getString(3);
                t2= fila.getString(4);
                t3= fila.getString(5);
                t4= fila.getString(6);
                t5= fila.getString(7);
                t6= fila.getString(8);
                tRadio= fila.getString(9);
                //tvRadio.setText(fila.getString(9));
                tvMonto.setText(fila.getString(10));

                boolean bollbo1 = false;
                if (t1.equals("1")) {
                    bollbo1 = true;
                }
                if (bollbo1 == true) {
                    CheckBox chkPrimeraNecesidad = (CheckBox) findViewById(R.id.chkPrimeraNecesidad);
                    chkPrimeraNecesidad.setChecked(true);
                }

                boolean bollbo2 = false;
                if (t2.equals("1")) {
                    bollbo2 = true;
                }
                if (bollbo2 == true) {
                    CheckBox chkSegundaNecesidad = (CheckBox) findViewById(R.id.chkSegundaNecesidad);
                    chkSegundaNecesidad.setChecked(true);
                }

                boolean bollbo3 = false;
                if (t3.equals("1")) {
                    bollbo3 = true;
                }
                if (bollbo3 == true) {
                    CheckBox chkOsio = (CheckBox) findViewById(R.id.chkOsio);
                    chkOsio.setChecked(true);
                }

                boolean bollbo4 = false;
                if (t4.equals("1")) {
                    bollbo4 = true;
                }
                if (bollbo4 == true) {
                    CheckBox chkSalud = (CheckBox) findViewById(R.id.chkSalud);
                    chkSalud.setChecked(true);
                }

                boolean bollbo5 = false;
                if (t5.equals("1")) {
                    bollbo5 = true;
                }
                if (bollbo5 == true) {
                    CheckBox chkHobby = (CheckBox) findViewById(R.id.chkHobby);
                    chkHobby.setChecked(true);
                }

                boolean bollbo6 = false;
                if (t6.equals("1")) {
                    bollbo6 = true;
                }
                if (bollbo6 == true) {
                    CheckBox chkPrestamo = (CheckBox) findViewById(R.id.chkPrestamo);
                    chkPrestamo.setChecked(true);
                }

                boolean bool1 = false;
                boolean bool2 = false;
                boolean bool3 = false;

                if (tRadio.equals("1")) {
                    bool1 = true;
                } else if (tRadio.equals("2")) {
                    bool2 = true;
                } else if (tRadio.equals("3")) {
                    bool3 = true;
                }

                if (bool1 == true) {
                    RadioButton rbEfectivo = (RadioButton) findViewById(R.id.rbEfectivo);
                    rbEfectivo.setChecked(true);
                } else if (bool2 == true) {
                    RadioButton rbTarjetaCredito = (RadioButton) findViewById(R.id.rbTarjetaCredito);
                    rbTarjetaCredito.setChecked(true);
                } else if (bool3 == true) {
                    RadioButton rbTarjetaDebito = (RadioButton) findViewById(R.id.rbTarjetaDebito);
                    rbTarjetaDebito.setChecked(true);
                }

            }else {
                Toast.makeText(this, "No existe numero de factura", Toast.LENGTH_SHORT).show();
            }
            bd.close();
        }catch (Exception ex){
            Toast.makeText(this, "Error" + ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public void Borrar(View v){
        SQLiteDatabase bd= admin.getWritableDatabase();
        String Nfactura = tvNoFactura.getText().toString();
        int cant = bd.delete("facturas", "Nfactura='" + Nfactura + "'", null);
        if(cant==1){
            Toast.makeText(this, "Se elimino la factura", Toast.LENGTH_SHORT).show();
            tvNoFactura.setText("");
            tvDescripcion.setText("");
            tvFecha.setText("");
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
        } else {
            Toast.makeText(this, "No existe factura con este numero de factura", Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }

    public void Modificar(View v){
        boolean chkBool= false, radioBool=false;
        SQLiteDatabase bd= admin.getWritableDatabase();

        String Nfactura = tvNoFactura.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("descripcion", tvDescripcion.getText().toString());
        registro.put("fecha", tvFecha.getText().toString());
        registro.put("monto", tvMonto.getText().toString());

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

        if(radioBool== false){
            Toast notificacion = Toast.makeText(MainActivity2.this, "Debe rellenar los espacios vacios para guardar la factura.", Toast.LENGTH_SHORT);
            notificacion.show();
        } else if(chkBool == false){
            Toast notificacion = Toast.makeText(MainActivity2.this, "Debe rellenar los espacios vacios para guardar la factura.", Toast.LENGTH_SHORT);
            notificacion.show();
        } else if(tvNoFactura.getText().toString().trim().isEmpty() || tvDescripcion.getText().toString().trim().isEmpty() || tvMonto.getText().toString().trim().isEmpty() || tvFecha.getText().toString().trim().isEmpty()) {
            Toast notificacion = Toast.makeText(MainActivity2.this, "Debe rellenar los espacios vacios para guardar la factura.", Toast.LENGTH_SHORT);
            notificacion.show();
        }else{
            int cant = bd.update( "facturas", registro, "Nfactura='" +Nfactura+ "'",null);
            if(cant==1){
                Toast.makeText(this, "Se modificaron los datos de la factura", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "No existe factura con este numero de factura", Toast.LENGTH_SHORT).show();
            }
            bd.close();
        }


    }
}