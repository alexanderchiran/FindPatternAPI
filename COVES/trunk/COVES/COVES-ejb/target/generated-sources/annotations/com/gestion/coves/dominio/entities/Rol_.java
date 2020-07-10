package com.gestion.coves.dominio.entities;

import com.gestion.coves.dominio.entities.MenuRol;
import com.gestion.coves.dominio.entities.UsuarioRol;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T08:51:30")
@StaticMetamodel(Rol.class)
public class Rol_ { 

    public static volatile SingularAttribute<Rol, Integer> idRol;
    public static volatile SingularAttribute<Rol, String> estado;
    public static volatile ListAttribute<Rol, UsuarioRol> usuarioRolList;
    public static volatile ListAttribute<Rol, MenuRol> menuRolList;
    public static volatile SingularAttribute<Rol, String> usuarioActualizacion;
    public static volatile SingularAttribute<Rol, Date> fechaCreacion;
    public static volatile SingularAttribute<Rol, Date> fechaActualizacion;
    public static volatile SingularAttribute<Rol, String> nombre;
    public static volatile SingularAttribute<Rol, String> usuarioCreacion;

}