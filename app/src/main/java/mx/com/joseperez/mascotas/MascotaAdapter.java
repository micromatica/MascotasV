package mx.com.joseperez.mascotas;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    ArrayList<MascotaModel> mascotas;
    Activity activity;

    public MascotaAdapter(ArrayList<MascotaModel> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota,parent,false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MascotaViewHolder mascotaViewHolder, int position) {
        final MascotaModel mascota = mascotas.get(position);
        mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvNombre.setText(mascota.getNombre());
        mascotaViewHolder.tvMeGusta.setText(String.valueOf(mascota.getMeGusta()));
        mascotaViewHolder.imgHuesoBlanco.setImageResource(mascota.getHuedoMeGusta());

        mascotaViewHolder.imgHuesoBlanco.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int meGusta = mascota.getMeGusta();
                mascota.setMeGusta(++meGusta);
                mascotaViewHolder.tvMeGusta.setText(String.valueOf(meGusta));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        private TextView tvNombre;
        private TextView tvMeGusta;
        private ImageView imgHuesoBlanco;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView)itemView.findViewById(R.id.imgFoto);
            tvNombre = (TextView)itemView.findViewById(R.id.tvNombre);
            tvMeGusta = (TextView)itemView.findViewById(R.id.tvMeGusta);
            imgHuesoBlanco = (ImageView) itemView.findViewById(R.id.imgHuesoBlanco);
        }
    }

}
