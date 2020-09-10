package mx.com.joseperez.mascotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.zip.Inflater;

import mx.com.joseperez.mascotas.db.BaseDatos;

public class MainActivity extends AppCompatActivity {


    ArrayList<MascotaModel> mascotas;

    // private RecyclerView listaMascotas;


    TabLayout tablayout;
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agragarFloatingActionButton();

        // Para mostrar el icono en la ActionBar antes del título (izquierda)
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_pets_black_24dp);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        tablayout = (TabLayout)findViewById(R.id.tablayout);
        viewpager = (ViewPager)findViewById(R.id.viewpager);

        setUpViewPager();

        mascotas = new ArrayList<MascotaModel>();

        //listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);
/*
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);

        inicializarListaMascotas();
        inicializarAdaptador();
         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return(true);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        CoordinatorLayout cordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayoutID);
        Intent intent;
        switch (item.getItemId()){
            case R.id.mTopFive:
                intent = new Intent(this,ActivityTopFive.class);
                intent.putExtra("listaTopFive",mascotas);
                startActivity(intent);
                break;
            case R.id.mnuContacto:
                intent = new Intent(this,ContactoActivity.class);
                startActivity(intent);
                break;
            case R.id.mnuAcercaDe:
                //Snackbar.make(cordinatorLayout,"Acerca de...",Snackbar.LENGTH_LONG).show();
                intent = new Intent(this,AcercaDeActivity.class);
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

    // Tabs
    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new InicioFragment());
        fragments.add(new PerfilFragment());

        return  fragments;
    }


    public void setUpViewPager() {
        viewpager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));

        tablayout.setupWithViewPager(viewpager);

        tablayout.getTabAt(0).setIcon(R.drawable.huesoamarillo);
        tablayout.getTabAt(1).setIcon(R.drawable.huesoblanco);
    }

}
