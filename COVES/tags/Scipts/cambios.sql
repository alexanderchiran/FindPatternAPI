-- -----------------------------------------------------
-- Table `controlsales`.`pago_proveedor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `controlsales`.`pago_proveedor` ;

CREATE TABLE IF NOT EXISTS `controlsales`.`pago_proveedor` (
  `id_pago_proveedor` INT NOT NULL AUTO_INCREMENT,
  `id_proveedor` VARCHAR(45) NULL,
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
  CONSTRAINT `fk_pago_proveedor`
    FOREIGN KEY (`id_pago_proveedor`)
    REFERENCES `controlsales`.`proveedor` (`id_proveedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `controlsales`.`detalle_pago`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `controlsales`.`detalle_pago` ;

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


ALTER TABLE `controlsales`.`invetario_tienda` 
RENAME TO  `controlsales`.`inventario_tienda` ;

ALTER TABLE `controlsales`.`inventario_tienda` 
CHANGE COLUMN `id_invetario_tienda` `id_inventario_tienda` INT(11) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `controlsales`.`factura_detalle` 
ADD INDEX `fk_factura_detalle_producto_idx` (`id_producto` ASC);
ALTER TABLE `controlsales`.`factura_detalle` 
ADD CONSTRAINT `fk_factura_detalle_producto`
  FOREIGN KEY (`id_producto`)
  REFERENCES `controlsales`.`producto` (`id_producto`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

