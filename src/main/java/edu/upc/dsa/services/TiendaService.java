package edu.upc.dsa.services;
import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.TiendaObject;
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
@Api(value = "/objects", description = "Endpoint to Object Service")
@Path("/objects")
public class TiendaService {
    static final Logger logger = Logger.getLogger(UsersService.class);
    private GameManager manager;

    public TiendaService(){
        //Configuring Log4j, location of the log4j.properties file and must always be inside the src folder
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        this.manager = GameManagerImpl.getInstance();
        if (this.manager.numUsers() == 0) {
            //Adding objects
            this.manager.addTiendaObjeto("001", "ObjetoAtaque", 40);

        }
    }
    //Objetos ordenados
    @GET
    @ApiOperation(value = "get all objects sorted", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response ordenarListaTienda() {

        List<TiendaObject> objetos = this.manager.ordenarlistaTienda();

        GenericEntity<List<TiendaObject>> entity = new GenericEntity<List<TiendaObject>>(objetos) {};
        return Response.status(201).entity(entity).build()  ;

    }
}
