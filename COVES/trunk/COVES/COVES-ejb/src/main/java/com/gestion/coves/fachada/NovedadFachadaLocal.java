/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Novedad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexander Chiran
 */
@Local
public interface NovedadFachadaLocal {
    
    public List<Novedad> findAll();

    public void edit(Novedad selected);

    public void remove(Novedad selected);

    public Novedad find(Integer id);
    
}
