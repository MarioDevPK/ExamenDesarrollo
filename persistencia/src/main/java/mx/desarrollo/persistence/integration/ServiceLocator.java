/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarrollo.persistence.integration;

import jakarta.persistence.EntityManager;
import mx.desarrollo.persistence.dao.*;
import mx.desarrollo.persistence.persistence.HibernateUtil;


/**
 *
 * @author total
 */
public class ServiceLocator {

    private static ProfesorDAO ProfesorDAO;
    private static UsuarioDAO usuarioDAO;
    private static AsignacionUnidadDAO asignacionUnidadDAO;
    private static UnidadAprendizajeDAO unidadAprendizajeDAO;

    private static EntityManager getEntityManager(){
        return HibernateUtil.getEntityManager();
    }

    /**
     * se crea la instancia para profesor DAO si esta no existe
     */
    public static ProfesorDAO getInstanceProfesorDAO(){
        if(ProfesorDAO == null){
            ProfesorDAO = new ProfesorDAO(getEntityManager());
            return ProfesorDAO;
        } else{
            return ProfesorDAO;
        }
    }
    /**
     * se crea la instancia de usuarioDAO si esta no existe
     */
    public static UsuarioDAO getInstanceUsuarioDAO(){
        if(usuarioDAO == null){
            usuarioDAO = new UsuarioDAO(getEntityManager());
            return usuarioDAO;
        } else{
            return usuarioDAO;
        }
    }

    /**
     * se crea la instancia de asignacionUnidadDAO si esta no existe
     */
    public static AsignacionUnidadDAO getInstanceAsignacionUnidadDAO(){
        if(asignacionUnidadDAO == null){
            asignacionUnidadDAO = new AsignacionUnidadDAO(getEntityManager());
            return asignacionUnidadDAO;
        } else{
            return asignacionUnidadDAO;
        }
    }

    /**
     * se crea la instancia de unidadAprendizajeDAO si esta no existe
     */
    public static UnidadAprendizajeDAO getInstanceUnidadAprendizajeDAO(){
        if(unidadAprendizajeDAO == null){
            unidadAprendizajeDAO = new UnidadAprendizajeDAO(getEntityManager());
            return unidadAprendizajeDAO;
        } else{
            return unidadAprendizajeDAO;
        }
    }

}
