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

        //Bundle parametros = getIntent().getExtras();

         //mascotas = (ArrayList<MascotaModel>) getIntent().getSerializableExtra("listaTopFive");

        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);

        mascotas = new ArrayList<MascotaModel>();
        inicializarAdaptador();
        inicializarListaMascotas();


        // Toast.makeText(this, mascotas.get(1).getNombre(),Toast.LENGTH_LONG).show();
    }

    public MascotaAdapter adaptador;

    public void inicializarAdaptador(){
        adaptador = new MascotaAdapter(mascotas,this);
        listaMascotas.setAdapter(adaptador);
    }

    public void inicializarListaMascotas(){
        mascotas.add(new MascotaModel(R.drawable.mascota01,"Perro 1",0,R.drawable.huesoblanco));
        mascotas.add(new MascotaModel(R.drawable.mascota02,"Perro 2",1,R.drawable.huesoblanco));
        mascotas.add(new MascotaModel(R.drawable.mascota03,"Perro 3",2,R.drawable.huesoblanco));
        mascotas.add(new MascotaModel(R.drawable.mascota04,"Perro 4",3,R.drawable.huesoblanco));
        mascotas.add(new MascotaModel(R.drawable.mascota05,"Perro 5",4,R.drawable.huesoblanco));
    }

}
