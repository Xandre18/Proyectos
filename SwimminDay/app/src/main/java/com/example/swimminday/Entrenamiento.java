package com.example.swimminday;

public class Entrenamiento {
    public String date;
    public int tiempo, distancia, velMedia;

    public Entrenamiento(String date, int tiempo, int distancia){
        this.date = date;
        this.tiempo = tiempo;
        this.distancia = distancia;
        this.velMedia = distancia / tiempo;
    }
    public Entrenamiento(){
        this.date = "15/12/2022";
        this.tiempo = 2;
        this.distancia = 2;
        this.velMedia = distancia / tiempo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getVelMedia() {
        return velMedia;
    }

    public void setVelMedia(int tiempo, int distancia) {
        this.velMedia = distancia/tiempo;
    }

    @Override
    public String toString() {
        return "Entrenamiento:\n" +
                "Fecha: " + date +
                "\nTiempo: " + tiempo +
                " minutos. \nDistancia: " + distancia +
                " metros. \nVelocidad media: " + distancia / tiempo + " metros/minutos";
    }
}


