package edu.upc.dsa;

import edu.upc.dsa.models.TiendaObject;
import edu.upc.dsa.models.User;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;


public class GameManagerTest {
    //Log4j Logger initialization
    final static Logger logger = Logger.getLogger(GameManagerTest.class);
    public GameManager manager = null;

    //Estructura de datos
    User user;
    List<TiendaObject> listTiendaObjects;

    //Metodo SetUp
    @Before
    public void setUp() {
        //Configuring Log4j
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        logger.debug("Debug Test Message!");
        logger.info("Info Test Message!");
        logger.warn("Warning Test Message!");
        logger.error("Error Test Message!");
        //Instancing GameManager Implementation
        manager = GameManagerImpl.getInstance();
        //Initializing Object List
        listTiendaObjects =  new LinkedList<TiendaObject>();
        //Initialzing Test User
        user = new User("xyz", "Oscar", "Planas", "Lara", "oscar@gmail.com", "21/01/2021", 50, "9189498H");
        //Appending data to Object List
        listTiendaObjects.add(new TiendaObject("001", "Sword", 80));
        listTiendaObjects.add(new TiendaObject("002", "Shield", 100));
        listTiendaObjects.add(new TiendaObject("003", "Bow", 70));
        //Adding Objects list to Game Manager
        manager.addTiendaObjects(listTiendaObjects);
    }
    //Tests
    //Metodo Test para añadir un usuario en el sistema y verificar el número de usuarios
    @Test
    public void addUserTest(){
        //Initial Test, initial users in game Zero!
        Assert.assertEquals(0, this.manager.numUsers());
        //Adding a user to the TiendaManager
        manager.addUser(user.getId(),user.getName(),user.getSurname1(),user.getSurname2(),user.getMail(), user.getFecha());
        Assert.assertEquals(1, manager.numUsers());
        //Adding a second user to the TiendaManager
        manager.addUser("abc","CArlos","ARg", "Zz", "carlos@gmail.com", "30/04/1960");
        Assert.assertEquals(2, manager.numUsers());
        manager.getSortedUsersAlphabetical();
    }

    @Test
    public void addObjectTest(){
        List<TiendaObject> l = manager.listTiendaObjects();

        manager.ordenarlistaTienda();
        /*//Setting up with 1 Test User
        manager.addUser(user.getId(),user.getName(),user.getSurname1(),user.getSurname2(),user.getMail(), user.getFecha());
        //Test for the objects the test user has equals 0 as setUp method
        Assert.assertEquals(0, manager.getNumGameObjectsUser(user.getId()));
        //Adding an object to the User passing a id of the Object, Expects http 201 Ok
        Assert.assertEquals(201,manager.addUserGameObject(user.getId(), listGameObjects.get(0).getId()));
        //Test if the number of objects inside Test User has increased to 1
        Assert.assertEquals(1, manager.getNumGameObjectsUser(user.getId()));*/
    }
    //Metodo Teardown
    @After
    public void tearDown() {
        manager.liberateReserves();
    }

}