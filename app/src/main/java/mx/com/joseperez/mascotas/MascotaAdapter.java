package mx.com.joseperez.mascotas;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mx.com.joseperez.mascotas.db.BaseDatos;

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

        final int mascotaID = mascota.getId();

        mascotaViewHolder.imgHuesoBlanco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDatos bd = new BaseDatos(v.getContext());
                bd.insertarMascotaLike(mascotaID);

                int likes = bd.getMascotaLikes(mascotaID);
                mascotaViewHolder.tvMeGusta.setText(String.valueOf(likes));
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
