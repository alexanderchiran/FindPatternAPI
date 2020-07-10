package com.gestion.coves.dominio.entities;

import com.gestion.coves.dominio.entities.Compra;
import com.gestion.coves.dominio.entities.TipoIdentificacion;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T08:51:30")
@StaticMetamodel(Proveedor.class)
public class Proveedor_ { 

    public static volatile SingularAttribute<Proveedor, String> estado;
    public static volatile SingularAttribute<Proveedor, TipoIdentificacion> idTipoIdentificacion;
    public static volatile SingularAttribute<Proveedor, String> direccion;
    public static volatile SingularAttribute<Proveedor, BigInteger> identificacion;
    public static volatile ListAttribute<Proveedor, Compra> compraList;
    public static volatile SingularAttribute<Proveedor, String> usuarioCreacion;
    public static volatile SingularAttribute<Proveedor, String> representante;
    public static volatile SingularAttribute<Proveedor, Integer> idProveedor;
    public static volatile SingularAttribute<Proveedor, String> razonsocial;
    public static volatile SingularAttribute<Proveedor, String> usuarioActualizacion;
    public static volatile SingularAttribute<Proveedor, String> celular;
    public static volatile SingularAttribute<Proveedor, Date> fechaCreacion;
    public static volatile SingularAttribute<Proveedor, Date> fechaActualizacion;
    public static volatile SingularAttribute<Proveedor, String> telefono;
    public static volatile SingularAttribute<Proveedor, String> email;

}