/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.util;

import com.gestion.coves.dominio.entities.Usuario;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alexander Chiran
 */
public class Operaciones {

    /**
     * Este metodo limpia la cadena de espacios dentro y a fuera
     *
     * @param cadena
     * @return
     */
    public static String limpiarCadena(String cadena) {
        try {
            cadena = cadena.trim();
            cadena = cadena.replace(" ", "");
            return cadena;
        } catch (Exception e) {
            UtilidadLog.hacerLogError(e);
        }
        return "";
    }
    /**
     * 
     * @param fecha
     * @return 
     */
    public static String fechaACadena(Date fecha){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            if(fecha!=null){
                return sdf.format(fecha);
            }
            
        } catch (Exception e) {
             UtilidadLog.hacerLogError(e);
        }   
        return "";
    }
    
    public static String usuarioSesion() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Usuario usuario = (Usuario) session.getAttribute(Global.USUARIO_SESION);
        if (usuario != null) {            
            return usuario.getNombres().concat(" ").concat(usuario.getApellidos()).concat(" ").concat(usuario.getUsuario());
        }        
        return "Sin User";
        
    }
}
