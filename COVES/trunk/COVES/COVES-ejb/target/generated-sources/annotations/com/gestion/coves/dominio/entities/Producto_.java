package com.gestion.coves.dominio.entities;

import com.gestion.coves.dominio.entities.Compra;
import com.gestion.coves.dominio.entities.Inventario;
import com.gestion.coves.dominio.entities.InventarioTienda;
import com.gestion.coves.dominio.entities.Novedad;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T08:51:30")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, String> descripcion;
    public static volatile SingularAttribute<Producto, String> codigo;
    public static volatile ListAttribute<Producto, InventarioTienda> InventarioTiendaList;
    public static volatile SingularAttribute<Producto, BigDecimal> precio1;
    public static volatile SingularAttribute<Producto, BigDecimal> precio2;
    public static volatile ListAttribute<Producto, Compra> compraList;
    public static volatile ListAttribute<Producto, Novedad> novedadList;
    public static volatile SingularAttribute<Producto, BigDecimal> precio3;
    public static volatile SingularAttribute<Producto, BigDecimal> precio4;
    public static volatile SingularAttribute<Producto, String> usuarioCreacion;
    public static volatile SingularAttribute<Producto, String> usuarioActualizacion;
    public static volatile ListAttribute<Producto, Inventario> inventarioList;
    public static volatile SingularAttribute<Producto, Date> fechaCreacion;
    public static volatile SingularAttribute<Producto, Date> fechaActualizacion;
    public static volatile SingularAttribute<Producto, Integer> idProducto;
    public static volatile SingularAttribute<Producto, String> rutaImagen;

}