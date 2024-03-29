/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Proveedor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexander Chiran
 */
@Local
public interface ProveedorFachadaLocal {
    
    public List<Proveedor> findAll();

    public void edit(Proveedor selected);

    public void remove(Proveedor selected);

    public Proveedor find(Integer id);
    
}
