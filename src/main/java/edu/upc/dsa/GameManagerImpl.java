package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;
import java.util.*;

public class GameManagerImpl implements GameManager {

    private static GameManager instance;
    protected List<User> users;
    private HashMap<String , User> mapUser;
    private HashMap<String , TiendaObject> mapObject;
    private List<TiendaObject> listTiendaObjects;
    final static Logger log = Logger.getLogger(GameManagerImpl.class);

    private GameManagerImpl() {
        this.users = new LinkedList<>();
        this.mapUser = new HashMap<>();
        this.listTiendaObjects = new LinkedList<>();

    }
    //Singleton implementation for the instance of the GameManager
    public static GameManager getInstance(){
        if(instance == null) {
            instance = new GameManagerImpl();
        }
        return instance;
    }
    public List<TiendaObject> listTiendaObjects() {
        return this.listTiendaObjects;
    }
    //Añadir usuario
    @Override
    public int addUser(String password, String name, String surname1, String surname2, String mail, String fecha ) {
        User tmp_user = new User(password, name,surname1,surname2, mail, fecha, 50, "93810981X");
        try{
            mapUser.put(name,tmp_user);
            log.info("User Added: " +tmp_user );
            log.info("Se ha añadido el usuario con name " +tmp_user.getName());
            return 201; //OK CREATED
        }
        catch (IndexOutOfBoundsException e){
            log.error("UserMap Full Error");
            return 507; //INSUFFICIENT STORAGE
        }
        catch (IllegalArgumentException e){
            log.error("Incorrect format exception");
            return 400; //BAD REQUEST
        }
    }

    //Añadir objeto
    @Override
    public int addTiendaObjeto(String name, String descripcion, int coins) {
        TiendaObject tmp_to = new TiendaObject(name,descripcion,coins);
        try{
            mapObject.put(name,tmp_to);
            log.info("Objet Added: " +tmp_to );
            log.info("Se ha añadido el usuario con name " +tmp_to.getName());
            return 201; //OK CREATED
        }
        catch (IndexOutOfBoundsException e){
            log.error("UserMap Full Error");
            return 507; //INSUFFICIENT STORAGE
        }
        catch (IllegalArgumentException e){
            log.error("Incorrect format exception");
            return 400; //BAD REQUEST
        }
    }
    //Listado de usuarios ordenado alfabéticamente
    @Override
    public List<User> getSortedUsersAlphabetical() {
        //Map of Users is not empty

        //prueba
        /*users.sort(Comparator.comparing(User::getName).reversed());
        log.info("Lista de usuarios ordenados alfabeticamente");
        for(User p:this.users)
            log.info(p.getName() + " " + p.getSurname1());
        */
        if(this.mapUser != null) {
            List<User> result = new LinkedList<>(mapUser.values());

            Collections.sort(result, (u1, u2) -> {
                //ToIgnoreCase: To not distinguish between Capital and LowerCase
                return u1.getName().compareToIgnoreCase(u2.getName());
            });
            log.info("List Ordered Alphabetically: " + result.toString());
            return result; //200 OK PETITION
        }
        else
            return null; //404 (Empty Table)
    }


    @Override
    public int addTiendaObject(TiendaObject tiendaObject) {
        int result;
        try {
            this.listTiendaObjects.add(tiendaObject);
            log.info("201: Object Added: " + tiendaObject.getName());
            result = 201;//OK CREATED
        } catch (IllegalArgumentException e) {
            log.error("400: Bad Object parameters");
            result = 400;//BAD REQUEST
        } catch (IndexOutOfBoundsException e) {
            log.error("507: Insufficient Storage");
            result = 507;//INSUFFICIENT STORAGE
        }
        return result;
    }
    @Override
    public int addTiendaObjects(List<TiendaObject> listTiendaObjects) {
        int result;
        try {
            this.listTiendaObjects.addAll(listTiendaObjects);
            log.info("201: Objects Added: " + listTiendaObjects.toString());
            result = 200;//OK Added
        } catch (IllegalArgumentException e) {
            log.error("400: Bad Object parameters");
            result = 400;//BAD REQUEST
        } catch (IndexOutOfBoundsException e) {
            log.error("507: Insufficient Storage");
            result = 507;//INSUFFICIENT STORAGE
        }
        return result;
    }
    //Consultar usuario
    @Override
    public User getUser(String name) {
        User upd_usr = this.mapUser.get(name);
        if(upd_usr!=null){
            log.info("User found: " + upd_usr);
        }else{
            log.error("User not found for ID: "+name);
        }
        return upd_usr;
    }
    @Override
    public int numUsers() {
        return this.mapUser.size();
    }

    @Override
    public List<TiendaObject> ordenarlistaTienda() {
        Collections.sort(listTiendaObjects, new Comparator<TiendaObject>() {
            @Override
            public int compare(TiendaObject t1, TiendaObject t2) {
                return Double.compare(t2.getCoins(), t1.getCoins());
            }
        });
        log.info("Ordenado de menor a mayor precio:");
        for(TiendaObject p:this.listTiendaObjects)
            log.info(p.getName());
        log.info("===================================");
        return this.listTiendaObjects;
    }

    @Override
    public List<User> getUsersList() {
        List<User> result = null;
        if(this.mapUser.size() !=0){
            result = new LinkedList<>(this.mapUser.values());
            log.info("User List: "+result.toString());
        }
        return result; //Null: 404 Empty User HashMap
    }
    //Generate Id
    @Override
    public String generateId(){
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 3) { // length of the random generated ID
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    //Liberar Recursos
    @Override
    public void liberateReserves() {
        this.listTiendaObjects.clear();
        this.mapUser.clear();
    }


    /*public static TiendaManager getInstance() {
        if (instance==null) instance = new TiendaManagerImpl();
        return instance;
    }
    public int size() {
        int ret = this.products.size();
        log.info("size " + ret);

        return ret;
    }
    public void addProduct(Product t) {
        log.info("Nuevo producto: " + t.getName() + " con un coste de " + t.getPrice() + "€");

        this.products.add (t);
        log.info("El producto ha sido añadido");
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
        log.info("Ordenado de menor a mayor precio:");
        for(Product p:this.products)
            log.info(p.getName());
        log.info("===================================");
        return this.products;
    }

    public OrderTO addOrder(OrderTO o,String u){
        o.setId(u);
        this.orders.add(o);
        orderQueue.add(o);
        log.info("Se ha añadido un pedido");
        return o;
    }

    @Override
    public List<Product> getListProductsBySells(){
        products.sort(Comparator.comparingDouble(Product::getNumSells).reversed());
        log.info("Lista de productos ordenado por ventas");
        for(Product p:this.products)
            log.info(p.getName() + " " + p.getNumSells());
        log.info("===================================");
        return products;
    }

    @Override
    public List<OrderTO> listaOrderporUsuario(String u) {
        for(OrderTO o : this.orders){
            if(o.getId()==u){
                for(ProdCantTO prod: o.getListap()) {
                    ordersusuarios.add(o);
                    log.info(o.getId() + " " + prod.getCantidad() + " " + prod.getProd());
                }
            }
        }
        log.info("===================================");
        return this.ordersusuarios;
    }

    public void addProdCantTO(ProdCantTO pc) {
        log.info("new ProdCant " + pc);

        this.prodcants.add (pc);
        log.info("Added " + pc.getCantidad() + " " + pc.getProd());
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
            log.info("Se ha añadido numsells en: " + prod.getProd() + " " + prod.getCantidad() + " veces");
            i= 0;
        }
        log.info("Se ha servido el pedido con ID: " + o.getId());
    }*/

}
