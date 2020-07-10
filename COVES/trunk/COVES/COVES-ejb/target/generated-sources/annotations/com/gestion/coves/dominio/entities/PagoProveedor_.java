package com.gestion.coves.dominio.entities;

import com.gestion.coves.dominio.entities.DetallePago;
import com.gestion.coves.dominio.entities.Proveedor;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T08:51:30")
@StaticMetamodel(PagoProveedor.class)
public class PagoProveedor_ { 

    public static volatile SingularAttribute<PagoProveedor, BigDecimal> valorTotal;
    public static volatile SingularAttribute<PagoProveedor, Date> fechaFactura;
    public static volatile SingularAttribute<PagoProveedor, Proveedor> idProveedor;
    public static volatile ListAttribute<PagoProveedor, DetallePago> detallePagoList;
    public static volatile SingularAttribute<PagoProveedor, String> usuarioActualizacion;
    public static volatile SingularAttribute<PagoProveedor, Date> fechaCreacion;
    public static volatile SingularAttribute<PagoProveedor, Date> fechaActualizacion;
    public static volatile SingularAttribute<PagoProveedor, String> numeroFactura;
    public static volatile SingularAttribute<PagoProveedor, BigDecimal> valorPagado;
    public static volatile SingularAttribute<PagoProveedor, Integer> idPagoProveedor;
    public static volatile SingularAttribute<PagoProveedor, String> usuarioCreacion;

}