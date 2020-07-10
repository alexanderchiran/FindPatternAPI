/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Impuesto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexander Chiran
 */
@Local
public interface ImpuestoFachadaLocal {
    
    public List<Impuesto> findAll();
    
    public void edit(Impuesto selected);

    public void remove(Impuesto selected);
    
    public Impuesto find(Integer id);
    
}
