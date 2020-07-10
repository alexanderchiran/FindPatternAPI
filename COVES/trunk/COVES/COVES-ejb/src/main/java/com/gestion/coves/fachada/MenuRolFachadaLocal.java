/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexander Chiran
 */
@Local
public interface MenuRolFachadaLocal {
 
    public List<com.gestion.coves.dominio.entities.MenuRol> findAll();

    public void edit(com.gestion.coves.dominio.entities.MenuRol selected);

    public void remove(com.gestion.coves.dominio.entities.MenuRol selected);

    public com.gestion.coves.dominio.entities.MenuRol find(Integer id);
    
}
