package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//Models or Element Entity
//Swagger Imports
@Api(value = "/users", description = "Endpoint to User Service")
@Path("/users")
public class UsersService {
    static final Logger logger = Logger.getLogger(UsersService.class);
    private GameManager manager;

    public UsersService(){
        //Configuring Log4j, location of the log4j.properties file and must always be inside the src folder
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        this.manager = GameManagerImpl.getInstance();
        if (this.manager.numUsers() == 0) {
            //Adding Users
            this.manager.addUser("001","Adrian","Gonz", "Blanc", "adri@gmail.com", "01/10/1998");

        }
    }
    @GET
    @ApiOperation(value = "Get all Users", notes = "Retrieves the list of Users")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/listUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        List<User> user = this.manager.getUsersList();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(user) {};
        return Response.status(201).entity(entity).build()  ;
    }
    //Añadir un usuario
    //Adds a new user given multiple parameters(
    @POST
    @ApiOperation(value = "create a new User", notes = "Adds a new user given name, surnames and mail")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/addUser/{name}/{surname1}/{surname2}/{mail}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newUser(@PathParam("name") String name,@PathParam("surname1") String surname1,@PathParam("surname2") String surname2,@PathParam("mail") String mail, @PathParam("fecha") String fecha ) {
        if (name.isEmpty() || surname1.isEmpty() || surname2.isEmpty() || mail.isEmpty() || fecha.isEmpty())  return Response.status(500).entity(new User()).build();
        String temp_id = manager.generateId();
        this.manager.addUser(temp_id,name,surname1,surname2,mail, fecha);
        return Response.status(201).entity(manager.getUser(temp_id)).build();
    }

    //Usuarios ordenados
    @GET
    @ApiOperation(value = "get all users sorted", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsSorted() {

        List<User> users = this.manager.getSortedUsersAlphabetical();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build()  ;

    }
    /*private TiendaManager pm;

    public UsersService() {
        this.pm = TiendaManagerImpl.getInstance();
        if (pm.size()==0) {
            this.pm.addProduct("pera", 1.5);
            this.pm.addProduct("pepsi", 2);
            this.pm.addProduct("cheetos", 5);
            this.pm.addProduct("agua", 1);
            this.pm.addProduct("galletas", 4);
        }


    }

    @GET
    @ApiOperation(value = "get all products sorted", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsSorted() {

        List<Product> products = this.pm.ordenarlistaProductos();

        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(products) {};
        return Response.status(201).entity(entity).build()  ;

    }

    /*@GET
    @ApiOperation(value = "get a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrack(@PathParam("id") String id) {
        Product t = this.pm.get(id);
        if (t == null) return Response.status(404).build();
        else  return Response.status(201).entity(t).build();
    }

    @DELETE
    @ApiOperation(value = "delete a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    public Response deleteTrack(@PathParam("id") String id) {
        Track t = this.pm.getTrack(id);
        if (t == null) return Response.status(404).build();
        else this.pm.deleteTrack(id);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "update a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    public Response updateTrack(Track track) {

        Track t = this.pm.updateTrack(track);

        if (t == null) return Response.status(404).build();

        return Response.status(201).build();
    }*/


    /*@POST
    @ApiOperation(value = "create a new order", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= TiendaObject.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/orders")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newOrder(OrderTO order) {
        System.out.println(order.getId() + " " + order.getListap());
        if (order.getListap()==null || order.getId()==null)  return Response.status(500).entity(order).build();
        this.pm.addOrder(order, RandomUtils.getId());
        return Response.status(201).entity(order).build();
    }*/
}
