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
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,NULL,'Gestion','GESTION',1,'2017-02-13 00:00:00','Admin'),(2,NULL,'Productos','PRODUCTOS',2,NULL,'ADMIN'),(3,NULL,'Facturación','FACTURACION',3,NULL,'ADMIN'),(4,1,'Empresa','/COVES-web/faces/empresa/',100,'2017-02-13 00:00:00','Admin'),(5,1,'Impuesto','/COVES-web/faces/impuesto/',200,'2017-02-13 00:00:00','Admin'),(6,1,'Menu Roles','/COVES-web/faces/menuRol/',400,'2017-02-13 00:00:00','Admin'),(7,1,'Roles','/COVES-web/faces/rol/',300,'2017-02-13 00:00:00','Admin'),(8,1,'Usuarios','/COVES-web/faces/usuario/',500,'2017-02-13 00:00:00','Admin'),(9,1,'Usuarios Roles','/COVES-web/faces/usuarioRol/',600,'2017-02-13 00:00:00','Admin'),(10,1,'Tipo de novedades','/COVES-web/faces/tipoNoveda/',700,'2017-02-13 00:00:00','Admin'),(11,2,'Clientes','/COVES-web/faces/cliente/',100,'2017-02-13 00:00:00','Admin'),(12,2,'Compras','/COVES-web/faces/compra/',200,'2017-02-13 00:00:00','Admin'),(13,2,'Inventario Global','/COVES-web/faces/inventario/',300,'2017-02-13 00:00:00','Admin'),(14,2,'Inventario por Local','/COVES-web/faces/invetarioTienda/',400,'2017-02-13 00:00:00','Admin'),(15,2,'Novedades','/COVES-web/faces/novedad/',500,'2017-02-13 00:00:00','Admin'),(16,2,'Productos','/COVES-web/faces/producto/',600,'2017-02-13 00:00:00','Admin'),(17,2,'Proveedores','/COVES-web/faces/proveedor/',700,'2017-02-13 00:00:00','Admin'),(19,2,'Locales','/COVES-web/faces/tienda/',800,'2017-02-13 00:00:00','Admin'),(20,3,'Consultar Facturas','/COVES-web/faces/factura/',100,'2017-02-13 00:00:00','Admin'),(21,3,'Facturar','/COVES-web/faces/facturar/',200,'2017-02-13 00:00:00','Admin');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-18 15:11:36
