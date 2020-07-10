/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Inventario;
import com.gestion.coves.dto.InventarioDTO;
import com.gestion.coves.exception.ExceptionControl;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexander Chiran
 */
@Local
public interface InventarioFachadaLocal {
    
    public List<Inventario> findAll();
    
    public void edit(Inventario selected);

    public void remove(Inventario selected);
    
    public Inventario find(Integer id);

    public void actualizarInventarioGlobal(InventarioDTO inventario) throws ExceptionControl;
    
}
