package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<ProdCant> listap = new ArrayList<ProdCant>();
    private String id;

    public Order(){}

    public Order(List<ProdCant> listap, String id){
        this.listap = listap;
        this.id = id;
    }
    public void addProdCant(Product product,int quant){
        listap.add(new ProdCant(product,quant));
    }

    public List<ProdCant> getListap() {
        return listap;
    }

    public void setListap(List<ProdCant> listap) {
        this.listap = listap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
