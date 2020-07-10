/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.services;

import com.gestion.coves.dominio.entities.Compra;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author natalia
 */
@Local
public interface CompraServicesLocal {

    public List<Compra> findAll();

    public void edit(Compra selected);

    public Compra find(Object id);
    
    public List<Compra> findAllCompras();
}
