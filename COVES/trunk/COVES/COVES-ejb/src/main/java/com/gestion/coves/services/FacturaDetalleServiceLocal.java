/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.services;

import com.gestion.coves.dominio.entities.FacturaDetalle;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexander Chiran
 */
@Local
public interface FacturaDetalleServiceLocal {

    public List<FacturaDetalle> findAll();

    public void edit(FacturaDetalle selected);

    public void remove(FacturaDetalle selected);
    
    public FacturaDetalle find(Object id);

    public List<FacturaDetalle> findByIdFactura(Integer idFactura);
    
}
