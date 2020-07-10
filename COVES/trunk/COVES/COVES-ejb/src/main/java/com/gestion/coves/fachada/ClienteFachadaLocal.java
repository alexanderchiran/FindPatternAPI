/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Cliente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexander Chiran
 */
@Local
public interface ClienteFachadaLocal {

    public List<Cliente> findAll();

    public void edit(Cliente selected);

    public void remove(Cliente selected);

    public Cliente find(Integer id);

    
}
