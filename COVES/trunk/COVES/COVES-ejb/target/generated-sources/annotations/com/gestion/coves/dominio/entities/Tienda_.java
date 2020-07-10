package com.gestion.coves.dominio.entities;

import com.gestion.coves.dominio.entities.Compra;
import com.gestion.coves.dominio.entities.InventarioTienda;
import com.gestion.coves.dominio.entities.Novedad;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T08:51:30")
@StaticMetamodel(Tienda.class)
public class Tienda_ { 

    public static volatile SingularAttribute<Tienda, String> descripcion;
    public static volatile ListAttribute<Tienda, InventarioTienda> InventarioTiendaList;
    public static volatile SingularAttribute<Tienda, Date> fechaCreacion;
    public static volatile ListAttribute<Tienda, Compra> compraList;
    public static volatile ListAttribute<Tienda, Novedad> novedadList;
    public static volatile SingularAttribute<Tienda, Integer> idTienda;
    public static volatile SingularAttribute<Tienda, String> usuarioCreacion;

}