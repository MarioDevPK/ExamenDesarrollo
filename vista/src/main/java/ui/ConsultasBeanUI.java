package ui;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.*;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import mx.desarrollo.entity.AsignacionUnidad;
import mx.desarrollo.entity.Profesor;
import mx.desarrollo.integration.ServiceFacadeLocator;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Named("consultasBeanUI")
@ViewScoped
public class ConsultasBeanUI implements Serializable {

    private String nombreUsuario = "Mtro. Carlos Mendoza";
    private String rolUsuario = "Coordinador Académico";

    private String filtro;
    private List<ProfesorConsultaUI> profesores = new ArrayList<>();

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    private void cargarDatos() {
        List<Profesor> profesoresEntity = ServiceFacadeLocator.getInstanceFacadeProfesor()
                .consultarProfesoresConAsignaciones();

        profesores = new ArrayList<>();
        for (Profesor p : profesoresEntity) {
            ProfesorConsultaUI item = new ProfesorConsultaUI(
                    p.getNombreProfesor() + " " + p.getApellidoPaterno() + " " + p.getApellidoMaterno(),
                    p.getRfc());

            for (AsignacionUnidad a : p.getAsignacionUnidads()) {
                item.getAsignaciones().add(new AsignacionUI(
                        a.getIdUnidad().getNombre(),
                        a.getDiaClases(),
                        a.getHoraInicio(),
                        a.getHoraFinalizacion()));
            }
            profesores.add(item);
        }
    }

    public List<ProfesorConsultaUI> getProfesoresFiltrados() {
        if (filtro == null || filtro.trim().isEmpty()) {
            return profesores;
        }
        String texto = filtro.toLowerCase();
        List<ProfesorConsultaUI> resultado = new ArrayList<>();
        for (ProfesorConsultaUI p : profesores) {
            if (p.getNombreCompleto().toLowerCase().contains(texto) || p.getRfc().toLowerCase().contains(texto)) {
                resultado.add(p);
                continue;
            }
            for (AsignacionUI a : p.getAsignaciones()) {
                if (a.getMateria().toLowerCase().contains(texto)) {
                    resultado.add(p);
                    break;
                }
            }
        }
        return resultado;
    }

    public int getTotalDocentes() {
        return profesores.size();
    }

    public int getTotalHoras() {
        int total = 0;
        for (ProfesorConsultaUI p : profesores) {
            total += p.getHorasSemanaInt();
        }
        return total;
    }

    public String getFiltro() { return filtro; }
    public void setFiltro(String filtro) { this.filtro = filtro; }
    public String getNombreUsuario() { return nombreUsuario; }
    public String getRolUsuario() { return rolUsuario; }

    public static class ProfesorConsultaUI implements Serializable {
        private String nombreCompleto;
        private String rfc;
        private List<AsignacionUI> asignaciones = new ArrayList<>();

        public ProfesorConsultaUI(String nombreCompleto, String rfc) {
            this.nombreCompleto = nombreCompleto;
            this.rfc = rfc;
        }

        public String getNombreCompleto() { return nombreCompleto; }
        public String getRfc() { return rfc; }
        public List<AsignacionUI> getAsignaciones() { return asignaciones; }

        public String getIniciales() {
            String[] partes = nombreCompleto.replaceAll("^(Dr\\.|Dra\\.|Mtro\\.|Mtra\\.|Ing\\.)\\s*", "").split(" ");
            String resultado = "";
            for (int i = 0; i < partes.length && resultado.length() < 2; i++) {
                if (!partes[i].isEmpty()) resultado += partes[i].charAt(0);
            }
            return resultado.toUpperCase();
        }

        public int getHorasSemanaInt() {
            int total = 0;
            for (AsignacionUI a : asignaciones) total += a.getHoras();
            return total;
        }

        public String getHorasSemana() { return String.valueOf(getHorasSemanaInt()); }
    }

    public static class AsignacionUI implements Serializable {
        private String materia;
        private String diasHorario;
        private int horas;

        public AsignacionUI(String materia, String diaClases, LocalTime horaInicio, LocalTime horaFinalizacion) {
            this.materia = materia;
            this.diasHorario = diaClases + " • " + horaInicio + " - " + horaFinalizacion;
            this.horas = (int) Duration.between(horaInicio, horaFinalizacion).toHours();
        }

        public String getMateria() { return materia; }
        public String getDiasHorario() { return diasHorario; }
        public int getHoras() { return horas; }
    }
}
