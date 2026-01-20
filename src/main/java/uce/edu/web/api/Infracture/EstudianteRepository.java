package uce.edu.web.api.Infracture;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.Domain.Estudiante;

@ApplicationScoped
public class EstudianteRepository implements PanacheRepository<Estudiante> {

}
