package mx.com.joseperez.mascotas.db;

public class BaseDatosConstantes {
    public static final String DATABASE_NAME = "dbMascotas";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_MASCOTAS = "catMascotas";
    public static final String TABLE_MASCOTA_ID = "id";
    public static final String TABLE_MASCOTA_FOTO= "cFoto";
    public static final String TABLE_MASCOTA_NOMBRE = "cNombre";

    public static final String TABLE_MASCOTA_LIKES = "datMascotasLikes";
    public static final String TABLE_MASCOTA_LIKES_MASCOTA_ID = "id";
    public static final String TABLE_MASCOTA_LIKES_NUMERO_LIKES = "cLike";
}
