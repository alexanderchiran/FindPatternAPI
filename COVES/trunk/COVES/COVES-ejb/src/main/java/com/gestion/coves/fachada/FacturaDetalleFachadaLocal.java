/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Factura;
import com.gestion.coves.dominio.entities.FacturaDetalle;
import com.gestion.coves.util.Mensaje;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexander Chiran
 */
@Local
public interface FacturaDetalleFachadaLocal {
    
    public List<FacturaDetalle> findAll();
    
    public void edit(FacturaDetalle selected);

    public void remove(FacturaDetalle selected);
    
    public FacturaDetalle find(Integer id);

    public List<Mensaje> insertarDetalles(List<FacturaDetalle> listaFacturaDetalle, Factura selected);

    public List<FacturaDetalle> findByIdFactura(Integer idFactura);
    
}
