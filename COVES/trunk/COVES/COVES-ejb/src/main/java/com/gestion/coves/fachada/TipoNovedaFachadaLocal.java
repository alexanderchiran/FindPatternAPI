/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.TipoNoveda;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexander Chiran
 */
@Local
public interface TipoNovedaFachadaLocal {
   
    
    public List<TipoNoveda> findAll();

    public void edit(TipoNoveda selected);

    public void remove(TipoNoveda selected);

    public TipoNoveda find(Integer id);
    
}
