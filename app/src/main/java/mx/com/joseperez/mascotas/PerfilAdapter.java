package mx.com.joseperez.mascotas;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PerfilAdapter extends RecyclerView.Adapter<PerfilAdapter.PerfilViewHolder> {

    ArrayList<MascotaModel> mascotas;
    Activity activity;

    public PerfilAdapter(ArrayList<MascotaModel> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PerfilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil,parent,false);
        return new PerfilAdapter.PerfilViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PerfilViewHolder perfilViewHolder, int position) {
        final MascotaModel mascota = mascotas.get(position);
        perfilViewHolder.imgFoto.setImageResource(mascota.getFoto());
        perfilViewHolder.tvMeGusta.setText(String.valueOf(mascota.getMeGusta()));
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class PerfilViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        private TextView tvNombre;
        private TextView tvMeGusta;
        private ImageView imgHuesoBlanco;

        public PerfilViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView)itemView.findViewById(R.id.imgFoto);
            tvNombre = (TextView)itemView.findViewById(R.id.tvNombre);
            tvMeGusta = (TextView)itemView.findViewById(R.id.tvMeGusta);
            imgHuesoBlanco = (ImageView) itemView.findViewById(R.id.imgHuesoBlanco);
        }
    }
}
