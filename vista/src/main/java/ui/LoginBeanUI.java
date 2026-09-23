package ui;

import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named
@SessionScoped
public class LoginBeanUI implements Serializable {

    private String usuario;
    private String contrasenia;
    private boolean autenticado;

    public String login() {
        boolean credencialesValidas = validarConNegocio(usuario, contrasenia);

        if (credencialesValidas) {
            autenticado = true;
            return "menu?faces-redirect=true";
        }

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Usuario o contraseña incorrectos", null));
        return null;
    }

    private boolean validarConNegocio(String usuario, String contrasenia) {
        // TODO: conectar con la capa de Negocio (ej. DelegateLogin.autenticar(...))
        return false;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isAutenticado() {
        return autenticado;
    }
}
