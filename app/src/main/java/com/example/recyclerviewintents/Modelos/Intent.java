package com.example.recyclerviewintents.Modelos;

public class Intent {
    private String nombreIntent;
    private Integer id;
    private android.content.Intent intento;

    public Intent(String nombreIntent, Integer id, android.content.Intent intento) {
        this.nombreIntent = nombreIntent;
        this.id = id;
        this.intento = intento;
    }

    public String getNombreIntent() {
        return nombreIntent;
    }

    public void setNombreIntent(String nombreIntent) {
        this.nombreIntent = nombreIntent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public android.content.Intent getIntento() {
        return intento;
    }

    public void setIntento(android.content.Intent intento) {
        this.intento = intento;
    }
}
