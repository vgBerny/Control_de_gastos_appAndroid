package com.example.controldegastosapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    EditText tvNoFactura;
    AdminSQLiteOpenHelper admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        admin = new AdminSQLiteOpenHelper(this, "bd1", null,1);

        tvNoFactura = findViewById(R.id.tvNoFactura);
        mostrarTotalMonto();

        int rowCount = admin.getRowCount();

        TextView textView = findViewById(R.id.textView3);
        textView.setText("Facturas ingresadas: " + rowCount);

    }
    private void mostrarTotalMonto() {
        SQLiteDatabase bd= admin.getWritableDatabase();
        String query = "SELECT SUM(monto) as total FROM facturas";
        Cursor cursor =bd.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            int total = cursor.getInt(cursor.getColumnIndexOrThrow("total"));
            tvNoFactura.setText(String.valueOf(total));
        }
        cursor.close();
    }
}