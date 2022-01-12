package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dtos.OwnersDTO;
import entities.Boat;
import errorhandling.API_Exception;
import facades.HarbourFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("harbour")
public class HarbourResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final HarbourFacade instance = HarbourFacade.getHarbourFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    Boat boat = new Boat();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("owners")
    public String getAllUsers() {
        OwnersDTO o = instance.getAllOwners();
        return GSON.toJson(o);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("create/boat")
    public Response newBoat (String jsonString) throws API_Exception {
        String brand;
        String make;
        String name;
        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            brand = json.get("boatBrand").getAsString();
            boat.setBrand(brand);
            make = json.get("boatMake").getAsString();
            boat.setMake(make);
            name = json.get("boatName").getAsString();
            boat.setName(name);
            instance.createBoat(boat);
        } catch (Exception e) {
            throw new API_Exception("hehe", 400, e);
        }
        return null;
    }

}