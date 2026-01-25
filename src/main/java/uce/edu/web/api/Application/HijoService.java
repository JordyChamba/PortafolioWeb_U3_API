package uce.edu.web.api.Application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.Domain.Hijo;
import uce.edu.web.api.Infracture.HijoRepository;

@ApplicationScoped
public class HijoService {

    @Inject
    private HijoRepository hijoRepository;

    public List<Hijo> buscarPorIdEstudiante(Integer idEstudiante) {
        return this.hijoRepository.buscarPorIdEstudiante(idEstudiante);
    }
}
