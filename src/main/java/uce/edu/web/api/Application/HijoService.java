package uce.edu.web.api.Application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.Application.represetation.HijoRepresentation;
import uce.edu.web.api.Domain.Hijo;
import uce.edu.web.api.Infracture.HijoRepository;

@ApplicationScoped
public class HijoService {

    @Inject
    private HijoRepository hijoRepository;

    public List<HijoRepresentation> buscarPorIdEstudiante(Integer idEstudiante) {
        List<HijoRepresentation> lista = new ArrayList<>();

        for (Hijo hijo : this.hijoRepository.buscarPorIdEstudiante(idEstudiante)) {
            lista.add(this.mapperToHR(hijo));
        }

        return lista;
    }

    private HijoRepresentation mapperToHR(Hijo hijo) {
        HijoRepresentation hijoRepresentation = new HijoRepresentation();
        hijoRepresentation.setId(hijo.getId());
        hijoRepresentation.setNombre(hijo.getNombre());
        hijoRepresentation.setApellido(hijo.getApellido());
        return hijoRepresentation;
    }
}
