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
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.Application.EstudianteService;
import uce.edu.web.api.Application.HijoService;
import uce.edu.web.api.Application.represetation.EstudianteRepresentation;
import uce.edu.web.api.Domain.Hijo;
import jakarta.ws.rs.Produces;

@Path("/estudiantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;

    @Inject
    private HijoService hijoService;

    @GET
    public List<EstudianteRepresentation> consultarTodos() {
        return estudianteService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public EstudianteRepresentation consultarPorId(@PathParam("id") Integer id) {
        return estudianteService.consulEstudiantePorId(id);
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
    public void actualizarParcial(@PathParam("id") Integer id,
            EstudianteRepresentation estudiante) {
        estudianteService.actualizarEstudianteParcial(id, estudiante);
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Integer id) {
        estudianteService.eliminarEstudiante(id);
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
    public List<Hijo> buscarHijosPorEstudiantes(@PathParam("id") Integer id) {
        return this.hijoService.buscarPorIdEstudiante(id);
    }

}
