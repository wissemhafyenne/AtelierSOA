package restApi;
import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
@Path("/logement")
public class LogementRessources {
    LogementBusiness help = new LogementBusiness();
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(help.getLogements())
                .build();
    }

    // GET by PathParam
    @GET
    @Path("/get/{ref}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByReference(@PathParam("ref") int ref) {
        Logement logement = help.getLogementByReference(ref);
        if (logement != null) {
            return Response.ok(logement).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Logement not found").build();
    }


    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByGouvernorat(@QueryParam("gouvernorat") String gouvernorat) {
        List<Logement> filtered = help.getLogementsByGouvernorat(gouvernorat);
        return Response.ok(filtered).build();
    }


    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement logement) {
        help.addLogement(logement);
        return Response.status(Response.Status.CREATED).entity("Logement added").build();
    }

    // PUT – Modifier
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateLogement(Logement logement) {
        boolean updated = help.updateLogement(logement);
        if (updated) {
            return Response.ok("Logement updated").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Logement not found").build();
    }


    @DELETE
    @Path("/delete/{ref}")
    public Response deleteLogement(@PathParam("ref") int ref) {
        boolean deleted = help.deleteLogement(ref);
        if (deleted) {
            return Response.ok("Logement deleted").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Logement not found").build();
    }
}
