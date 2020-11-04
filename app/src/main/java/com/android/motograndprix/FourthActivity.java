package com.android.motograndprix;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

//public class FourthActivity extends AppCompatActivity implements View.OnClickListener {
public class FourthActivity extends AppCompatActivity{

    EditText titulo;
    EditText circuito;
    EditText descripcion;
    Button crearEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_fourth );

        //flecha action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        //Icono
        getSupportActionBar().setDisplayShowHomeEnabled( true );
        getSupportActionBar().setIcon( R.mipmap.ic_launcherlogo );

    titulo= (EditText) findViewById(R.id.editNOmbreEvento);
    circuito=(EditText) findViewById( R.id.editTextCircuito );
    descripcion=(EditText) findViewById( R.id.editTextDescripcion );
    crearEvento= (Button)findViewById( R.id.buttonCrearEvento );

         crearEvento.setOnClickListener( new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (!titulo.getText().toString().isEmpty() && !circuito.getText().toString().isEmpty() && !descripcion.getText().toString().isEmpty()) {
                     Intent intent= new Intent(Intent.ACTION_INSERT);
                     intent.setData( CalendarContract.Events.CONTENT_URI );
                     intent.putExtra( CalendarContract.Events.TITLE,titulo.getText().toString());
                     intent.putExtra( CalendarContract.Events.EVENT_LOCATION,circuito.getText().toString());
                     intent.putExtra( CalendarContract.Events.DESCRIPTION,descripcion.getText().toString());
                    intent.putExtra( CalendarContract.Events.ALL_DAY, "true" );
                   intent.putExtra( Intent.EXTRA_EMAIL,"" );

                   if(intent.resolveActivity( getPackageManager() )!=null){
                      startActivity(intent );
                    }else {
                        Toast.makeText( FourthActivity.this, "Esta app no soporta esta accion",Toast.LENGTH_SHORT ).show();
                     }
                 } else {
                     Toast.makeText( FourthActivity.this, "Complete los campos para continuar",Toast.LENGTH_SHORT ).show();
                 }
             }
         } );
    }
}




