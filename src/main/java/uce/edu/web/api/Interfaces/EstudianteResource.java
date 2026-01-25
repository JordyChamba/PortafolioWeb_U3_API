package uce.edu.web.api.Interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import uce.edu.web.api.Application.EstudianteService;
import uce.edu.web.api.Domain.Estudiante;

@Path("/estudiantes")
public class EstudianteResource {
    @Inject
    private EstudianteService estudianteService;

    @GET
    @Path("/")
    public List<Estudiante> consultarTodos() {
        return this.estudianteService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Estudiante consultarTodosId(@PathParam("id") Integer id) {
        return this.estudianteService.listarPorId(id);
    }

    @POST
    @Path("/")
    public void guardarEstudiante(Estudiante estudiante) {
        this.estudianteService.crearEstudiante(estudiante);
    }

    @PUT
    @Path("/{id}")
    public void actualizarEstudiante(@PathParam("id") Integer id, Estudiante estudiante) {
        this.estudianteService.actualizarEstudiante(id, estudiante);
    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcialEstudiante(@PathParam("id") Integer id, Estudiante estudiante) {
        this.estudianteService.actualizarParcialEstudiante(id, estudiante);
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Integer id) {
        this.estudianteService.eliminar(id);
    }

    // ------------------------------------------------------------

    @GET
    @Path("/listarProvincia")
    public List<Estudiante> listarPorProvincia(@QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {
        return this.estudianteService.listarPorProvincia(provincia, genero);
    }

}
