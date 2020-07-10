package com.gestion.coves.dominio.entities;

import com.gestion.coves.dominio.entities.TipoIdentificacion;
import com.gestion.coves.dominio.entities.UsuarioRol;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T08:51:30")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> apellidos;
    public static volatile SingularAttribute<Usuario, String> estado;
    public static volatile SingularAttribute<Usuario, String> clave;
    public static volatile SingularAttribute<Usuario, Integer> idUsuario;
    public static volatile ListAttribute<Usuario, UsuarioRol> usuarioRolList;
    public static volatile SingularAttribute<Usuario, TipoIdentificacion> idTipoIdentificacion;
    public static volatile SingularAttribute<Usuario, BigInteger> identificacion;
    public static volatile SingularAttribute<Usuario, String> nombres;
    public static volatile SingularAttribute<Usuario, String> usuarioCreacion;
    public static volatile SingularAttribute<Usuario, String> usuarioActualizacion;
    public static volatile SingularAttribute<Usuario, Date> fechaCreacion;
    public static volatile SingularAttribute<Usuario, Date> fechaActualizacion;
    public static volatile SingularAttribute<Usuario, String> usuario;

}