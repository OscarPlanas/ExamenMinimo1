package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class TiendaObject {
    //private List<ProdCant> listap = new ArrayList<ProdCant>();
    //private String id;
    private String name;
    private String descripcion;
    private int coins;

    public TiendaObject(){}

    public TiendaObject(String name, String descripcion, int coins){
        this.name = name;
        this.descripcion = descripcion;
        this.coins = coins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
