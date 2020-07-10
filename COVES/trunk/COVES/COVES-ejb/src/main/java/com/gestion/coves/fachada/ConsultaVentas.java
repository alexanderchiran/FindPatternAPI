/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dto.ConsultaDtoRequest;
import com.gestion.coves.dto.ConsultaDtoResponse;
import com.gestion.coves.exception.ExceptionControl;
import com.gestion.coves.services.ConsultaVentasServiceLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class ConsultaVentas implements ConsultaVentasLocal {

    @Inject
    private ConsultaVentasServiceLocal consultas;
    
    
    @Override
    public  List<ConsultaDtoResponse> consultarVentas(ConsultaDtoRequest dto) throws ExceptionControl{
        return consultas.consultarVentas(dto);
    }
    
    

}
