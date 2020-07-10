/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.MenuRol;
import com.gestion.coves.services.MenuRolServiceLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class MenuRolFachada implements MenuRolFachadaLocal {

    @Inject
    private MenuRolServiceLocal menuRolService;

    @Override
    public List<MenuRol> findAll() {
        return menuRolService.findAll();
    }

    @Override
    public void edit(MenuRol selected) {
        menuRolService.edit(selected);
    }

    @Override
    public void remove(MenuRol selected) {
        menuRolService.remove(selected);
    }

    @Override
    public MenuRol find(Integer id) {
        return menuRolService.find(id);
    }
    
    
    
}
