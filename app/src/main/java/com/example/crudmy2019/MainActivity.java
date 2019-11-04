package com.example.crudmy2019;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setTitleTextColor(getResources().getColor(R.color.mycolor1));
        toolbar.setTitleMargin(0, 0, 0, 0);
        toolbar.setSubtitle("CRUD MySQL~2019");
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.mycolor));
        toolbar.setTitle("Prof. Gámez");
        setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        et_codigo = (EditText) findViewById(R.id.et_codigo);
        et_descripcion = (EditText) findViewById(R.id.et_descripcion);
        et_precio = (EditText) findViewById(R.id.et_precio);
        btn_guardar = (Button) findViewById(R.id.btn_guardar);
        btn_consultaCodigo = (Button) findViewById(R.id.btn_consultaCodigo);
        btn_consultaDescripcion = (Button) findViewById(R.id.btn_consultaDescripcion);
        btn_eliminar = (Button) findViewById(R.id.btn_eliminar);
        btn_actualizar = (Button) findViewById(R.id.btn_actualizar);
        //tv_resultado = (TextView) findViewById(R.id.tv_resultado);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogConfirmacion();
            }
        });

        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if (bundle != null) {

                senal = bundle.getString("senal");
                codigo = bundle.getString("codigo");
                descripcion = bundle.getString("descripcion");
                precio = bundle.getString("precio");
                if (senal.equals("1")) {
                    et_codigo.setText(codigo);
                    et_descripcion.setText(descripcion);
                    et_precio.setText(precio);
                    //finish();
                }else if(senal.equals("2")){

                }
            }
        }catch (Exception e){

        }
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(et_codigo.getText().toString().length()==0){
                    et_codigo.setError("Campo obligatorio");
                    inputEt = false;
                }else {
                    inputEt=true;
                }
                if(et_descripcion.getText().toString().length()==0){
                    et_descripcion.setError("Campo obligatorio");
                    inputEd = false;
                }else {
                    inputEd=true;
                }
                if(et_precio.getText().toString().length()==0){
                    et_precio.setError("Campo obligatorio");
                    input1 = false;
                }else {
                    input1=true;
                }

                if (inputEt && inputEd && input1){
                    String codigo = et_codigo.getText().toString();
                    String descripcion = et_descripcion.getText().toString();
                    String precio = et_precio.getText().toString();
                    manto.guardar(MainActivity.this, codigo, descripcion, precio);

                    limpiarDatos();
                    et_codigo.requestFocus();
                }
            }
                });
        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_codigo.getText().toString().length()==0){
                    et_codigo.setError("campo obligatorio");
                    inputEt = false;
                }else {
                    inputEt=true;
                }

                if(inputEt){
                    String codigo = et_codigo.getText().toString();
                    manto.eliminar(MainActivity.this, codigo);

                    limpiarDatos();
                    et_codigo.requestFocus();

                }
            });
         btn_consultaCodigo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Begin...
                    if(et_codigo.getText().toString().length()==0){
                        et_codigo.setError("campo obligatorio");
                        inputEt = false;
                    }else {
                        inputEt=true;
                    }

                    if(inputEt) {
                        String codigo = et_codigo.getText().toString();
                        manto.consultarCodigo(MainActivity.this, codigo);
                        et_codigo.requestFocus();
                    }
                    //End

                }
            });



        btn_consultaDescripcion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(et_descripcion.getText().toString().length()==0){
                        et_descripcion.setError("Campo obligatorio");
                        inputEd = false;
                    }else {
                        inputEd=true;
                    }
                    if(inputEd){
                        String descripcion = et_descripcion.getText().toString();
                        manto.consultarDescripcion(MainActivity.this, descripcion);
                        et_descripcion.requestFocus();
                    }

                }
            });
         btn_actualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(et_codigo.getText().toString().length()==0){
                        et_codigo.setError("campo obligatorio");
                        inputEt = false;
                    }else {
                        inputEt=true;
                    }

                    if(inputEt) {

                        String cod = et_codigo.getText().toString();
                        String descripcion = et_descripcion.getText().toString();
                        String precio = et_precio.getText().toString();

                        datos.setCodigo(Integer.parseInt(cod));
                        datos.setDescripcion(descripcion);
                        datos.setPrecio(Double.parseDouble(precio));
                        manto.modificar(MainActivity.this, datos);
                        limpiarDatos();
                        et_codigo.requestFocus();
                    }

                }
            });


            FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

        }


        public void limpiarDatos(){
            et_codigo.setText(null);
            et_descripcion.setText(null);
            et_precio.setText(null);
        }

    }
}
