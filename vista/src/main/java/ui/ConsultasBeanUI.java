package ui;

import jakarta.enterprise.context.*;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Named("consultasBeanUI")
@ViewScoped
public class ConsultasBeanUI implements Serializable {

    private String nombreUsuario = "Mtro. Carlos Mendoza";
    private String rolUsuario = "Coordinador Académico";

    private String filtro;
    private List<ProfesorConsultaUI> profesores = new ArrayList<>();

    public ConsultasBeanUI() {
        cargarDatosDemo();
        profesores.sort(Comparator.comparing(ProfesorConsultaUI::getNombreCompleto));
    }

    // TODO: reemplazar cargarDatosDemo() por la llamada real a la
    // capa de Negocio (ej. DelegateConsulta.obtenerCargaAcademica()).
    private void cargarDatosDemo() {
        ProfesorConsultaUI lucia = new ProfesorConsultaUI("Dra. Lucía Ramos", "Ciencias Computacionales");
        lucia.getAsignaciones().add(new AsignacionUI("Inteligencia Artificial", "Lun, Mié • 08:00 - 10:00", "Edif. T - Lab 4", 8));
        lucia.getAsignaciones().add(new AsignacionUI("Redes Neuronales Avanzadas", "Mar, Jue • 11:00 - 14:00", "Edif. C - Aula 204", 6));
        profesores.add(lucia);

        ProfesorConsultaUI fernando = new ProfesorConsultaUI("Dr. Fernando Ruiz", "Electrónica");
        fernando.getAsignaciones().add(new AsignacionUI("Arquitectura de Computadoras", "Lun, Mié, Vie • 07:00 - 09:00", "Edif. E - Aula 102", 6));
        fernando.getAsignaciones().add(new AsignacionUI("Sistemas Embebidos", "Vie • 14:00 - 20:00", "Edif. T - Lab Hardware", 6));
        profesores.add(fernando);

        profesores.add(new ProfesorConsultaUI("Ing. Roberto Gómez", "Matemáticas"));

        ProfesorConsultaUI sofia = new ProfesorConsultaUI("Ing. Sofía Reyes", "Sistemas de Información");
        sofia.getAsignaciones().add(new AsignacionUI("Bases de Datos Relacionales", "Lun, Mié • 10:00 - 13:00", "Edif. D - Lab 1", 6));
        sofia.getAsignaciones().add(new AsignacionUI("Gestión de Bases NoSQL", "Mar, Jue • 08:00 - 11:00", "Edif. D - Lab 3", 6));
        sofia.getAsignaciones().add(new AsignacionUI("Taller de Big Data", "Vie • 08:00 - 12:00", "Edif. D - Lab 1", 4));
        profesores.add(sofia);

        ProfesorConsultaUI carlos = new ProfesorConsultaUI("Mtro. Carlos Mendoza", "Coordinación Académica");
        carlos.getAsignaciones().add(new AsignacionUI("Ingeniería de Software", "Mar, Jue • 07:00 - 10:00", "Edif. B - Aula 108", 6));
        carlos.getAsignaciones().add(new AsignacionUI("Gestión de Proyectos", "Vie • 16:00 - 20:00", "Edif. B - Aula 112", 4));
        profesores.add(carlos);

        ProfesorConsultaUI elena = new ProfesorConsultaUI("Mtra. Elena Vázquez", "Ciencias Computacionales");
        elena.getAsignaciones().add(new AsignacionUI("Estructuras de Datos y Algoritmos", "Lun, Mié • 12:00 - 15:00", "Edif. C - Lab 2", 6));
        elena.getAsignaciones().add(new AsignacionUI("Programación Orientada a Objetos", "Jue • 14:00 - 18:00", "Edif. C - Aula 201", 4));
        profesores.add(elena);
    }

    public List<ProfesorConsultaUI> getProfesoresFiltrados() {
        if (filtro == null || filtro.trim().isEmpty()) {
            return profesores;
        }
        String texto = filtro.toLowerCase();
        List<ProfesorConsultaUI> resultado = new ArrayList<>();
        for (ProfesorConsultaUI p : profesores) {
            if (p.getNombreCompleto().toLowerCase().contains(texto)) {
                resultado.add(p);
                continue;
            }
            for (AsignacionUI a : p.getAsignaciones()) {
                if (a.getMateria().toLowerCase().contains(texto) || a.getAula().toLowerCase().contains(texto)) {
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

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public static class ProfesorConsultaUI implements Serializable {
        private String nombreCompleto;
        private String area;
        private List<AsignacionUI> asignaciones = new ArrayList<>();

        public ProfesorConsultaUI(String nombreCompleto, String area) {
            this.nombreCompleto = nombreCompleto;
            this.area = area;
        }

        public String getNombreCompleto() {
            return nombreCompleto;
        }

        public String getArea() {
            return area;
        }

        public List<AsignacionUI> getAsignaciones() {
            return asignaciones;
        }

        public String getIniciales() {
            String[] partes = nombreCompleto.replaceAll("^(Dr\\.|Dra\\.|Mtro\\.|Mtra\\.|Ing\\.)\\s*", "").split(" ");
            String resultado = "";
            for (int i = 0; i < partes.length && resultado.length() < 2; i++) {
                if (!partes[i].isEmpty()) {
                    resultado += partes[i].charAt(0);
                }
            }
            return resultado.toUpperCase();
        }

        public int getHorasSemanaInt() {
            int total = 0;
            for (AsignacionUI a : asignaciones) {
                total += a.getHoras();
            }
            return total;
        }

        public String getHorasSemana() {
            return String.valueOf(getHorasSemanaInt());
        }
    }

    public static class AsignacionUI implements Serializable {
        private String materia;
        private String diasHorario;
        private String aula;
        private int horas;

        public AsignacionUI(String materia, String diasHorario, String aula, int horas) {
            this.materia = materia;
            this.diasHorario = diasHorario;
            this.aula = aula;
            this.horas = horas;
        }

        public String getMateria() {
            return materia;
        }

        public String getDiasHorario() {
            return diasHorario;
        }

        public String getAula() {
            return aula;
        }

        public int getHoras() {
            return horas;
        }
    }
}
