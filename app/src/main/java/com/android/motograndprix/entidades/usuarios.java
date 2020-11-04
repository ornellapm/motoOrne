package com.android.motograndprix.entidades;

import android.content.Intent;

import org.w3c.dom.Text;

public class usuarios {

    private Text nombre;
    private String mail;
    private String password;


    public usuarios(Intent idsocio, Integer id, Text nombre, String mail, String password) {

        this.nombre = nombre;
        this.mail = mail;
        this.password = password;

    }



    public Text getNombre() {
        return nombre;
    }

    public void setNombre(Text nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
