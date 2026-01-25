package uce.edu.web.api.Application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.Domain.Estudiante;
import uce.edu.web.api.Infracture.EstudianteRepository;

@ApplicationScoped
public class EstudianteService {
    @Inject
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> listarTodos() {
        return this.estudianteRepository.findAll().list();
    }

    public Estudiante listarPorId(Integer id) {
        return this.estudianteRepository.findById(id.longValue());
    }

    @Transactional
    public void crearEstudiante(Estudiante estudiante) {
        this.estudianteRepository.persist(estudiante);
    }

    @Transactional
    public void actualizarEstudiante(Integer id, Estudiante estudiante) {
        Estudiante estu = this.listarPorId(id);
        estu.setNombre(estudiante.getNombre());
        estu.setApellido(estudiante.getApellido());
        estu.setFechaNacimiento(estudiante.getFechaNacimiento());
        estu.setGenero(estudiante.getGenero());
        estu.setProvincia(estudiante.getProvincia());
    }

    @Transactional
    public void actualizarParcialEstudiante(Integer id, Estudiante estudiante) {
        Estudiante estu = this.listarPorId(id);
        if (estudiante.getNombre() == null) {
            estu.setNombre(estudiante.getNombre());
        }
        if (estudiante.getApellido() == null) {
            estu.setApellido(estudiante.getApellido());
        }
        if (estudiante.getFechaNacimiento() == null) {
            estu.setFechaNacimiento(estudiante.getFechaNacimiento());
        }
        if (estudiante.getGenero() == null) {
            estu.setGenero(estudiante.getGenero());
        }
        if (estudiante.getProvincia() == null) {
            estu.setProvincia(estudiante.getProvincia());
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        this.estudianteRepository.deleteById(id.longValue());
    }

    public List<Estudiante> listarPorProvincia(String provincia, String genero) {
        return this.estudianteRepository.find("provincia =?1 and genero =? 2", provincia, genero).list();
    }
}
