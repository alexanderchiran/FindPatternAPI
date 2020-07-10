/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.services;

import com.gestion.coves.dominio.entities.DetallePago;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Alexander Chiran
 */
public interface DetallePagoServiceLocal {

    public List<DetallePago> findAll();

    public void edit(DetallePago selected);

    public void remove(DetallePago selected);

    public DetallePago find(Object id);

    public List<DetallePago> findByIdPago(Integer idPagoProveedor);

    public BigDecimal sumarPagos(Integer idPagoProveedor);

   
    
}
