CREATE DATABASE  IF NOT EXISTS `controlsales` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `controlsales`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: controlsales
-- ------------------------------------------------------
-- Server version	5.6.35-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT COMMENT 'llave primaria',
  `nombres` varchar(200) DEFAULT NULL COMMENT 'nombres del cliente',
  `apellidos` varchar(200) DEFAULT NULL COMMENT 'apellidos del cliente',
  `identificacion` bigint(15) DEFAULT NULL COMMENT 'número de identicicación del cliente',
  `id_tipo_identificacion` int(11) DEFAULT NULL COMMENT 'tipo de identificación, ejemplo: cedula, NIT, cedula de extranjeria',
  `email` varchar(100) DEFAULT NULL COMMENT 'correo electronico',
  `telefono` varchar(50) DEFAULT NULL COMMENT 'teléfono fijo o celular',
  `direccion` varchar(200) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL COMMENT 'datos de auditoria',
  `usuario_creacion` varchar(50) DEFAULT NULL COMMENT 'datos de auditoria',
  `fecha_actualizacion` datetime DEFAULT NULL COMMENT 'datos de auditoria',
  `usuario_actualizacion` varchar(50) DEFAULT NULL COMMENT 'datos de auditoria',
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `identificacion_UNIQUE` (`identificacion`),
  KEY `fk_cliente_tipo_id_idx` (`id_tipo_identificacion`),
  CONSTRAINT `fk_cliente_tipo_id` FOREIGN KEY (`id_tipo_identificacion`) REFERENCES `tipo_identificacion` (`id_tipo_identificacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compra` (
  `id_compra` int(11) NOT NULL AUTO_INCREMENT,
  `id_proveedor` int(11) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `id_tienda` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `costo_unitario` decimal(12,2) DEFAULT NULL,
  `costo_total` decimal(12,2) DEFAULT NULL,
  `fecha_compra` datetime DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_creacion` varchar(50) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  `usuario_actualizacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_compra`),
  KEY `fk_compra_proveedor_idx` (`id_proveedor`),
  KEY `fk_compra_producto_idx` (`id_producto`),
  KEY `fk_compra_tienda_idx` (`id_tienda`),
  CONSTRAINT `fk_compra_producto` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_compra_proveedor` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_compra_tienda` FOREIGN KEY (`id_tienda`) REFERENCES `tienda` (`id_tienda`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
INSERT INTO `compra` VALUES (1,1,1,1,12,500.00,6000.00,'2017-02-12 00:00:00',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empresa` (
  `idempresa` int(11) NOT NULL AUTO_INCREMENT,
  `razonsocial` varchar(200) DEFAULT NULL,
  `representante` varchar(200) DEFAULT NULL,
  `identificacion` bigint(15) DEFAULT NULL,
  `id_tipo_identificacion` int(11) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `regimen` varchar(50) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_creacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idempresa`),
  KEY `fk_empresa_tipo_id_idx` (`id_tipo_identificacion`),
  CONSTRAINT `fk_empresa_tipo_id` FOREIGN KEY (`id_tipo_identificacion`) REFERENCES `tipo_identificacion` (`id_tipo_identificacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (1,'BEBES','Janet Rodriguez',123456789,NULL,'7211066','Centro','Común',NULL,NULL);
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factura` (
  `id_factura` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) DEFAULT NULL,
  `vendedor` varchar(200) DEFAULT NULL,
  `numero_factura` varchar(45) DEFAULT NULL,
  `fecha_venta` datetime DEFAULT NULL,
  `fecha_cencimiento` datetime DEFAULT NULL,
  `tipo_pago` varchar(50) DEFAULT NULL,
  `subtotal` decimal(12,2) DEFAULT NULL,
  `iva` decimal(12,2) DEFAULT NULL,
  `total` decimal(12,2) DEFAULT NULL,
  `efectivo` decimal(12,2) DEFAULT NULL,
  `cambio` decimal(12,2) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_creacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_factura`),
  KEY `fk_factura_cliente_idx` (`id_cliente`),
  CONSTRAINT `fk_factura_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura_detalle`
--

DROP TABLE IF EXISTS `factura_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factura_detalle` (
  `id_factura_detalle` int(11) NOT NULL AUTO_INCREMENT,
  `id_factura` int(11) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio_unitario` decimal(12,2) DEFAULT NULL,
  `precio_total` decimal(12,2) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_creacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_factura_detalle`),
  KEY `fk_factura_detalle_f_idx` (`id_factura`),
  CONSTRAINT `fk_factura_detalle_f` FOREIGN KEY (`id_factura`) REFERENCES `factura` (`id_factura`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura_detalle`
--

LOCK TABLES `factura_detalle` WRITE;
/*!40000 ALTER TABLE `factura_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura_detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `impuesto`
--

DROP TABLE IF EXISTS `impuesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `impuesto` (
  `id_impuesto` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) DEFAULT NULL,
  `valor` int(11) DEFAULT NULL,
  `sigla` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_impuesto`),
  UNIQUE KEY `sigla_UNIQUE` (`sigla`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `impuesto`
--

LOCK TABLES `impuesto` WRITE;
/*!40000 ALTER TABLE `impuesto` DISABLE KEYS */;
INSERT INTO `impuesto` VALUES (1,'Impuesto Valor Agregado',19,'IVA');
/*!40000 ALTER TABLE `impuesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventario`
--

DROP TABLE IF EXISTS `inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventario` (
  `id_inventario` int(11) NOT NULL AUTO_INCREMENT,
  `id_producto` int(11) DEFAULT NULL,
  `existencia` int(11) DEFAULT NULL,
  `costo_unitario` decimal(12,2) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_creacion` varchar(50) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  `usuario_actualizacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_inventario`),
  KEY `fk_inventario_producto_idx` (`id_producto`),
  CONSTRAINT `fk_inventario_producto` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario`
--

LOCK TABLES `inventario` WRITE;
/*!40000 ALTER TABLE `inventario` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invetario_tienda`
--

DROP TABLE IF EXISTS `invetario_tienda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invetario_tienda` (
  `id_invetario_tienda` int(11) NOT NULL AUTO_INCREMENT,
  `id_producto` int(11) DEFAULT NULL,
  `id_tienda` int(11) DEFAULT NULL,
  `existencia` int(11) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_creacion` varchar(50) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  `usuario_actualizacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_invetario_tienda`),
  KEY `fk_inventario_tienda_t_idx` (`id_tienda`),
  KEY `fk_inventario_producto_t_idx` (`id_producto`),
  CONSTRAINT `fk_inventario_producto_t` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventario_tienda_t` FOREIGN KEY (`id_tienda`) REFERENCES `tienda` (`id_tienda`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invetario_tienda`
--

LOCK TABLES `invetario_tienda` WRITE;
/*!40000 ALTER TABLE `invetario_tienda` DISABLE KEYS */;
/*!40000 ALTER TABLE `invetario_tienda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id_menu` int(11) NOT NULL AUTO_INCREMENT,
  `id_menu_padre` int(11) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `opcion` varchar(200) DEFAULT NULL,
  `orden` int(11) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_creacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_menu`),
  UNIQUE KEY `opcion_UNIQUE` (`opcion`),
  KEY `fk_menu_menu_idx` (`id_menu_padre`),
  CONSTRAINT `fk_menu_menu` FOREIGN KEY (`id_menu_padre`) REFERENCES `menu` (`id_menu`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,NULL,'Gestion','GESTION',1,'2017-02-13 00:00:00','Admin'),(2,NULL,'Productos','PRODUCTOS',2,NULL,'ADMIN'),(3,NULL,'Facturación','FACTURACION',3,NULL,'ADMIN'),(4,1,'Empresa','/COVES-web/faces/empresa/',100,'2017-02-13 00:00:00','Admin'),(5,1,'Impuesto','/COVES-web/faces/impuesto/',200,'2017-02-13 00:00:00','Admin'),(6,1,'Menu Roles','/COVES-web/faces/menuRol/',300,'2017-02-13 00:00:00','Admin'),(7,1,'Roles','/COVES-web/faces/rol/',400,'2017-02-13 00:00:00','Admin'),(8,1,'Usuarios','/COVES-web/faces/usuario/',500,'2017-02-13 00:00:00','Admin'),(9,1,'Usuarios Roles','/COVES-web/faces/usuarioRol/',600,'2017-02-13 00:00:00','Admin'),(10,1,'Tipo de novedades','/COVES-web/faces/tipoNoveda/',700,'2017-02-13 00:00:00','Admin'),(11,2,'Clientes','/COVES-web/faces/cliente/',100,'2017-02-13 00:00:00','Admin'),(12,2,'Compras','/COVES-web/faces/compra/',200,'2017-02-13 00:00:00','Admin'),(13,2,'Inventario Global','/COVES-web/faces/inventario/',300,'2017-02-13 00:00:00','Admin'),(14,2,'Inventario por Local','/COVES-web/faces/invetarioTienda/',400,'2017-02-13 00:00:00','Admin'),(15,2,'Novedades','/COVES-web/faces/novedad/',500,'2017-02-13 00:00:00','Admin'),(16,2,'Productos','/COVES-web/faces/producto/',600,'2017-02-13 00:00:00','Admin'),(17,2,'Proveedores','/COVES-web/faces/proveedor/',700,'2017-02-13 00:00:00','Admin'),(19,2,'Locales','/COVES-web/faces/tienda/',800,'2017-02-13 00:00:00','Admin');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_rol`
--

DROP TABLE IF EXISTS `menu_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu_rol` (
  `id_menu_rol` int(11) NOT NULL AUTO_INCREMENT,
  `id_rol` int(11) NOT NULL,
  `id_menu` int(11) NOT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_creacion` varchar(50) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  `usuario_actualizacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_menu_rol`),
  UNIQUE KEY `unique_menu_rol` (`id_rol`,`id_menu`),
  KEY `fk_menu_rol_menu_idx` (`id_menu`),
  KEY `fk_menu_rol_rol_idx` (`id_rol`),
  CONSTRAINT `fk_menu_rol_menu` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`id_menu`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_menu_rol_rol` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_rol`
--

LOCK TABLES `menu_rol` WRITE;
/*!40000 ALTER TABLE `menu_rol` DISABLE KEYS */;
INSERT INTO `menu_rol` VALUES (1,1,1,NULL,NULL,NULL,NULL),(2,1,2,NULL,NULL,NULL,NULL),(3,1,3,NULL,NULL,NULL,NULL),(4,1,4,NULL,NULL,NULL,NULL),(5,1,5,NULL,NULL,NULL,NULL),(6,1,6,NULL,NULL,NULL,NULL),(7,1,7,NULL,NULL,NULL,NULL),(8,1,8,NULL,NULL,NULL,NULL),(9,1,9,NULL,NULL,NULL,NULL),(10,1,10,NULL,NULL,NULL,NULL),(11,1,11,NULL,NULL,NULL,NULL),(12,1,12,NULL,NULL,NULL,NULL),(13,1,13,NULL,NULL,NULL,NULL),(14,1,14,NULL,NULL,NULL,NULL),(15,1,15,NULL,NULL,NULL,NULL),(16,1,16,NULL,NULL,NULL,NULL),(17,1,17,NULL,NULL,NULL,NULL),(18,1,19,NULL,NULL,NULL,NULL),(19,2,1,NULL,NULL,NULL,NULL),(20,2,4,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `menu_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `novedad`
--

DROP TABLE IF EXISTS `novedad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `novedad` (
  `id_novedad` int(11) NOT NULL AUTO_INCREMENT,
  `id_producto` int(11) DEFAULT NULL,
  `id_tienda` int(11) DEFAULT NULL,
  `id_tipo_novedad` int(11) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `costo_unitario` decimal(12,2) DEFAULT NULL,
  `costo_total` decimal(12,2) DEFAULT NULL,
  `fecha_novedad` datetime DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_creacion` varchar(50) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  `usuario_actualizacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_novedad`),
  KEY `fk_novedad_producto_idx` (`id_producto`),
  KEY `fk_novedad_tienda_idx` (`id_tienda`),
  KEY `fk_novedad_tipo_nov_idx` (`id_tipo_novedad`),
  CONSTRAINT `fk_novedad_producto` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_novedad_tienda` FOREIGN KEY (`id_tienda`) REFERENCES `tienda` (`id_tienda`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_novedad_tipo_nov` FOREIGN KEY (`id_tipo_novedad`) REFERENCES `tipo_noveda` (`id_tipo_noveda`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `novedad`
--

LOCK TABLES `novedad` WRITE;
/*!40000 ALTER TABLE `novedad` DISABLE KEYS */;
INSERT INTO `novedad` VALUES (1,1,1,1,'Me devolvieron',1,100.00,100.00,'2017-02-12 00:00:00',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `novedad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parametro`
--

DROP TABLE IF EXISTS `parametro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parametro` (
  `id_parametro` int(11) NOT NULL AUTO_INCREMENT COMMENT 'llave primaria',
  `grupo` varchar(100) DEFAULT NULL COMMENT 'grupo de parámetros',
  `llave` varchar(100) DEFAULT NULL COMMENT 'llave del parámetro',
  `valor_cadena` varchar(100) DEFAULT NULL COMMENT 'valor en string',
  `valor_numero` int(11) DEFAULT NULL COMMENT 'valor numérico',
  `estado` varchar(1) DEFAULT NULL COMMENT 'Activo=A, Inactivo=I',
  `fech_creacion` datetime DEFAULT NULL COMMENT 'datos de auditoria',
  `usuario_creacion` varchar(50) DEFAULT NULL COMMENT 'datos de auditoria',
  PRIMARY KEY (`id_parametro`),
  UNIQUE KEY `grupo_llave_UNIQUE` (`grupo`,`llave`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametro`
--

LOCK TABLES `parametro` WRITE;
/*!40000 ALTER TABLE `parametro` DISABLE KEYS */;
/*!40000 ALTER TABLE `parametro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL AUTO_INCREMENT COMMENT 'llave primaria',
  `codigo` varchar(50) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `ruta_imagen` varchar(200) DEFAULT NULL,
  `precio1` decimal(12,2) DEFAULT NULL,
  `precio2` decimal(12,2) DEFAULT NULL,
  `precio3` decimal(12,2) DEFAULT NULL,
  `precio4` decimal(12,2) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_creacion` varchar(50) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  `usuario_actualizacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_producto`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'CAMISA-01','amisas','',1000.00,1100.00,1200.00,1500.00,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor` (
  `id_proveedor` int(11) NOT NULL AUTO_INCREMENT,
  `razonsocial` varchar(200) DEFAULT NULL,
  `representante` varchar(200) DEFAULT NULL,
  `id_tipo_identificacion` int(11) DEFAULT NULL,
  `identificacion` bigint(15) DEFAULT NULL,
  `telefono` varchar(100) DEFAULT NULL,
  `celular` varchar(100) DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `estado` varchar(1) DEFAULT NULL COMMENT 'Activo=A, Inactivo=I',
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_creacion` varchar(50) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  `usuario_actualizacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_proveedor`),
  UNIQUE KEY `identificacion_UNIQUE` (`identificacion`),
  KEY `fk_proveedor_tipo_id_idx` (`id_tipo_identificacion`),
  CONSTRAINT `fk_proveedor_tipo_id` FOREIGN KEY (`id_tipo_identificacion`) REFERENCES `tipo_identificacion` (`id_tipo_identificacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,'toto','Juan',NULL,123,'12345','12345','Calle 123','juan@gmail.com','A',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `id_rol` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `estado` varchar(1) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_creacion` varchar(50) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  `usuario_actualizacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_rol`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'Adminstrador','A',NULL,NULL,NULL,NULL),(2,'Vendedor','A',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tienda`
--

DROP TABLE IF EXISTS `tienda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tienda` (
  `id_tienda` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_creacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_tienda`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tienda`
--

LOCK TABLES `tienda` WRITE;
/*!40000 ALTER TABLE `tienda` DISABLE KEYS */;
INSERT INTO `tienda` VALUES (1,'Tienda1',NULL,NULL);
/*!40000 ALTER TABLE `tienda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_identificacion`
--

DROP TABLE IF EXISTS `tipo_identificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_identificacion` (
  `id_tipo_identificacion` int(11) NOT NULL AUTO_INCREMENT COMMENT 'llave primaria',
  `descripcion` varchar(100) NOT NULL COMMENT 'nombre del tipo de identificacion',
  `sigla` varchar(10) NOT NULL COMMENT 'sigla ejemploe: CC, NIT, CE, TI',
  `fecha_creacion` datetime DEFAULT NULL COMMENT 'datos de auditoria',
  `usuario_creacion` varchar(50) DEFAULT NULL COMMENT 'datos de auditoria',
  PRIMARY KEY (`id_tipo_identificacion`),
  UNIQUE KEY `sigla_UNIQUE` (`sigla`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_identificacion`
--

LOCK TABLES `tipo_identificacion` WRITE;
/*!40000 ALTER TABLE `tipo_identificacion` DISABLE KEYS */;
INSERT INTO `tipo_identificacion` VALUES (1,'Cédula','CC','2017-02-12 00:00:00','Admin');
/*!40000 ALTER TABLE `tipo_identificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_noveda`
--

DROP TABLE IF EXISTS `tipo_noveda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_noveda` (
  `id_tipo_noveda` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) DEFAULT NULL,
  `sigla` varchar(50) DEFAULT NULL,
  `estado` varchar(1) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_creacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_noveda`),
  UNIQUE KEY `sigla_UNIQUE` (`sigla`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_noveda`
--

LOCK TABLES `tipo_noveda` WRITE;
/*!40000 ALTER TABLE `tipo_noveda` DISABLE KEYS */;
INSERT INTO `tipo_noveda` VALUES (1,'Devolucion','+','A',NULL,NULL),(2,'Dar de baja','-','A',NULL,NULL);
/*!40000 ALTER TABLE `tipo_noveda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(200) DEFAULT NULL,
  `apellidos` varchar(200) DEFAULT NULL,
  `id_tipo_identificacion` int(11) DEFAULT NULL,
  `identificacion` bigint(15) DEFAULT NULL,
  `estado` varchar(1) DEFAULT NULL,
  `usuario` varchar(100) DEFAULT NULL,
  `clave` varchar(100) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_creacion` varchar(50) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  `usuario_actualizacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `usuario_UNIQUE` (`usuario`),
  KEY `fk_usuario_tipo_id_idx` (`id_tipo_identificacion`),
  CONSTRAINT `fk_usuario_tipo_id` FOREIGN KEY (`id_tipo_identificacion`) REFERENCES `tipo_identificacion` (`id_tipo_identificacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Alexander','Chiran',1,123456789,'A','alex','alex',NULL,NULL,NULL,NULL),(2,'Ana','Perez',1,1244343431,'A','ana','ana',NULL,NULL,NULL,NULL),(3,'Jose','Paz',1,3223431,'A','jose','','2017-02-16 23:20:29','Alexander Chiran alex','2017-02-16 23:20:50','Alexander Chiran alex');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_rol`
--

DROP TABLE IF EXISTS `usuario_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_rol` (
  `id_usuario_rol` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `usuario_creacion` varchar(50) DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  `usuario_actualizacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_usuario_rol`),
  UNIQUE KEY `Unique_usuario_rol` (`id_usuario`,`id_rol`),
  KEY `fk_usuario_rol_usu_idx` (`id_usuario`),
  KEY `fk_usuario_rol_rol_idx` (`id_rol`),
  CONSTRAINT `fk_usuario_rol_rol` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_rol_usu` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_rol`
--

LOCK TABLES `usuario_rol` WRITE;
/*!40000 ALTER TABLE `usuario_rol` DISABLE KEYS */;
INSERT INTO `usuario_rol` VALUES (1,1,1,NULL,NULL,NULL,NULL),(2,2,2,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `usuario_rol` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-16 23:30:03
