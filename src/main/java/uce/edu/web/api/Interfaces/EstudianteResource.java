package uce.edu.web.api.Interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import uce.edu.web.api.Application.EstudianteService;
import uce.edu.web.api.Domain.Estudiante;

@Path("/estudiantes")
public class EstudianteResource {
    @Inject
    private EstudianteService estudianteService;

    @GET
    @Path("/todos")
    public List<Estudiante> consultarTodos() {
        return this.estudianteService.listarTodos();
    }
}
