/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Rol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexander Chiran
 */
@Local
public interface RolFachadaLocal {
    
    public List<Rol> findAll();

    public void edit(Rol selected);

    public void remove(Rol selected);

    public Rol find(Integer id);
    
}
