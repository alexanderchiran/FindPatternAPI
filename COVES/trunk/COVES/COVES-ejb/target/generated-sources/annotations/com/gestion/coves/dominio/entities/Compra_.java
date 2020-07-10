package com.gestion.coves.dominio.entities;

import com.gestion.coves.dominio.entities.Producto;
import com.gestion.coves.dominio.entities.Proveedor;
import com.gestion.coves.dominio.entities.Tienda;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T08:51:30")
@StaticMetamodel(Compra.class)
public class Compra_ { 

    public static volatile SingularAttribute<Compra, Date> fechaCompra;
    public static volatile SingularAttribute<Compra, Integer> idCompra;
    public static volatile SingularAttribute<Compra, Proveedor> idProveedor;
    public static volatile SingularAttribute<Compra, BigDecimal> costoTotal;
    public static volatile SingularAttribute<Compra, String> usuarioActualizacion;
    public static volatile SingularAttribute<Compra, Date> fechaCreacion;
    public static volatile SingularAttribute<Compra, Date> fechaActualizacion;
    public static volatile SingularAttribute<Compra, Integer> cantidad;
    public static volatile SingularAttribute<Compra, Producto> idProducto;
    public static volatile SingularAttribute<Compra, BigDecimal> costoUnitario;
    public static volatile SingularAttribute<Compra, Tienda> idTienda;
    public static volatile SingularAttribute<Compra, String> usuarioCreacion;

}