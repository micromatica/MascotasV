package mx.com.joseperez.mascotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    ArrayList<MascotaModel> mascotas;
    private RecyclerView listaMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agragarFloatingActionButton();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_pets_black_24dp);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        mascotas = new ArrayList<MascotaModel>();
        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);

        inicializarListaMascotas();
        inicializarAdaptador();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mTopFive:
                Intent intent = new Intent(this,ActivityTopFive.class);
                intent.putExtra("listaTopFive",mascotas);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // Habilitar la acción de FloatingActionButton
    public void agragarFloatingActionButton(){
        FloatingActionButton miFAB = (FloatingActionButton) findViewById(R.id.floating_action_button);
        miFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Jose",Toast.LENGTH_SHORT).show();
            }
        });
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
