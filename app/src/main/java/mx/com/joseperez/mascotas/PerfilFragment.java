package mx.com.joseperez.mascotas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class PerfilFragment extends Fragment {

    ArrayList<MascotaModel> miMascota;
    RecyclerView miMascotaList;;

    View v;

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
        //mascotas.add(new MascotaModel(R.drawable.mascota01,"Perro 1",0,R.drawable.huesoblanco));
        miMascota.add(new MascotaModel(R.drawable.mascota01,"foto 1",0,R.drawable.huesoblanco));
        miMascota.add(new MascotaModel(R.drawable.mascota01,"foto 2",0,R.drawable.huesoblanco));
        miMascota.add(new MascotaModel(R.drawable.mascota01,"foto 3",0,R.drawable.huesoblanco));
        miMascota.add(new MascotaModel(R.drawable.mascota01,"foto 4",0,R.drawable.huesoblanco));
        miMascota.add(new MascotaModel(R.drawable.mascota01,"foto 5",0,R.drawable.huesoblanco));
        miMascota.add(new MascotaModel(R.drawable.mascota01,"foto 6",0,R.drawable.huesoblanco));
        miMascota.add(new MascotaModel(R.drawable.mascota01,"foto 7",0,R.drawable.huesoblanco));
        miMascota.add(new MascotaModel(R.drawable.mascota01,"foto 8",0,R.drawable.huesoblanco));
        miMascota.add(new MascotaModel(R.drawable.mascota01,"foto 9",0,R.drawable.huesoblanco));
        miMascota.add(new MascotaModel(R.drawable.mascota01,"foto 10",0,R.drawable.huesoblanco));
    }

}