package uce.edu.web.api.Application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.Application.represetation.EstudianteRepresentation;
import uce.edu.web.api.Domain.Estudiante;
import uce.edu.web.api.Infracture.EstudianteRepository;

@ApplicationScoped

public class EstudianteService {

    @Inject
    EstudianteRepository estudianteRepository;

    public List<EstudianteRepresentation> listarTodos() {
        List<EstudianteRepresentation> listaEstudiantesR = new ArrayList<>();
        for (Estudiante est : estudianteRepository.listAll()) {
            listaEstudiantesR.add(this.mapperToER(est));
        }
        return listaEstudiantesR;
    }

    public EstudianteRepresentation consulEstudiantePorId(Integer id) {
        return this.mapperToER(this.estudianteRepository.findById(id.longValue()));
    }

    @Transactional
    public void crearEstudiante(EstudianteRepresentation estu) {
        estudianteRepository.persist(this.mapperToEstudiante(estu));
    }

    @Transactional
    public void actualizarEstudiante(Integer id, EstudianteRepresentation estudiante) {
        Estudiante estu = this.mapperToEstudiante(this.consulEstudiantePorId(id));
        estu.setNombre(estudiante.getNombre());
        estu.setApellido(estudiante.getApellido());
        estu.setFechaNacimiento(estudiante.getFechaNacimiento());
        estu.setGenero(estudiante.getGenero());
        estu.setProvincia(estudiante.getProvincia());
        // se actualiza automaticamente por dirty checking
    }

    @Transactional
    public void actualizarEstudianteParcial(Integer id, EstudianteRepresentation estudiante) {
        Estudiante estu = this.mapperToEstudiante(this.consulEstudiantePorId(id));
        if (estudiante.getNombre() != null) {
            estu.setNombre(estudiante.getNombre());
        }
        if (estudiante.getApellido() != null) {
            estu.setApellido(estudiante.getApellido());
        }
        if (estudiante.getFechaNacimiento() != null) {
            estu.setFechaNacimiento(estudiante.getFechaNacimiento());
        }
        if (estudiante.getGenero() != null) {
            estu.setGenero(estudiante.getGenero());
        }
        if (estudiante.getProvincia() != null) {
            estu.setProvincia(estudiante.getProvincia());
        }
        // se actualiza automaticamente por dirty checking
    }

    @Transactional
    public void eliminarEstudiante(Integer id) {
        estudianteRepository.deleteById(id.longValue());
    }

    /*
     * public List<Estudiante> buscarPorProvincia(String provincia) {
     * return estudianteRepository.find("provincia", provincia).list();
     * }
     */

    public List<EstudianteRepresentation> buscarPorProvincia(String provincia, String genero) {
        List<EstudianteRepresentation> listaEstudiantesR = new ArrayList<>();
        for (Estudiante estu : estudianteRepository.find("provincia = ?1 and genero = ?2", provincia, genero).list()) {
            listaEstudiantesR.add(this.mapperToER(estu));
        }
        return listaEstudiantesR;
    }

    private EstudianteRepresentation mapperToER(Estudiante estudiante) {
        EstudianteRepresentation estudianteRepresentation = new EstudianteRepresentation();
        estudianteRepresentation.setId(estudiante.getId());
        estudianteRepresentation.setNombre(estudiante.getNombre());
        estudianteRepresentation.setApellido(estudiante.getApellido());
        estudianteRepresentation.setFechaNacimiento(estudiante.getFechaNacimiento());
        estudianteRepresentation.setProvincia(estudiante.getProvincia());
        estudianteRepresentation.setGenero(estudiante.getGenero());
        return estudianteRepresentation;

    }

    private Estudiante mapperToEstudiante(EstudianteRepresentation estudiante) {
        Estudiante estudiante1 = new Estudiante();
        estudiante1.setId(estudiante.getId());
        estudiante1.setNombre(estudiante.getNombre());
        estudiante1.setApellido(estudiante.getApellido());
        estudiante1.setFechaNacimiento(estudiante.getFechaNacimiento());
        estudiante1.setProvincia(estudiante.getProvincia());
        estudiante1.setGenero(estudiante.getGenero());
        return estudiante1;

    }

}