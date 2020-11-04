package com.android.motograndprix;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class conexionSQLiteHelper extends SQLiteOpenHelper {

    public static String TABLA_USUARIOS="usuarios";
    public static String CAMPO_NOMBRE="nombre";
    public static String CAMPO_MAIL="mail";
    public static String CAMPO_PASSWORD="password";
    public static final String USUARIOS="CREATE TABLE "+TABLA_USUARIOS+" ("+CAMPO_NOMBRE+" Text, "+CAMPO_MAIL+" String, "+CAMPO_PASSWORD+" String)";

    public conexionSQLiteHelper(@Nullable Context context) {
        super( context,"BasedeDatos.db", null, 3 );
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL(USUARIOS);
        Log.e("oncreate","oncreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase BaseDeDatos, int versionAntigua, int versionNueva) {
        BaseDeDatos.execSQL("DROP TABLE IF EXISTS USUARIOS");
        onCreate(BaseDeDatos);
        Log.e("onupgrade","onupgrade");
    }

}
