package mx.com.joseperez.mascotas;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class InicioFragment extends Fragment {

    ArrayList<MascotaModel> mascotas;
    private RecyclerView listaMascotas;
    View v;

    public InicioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_inicio, container, false);

        mascotas = new ArrayList<MascotaModel>();
        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);

        inicializarListaMascotas();
        inicializarAdaptador();
        // Inflate the layout for this fragment
        return (v);
    }

    /*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        CoordinatorLayout cordinatorLayout = (CoordinatorLayout) v.findViewById(R.id.coordinatorLayoutID);
        Intent intent;
        switch (item.getItemId()){
            case R.id.mTopFive:
                //Intent intent = new Intent(this,ActivityTopFive.class);
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
     */

    public MascotaAdapter adaptador;

    public void inicializarAdaptador(){
        adaptador = new MascotaAdapter(mascotas,getActivity());
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