package com.gestion.coves.dominio.entities;

import com.gestion.coves.dominio.entities.Producto;
import com.gestion.coves.dominio.entities.Tienda;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T08:51:30")
@StaticMetamodel(InventarioTienda.class)
public class InventarioTienda_ { 

    public static volatile SingularAttribute<InventarioTienda, Integer> existencia;
    public static volatile SingularAttribute<InventarioTienda, String> usuarioActualizacion;
    public static volatile SingularAttribute<InventarioTienda, Date> fechaCreacion;
    public static volatile SingularAttribute<InventarioTienda, Date> fechaActualizacion;
    public static volatile SingularAttribute<InventarioTienda, Integer> idInventarioTienda;
    public static volatile SingularAttribute<InventarioTienda, Producto> idProducto;
    public static volatile SingularAttribute<InventarioTienda, Tienda> idTienda;
    public static volatile SingularAttribute<InventarioTienda, String> usuarioCreacion;

}