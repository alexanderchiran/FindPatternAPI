/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.util;

/*
* Nombre CargueControllerBB
*
* @version 1.0
*
* Fecha : 28 / junio / 2016
*
* alexander.chiran
*
* Itac
 */
public final class RecursoPermitidoHelper {

    public static final boolean esExtensionPermitidaPorGet(String uriSolicitada) {
        return (uriSolicitada.endsWith("js"))
                || (uriSolicitada.endsWith("gif"))
                || (uriSolicitada.endsWith("jpg"))
                || (uriSolicitada.endsWith("jpeg"))
                || (uriSolicitada.endsWith("swf"))
                || (uriSolicitada.endsWith("bmp"))               
                || (uriSolicitada.endsWith("htc"))
                || (uriSolicitada.endsWith("ico"))
                || (uriSolicitada.endsWith("pdf"))
                || (uriSolicitada.endsWith("png"))
                || (uriSolicitada.endsWith("css"))
                || (uriSolicitada.endsWith("xml"))               
                || (uriSolicitada.endsWith("svg"))
                || (uriSolicitada.endsWith("css"))
                || (uriSolicitada.endsWith("woff2"))
                || (uriSolicitada.endsWith("woff"))
                || (uriSolicitada.endsWith("ttf"));
    }
}
