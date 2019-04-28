package com.example.examen2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MostrarActivity extends AppCompatActivity {
    private Button btnMostrarNombres;
    private String nombresShared;
    private SharedPreferences.Editor editor;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        inicializarCosasPantalla();
        inicializarSharedPreferences();
    }

    public void inicializarCosasPantalla(){
        btnMostrarNombres = (Button) findViewById(R.id.btnMostrarNombres);

        listView = (ListView) findViewById(R.id.listaNombres);

        btnMostrarNombres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> nombresListos = new ArrayList<>();
                String [] nombresArray = nombresShared.split(",");
                for(String nombres:nombresArray)
                    nombresListos.add(nombres);

                renderizandoListView(nombresListos);
            }
        });
    }

    public void inicializarSharedPreferences(){
        SharedPreferences prefs = getSharedPreferences("prefs", 0 );
        editor = prefs.edit();
        nombresShared = prefs.getString("words","");
    }

    public void renderizandoListView(ArrayList<String> array){
        ArrayAdapter adaptorArray = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
        listView.setAdapter(adaptorArray);
    }

}
