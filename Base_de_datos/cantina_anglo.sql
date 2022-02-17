
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE database IF NOT EXISTS `cantina_anglo` DEFAULT CHARACTER SET utf8mb4;
USE `cantina_anglo` ;

-- -----------------------------------------------------
-- Table `cantina_anglo`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cantina_anglo`.`roles` ;

CREATE TABLE IF NOT EXISTS `cantina_anglo`.`roles` (
  `idrole` INT NOT NULL AUTO_INCREMENT,
  `rol` VARCHAR(30) NOT NULL,
  `desc` VARCHAR(250) NULL,
  `estado` TINYINT(1) UNSIGNED NULL,
  PRIMARY KEY (`idrole`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cantina_anglo`.`usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cantina_anglo`.`usuarios` ;

CREATE TABLE IF NOT EXISTS `cantina_anglo`.`usuarios` (
  `idusuario` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `estado` TINYINT(1) UNSIGNED NOT NULL,
  `pass` VARCHAR(40) NULL,
  `fk_roles` INT NOT NULL,
  PRIMARY KEY (`idusuario`),
  INDEX `fk_usuarios_roles_idx` (`fk_roles` ASC) ,
  CONSTRAINT `fk_usuarios_roles`
    FOREIGN KEY (`fk_roles`)
    REFERENCES `cantina_anglo`.`roles` (`idrole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cantina_anglo`.`ventas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cantina_anglo`.`ventas` ;

CREATE TABLE IF NOT EXISTS `cantina_anglo`.`ventas` (
  `idventa` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATETIME NULL,
  `total` INT NULL,
  `fk_usuario` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idventa`),
  INDEX `fk_ventas_usuarios1_idx` (`fk_usuario` ASC) ,
  CONSTRAINT `fk_ventas_usuarios1`
    FOREIGN KEY (`fk_usuario`)
    REFERENCES `cantina_anglo`.`usuarios` (`idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cantina_anglo`.`categorias`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cantina_anglo`.`categorias` ;

CREATE TABLE IF NOT EXISTS `cantina_anglo`.`categorias` (
  `idcategoria` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(200) NULL,
  `estado` TINYINT(1) UNSIGNED NULL,
  PRIMARY KEY (`idcategoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cantina_anglo`.`articulos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cantina_anglo`.`articulos` ;

CREATE TABLE IF NOT EXISTS `cantina_anglo`.`articulos` (
  `idarticulo` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(50) NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `precio_venta` INT UNSIGNED NOT NULL,
  `costo` INT UNSIGNED NULL,
  `stock` INT NULL,
  `descripcion` VARCHAR(255) NULL,
  `estado` TINYINT(1) UNSIGNED NULL,
  `fk_categorias` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idarticulo`),
  INDEX `fk_articulos_categorias1_idx` (`fk_categorias` ASC) ,
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) ,
  CONSTRAINT `fk_articulos_categorias1`
    FOREIGN KEY (`fk_categorias`)
    REFERENCES `cantina_anglo`.`categorias` (`idcategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cantina_anglo`.`detalle_venta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cantina_anglo`.`detalle_venta` ;

CREATE TABLE IF NOT EXISTS `cantina_anglo`.`detalle_venta` (
  `iddetalle_venta` INT NOT NULL AUTO_INCREMENT,
  `fk_articulos` INT NOT NULL,
  `fk_venta` INT NOT NULL,
  `cantidad` INT UNSIGNED NULL,
  `precio` INT UNSIGNED NULL,
  `descuento` INT UNSIGNED NULL,
  PRIMARY KEY (`iddetalle_venta`),
  INDEX `fk_detalle_venta_articulos1_idx` (`fk_articulos` ASC) ,
  INDEX `fk_detalle_venta_ventas1_idx` (`fk_venta` ASC) ,
  CONSTRAINT `fk_detalle_venta_articulos1`
    FOREIGN KEY (`fk_articulos`)
    REFERENCES `cantina_anglo`.`articulos` (`idarticulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_detalle_venta_ventas1`
    FOREIGN KEY (`fk_venta`)
    REFERENCES `cantina_anglo`.`ventas` (`idventa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
