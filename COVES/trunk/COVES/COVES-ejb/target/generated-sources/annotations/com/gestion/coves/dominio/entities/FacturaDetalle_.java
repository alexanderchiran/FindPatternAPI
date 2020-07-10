package com.gestion.coves.dominio.entities;

import com.gestion.coves.dominio.entities.Factura;
import com.gestion.coves.dominio.entities.Producto;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T08:51:30")
@StaticMetamodel(FacturaDetalle.class)
public class FacturaDetalle_ { 

    public static volatile SingularAttribute<FacturaDetalle, String> descripcion;
    public static volatile SingularAttribute<FacturaDetalle, BigDecimal> precioUnitario;
    public static volatile SingularAttribute<FacturaDetalle, Factura> idFactura;
    public static volatile SingularAttribute<FacturaDetalle, Date> fechaCreacion;
    public static volatile SingularAttribute<FacturaDetalle, Integer> cantidad;
    public static volatile SingularAttribute<FacturaDetalle, Producto> idProducto;
    public static volatile SingularAttribute<FacturaDetalle, BigDecimal> precioTotal;
    public static volatile SingularAttribute<FacturaDetalle, Integer> idFacturaDetalle;
    public static volatile SingularAttribute<FacturaDetalle, String> usuarioCreacion;

}