package edu.upc.dsa;

import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.OrderTO;
import edu.upc.dsa.models.ProdCant;
import edu.upc.dsa.models.ProdCantTO;
import edu.upc.dsa.models.Product;

import java.util.*;

import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

public class ProductManagerImpl implements ProductManager {

    private static ProductManager instance;
    protected List<Product> products;

    private Queue<OrderTO> orderQueue;
    HashMap<String, User> userByID;
    protected List<ProdCantTO> prodcants;
    protected List<OrderTO> ordersusuarios ;
    protected List<OrderTO> orders ;
    final static Logger logger = Logger.getLogger(ProductManagerImpl.class);

    private ProductManagerImpl() {
        this.products = new LinkedList<>();
        this.prodcants = new LinkedList<>();
        this.orderQueue = new ArrayDeque<>();
        this.userByID = new HashMap<>();
        this.ordersusuarios = new LinkedList<>();
        this.orders = new LinkedList<>();
    }

    public static ProductManager getInstance() {
        if (instance==null) instance = new ProductManagerImpl();
        return instance;
    }
    public int size() {
        int ret = this.products.size();
        logger.info("size " + ret);

        return ret;
    }
    public void addProduct(Product t) {
        logger.info("Nuevo producto: " + t.getName() + " con un coste de " + t.getPrice() + "€");

        this.products.add (t);
        logger.info("El producto ha sido añadido");
    }
    public void addProduct(String name, double price) {
        this.addProduct(new Product(name, price));
    }
    public List<Product> listaProductos() {
        return this.products;
    }

    public List<Product> ordenarlistaProductos() {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return Double.compare(o1.getPrice(), o2.getPrice());
            }
        });
        logger.info("Ordenado de menor a mayor precio:");
        for(Product p:this.products)
            logger.info(p.getName());
        logger.info("===================================");
        return this.products;
    }

    public OrderTO addOrder(OrderTO o,String u){
        o.setId(u);
        this.orders.add(o);
        orderQueue.add(o);
        logger.info("Se ha añadido un pedido");
        return o;
    }

    @Override
    public List<Product> getListProductsBySells(){
        products.sort(Comparator.comparingDouble(Product::getNumSells).reversed());
        logger.info("Lista de productos ordenado por ventas");
        for(Product p:this.products)
            logger.info(p.getName() + " " + p.getNumSells());
        logger.info("===================================");
        return products;
    }

    @Override
    public List<OrderTO> listaOrderporUsuario(String u) {
        for(OrderTO o : this.orders){
            if(o.getId()==u){
                for(ProdCantTO prod: o.getListap()) {
                    ordersusuarios.add(o);
                    logger.info(o.getId() + " " + prod.getCantidad() + " " + prod.getProd());
                }
            }
        }
        logger.info("===================================");
        return this.ordersusuarios;
    }

    public void addProdCantTO(ProdCantTO pc) {
        logger.info("new ProdCant " + pc);

        this.prodcants.add (pc);
        logger.info("Added " + pc.getCantidad() + " " + pc.getProd());
    }

    public void addProdCantTO(String producto, int cantidad){
        this.addProdCantTO(new ProdCantTO(producto, cantidad));
    }
    public List<ProdCantTO> ListaProdCant() {
        return this.prodcants;
    }



    public void deliverOrder() {
        OrderTO o = orderQueue.poll();
        User u = userByID.get(o.getId());
        ProdCantTO prod = o.getListap().get(1);
        Product p = null;
        int i = 0;
        int j = 0;
        for (ProdCantTO lp: o.getListap()) {
            for(Product pro: this.products){
                if(pro.getName() == lp.getProd()){
                    p = products.get(i);
                    j = i;
                }else i++;
            }
            this.products.get(j).addNumSells(lp.getCantidad());
            logger.info("Se ha añadido numsells en: " + prod.getProd() + " " + prod.getCantidad() + " veces");
            i= 0;
        }
        logger.info("Se ha servido el pedido con ID: " + o.getId());
    }

}
