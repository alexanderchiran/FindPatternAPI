package com.gestion.coves.dominio.entities;

import com.gestion.coves.dominio.entities.Menu;
import com.gestion.coves.dominio.entities.Rol;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T08:51:30")
@StaticMetamodel(MenuRol.class)
public class MenuRol_ { 

    public static volatile SingularAttribute<MenuRol, Integer> idMenuRol;
    public static volatile SingularAttribute<MenuRol, Rol> idRol;
    public static volatile SingularAttribute<MenuRol, String> usuarioActualizacion;
    public static volatile SingularAttribute<MenuRol, Menu> idMenu;
    public static volatile SingularAttribute<MenuRol, Date> fechaCreacion;
    public static volatile SingularAttribute<MenuRol, Date> fechaActualizacion;
    public static volatile SingularAttribute<MenuRol, String> usuarioCreacion;

}