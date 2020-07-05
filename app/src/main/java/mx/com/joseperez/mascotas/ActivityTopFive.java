package mx.com.joseperez.mascotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityTopFive extends AppCompatActivity {

    ArrayList<MascotaModel> mascotas;
    private RecyclerView listaMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_five);

        Bundle parametros = getIntent().getExtras();

         mascotas = (ArrayList<MascotaModel>) getIntent().getSerializableExtra("listaTopFive");

        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);

        inicializarAdaptador();

        // Toast.makeText(this, mascotas.get(1).getNombre(),Toast.LENGTH_LONG).show();
    }

    public MascotaAdapter adaptador;

    public void inicializarAdaptador(){
        adaptador = new MascotaAdapter(mascotas,this);
        listaMascotas.setAdapter(adaptador);
    }

}
