-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 25, 2022 at 07:05 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cantina_anglo`
--

-- --------------------------------------------------------

--
-- Table structure for table `arqueoscaja`
--

CREATE TABLE `arqueoscaja` (
  `id_arqueo` int(11) NOT NULL,
  `fk_vendedor` int(11) DEFAULT NULL,
  `fk_admin` int(11) DEFAULT NULL,
  `fk_ajuste` int(11) DEFAULT NULL,
  `fecha` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `articulos`
--

CREATE TABLE `articulos` (
  `idarticulo` int(11) NOT NULL,
  `codigo` varchar(50) DEFAULT NULL,
  `precio_venta` int(10) UNSIGNED NOT NULL,
  `costo` int(10) UNSIGNED DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `estado` tinyint(1) UNSIGNED NOT NULL DEFAULT 1,
  `fk_categorias` int(10) UNSIGNED NOT NULL,
  `date_arti` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `articulos`
--

INSERT INTO `articulos` (`idarticulo`, `codigo`, `precio_venta`, `costo`, `stock`, `descripcion`, `estado`, `fk_categorias`, `date_arti`) VALUES
(1, '7842714004690', 3000, 1500, 1, 'caja', 1, 1, '2022-02-25 00:00:00'),
(2, '3123211234', 4000, 2000, 2, 'Empanada ', 1, 1, '2022-02-25 00:00:00'),
(3, '123456789', 2500, 4000, 5, 'Pancho con queso', 1, 3, '2022-02-25 00:00:00'),
(4, '123654987', 200, 1000, 20, 'Turron mani', 1, 4, '2022-02-25 00:00:00'),
(5, '321654987', 3000, 6000, 5, 'Pan dulce', 1, 3, '2022-02-25 00:00:00'),
(6, '951874632', 5000, 500, 10, 'Empanada', 1, 2, '2022-02-25 00:00:00'),
(8, '89632563256', 2500, 500, 2, 'Algo', 1, 4, '2022-02-25 00:00:00'),
(9, '3216548946', 2000, 500, 2, 'Prue', 1, 3, '2022-02-25 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `categorias`
--

CREATE TABLE `categorias` (
  `idcategoria` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `estado` tinyint(1) UNSIGNED NOT NULL DEFAULT 1,
  `date_categorias` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `categorias`
--

INSERT INTO `categorias` (`idcategoria`, `nombre`, `estado`, `date_categorias`) VALUES
(1, 'categoria 1', 1, '2022-02-25 00:00:00'),
(2, 'categoria 2', 1, '2022-02-25 00:00:00'),
(3, 'Lacteos', 1, '2022-02-25 00:00:00'),
(4, 'Dani', 1, '2022-02-25 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `detalle_venta`
--

CREATE TABLE `detalle_venta` (
  `iddetalle_venta` int(11) NOT NULL,
  `fk_articulos` int(11) NOT NULL,
  `fk_venta` int(11) NOT NULL,
  `cantidad` int(10) UNSIGNED DEFAULT NULL,
  `precio` int(10) UNSIGNED DEFAULT NULL,
  `descuento` int(10) UNSIGNED DEFAULT NULL,
  `estado` tinyint(1) UNSIGNED NOT NULL DEFAULT 1,
  `date_detalleVenta` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `idrole` int(11) NOT NULL,
  `rol` varchar(30) NOT NULL,
  `desc` varchar(250) DEFAULT NULL,
  `estado` tinyint(1) UNSIGNED NOT NULL DEFAULT 1,
  `date_rol` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`idrole`, `rol`, `desc`, `estado`, `date_rol`) VALUES
(1, 'ADMIN', 'Administrador del sistema, tiene todas las opciones disponibles', 1, '2022-02-25 00:00:00'),
(2, 'VENDEDOR', 'Puede realizar ventas, anularlas, cargar productos nuevos y acutalizar el stock', 1, '2022-02-25 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

CREATE TABLE `usuarios` (
  `idusuario` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `estado` tinyint(1) UNSIGNED NOT NULL DEFAULT 1,
  `pass` varchar(40) DEFAULT NULL,
  `fk_roles` int(11) NOT NULL,
  `date_user` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`idusuario`, `nombre`, `estado`, `pass`, `fk_roles`, `date_user`) VALUES
(1, 'Lucas', 1, '1234', 1, '2022-02-25 00:00:00'),
(2, 'Dani', 1, '1234', 1, '2022-02-25 00:00:00'),
(3, 'Vendedor', 1, '1234', 2, '2022-02-25 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `ventas`
--

CREATE TABLE `ventas` (
  `idventa` int(11) NOT NULL,
  `fecha` datetime DEFAULT current_timestamp(),
  `total` int(11) DEFAULT NULL,
  `fk_usuario` int(10) UNSIGNED NOT NULL,
  `estado` tinyint(1) UNSIGNED NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `arqueoscaja`
--
ALTER TABLE `arqueoscaja`
  ADD PRIMARY KEY (`id_arqueo`);

--
-- Indexes for table `articulos`
--
ALTER TABLE `articulos`
  ADD PRIMARY KEY (`idarticulo`),
  ADD UNIQUE KEY `codigo_UNIQUE` (`codigo`),
  ADD KEY `fk_articulos_categorias1_idx` (`fk_categorias`);

--
-- Indexes for table `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`idcategoria`);

--
-- Indexes for table `detalle_venta`
--
ALTER TABLE `detalle_venta`
  ADD PRIMARY KEY (`iddetalle_venta`),
  ADD KEY `fk_detalle_venta_articulos1_idx` (`fk_articulos`),
  ADD KEY `fk_detalle_venta_ventas1_idx` (`fk_venta`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`idrole`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`idusuario`),
  ADD KEY `fk_usuarios_roles_idx` (`fk_roles`);

--
-- Indexes for table `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`idventa`),
  ADD KEY `fk_ventas_usuarios1_idx` (`fk_usuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `arqueoscaja`
--
ALTER TABLE `arqueoscaja`
  MODIFY `id_arqueo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `articulos`
--
ALTER TABLE `articulos`
  MODIFY `idarticulo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `categorias`
--
ALTER TABLE `categorias`
  MODIFY `idcategoria` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `detalle_venta`
--
ALTER TABLE `detalle_venta`
  MODIFY `iddetalle_venta` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `idrole` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `idusuario` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `ventas`
--
ALTER TABLE `ventas`
  MODIFY `idventa` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `articulos`
--
ALTER TABLE `articulos`
  ADD CONSTRAINT `fk_articulos_categorias1` FOREIGN KEY (`fk_categorias`) REFERENCES `categorias` (`idcategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `detalle_venta`
--
ALTER TABLE `detalle_venta`
  ADD CONSTRAINT `fk_detalle_venta_articulos1` FOREIGN KEY (`fk_articulos`) REFERENCES `articulos` (`idarticulo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_detalle_venta_ventas1` FOREIGN KEY (`fk_venta`) REFERENCES `ventas` (`idventa`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `fk_usuarios_roles` FOREIGN KEY (`fk_roles`) REFERENCES `roles` (`idrole`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `fk_ventas_usuarios1` FOREIGN KEY (`fk_usuario`) REFERENCES `usuarios` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
