UPDATE `controlsales`.`menu` SET `opcion`='/COVES-web/faces/inventarioTienda/' WHERE `id_menu`='14';

INSERT INTO `controlsales`.`menu` (`id_menu_padre`, `descripcion`, `opcion`, `orden`, `fecha_creacion`, `usuario_creacion`) VALUES ('2', 'Pagos a proveedores', '/COVES-web/faces/pagoProveedor/', '900', '2017-02-13 00:00:00', 'Admin');


drop table detalle_pago;

drop table pago_proveedor;

CREATE TABLE IF NOT EXISTS `controlsales`.`pago_proveedor` (
  `id_pago_proveedor` INT NOT NULL AUTO_INCREMENT,
  `id_proveedor` INT NULL,
  `numero_factura` VARCHAR(50) NULL,
  `valor_total` DECIMAL(12,2) NULL,
  `valor_pagado` DECIMAL(12,2) NULL,
  `fecha_factura` DATETIME NULL,
  `fecha_creacion` DATETIME NULL,
  `usuario_creacion` VARCHAR(50) NULL,
  `fecha_actualizacion` DATETIME NULL,
  `usuario_actualizacion` VARCHAR(50) NULL,
  PRIMARY KEY (`id_pago_proveedor`),
  UNIQUE INDEX `numero_factura_UNIQUE` (`numero_factura` ASC),
  INDEX `fk_pago_proveedor_idx` (`id_proveedor` ASC),
  CONSTRAINT `fk_pago_proveedor`
    FOREIGN KEY (`id_proveedor`)
    REFERENCES `controlsales`.`proveedor` (`id_proveedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `controlsales`.`detalle_pago` (
  `id_detalle_pago` INT NOT NULL AUTO_INCREMENT,
  `id_pago_proveedor` INT NULL,
  `valor_abono` DECIMAL(12,2) NULL,
  `fecha_abono` DATETIME NULL,
  `fecha_creacion` DATETIME NULL,
  `usuario_creacion` VARCHAR(50) NULL,
  `fecha_actualizacion` DATETIME NULL,
  `usuario_actualizacion` VARCHAR(50) NULL,
  PRIMARY KEY (`id_detalle_pago`),
  INDEX `fk_detalle_pago_proveedor_idx` (`id_pago_proveedor` ASC),
  CONSTRAINT `fk_detalle_pago_proveedor`
    FOREIGN KEY (`id_pago_proveedor`)
    REFERENCES `controlsales`.`pago_proveedor` (`id_pago_proveedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
