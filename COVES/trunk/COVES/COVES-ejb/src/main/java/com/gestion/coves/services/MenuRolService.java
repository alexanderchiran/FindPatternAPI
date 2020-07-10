/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.services;

import com.gestion.coves.dominio.entities.Menu;
import com.gestion.coves.dominio.entities.MenuRol;
import com.gestion.coves.dominio.entities.UsuarioRol;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class MenuRolService extends AbstractService<MenuRol> implements MenuRolServiceLocal {

    @PersistenceContext(unitName = "persistence-COVES")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuRolService() {
        super(MenuRol.class);
    }

    @Override
    public List<Menu> findHijos(Integer idMenu, List<UsuarioRol> usuarioRoles) {
        List<Integer> roles = new ArrayList<>();
        roles.add(-1);
        if (usuarioRoles != null) {
            for (UsuarioRol usuarioRol : usuarioRoles) {
                roles.add(usuarioRol.getIdRol().getIdRol());
            }
        }
        List<MenuRol> listaMenuRol = em.createNamedQuery("MenuRol.findHijos").setParameter("idMenu", idMenu).setParameter("roles", roles).getResultList();
        List<Menu> listaMenu = new ArrayList<>();
        if (listaMenuRol != null) {
            for (MenuRol menuRol : listaMenuRol) {
                listaMenu.add(menuRol.getIdMenu());
            }
        }
        return listaMenu;
    }

}
