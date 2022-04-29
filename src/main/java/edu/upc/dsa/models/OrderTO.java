package edu.upc.dsa.models;

import edu.upc.dsa.ProductManagerImpl;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OrderTO {
    final static Logger logger = Logger.getLogger(ProductManagerImpl.class);

    private List<ProdCantTO> listap = new ArrayList<ProdCantTO>();
    private String id;

    public OrderTO(){}

    public OrderTO(List<ProdCantTO> listap, String id){
        this.listap = listap;
        this.id = id;
    }
    public void addProdCantTO(String product,int quant){
        listap.add(new ProdCantTO(product,quant));
        logger.info("Added " + product + " " + quant);
    }

    public List<ProdCantTO> getListap() {
        return listap;
    }

    public void setListap(List<ProdCantTO> listap) {
        this.listap = listap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}