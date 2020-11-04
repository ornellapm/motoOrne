package com.android.motograndprix;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public View registro;
    public EditText campoMail;
    public EditText campoPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_main);

        //Icono
        getSupportActionBar().setDisplayShowHomeEnabled( true );
        getSupportActionBar().setIcon( R.mipmap.ic_launcherlogo );

        campoMail = (EditText) findViewById( R.id.editTextUsuario );
        campoPassword = (EditText) findViewById( R.id.editTextPassword );

        registro = (Button) findViewById( R.id.buttonRegistro );
        registro.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, ThirdActivity.class );
                startActivity( intent );
            }
        } );

    }
    public void ingresar(View view){
                conexionSQLiteHelper conec = new conexionSQLiteHelper( getBaseContext() );
                SQLiteDatabase BaseDeDatos = conec.getWritableDatabase();

                String mail = campoMail.getText().toString();
                String password = campoPassword.getText().toString();

                if ((!mail.isEmpty()) || (!password.isEmpty())) {
                    Cursor fila = BaseDeDatos.rawQuery
                            ( "Select mail, password from usuarios where mail ='" + mail + "' and password = '" + password + "'", null );

                    if (fila.moveToFirst()) {
                        campoMail.setText( fila.getString( 0 ) );
                        campoPassword.setText( fila.getString( 1 ) );
                            Toast.makeText(this, "Bienvenido!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent( MainActivity.this, SecondActivity.class );
                        startActivity( intent );
                        BaseDeDatos.close();
                    } else {
                        Toast.makeText(this, "Verificar usuario/contraseña.", Toast.LENGTH_SHORT).show();
                        BaseDeDatos.close();
                    }
                } else{
                    Toast.makeText(this, "Por favor completar todos los campos.", Toast.LENGTH_SHORT).show();
                }
    }
}
