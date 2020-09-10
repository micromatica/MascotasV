package mx.com.joseperez.mascotas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import mx.com.joseperez.mascotas.db.BaseDatos;

public class PerfilFragment extends Fragment {

    ArrayList<MascotaModel> miMascota;
    RecyclerView miMascotaList;;

    View v;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_perfil, container, false);

        miMascota = new ArrayList<MascotaModel>();
        miMascotaList = (RecyclerView) v.findViewById(R.id.rvMiMascota);

        GridLayoutManager glm = new GridLayoutManager(getContext(),2);
        miMascotaList.setLayoutManager(glm);

        inicializarMiMascotaList();
        inicializarAdapter();

        return (v);
    }

    public PerfilAdapter adaptador;

    private void inicializarAdapter(){
        adaptador = new PerfilAdapter(miMascota,getActivity());
        miMascotaList.setAdapter(adaptador);
    }

    private void inicializarMiMascotaList(){
        BaseDatos db = new BaseDatos(getContext());
        miMascota = db.cargarMascotas();
        Toast.makeText(this.getContext(),"PERFIL: " + String.valueOf(miMascota.size()),Toast.LENGTH_LONG).show();
    }

}