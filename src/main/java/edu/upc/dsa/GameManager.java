package edu.upc.dsa;

import edu.upc.dsa.models.User;
import edu.upc.dsa.models.TiendaObject;


import java.util.List;

public interface GameManager {

    //Añadir un usuario
    int addUser(String password , String name, String surname1, String surname2, String mail, String fechanacimiento);
    //Consultar información de un usuario
    User getUser(String id);
    //Consultar número de un usuarios que hay en el sistema
    int numUsers();
    //listado de usuarios ordenado alfaticamente
    List<User> getSortedUsersAlphabetical();

    public List<TiendaObject> listTiendaObjects();
    //Consultar la lista de usuarios
    List<User> getUsersList();
    //Añadir un Objeto
    int addTiendaObject(TiendaObject tiendaObject);
    //Añadir una lista de Objetos
    int addTiendaObjects(List<TiendaObject> listTiendaObjects);
    //Lista Tienda Ordenada
    public List<TiendaObject> ordenarlistaTienda();
    //Generate Id
    String generateId();
    //Liberar Recursos
    void liberateReserves();

    int addTiendaObjeto(String nombre, String descripcion, int coins);
}