package com.gestion.coves.dominio.entities;

import com.gestion.coves.dominio.entities.Producto;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T08:51:30")
@StaticMetamodel(Inventario.class)
public class Inventario_ { 

    public static volatile SingularAttribute<Inventario, Integer> existencia;
    public static volatile SingularAttribute<Inventario, String> usuarioActualizacion;
    public static volatile SingularAttribute<Inventario, Date> fechaCreacion;
    public static volatile SingularAttribute<Inventario, Date> fechaActualizacion;
    public static volatile SingularAttribute<Inventario, Producto> idProducto;
    public static volatile SingularAttribute<Inventario, Integer> idInventario;
    public static volatile SingularAttribute<Inventario, BigDecimal> costoUnitario;
    public static volatile SingularAttribute<Inventario, String> usuarioCreacion;

}