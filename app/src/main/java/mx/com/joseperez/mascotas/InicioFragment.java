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
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import mx.com.joseperez.mascotas.db.BaseDatos;

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

    public MascotaAdapter adaptador;

    public void inicializarAdaptador(){
        adaptador = new MascotaAdapter(mascotas,getActivity());
        listaMascotas.setAdapter(adaptador);
    }

    public void inicializarListaMascotas(){
        BaseDatos db = new BaseDatos(getContext());
        mascotas = db.cargarMascotas();
    }

}