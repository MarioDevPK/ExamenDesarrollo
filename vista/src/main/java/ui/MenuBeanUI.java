package ui;

import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class MenuBeanUI implements Serializable {

    private String nombreUsuario;
    private String rolUsuario;

    public String irAProfesores() {
        return "profesores?faces-redirect=true";
    }

    public String irAUnidadesAprendizaje() {
        return "unidadesAprendizaje?faces-redirect=true";
    }

    public String irAAsignaciones() {
        return "asignaciones?faces-redirect=true";
    }

    public String irAConsultas() {
        return "consultas?faces-redirect=true";
    }

    public String cerrarSesion() {
        // TODO: invalidar la sesión (ExternalContext.invalidateSession())
        return "login?faces-redirect=true";
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }
}
