package restApi;
import entities.RendezVous;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/rendezvous")
public class RendezVousRessources {

    private static List<RendezVous> rendezVousList = new ArrayList<>();


    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(rendezVousList).build();
    }


    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        for (RendezVous r : rendezVousList) {
            if (r.getId() == id) {
                return Response.ok(r).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Rendez-vous not found").build();
    }


    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByDate(@QueryParam("date") String date) {
        List<RendezVous> results = new ArrayList<>();
        for (RendezVous r : rendezVousList) {
            if (r.getDate().equalsIgnoreCase(date)) {
                results.add(r);
            }
        }
        return Response.ok(results).build();
    }


    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRendezVous(RendezVous r) {
        rendezVousList.add(r);
        return Response.status(Response.Status.CREATED).entity("Rendez-vous added").build();
    }


    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRendezVous(RendezVous updated) {
        for (int i = 0; i < rendezVousList.size(); i++) {
            if (rendezVousList.get(i).getId() == updated.getId()) {
                rendezVousList.set(i, updated);
                return Response.ok("Rendez-vous updated").build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Rendez-vous not found").build();
    }


    @DELETE
    @Path("/delete/{id}")
    public Response deleteRendezVous(@PathParam("id") int id) {
        Iterator<RendezVous> iterator = rendezVousList.iterator();
        while (iterator.hasNext()) {
            RendezVous r = iterator.next();
            if (r.getId() == id) {
                iterator.remove();
                return Response.ok("Rendez-vous deleted").build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Rendez-vous not found").build();
    }
}
