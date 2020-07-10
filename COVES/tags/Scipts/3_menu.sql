INSERT INTO `menu` VALUES (1,NULL,'Gestion','GESTION',1,'2017-02-13 00:00:00','Admin'),
(2,NULL,'Productos','PRODUCTOS',2,'2017-02-13 00:00:00','ADMIN'),
(3,NULL,'Facturación','FACTURACION',3,'2017-02-13 00:00:00','ADMIN');

#Gestion
INSERT INTO `menu` VALUES (null,1,'Empresa','/COVES-web/faces/empresa/',100,'2017-02-13 00:00:00','Admin');
INSERT INTO `menu` VALUES (null,1,'Impuesto','/COVES-web/faces/impuesto/',200,'2017-02-13 00:00:00','Admin');
INSERT INTO `menu` VALUES (null,1,'Menu Roles','/COVES-web/faces/menuRol/',300,'2017-02-13 00:00:00','Admin');
INSERT INTO `menu` VALUES (null,1,'Roles','/COVES-web/faces/rol/',400,'2017-02-13 00:00:00','Admin');
INSERT INTO `menu` VALUES (null,1,'Usuarios','/COVES-web/faces/usuario/',500,'2017-02-13 00:00:00','Admin');
INSERT INTO `menu` VALUES (null,1,'Usuarios Roles','/COVES-web/faces/usuarioRol/',600,'2017-02-13 00:00:00','Admin');
INSERT INTO `menu` VALUES (null,1,'Tipo de novedades','/COVES-web/faces/tipoNoveda/',700,'2017-02-13 00:00:00','Admin');
#Productos
INSERT INTO `menu` VALUES (null,2,'Clientes','/COVES-web/faces/cliente/',100,'2017-02-13 00:00:00','Admin');
INSERT INTO `menu` VALUES (null,2,'Compras','/COVES-web/faces/compra/',200,'2017-02-13 00:00:00','Admin');
INSERT INTO `menu` VALUES (null,2,'Inventario Global','/COVES-web/faces/inventario/',300,'2017-02-13 00:00:00','Admin');
INSERT INTO `menu` VALUES (null,2,'Inventario por Local','/COVES-web/faces/invetarioTienda/',400,'2017-02-13 00:00:00','Admin');
INSERT INTO `menu` VALUES (null,2,'Novedades','/COVES-web/faces/novedad/',500,'2017-02-13 00:00:00','Admin');
INSERT INTO `menu` VALUES (null,2,'Productos','/COVES-web/faces/producto/',600,'2017-02-13 00:00:00','Admin');
INSERT INTO `menu` VALUES (null,2,'Proveedores','/COVES-web/faces/proveedor/',700,'2017-02-13 00:00:00','Admin');
INSERT INTO `menu` VALUES (null,2,'Locales','/COVES-web/faces/tienda/',800,'2017-02-13 00:00:00','Admin');
#Facturación
commit;

