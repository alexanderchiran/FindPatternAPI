/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.services;

import com.gestion.coves.dominio.entities.PagoProveedor;
import java.util.List;

/**
 *
 * @author Alexander Chiran
 */
public interface PagoProveedorServiceLocal {

    public List<PagoProveedor> findAll();

    public void edit(PagoProveedor selected);

    public void remove(PagoProveedor selected);

    public PagoProveedor find(Object id);
    
}
