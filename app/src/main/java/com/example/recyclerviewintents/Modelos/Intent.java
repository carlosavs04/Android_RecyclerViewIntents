package com.example.recyclerviewintents.Modelos;

public class Intent {
    private String nombreIntent;
    private Integer id;

    public Intent(String nombreIntent, Integer id) {
        this.nombreIntent = nombreIntent;
        this.id = id;
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
}
