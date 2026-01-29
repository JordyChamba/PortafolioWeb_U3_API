package uce.edu.web.api.Application.represetation;

import java.time.LocalDateTime;
import java.util.List;

public class EstudianteRepresentation {

    private Integer id;
    private String Nombre;
    private String Apellido;
    private LocalDateTime fechaNacimiento;
    private String provincia;
    private String genero;
    private List<HijoRepresentation> hijos;
    private List<LinkDto> links;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public List<HijoRepresentation> getHijos() {
        return hijos;
    }

    public void setHijos(List<HijoRepresentation> hijos) {
        this.hijos = hijos;
    }

    public List<LinkDto> getLinks() {
        return links;
    }

    public void setLinks(List<LinkDto> links) {
        this.links = links;
    }
}
