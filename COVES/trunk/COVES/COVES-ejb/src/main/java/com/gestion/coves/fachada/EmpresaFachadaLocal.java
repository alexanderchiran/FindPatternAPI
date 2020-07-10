/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Empresa;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexander Chiran
 */
@Local
public interface EmpresaFachadaLocal {
  
    public List<Empresa> findAll();
    
     public void edit(Empresa selected);

    public void remove(Empresa selected);
    
    public Empresa find(Integer id);
    
}
