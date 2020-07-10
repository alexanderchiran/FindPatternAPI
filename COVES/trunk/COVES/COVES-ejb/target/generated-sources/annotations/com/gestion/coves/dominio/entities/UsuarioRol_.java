package com.gestion.coves.dominio.entities;

import com.gestion.coves.dominio.entities.Rol;
import com.gestion.coves.dominio.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T08:51:30")
@StaticMetamodel(UsuarioRol.class)
public class UsuarioRol_ { 

    public static volatile SingularAttribute<UsuarioRol, Integer> idUsuarioRol;
    public static volatile SingularAttribute<UsuarioRol, Rol> idRol;
    public static volatile SingularAttribute<UsuarioRol, Usuario> idUsuario;
    public static volatile SingularAttribute<UsuarioRol, String> usuarioActualizacion;
    public static volatile SingularAttribute<UsuarioRol, Date> fechaCreacion;
    public static volatile SingularAttribute<UsuarioRol, Date> fechaActualizacion;
    public static volatile SingularAttribute<UsuarioRol, String> usuarioCreacion;

}