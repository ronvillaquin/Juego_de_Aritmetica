package com.rrvq.aritmetica.puntaje;


public class PuntajesPais {

    String nombre_usu;
    String puntaje;
    String pais;
    String id;
    String pos;

    public PuntajesPais(String nombre_usu, String puntaje, String pais, String id, String pos) {
        this.nombre_usu = nombre_usu;
        this.puntaje = puntaje;
        this.pais = pais;
        this.id = id;
        this.pos = pos;
    }

    public String getNombre_usu() {
        return nombre_usu;
    }

    public void setNombre_usu(String nombre_usu) {
        this.nombre_usu = nombre_usu;
    }

    public String getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(String puntaje) {
        this.puntaje = puntaje;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
}
