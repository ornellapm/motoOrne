package com.android.motograndprix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {


    private View butcamera;
    private View butcalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toast.makeText(this, "Bienvenidos", Toast.LENGTH_LONG).show();

        //flecha action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Icono
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcherlogo);


        butcalendar=findViewById(R.id.imageCalendar);
        butcalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this,FourthActivity.class);
                startActivity(intent);
            }
        });

        butcamera =findViewById(R.id.imageCamara);
        butcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, camaraActivity.class);
                startActivity(intent);
            }
        });
        }
    }
