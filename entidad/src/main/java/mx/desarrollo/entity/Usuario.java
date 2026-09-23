package mx.desarrollo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Size(max = 50)
    @NotNull
    @Column(name = "nombre_usuario", nullable = false, length = 50)
    private String nombreUsuario;

    @Size(max = 60)
    @NotNull
    @Column(name = "contrasena", nullable = false, length = 60)
    private String contrasena;

    public Usuario() {
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
