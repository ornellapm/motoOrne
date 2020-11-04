package com.android.motograndprix;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;


public class camaraActivity extends AppCompatActivity {

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    public static final int GALLERY_REQUEST_CODE = 105;
    ImageView vistaFoto;
    Button camara;
    Button gallery;
    String currentPhotoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_fifth );

        //flecha action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        //Icono
        getSupportActionBar().setDisplayShowHomeEnabled( true );
        getSupportActionBar().setIcon( R.mipmap.ic_launcherlogo );

        vistaFoto = findViewById( R.id.imageMostrarFoto);
        camara = findViewById( R.id.tomarFoto);
        gallery = findViewById(R.id.galeria);


        camara.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            askWritePermissions();
            askCameraPermissions();
            }
        } );
//abrir galeria
        gallery.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery, GALLERY_REQUEST_CODE );
            }
        } );

    }
//camara permisos
    private void askCameraPermissions() {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE );
        }else{
            dispatchTakePictureIntent();
        }
    }

   private void askWritePermissions() {
       if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_PERM_CODE );
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       if(requestCode == CAMERA_PERM_CODE){
           if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED) {
               dispatchTakePictureIntent();
           }else{
                Toast.makeText( this,"Sin permisos",Toast.LENGTH_SHORT).show();
           }
       }
    }
//permisos de camara
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CAMERA_REQUEST_CODE){
                if(resultCode == Activity.RESULT_OK){
                    File f = new File(currentPhotoPath);
                    vistaFoto.setImageURI(Uri.fromFile(f));
                    Log.d("tag","La url de la imagen es "+Uri.fromFile(f));

                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri contentUri = Uri.fromFile(f);
                    mediaScanIntent.setData(contentUri);
                    this.sendBroadcast(mediaScanIntent);
                }
        }
        if(requestCode == GALLERY_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
              Uri contentUri = data.getData();
              String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format( new Date() );
                String imageFileName = "JPEG_" + timeStamp + "."+getFileExt(contentUri);
                Log.d("tag","onActivityResult "+imageFileName);
               vistaFoto.setImageURI(contentUri);
            }
        }
    }

    private String getFileExt(Uri contentUri){
        ContentResolver c = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }
    //crea el directorio de imagen
    private File createImageFile() throws IOException{
      //Crear un nombre de archivo de imagen
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
          File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefijo */
                ".jpg",         /* sufijo */
                storageDir      /* directorio */
        );

        // Guarda el archivo
        currentPhotoPath = image.getAbsolutePath();
        return image;

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // validar  actividad de camara
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Crear archivo donde deberia ir la foto
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (Exception ex) {
            ex.printStackTrace();
            }
            // continuar si fue creado el archivo
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.android.motograndprix.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }

}