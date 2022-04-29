package edu.upc.dsa.models;

public class ProdCant {
    private Product prod;
    private int cantidad;

    public ProdCant(){}

    public ProdCant(Product prod, int cantidad) {
        this.prod = prod;
        this.cantidad = cantidad;
    }

    public Product getProd() {
        return prod;
    }

    public void setProd(Product prod) {
        this.prod = prod;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
