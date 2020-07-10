package com.gestion.coves.dominio.entities;

import com.gestion.coves.dominio.entities.Menu;
import com.gestion.coves.dominio.entities.MenuRol;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T08:51:30")
@StaticMetamodel(Menu.class)
public class Menu_ { 

    public static volatile SingularAttribute<Menu, String> descripcion;
    public static volatile ListAttribute<Menu, Menu> menuList;
    public static volatile SingularAttribute<Menu, String> opcion;
    public static volatile ListAttribute<Menu, MenuRol> menuRolList;
    public static volatile SingularAttribute<Menu, Integer> idMenu;
    public static volatile SingularAttribute<Menu, Date> fechaCreacion;
    public static volatile SingularAttribute<Menu, Integer> orden;
    public static volatile SingularAttribute<Menu, String> usuarioCreacion;
    public static volatile SingularAttribute<Menu, Menu> idMenuPadre;

}