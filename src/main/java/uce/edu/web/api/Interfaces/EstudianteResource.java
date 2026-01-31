package uce.edu.web.api.Interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.Application.EstudianteService;
import uce.edu.web.api.Application.HijoService;
import uce.edu.web.api.Application.represetation.EstudianteRepresentation;
import uce.edu.web.api.Application.represetation.HijoRepresentation;
import uce.edu.web.api.Application.represetation.LinkDto;
import jakarta.ws.rs.Produces;

@Path("/estudiantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;

    @Inject
    private HijoService hijoService;

    @Context
    private UriInfo uriInfo;

    @GET
    public List<EstudianteRepresentation> consultarTodos() {
        return this.estudianteService.listarTodos().stream().map(estudiante -> addLinks(estudiante)).toList();
    }

    @GET
    @Path("/{id}")
    public Response consultarPorId(@PathParam("id") Integer id) {
        EstudianteRepresentation erp = this.estudianteService.consulEstudiantePorId(id);
        if (erp == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(this.addLinks(erp)).build();
    }

    @POST
    public Response guardarEstudiante(EstudianteRepresentation estudiante) {
        estudianteService.crearEstudiante(estudiante);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizarEstudiante(@PathParam("id") Integer id,
            EstudianteRepresentation estudiante) {
        estudianteService.actualizarEstudiante(id, estudiante);
        return Response.ok().build();
    }

    @PATCH
    @Path("/{id}")
    public Response actualizarParcial(@PathParam("id") Integer id,
            EstudianteRepresentation estudiante) {
        this.estudianteService.actualizarEstudianteParcial(id, estudiante);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Integer id) {
        this.estudianteService.eliminarEstudiante(id);
        return Response.noContent().build();
    }

    // ------------------------------------------------------------

    @GET
    @Path("/listarProvincia")
    public List<EstudianteRepresentation> listarPorProvincia(
            @QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {

        return estudianteService.buscarPorProvincia(provincia, genero);
    }

    // ------------------------------------------------------------

    @GET
    @Path("/{id}/hijos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HijoRepresentation> buscarHijosPorEstudiante(@PathParam("id") Integer id) {
        return this.hijoService.buscarPorIdEstudiante(id);
    }

    private EstudianteRepresentation addLinks(EstudianteRepresentation estudiante) {
        String self = this.uriInfo.getBaseUriBuilder().path("estudiantes").path(estudiante.getId().toString()).build()
                .toString();
        String hijos = this.uriInfo.getBaseUriBuilder().path("estudiantes").path(estudiante.getId().toString())
                .path("hijos").build().toString();
        estudiante.setLinks(List.of(new LinkDto(self, "self"), new LinkDto(hijos, "hijos")));
        return estudiante;

    }
}
