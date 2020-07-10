package com.gestion.coves.dominio.entities;

import com.gestion.coves.dominio.entities.PagoProveedor;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T08:51:30")
@StaticMetamodel(DetallePago.class)
public class DetallePago_ { 

    public static volatile SingularAttribute<DetallePago, Integer> idDetallePago;
    public static volatile SingularAttribute<DetallePago, Date> fechaAbono;
    public static volatile SingularAttribute<DetallePago, String> usuarioActualizacion;
    public static volatile SingularAttribute<DetallePago, Date> fechaCreacion;
    public static volatile SingularAttribute<DetallePago, Date> fechaActualizacion;
    public static volatile SingularAttribute<DetallePago, BigDecimal> valorAbono;
    public static volatile SingularAttribute<DetallePago, String> usuarioCreacion;
    public static volatile SingularAttribute<DetallePago, PagoProveedor> idPagoProveedor;

}