package ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.enterprise.context.*;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class ProfesoresBeanUI implements Serializable {

    private String nombreUsuario = "Mtro. Carlos Mendoza";
    private String rolUsuario = "Coordinador Académico";

    private List<ProfesorUI> profesores = new ArrayList<>();
    private int siguienteId = 1;

    private String nombreNuevo;
    private String apellidoPaternoNuevo;
    private String apellidoMaternoNuevo;
    private String rfcNuevo;

    private Integer profesorSeleccionadoId;
    private ProfesorUI profesorABajar;

    private ProfesorUI profesorEnEdicion;
    private String nombreEditar;
    private String apellidoPaternoEditar;
    private String apellidoMaternoEditar;
    private String rfcEditar;

    public String registrarProfesor() {
        if (nombreNuevo == null || nombreNuevo.trim().isEmpty()
                || apellidoPaternoNuevo == null || apellidoPaternoNuevo.trim().isEmpty()
                || apellidoMaternoNuevo == null || apellidoMaternoNuevo.trim().isEmpty()
                || rfcNuevo == null || rfcNuevo.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("formAlta",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Todos los campos son obligatorios", null));
            return null;
        }

        // TODO: reemplazar por la llamada real a la capa de Negocio
        // (ej. DelegateProfesor.registrarProfesor(...)) para validar
        // RFC duplicado y persistir en la base de datos.

        ProfesorUI nuevo = new ProfesorUI();
        nuevo.setId(siguienteId++);
        nuevo.setNombre(nombreNuevo);
        nuevo.setApellidoPaterno(apellidoPaternoNuevo);
        nuevo.setApellidoMaterno(apellidoMaternoNuevo);
        nuevo.setRfc(rfcNuevo.toUpperCase());
        profesores.add(nuevo);

        limpiarFormularioAlta();
        return null;
    }

    public String cancelarAlta() {
        limpiarFormularioAlta();
        return null;
    }

    private void limpiarFormularioAlta() {
        nombreNuevo = null;
        apellidoPaternoNuevo = null;
        apellidoMaternoNuevo = null;
        rfcNuevo = null;
    }

    public String solicitarBaja() {
        if (profesorSeleccionadoId == null) {
            FacesContext.getCurrentInstance().addMessage("formBaja",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecciona un profesor", null));
            return null;
        }
        profesorABajar = buscarPorId(profesorSeleccionadoId);
        return null;
    }

    public String confirmarBaja() {
        if (profesorABajar != null) {
            // TODO: reemplazar por la llamada real a la capa de Negocio
            // (ej. DelegateProfesor.darDeBaja(id)).
            profesores.remove(profesorABajar);
        }
        profesorABajar = null;
        profesorSeleccionadoId = null;
        return null;
    }

    public String cancelarBaja() {
        profesorABajar = null;
        profesorSeleccionadoId = null;
        return null;
    }

    public void prepararEdicion(ProfesorUI profesor) {
        profesorEnEdicion = profesor;
        nombreEditar = profesor.getNombre();
        apellidoPaternoEditar = profesor.getApellidoPaterno();
        apellidoMaternoEditar = profesor.getApellidoMaterno();
        rfcEditar = profesor.getRfc();
    }

    public String guardarEdicion() {
        if (nombreEditar == null || nombreEditar.trim().isEmpty()
                || apellidoPaternoEditar == null || apellidoPaternoEditar.trim().isEmpty()
                || apellidoMaternoEditar == null || apellidoMaternoEditar.trim().isEmpty()
                || rfcEditar == null || rfcEditar.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("formEditar",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Todos los campos son obligatorios", null));
            return null;
        }

        // TODO: reemplazar por la llamada real a la capa de Negocio
        // (ej. DelegateProfesor.actualizarProfesor(...)).

        profesorEnEdicion.setNombre(nombreEditar);
        profesorEnEdicion.setApellidoPaterno(apellidoPaternoEditar);
        profesorEnEdicion.setApellidoMaterno(apellidoMaternoEditar);
        profesorEnEdicion.setRfc(rfcEditar.toUpperCase());

        cancelarEdicion();
        return null;
    }

    public String cancelarEdicion() {
        profesorEnEdicion = null;
        nombreEditar = null;
        apellidoPaternoEditar = null;
        apellidoMaternoEditar = null;
        rfcEditar = null;
        return null;
    }

    private ProfesorUI buscarPorId(int id) {
        for (ProfesorUI p : profesores) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<ProfesorUI> getProfesores() {
        return profesores;
    }

    public String getNombreNuevo() {
        return nombreNuevo;
    }

    public void setNombreNuevo(String nombreNuevo) {
        this.nombreNuevo = nombreNuevo;
    }

    public String getApellidoPaternoNuevo() {
        return apellidoPaternoNuevo;
    }

    public void setApellidoPaternoNuevo(String apellidoPaternoNuevo) {
        this.apellidoPaternoNuevo = apellidoPaternoNuevo;
    }

    public String getApellidoMaternoNuevo() {
        return apellidoMaternoNuevo;
    }

    public void setApellidoMaternoNuevo(String apellidoMaternoNuevo) {
        this.apellidoMaternoNuevo = apellidoMaternoNuevo;
    }

    public String getRfcNuevo() {
        return rfcNuevo;
    }

    public void setRfcNuevo(String rfcNuevo) {
        this.rfcNuevo = rfcNuevo;
    }

    public Integer getProfesorSeleccionadoId() {
        return profesorSeleccionadoId;
    }

    public void setProfesorSeleccionadoId(Integer profesorSeleccionadoId) {
        this.profesorSeleccionadoId = profesorSeleccionadoId;
    }

    public String getNombreEditar() {
        return nombreEditar;
    }

    public void setNombreEditar(String nombreEditar) {
        this.nombreEditar = nombreEditar;
    }

    public String getApellidoPaternoEditar() {
        return apellidoPaternoEditar;
    }

    public void setApellidoPaternoEditar(String apellidoPaternoEditar) {
        this.apellidoPaternoEditar = apellidoPaternoEditar;
    }

    public String getApellidoMaternoEditar() {
        return apellidoMaternoEditar;
    }

    public void setApellidoMaternoEditar(String apellidoMaternoEditar) {
        this.apellidoMaternoEditar = apellidoMaternoEditar;
    }

    public String getRfcEditar() {
        return rfcEditar;
    }

    public void setRfcEditar(String rfcEditar) {
        this.rfcEditar = rfcEditar;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public static class ProfesorUI implements Serializable {
        private int id;
        private String nombre;
        private String apellidoPaterno;
        private String apellidoMaterno;
        private String rfc;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellidoPaterno() {
            return apellidoPaterno;
        }

        public void setApellidoPaterno(String apellidoPaterno) {
            this.apellidoPaterno = apellidoPaterno;
        }

        public String getApellidoMaterno() {
            return apellidoMaterno;
        }

        public void setApellidoMaterno(String apellidoMaterno) {
            this.apellidoMaterno = apellidoMaterno;
        }

        public String getRfc() {
            return rfc;
        }

        public void setRfc(String rfc) {
            this.rfc = rfc;
        }

        public String getNombreCompleto() {
            return nombre + " " + apellidoPaterno + " " + apellidoMaterno;
        }
    }
}
