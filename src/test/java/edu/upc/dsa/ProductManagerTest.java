package edu.upc.dsa;

import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.OrderTO;
import edu.upc.dsa.models.ProdCant;
import edu.upc.dsa.models.Product;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import java.util.List;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
import org.junit.Assert;


public class ProductManagerTest {
    final static Logger log = Logger.getLogger(ProductManagerTest.class);
    ProductManager pm;
    @Before
    public void addProducts() {
        pm=ProductManagerImpl.getInstance();
        pm.addProduct("pera", 1.5);
        pm.addProduct("pepsi", 2);
        pm.addProduct("cheetos", 5);
        pm.addProduct("agua", 1);
        pm.addProduct("galletas", 4);
        OrderTO o = new OrderTO();
        o.addProdCantTO(pm.listaProductos().get(0).getName(), 2);
        o.addProdCantTO(pm.listaProductos().get(2).getName(), 5);
        pm.addOrder(o, "31567455H");
        OrderTO o2 = new OrderTO();
        o2.addProdCantTO(pm.listaProductos().get(1).getName(), 3);
        o2.addProdCantTO(pm.listaProductos().get(4).getName(), 4);
        pm.addOrder(o2, "57987321K");

    }

    @Test
        public void testproduct(){
        List<Product> l = pm.listaProductos();

        pm.deliverOrder();
        pm.deliverOrder();
        pm.getListProductsBySells();
        pm.ordenarlistaProductos();


        pm.listaOrderporUsuario("31567455H");
    }
}