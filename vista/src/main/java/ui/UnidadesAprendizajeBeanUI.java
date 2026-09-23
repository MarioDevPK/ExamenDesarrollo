package ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.enterprise.context.*;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("unidadesBeanUI")
@ViewScoped
public class UnidadesAprendizajeBeanUI implements Serializable {

    private String nombreUsuario = "Mtro. Carlos Mendoza";
    private String rolUsuario = "Coordinador Académico";

    private List<UnidadUI> unidades = new ArrayList<>();
    private int siguienteId = 1;

    private UnidadUI unidadEnEdicion;
    private UnidadUI unidadAEliminar;

    private String nombreForm;
    private Integer horasClaseForm = 0;
    private Integer horasTallerForm = 0;
    private Integer horasLaboratorioForm = 0;

    public String getTituloDialogo() {
        return unidadEnEdicion == null ? "Nueva Unidad de Aprendizaje" : "Editar Unidad de Aprendizaje";
    }

    public void prepararEdicion(UnidadUI unidad) {
        unidadEnEdicion = unidad;
        nombreForm = unidad.getNombre();
        horasClaseForm = unidad.getHorasClase();
        horasTallerForm = unidad.getHorasTaller();
        horasLaboratorioForm = unidad.getHorasLaboratorio();
    }

    public String guardarUnidad() {
        if (nombreForm == null || nombreForm.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("formUnidad",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre es obligatorio", null));
            return null;
        }

        int clase = horasClaseForm == null ? 0 : horasClaseForm;
        int taller = horasTallerForm == null ? 0 : horasTallerForm;
        int laboratorio = horasLaboratorioForm == null ? 0 : horasLaboratorioForm;

        if (clase == 0 && taller == 0 && laboratorio == 0) {
            FacesContext.getCurrentInstance().addMessage("formUnidad",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Al menos un tipo de hora debe ser mayor a 0", null));
            return null;
        }

        // TODO: reemplazar por la llamada real a la capa de Negocio
        // (ej. DelegateUnidadAprendizaje.guardar(...)).

        if (unidadEnEdicion == null) {
            UnidadUI nueva = new UnidadUI();
            nueva.setId(siguienteId++);
            nueva.setNombre(nombreForm);
            nueva.setHorasClase(clase);
            nueva.setHorasTaller(taller);
            nueva.setHorasLaboratorio(laboratorio);
            unidades.add(nueva);
        } else {
            unidadEnEdicion.setNombre(nombreForm);
            unidadEnEdicion.setHorasClase(clase);
            unidadEnEdicion.setHorasTaller(taller);
            unidadEnEdicion.setHorasLaboratorio(laboratorio);
        }

        cancelarFormulario();
        return null;
    }

    public String cancelarFormulario() {
        unidadEnEdicion = null;
        nombreForm = null;
        horasClaseForm = 0;
        horasTallerForm = 0;
        horasLaboratorioForm = 0;
        return null;
    }

    public void prepararEliminacion(UnidadUI unidad) {
        unidadAEliminar = unidad;
    }

    public String confirmarEliminacion() {
        if (unidadAEliminar != null) {
            // TODO: reemplazar por la llamada real a la capa de Negocio
            // (ej. DelegateUnidadAprendizaje.eliminar(id)), validando
            // antes que no tenga asignaciones activas.
            unidades.remove(unidadAEliminar);
            unidadAEliminar = null;
        }
        return null;
    }

    public String cancelarEliminacion() {
        unidadAEliminar = null;
        return null;
    }

    public List<UnidadUI> getUnidades() {
        return unidades;
    }

    public String getNombreForm() {
        return nombreForm;
    }

    public void setNombreForm(String nombreForm) {
        this.nombreForm = nombreForm;
    }

    public Integer getHorasClaseForm() {
        return horasClaseForm;
    }

    public void setHorasClaseForm(Integer horasClaseForm) {
        this.horasClaseForm = horasClaseForm;
    }

    public Integer getHorasTallerForm() {
        return horasTallerForm;
    }

    public void setHorasTallerForm(Integer horasTallerForm) {
        this.horasTallerForm = horasTallerForm;
    }

    public Integer getHorasLaboratorioForm() {
        return horasLaboratorioForm;
    }

    public void setHorasLaboratorioForm(Integer horasLaboratorioForm) {
        this.horasLaboratorioForm = horasLaboratorioForm;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public static class UnidadUI implements Serializable {
        private int id;
        private String nombre;
        private int horasClase;
        private int horasTaller;
        private int horasLaboratorio;

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

        public int getHorasClase() {
            return horasClase;
        }

        public void setHorasClase(int horasClase) {
            this.horasClase = horasClase;
        }

        public int getHorasTaller() {
            return horasTaller;
        }

        public void setHorasTaller(int horasTaller) {
            this.horasTaller = horasTaller;
        }

        public int getHorasLaboratorio() {
            return horasLaboratorio;
        }

        public void setHorasLaboratorio(int horasLaboratorio) {
            this.horasLaboratorio = horasLaboratorio;
        }
    }
}
