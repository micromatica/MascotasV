package mx.com.joseperez.mascotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import mx.com.joseperez.mascotas.db.BaseDatos;

public class ActivityTopFive extends AppCompatActivity {

    ArrayList<MascotaModel> mascotas;
    private RecyclerView listaMascotas;
    public MascotaAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_five);

        //recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mascotas = new ArrayList<MascotaModel>();
        listaMascotas = (RecyclerView) findViewById(R.id.rvTopFive);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);

        inicializarListaMascotas();
        inicializarAdaptador();

    }

    public void inicializarAdaptador(){
        adaptador = new MascotaAdapter(mascotas,this);
        listaMascotas.setAdapter(adaptador);
    }

    public void inicializarListaMascotas(){
        BaseDatos db = new BaseDatos(this);
        mascotas = db.cargarMascotas();
    }

}
