ALTER TABLE `controlsales`.`factura` 
ADD COLUMN `id_tienda` INT NULL AFTER `cambio`,
ADD INDEX `fk_factura_tienda_idx` (`id_tienda` ASC);
ALTER TABLE `controlsales`.`factura` 
ADD CONSTRAINT `fk_factura_tienda`
  FOREIGN KEY (`id_tienda`)
  REFERENCES `controlsales`.`tienda` (`id_tienda`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
