package com.gestion.coves.dominio.entities;

import com.gestion.coves.dominio.entities.Cliente;
import com.gestion.coves.dominio.entities.FacturaDetalle;
import com.gestion.coves.dominio.entities.Tienda;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T08:51:30")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, BigDecimal> cambio;
    public static volatile SingularAttribute<Factura, String> vendedor;
    public static volatile SingularAttribute<Factura, Date> fechaVencimiento;
    public static volatile SingularAttribute<Factura, String> numeroFactura;
    public static volatile SingularAttribute<Factura, Tienda> idTienda;
    public static volatile SingularAttribute<Factura, String> usuarioCreacion;
    public static volatile SingularAttribute<Factura, BigDecimal> total;
    public static volatile SingularAttribute<Factura, Cliente> idCliente;
    public static volatile SingularAttribute<Factura, BigDecimal> iva;
    public static volatile SingularAttribute<Factura, BigDecimal> subtotal;
    public static volatile SingularAttribute<Factura, Integer> idFactura;
    public static volatile SingularAttribute<Factura, BigDecimal> efectivo;
    public static volatile ListAttribute<Factura, FacturaDetalle> facturaDetalleList;
    public static volatile SingularAttribute<Factura, Date> fechaCreacion;
    public static volatile SingularAttribute<Factura, String> tipoPago;
    public static volatile SingularAttribute<Factura, Date> fechaVenta;

}