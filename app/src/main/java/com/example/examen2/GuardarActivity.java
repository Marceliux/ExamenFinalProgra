package com.example.examen2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


public class GuardarActivity extends AppCompatActivity {
    private EditText txtNombre;
    private Button btnGuardarInfo;
    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar);
        inicializarCosas();
        inicializarSharedPreferences();
    }

    //Esto inicializa todos los componentes de la pantalla
    private void inicializarCosas(){
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        btnGuardarInfo = (Button) findViewById(R.id.btnGuardarInfo);

        btnGuardarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmpty(txtNombre)){
                    Toast toast = Toast.makeText(getApplicationContext(), "El nombre insertado no es correcto, intentelo otra vez", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    String nombreValidado = txtNombre.getText().toString();
                    guardarInfoShared(nombreValidado);
                }
            }
        });
    }


    //Forma sencilla de validar el input txtNombre
    public static boolean isEmpty(EditText editText) {
        String input = editText.getText().toString().trim();
        return input.length() == 0;
    }

    //Este metodo inicializa los shared preferences
    public void inicializarSharedPreferences() {
        prefs = getSharedPreferences("prefs", 0);
        editor = prefs.edit();
    }


    //Este metodo guarda la info en los shared preference
    private void guardarInfoShared(String nombre){
        String revisando = prefs.getString("words", "");
        if(revisando == "" || revisando.trim() == ""){
            StringBuilder primerNombre = new StringBuilder();
            primerNombre.append(nombre);
            primerNombre.append(",");
            editor.putString("words", primerNombre.toString());
            editor.commit();
        }else{
            revisando += nombre + ",";
            editor.putString("words", revisando);
            editor.commit();
        }

        txtNombre.setText("");
    }

}
