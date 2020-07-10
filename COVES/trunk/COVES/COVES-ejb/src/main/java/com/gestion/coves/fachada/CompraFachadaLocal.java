/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Compra;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexander Chiran
 */
@Local
public interface CompraFachadaLocal {
    
    public List<Compra> findAll();
    
    public void edit(Compra selected);
    
    public Compra find(Integer id);
    
    public List<Compra> findAllCompras();
}
