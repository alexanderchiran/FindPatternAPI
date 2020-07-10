/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Inventario;
import com.gestion.coves.dto.InventarioDTO;
import com.gestion.coves.exception.ExceptionControl;
import com.gestion.coves.services.InventarioServiceLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class InventarioFachada implements InventarioFachadaLocal {

    @Inject
    private InventarioServiceLocal inventarioService;

    @Override
    public List<Inventario> findAll() {
       return inventarioService.findAll();
    }

    @Override
    public void edit(Inventario selected) {
        inventarioService.edit(selected);
    }

    @Override
    public void remove(Inventario selected) {
        inventarioService.remove(selected);
    }

    @Override
    public Inventario find(Integer id) {
        return inventarioService.find(id);
    }

    @Override
    public void actualizarInventarioGlobal(InventarioDTO inventario) throws ExceptionControl{
        inventarioService.actualizarInventarioGlobal(inventario);
    }
    
    
}
