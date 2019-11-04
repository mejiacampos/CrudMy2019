package com.example.crudmy2019;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et_codigo, et_descripcion, et_precio;
    private Button btn_guardar, btn_consultacodigo, btn_consultaDescripcion, btn_eliminar, btn_actualizar;

    boolean inputEt=false;
    boolean inputEd=false;
    boolean input1=false;

    String senal = "";
    String codigo = "";
    String descripcion = "";
    String precio = "";

    MantenimientoMySQL manto = new MantenimientoMySQL();
    Dto datos = new Dto;

    boolean estadoGuarda = false;
    Boolean estadoEliminar = false;

    AlertDialog.Builder.dialogo;

    @Override
    protected boolean onKeyDwn(int KeyCode, KeyEvent) {
       if (KeyCode == KeyEvent.KEYCODE_BACK) {
           new android.app.AlertDialog.Builder(this)
                   .setIcon(R.drawable.ic_close)
                   .setTitle("Advertencia")
                   .setMessage("¿Realmente desea salir?")
                   .setNegativeButton(android.R.string.cancel, null)
                   .setPositiveButton(android.R.string.ok, {
                       MainActivity.this.finishAffinity();
                   })
           .show();
           return true;
       }
       return super.onKeyDown(KeyCode, event);
    }
}
