/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.services;

import com.gestion.coves.dominio.entities.Usuario;
import com.gestion.coves.util.Operaciones;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class UsuarioService extends AbstractService<Usuario> implements UsuarioServiceLocal {

    @PersistenceContext(unitName = "persistence-COVES")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioService() {
        super(Usuario.class);
    }

    @Override
    public Usuario validarUsuario(String usuario, String clave) {

        try {
            usuario = Operaciones.limpiarCadena(usuario);
            clave = Operaciones.limpiarCadena(clave);
            Usuario usuarioEnt = em.createNamedQuery("Usuario.findUsuarioClave", Usuario.class).setParameter("usuario", usuario).setParameter("clave", clave).getSingleResult();
            return usuarioEnt;
        } catch (NoResultException e) {
            return null;
        }

    }

}
