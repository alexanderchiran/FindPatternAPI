package com.gestion.coves.dominio.entities;

import com.gestion.coves.dominio.entities.Factura;
import com.gestion.coves.dominio.entities.TipoIdentificacion;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T08:51:30")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, String> apellidos;
    public static volatile SingularAttribute<Cliente, TipoIdentificacion> idTipoIdentificacion;
    public static volatile SingularAttribute<Cliente, String> direccion;
    public static volatile SingularAttribute<Cliente, BigInteger> identificacion;
    public static volatile SingularAttribute<Cliente, String> nombres;
    public static volatile SingularAttribute<Cliente, String> usuarioCreacion;
    public static volatile SingularAttribute<Cliente, Integer> idCliente;
    public static volatile SingularAttribute<Cliente, String> usuarioActualizacion;
    public static volatile ListAttribute<Cliente, Factura> facturaList;
    public static volatile SingularAttribute<Cliente, Date> fechaCreacion;
    public static volatile SingularAttribute<Cliente, Date> fechaActualizacion;
    public static volatile SingularAttribute<Cliente, String> telefono;
    public static volatile SingularAttribute<Cliente, String> email;

}