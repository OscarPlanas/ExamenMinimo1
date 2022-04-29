package edu.upc.dsa;

import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.OrderTO;
import edu.upc.dsa.models.ProdCant;
import edu.upc.dsa.models.ProdCantTO;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;

import java.util.List;

public interface ProductManager {


    public void addProduct(String name, double price);

    public List<Product> listaProductos();

    public List<Product> ordenarlistaProductos();
    public List<ProdCantTO> ListaProdCant();
    public void deliverOrder();
    public OrderTO addOrder(OrderTO o,String u);
    public void addProdCantTO(String o,int u);

    public List<Product> getListProductsBySells();

    public List<OrderTO> listaOrderporUsuario(String ID);

    public int size();
}