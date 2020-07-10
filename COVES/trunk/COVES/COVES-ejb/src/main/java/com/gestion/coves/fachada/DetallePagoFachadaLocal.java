/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.DetallePago;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexander Chiran
 */
@Local
public interface DetallePagoFachadaLocal {

    public List<DetallePago> findAll();

    public void edit(DetallePago selected);

    public void remove(DetallePago selected);

    public DetallePago find(Integer id);

    public List<DetallePago> findByIdPago(Integer idPagoProveedor);
    
}
