package com.gestion.coves.dominio.entities;

import com.gestion.coves.dominio.entities.TipoIdentificacion;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T08:51:30")
@StaticMetamodel(Empresa.class)
public class Empresa_ { 

    public static volatile SingularAttribute<Empresa, Integer> idempresa;
    public static volatile SingularAttribute<Empresa, String> representante;
    public static volatile SingularAttribute<Empresa, String> razonsocial;
    public static volatile SingularAttribute<Empresa, TipoIdentificacion> idTipoIdentificacion;
    public static volatile SingularAttribute<Empresa, String> direccion;
    public static volatile SingularAttribute<Empresa, Date> fechaCreacion;
    public static volatile SingularAttribute<Empresa, BigInteger> identificacion;
    public static volatile SingularAttribute<Empresa, String> telefono;
    public static volatile SingularAttribute<Empresa, String> regimen;
    public static volatile SingularAttribute<Empresa, String> usuarioCreacion;

}