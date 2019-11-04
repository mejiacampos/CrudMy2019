package com.example.crudmy2019;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        @Override
        public boolean onCreateOptionsMenu(Menu menu) { getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.action_limpiar) {
                et_codigo.setText(null);
                et_descripcion.setText(null);
                et_precio.setText(null);
                return true;
            }else if(id == R.id.action_listaArticulos){
                Intent spinnerActivity = new Intent(MainActivity.this, Consulta_RecyclerView.class);
                startActivity(spinnerActivity);
                return true;
            }else if(id == R.id.action_salir){
                DialogConfirmacion();
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
        private void DialogConfirmacion(){
            //startActivity(new Intent(getApplicationContext(),MainActivity.class));
            String mensaje = "¿Realmente desea salir?";
            dialogo = new AlertDialog.Builder(MainActivity.this);
            dialogo.setIcon(R.drawable.ic_close);
            dialogo.setTitle("Advertencia");
            dialogo.setMessage(mensaje);
            dialogo.setCancelable(false);
            dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo, int id) {
                    MainActivity.this.finishAffinity();
                }
            });
            dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo, int id) {
                    Toast.makeText(getApplicationContext(), "Operación Cancelada.", Toast.LENGTH_LONG).show();
                }
            });
            dialogo.show();
        }
        void Hilo(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=1; i<=1; i++){
                        demora();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String cod = getSharedCodigo(MainActivity.this);
                            String des = getSharedDescripcion(MainActivity.this);
                            String pre = getSharedPrecio(MainActivity.this);

                            et_codigo.setText(cod);
                            et_descripcion.setText(des);
                            et_precio.setText(pre);
                        }
                    });
                }
            }).start();
        }


        private void demora(){
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){}
        }


        public String getSharedCodigo(Context context) {
            SharedPreferences preferences = context.getSharedPreferences("profeGamez", MODE_PRIVATE);
            String codigo = preferences.getString("codigo","0");
            return codigo;
        }

        public String getSharedDescripcion(Context context) {
            SharedPreferences preferences = context.getSharedPreferences("profeGamez", MODE_PRIVATE);
            String descripcion = preferences.getString("descripcion","Sin descripción");
            return descripcion;
        }

        public String getSharedPrecio(Context context) {
            SharedPreferences preferences = context.getSharedPreferences("profeGamez", MODE_PRIVATE);
            String precio = preferences.getString("precio","0.0");
            return precio;
        }
        }
