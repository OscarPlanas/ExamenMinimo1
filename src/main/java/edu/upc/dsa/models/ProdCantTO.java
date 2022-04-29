package edu.upc.dsa.models;

public class ProdCantTO {
    private String prod;
    private int cantidad;

    public ProdCantTO(){}

    public ProdCantTO(String prod, int cantidad) {
        this.prod = prod;
        this.cantidad = cantidad;
    }

    public String getProd() {
        return prod;
    }

    public void setProd(String prod) {
        this.prod = prod;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
