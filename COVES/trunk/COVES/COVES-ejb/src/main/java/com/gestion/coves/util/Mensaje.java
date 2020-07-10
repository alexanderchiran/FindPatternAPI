/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.util;

/**
 *
 * @author Paulo Alexander Chiran paulo.alexander12@gmail.com
 */
public class Mensaje {

    private String mensaje;
    private String param1;
    private String param2;
    private String param3;

    public Mensaje(String mensaje, String param1) {
        this.mensaje = mensaje;
        this.param1 = param1;
    }

    public Mensaje(String mensaje, String param1, String param2) {
        this.mensaje = mensaje;
        this.param1 = param1;
        this.param2 = param2;
    }

    public Mensaje(String mensaje, String param1, String param2, String param3) {
        this.mensaje = mensaje;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the param1
     */
    public String getParam1() {
        return param1;
    }

    /**
     * @param param1 the param1 to set
     */
    public void setParam1(String param1) {
        this.param1 = param1;
    }

    /**
     * @return the param2
     */
    public String getParam2() {
        return param2;
    }

    /**
     * @param param2 the param2 to set
     */
    public void setParam2(String param2) {
        this.param2 = param2;
    }

    /**
     * @return the param3
     */
    public String getParam3() {
        return param3;
    }

    /**
     * @param param3 the param3 to set
     */
    public void setParam3(String param3) {
        this.param3 = param3;
    }
}
