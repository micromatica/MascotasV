package mx.com.joseperez.mascotas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import mx.com.joseperez.mascotas.MascotaModel;
import mx.com.joseperez.mascotas.R;

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public  BaseDatos(@Nullable Context context) {
        super(context, BaseDatosConstantes.DATABASE_NAME, null, BaseDatosConstantes.DATABASE_VERSION);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Toast.makeText(context,"bbb", Toast.LENGTH_LONG).show();

        String queryCrearTablaMascotas = "CREATE TABLE " + BaseDatosConstantes.TABLE_MASCOTAS + "(" +
                BaseDatosConstantes.TABLE_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BaseDatosConstantes.TABLE_MASCOTA_FOTO + " INTEGER, " +
                BaseDatosConstantes.TABLE_MASCOTA_NOMBRE + " TEXT " +
                ")";

        String queryCrearTablaMascotasLikes = "CREATE TABLE " + BaseDatosConstantes.TABLE_MASCOTA_LIKES + "(" +
                BaseDatosConstantes.TABLE_MASCOTA_LIKES_MASCOTA_ID + " INTEGER, " +
                BaseDatosConstantes.TABLE_MASCOTA_LIKES_NUMERO_LIKES + " INTEGER," +
                "FOREIGN_KEY " + BaseDatosConstantes.TABLE_MASCOTA_LIKES_MASCOTA_ID + " " +
                "REFERENCES " + BaseDatosConstantes.TABLE_MASCOTAS  + "(" +  BaseDatosConstantes.TABLE_MASCOTA_ID + ")" +
                ")";
        db.execSQL(queryCrearTablaMascotas);
        db.execSQL(queryCrearTablaMascotasLikes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Log.d("MyTag","onUpgrade");
        db.execSQL("DROP TABLE IF EXISTS " + BaseDatosConstantes.TABLE_MASCOTAS);
        db.execSQL("DROP TABLE IF EXISTS " + BaseDatosConstantes.TABLE_MASCOTA_LIKES);
        onCreate(db);
    }

    public void insertarCincoMascotas(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(BaseDatosConstantes.TABLE_MASCOTA_NOMBRE, "Perrito 1");
        contentValues.put(BaseDatosConstantes.TABLE_MASCOTA_FOTO, R.drawable.mascota01);
        db.insert(BaseDatosConstantes.TABLE_MASCOTAS,null, contentValues);

        contentValues.put(BaseDatosConstantes.TABLE_MASCOTA_NOMBRE, "Perrito 2");
        contentValues.put(BaseDatosConstantes.TABLE_MASCOTA_FOTO, R.drawable.mascota02);
        db.insert(BaseDatosConstantes.TABLE_MASCOTAS,null, contentValues);

        contentValues.put(BaseDatosConstantes.TABLE_MASCOTA_NOMBRE, "Perrito 3");
        contentValues.put(BaseDatosConstantes.TABLE_MASCOTA_FOTO, R.drawable.mascota03);
        db.insert(BaseDatosConstantes.TABLE_MASCOTAS,null, contentValues);

        contentValues.put(BaseDatosConstantes.TABLE_MASCOTA_NOMBRE, "Perrito 4");
        contentValues.put(BaseDatosConstantes.TABLE_MASCOTA_FOTO, R.drawable.mascota04);
        db.insert(BaseDatosConstantes.TABLE_MASCOTAS,null, contentValues);

        contentValues.put(BaseDatosConstantes.TABLE_MASCOTA_NOMBRE, "Perrito 5");
        contentValues.put(BaseDatosConstantes.TABLE_MASCOTA_FOTO, R.drawable.mascota05);
        db.insert(BaseDatosConstantes.TABLE_MASCOTAS,null, contentValues);

        db.close();
    }

    public void insertarMascotaLike(Integer mascotaID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contenValues = new ContentValues();

        contenValues.put(BaseDatosConstantes.TABLE_MASCOTA_LIKES_MASCOTA_ID,mascotaID);
        contenValues.put(BaseDatosConstantes.TABLE_MASCOTA_LIKES_NUMERO_LIKES,1);

        db.insert(BaseDatosConstantes.TABLE_MASCOTA_LIKES,null,contenValues);

        db.close();
    }

    public ArrayList<MascotaModel> cargarMascotas(){
        ArrayList<MascotaModel> mascotas = new ArrayList<>();

        String query = "SELECT * " +
                        "FROM " + BaseDatosConstantes.TABLE_MASCOTAS;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor registros = db.rawQuery(query, null);

        while(registros.moveToNext()){
            MascotaModel contactoActual = new MascotaModel();	// 30.30min
            contactoActual.setId(registros.getInt(0));
            contactoActual.setFoto(registros.getInt(1));
            contactoActual.setNombre(registros.getString(2));

            String queryLikes = "SELECT COUNT(" + BaseDatosConstantes.TABLE_MASCOTA_LIKES_NUMERO_LIKES +") as likes" +
                                " FROM " + BaseDatosConstantes.TABLE_MASCOTA_LIKES + // +
                                " WHERE " + BaseDatosConstantes.TABLE_MASCOTA_LIKES_MASCOTA_ID + "=" + contactoActual.getId() ;

            Cursor registrosLikes = db.rawQuery(queryLikes,null);

            if(registrosLikes.moveToNext()){
                //Log.d("MyTag","LIKES: " + queryLikes + " - " + String.valueOf(registrosLikes.getInt(0)));
                contactoActual.setMeGusta(registrosLikes.getInt(0));
            } else {
                contactoActual.setHuedoMeGusta(0);
            }

            mascotas.add(contactoActual);
        }
        db.close();
        return mascotas;
    }

    public int getMascotaLikes(int mascotaID){
        ArrayList<MascotaModel> mascotas = new ArrayList<>();

        String query = "SELECT Count(cLike) " +
                        "FROM " + BaseDatosConstantes.TABLE_MASCOTA_LIKES +
                        " WHERE " + BaseDatosConstantes.TABLE_MASCOTA_LIKES_MASCOTA_ID + " = " + mascotaID;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor registros = db.rawQuery(query, null);

        Integer likes = 0;
        if(registros.moveToNext())
            likes = registros.getInt(0);

        Log.d("MyTag","Mascota " + String.valueOf(likes) + String.valueOf(likes));

        db.close();

        return  likes;
    }

    public int getMascotaConMasLikes(){
        ArrayList<MascotaModel> mascotas = new ArrayList<>();

        String query = "SELECT id, Count(cLike) " +
                        "FROM " + BaseDatosConstantes.TABLE_MASCOTA_LIKES +
                        " Group by id" +
                        " Order by count(id) desc" ;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor registros = db.rawQuery(query, null);

        Integer id = 0;
        if(registros.moveToNext())
            id = registros.getInt(0);

        Log.d("MyTag","In likes max: " + String.valueOf(id));

        db.close();

        return  id;
    }

}
