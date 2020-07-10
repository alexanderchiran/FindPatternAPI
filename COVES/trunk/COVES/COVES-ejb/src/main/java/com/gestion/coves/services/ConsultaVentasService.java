/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.services;

import com.gestion.coves.dominio.entities.Inventario;
import com.gestion.coves.dto.ConsultaDtoRequest;
import com.gestion.coves.dto.ConsultaDtoResponse;
import com.gestion.coves.exception.ExceptionControl;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class ConsultaVentasService implements ConsultaVentasServiceLocal {

    @PersistenceContext(unitName = "persistence-COVES")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<ConsultaDtoResponse> consultarVentas(ConsultaDtoRequest dto) throws ExceptionControl {
        ConsultaDtoResponse dtoRespuesta = null;
        List<ConsultaDtoResponse> listRespuesta = new ArrayList<>();

        try {
            Integer codProducto = dto.getCodProducto();
            Integer codTienda = dto.getCodTienda();
            String fechaFin = formatoFecha(dto.getFechaFin(),"F");
            String fechaInicio = formatoFecha(dto.getFechaInicio(),"I");

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT g.id_producto, SUM(g.cantidad), round(avg(g.precio_unitario),0), SUM(g.precio_total) "
                    + "FROM factura c, factura_detalle g WHERE");

            if (fechaInicio != null && fechaFin != null) {
                sql.append(" (c.fecha_venta BETWEEN \'").append(fechaInicio).append("\'").
                        append(" AND \'").append(fechaFin).append("\')");
            }

            if (codProducto != null && codProducto != -1) {
                sql.append(" AND g.id_producto =").append(codProducto);
            }

            if (codTienda != null && codTienda != -1) {
                sql.append(" AND c.id_tienda =").append(codTienda);
            }

            sql.append(" AND c.id_factura=g.id_factura GROUP BY g.id_producto");

            List<Object[]> createNativeQuery = em.createNativeQuery(sql.toString()).getResultList();

            if (!createNativeQuery.isEmpty()) {
                for (Object[] o : createNativeQuery) {
                    dtoRespuesta = new ConsultaDtoResponse();
                    Integer idProducto = (Integer) o[0];
                    //Producto producto = (Producto) em.createNamedQuery("Producto.findByIdProducto").setParameter("idProducto", idProducto).getSingleResult();
                    Inventario inventario = em.createNamedQuery("Inventario.findByIdProducto", Inventario.class).setParameter("id_producto", idProducto).getSingleResult();
                    dtoRespuesta.setProducto(inventario.getIdProducto());
                    //cantidad
                    BigDecimal cantidadB = (BigDecimal) o[1];
                    dtoRespuesta.setCantidad(cantidadB.intValue());
                    //costo unitario
                    dtoRespuesta.setCostoUnitario(inventario.getCostoUnitario());
                    //costo total
                    dtoRespuesta.setCostoTotal(inventario.getCostoUnitario().multiply(cantidadB));
                    // precio venta
                    dtoRespuesta.setPrecioPromedioVenta((BigDecimal) o[2]);
                    //venta total
                    dtoRespuesta.setPrecioVentaTotal((BigDecimal) o[3]);
                    //ganancia
                    dtoRespuesta.setGananciaUnitario(calcularGanancia(dtoRespuesta.getCostoTotal(), dtoRespuesta.getPrecioVentaTotal()));

                    listRespuesta.add(dtoRespuesta);
                }
            } else {
                throw new ExceptionControl("No hay resultados de ventas");
            }
        } catch (ExceptionControl ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExceptionControl("Error");
        }

        return listRespuesta;
    }

    private BigDecimal calcularGanancia(BigDecimal costototal, BigDecimal ventatotal) {
        costototal = costototal.multiply(new BigDecimal(-1));
        return ventatotal.add(costototal);
    }

    private String formatoFecha(Date fecha, String inifin) {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        String format = dt.format(fecha);
        if (inifin.equals("I")) {
            format = format.concat(" 00:00:00");
        } else {
            format = format.concat(" 23:59:59");
        }
        return format;
    }

}
