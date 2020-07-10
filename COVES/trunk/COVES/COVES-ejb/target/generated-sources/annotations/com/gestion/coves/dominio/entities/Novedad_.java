package com.gestion.coves.dominio.entities;

import com.gestion.coves.dominio.entities.Producto;
import com.gestion.coves.dominio.entities.Tienda;
import com.gestion.coves.dominio.entities.TipoNoveda;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T08:51:30")
@StaticMetamodel(Novedad.class)
public class Novedad_ { 

    public static volatile SingularAttribute<Novedad, String> descripcion;
    public static volatile SingularAttribute<Novedad, BigDecimal> costoTotal;
    public static volatile SingularAttribute<Novedad, BigDecimal> costoUnitario;
    public static volatile SingularAttribute<Novedad, Tienda> idTienda;
    public static volatile SingularAttribute<Novedad, String> usuarioCreacion;
    public static volatile SingularAttribute<Novedad, TipoNoveda> idTipoNovedad;
    public static volatile SingularAttribute<Novedad, String> usuarioActualizacion;
    public static volatile SingularAttribute<Novedad, Integer> idNovedad;
    public static volatile SingularAttribute<Novedad, Date> fechaCreacion;
    public static volatile SingularAttribute<Novedad, Date> fechaActualizacion;
    public static volatile SingularAttribute<Novedad, Integer> cantidad;
    public static volatile SingularAttribute<Novedad, Producto> idProducto;
    public static volatile SingularAttribute<Novedad, Date> fechaNovedad;

}