DROP DATABASE IF EXISTS `cantina_anglo`;
CREATE DATABASE `cantina_anglo`;
USE `cantina_anglo`;
-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 18, 2022 at 07:41 PM
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
  `fk_caja` int(11) NOT NULL,
  `fk_usuario` int(11) DEFAULT NULL,
  `fecha_inicio` timestamp NULL DEFAULT NULL,
  `fecha_fin` timestamp NULL DEFAULT NULL,
  `monto_inicial` int(11) DEFAULT NULL,
  `monto_final` int(11) DEFAULT NULL,
  `total_ventas` int(11) DEFAULT NULL,
  `Confirmado` tinyint(1) UNSIGNED DEFAULT 0,
  `fk_admin` int(10) UNSIGNED DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `arqueoscaja`
--

INSERT INTO `arqueoscaja` (`id_arqueo`, `fk_caja`, `fk_usuario`, `fecha_inicio`, `fecha_fin`, `monto_inicial`, `monto_final`, `total_ventas`, `Confirmado`, `fk_admin`, `estado`) VALUES
(1, 1, 2, '2022-03-03 15:40:08', '2022-03-03 17:22:40', 50000, 100000, NULL, 1, NULL, 1),
(2, 1, 1, '2022-03-03 17:24:47', '2022-03-03 17:28:44', 20000, 100000, 0, 1, NULL, 1),
(3, 1, 2, '2022-03-03 17:49:08', '2022-03-03 17:55:22', 25000, 50000, 0, 1, NULL, 1),
(4, 1, 2, '2022-03-03 17:55:46', '2022-03-03 20:29:04', 23000, 60000, 0, 1, NULL, 1),
(5, 1, 1, '2022-03-05 23:22:18', NULL, 50000, NULL, NULL, 0, NULL, 1),
(6, 1, 1, '2022-03-05 23:22:20', NULL, 50000, NULL, NULL, 0, NULL, 1),
(7, 1, 1, '2022-03-05 23:22:31', NULL, 50000, NULL, NULL, 0, NULL, 1),
(8, 1, 1, '2022-03-05 23:22:35', '2022-03-08 17:03:58', 50000, 60000, 0, 0, NULL, 1),
(9, 1, 2, '2022-03-08 17:10:12', '2022-03-08 17:10:28', 20000, 22000, 0, 0, NULL, 1),
(10, 1, 1, '2022-03-09 01:16:11', '2022-03-09 01:47:03', 25000, 50000, 0, 0, NULL, 1),
(11, 1, 1, '2022-03-09 02:05:24', '2022-03-09 02:05:37', 10000, 15000, 0, 0, NULL, 1),
(12, 1, 1, '2022-03-09 02:05:24', '2022-03-09 02:05:37', 10000, 10000, 0, 1, NULL, 1),
(13, 1, 1, '2022-03-09 06:03:17', '2022-03-09 06:03:42', 220000, 300000, 0, 0, NULL, 1),
(14, 1, 1, '2022-03-09 06:03:17', '2022-03-09 06:03:42', 220000, 300000, 0, 1, NULL, 1),
(15, 1, 1, '2022-03-09 06:20:36', '2022-03-09 06:20:57', 230000, 500000, 0, 1, NULL, 1),
(16, 1, 1, '2022-03-09 06:22:18', '2022-03-09 06:23:06', 322000, 400000, 0, 1, NULL, 1),
(17, 1, 1, '2022-03-09 06:25:58', '2022-03-09 06:26:14', 50000, 400000, 0, 1, NULL, 1),
(18, 1, 1, '2022-03-09 06:27:05', '2022-03-09 06:27:15', 15000, 30000, 0, 1, NULL, 1),
(19, 1, 3, '2022-03-09 06:29:07', '2022-03-09 06:30:51', 10000, 22000, 1, 1, NULL, 1),
(20, 1, 1, '2022-03-09 07:27:12', '2022-03-09 07:27:51', 5000, 11000, 1, 1, NULL, 1),
(21, 1, 2, '2022-03-09 07:31:01', '2022-03-09 07:32:01', 250000, 256000, 1, 1, NULL, 1),
(22, 1, 1, '2022-03-09 07:33:55', '2022-03-09 07:34:40', 5000, 9000, 1, 1, NULL, 1),
(23, 1, 2, '2022-03-09 13:45:09', '2022-03-09 13:46:09', 431000, 600000, 0, 1, NULL, 1),
(24, 1, 2, '2022-03-09 13:52:03', '2022-03-09 13:52:24', 123654, 764136, 0, 1, NULL, 1),
(25, 1, 2, '2022-03-09 16:02:20', '2022-03-10 01:55:35', 600873, 650000, 2, 1, NULL, 1),
(26, 1, 1, '2022-03-10 02:44:27', '2022-03-10 03:01:25', 20000, 35000, 3, 1, NULL, 1),
(27, 1, 1, '2022-03-10 03:03:51', '2022-03-15 01:31:51', 10000, 20000, 1, 1, NULL, 1),
(28, 1, 3, '2022-03-15 02:28:01', '2022-03-15 02:28:16', 15000, 20000, 0, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `articulos`
--

CREATE TABLE `articulos` (
  `idarticulo` int(11) NOT NULL,
  `codigo` varchar(50) DEFAULT NULL,
  `precio_venta` int(10) UNSIGNED NOT NULL,
  `costo` int(10) UNSIGNED DEFAULT NULL,
  `stock` int(11) UNSIGNED DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `estado` tinyint(1) UNSIGNED NOT NULL DEFAULT 1,
  `fk_categorias` int(10) UNSIGNED NOT NULL,
  `date_arti` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `articulos`
--

INSERT INTO `articulos` (`idarticulo`, `codigo`, `precio_venta`, `costo`, `stock`, `descripcion`, `estado`, `fk_categorias`, `date_arti`) VALUES
(1, '7842714004690', 3000, 1500, 2, 'caja', 1, 1, '2022-03-09 16:36:24'),
(2, '3123211234', 4000, 2000, 2, 'Empanada ', 1, 1, '2022-02-25 03:00:00'),
(3, '123456789', 2500, 4000, 2, 'Pancho con queso', 1, 3, '2022-03-10 02:58:37'),
(4, '123654987', 1000, 200, 5, 'Turron mani', 1, 4, '2022-03-01 00:02:08'),
(5, '321654987', 3000, 6000, 5, 'Pan dulce', 1, 3, '2022-02-25 03:00:00'),
(6, '951874632', 5000, 500, 10, 'Empanada', 1, 2, '2022-02-25 03:00:00'),
(8, '89632563256', 2500, 500, 2, 'Algo', 1, 4, '2022-02-25 03:00:00'),
(9, '3216548946', 2000, 500, 2, 'Prue', 1, 3, '2022-02-25 03:00:00'),
(11, '65198416495', 5000, 2000, 5, 'Tortilla', 1, 5, NULL),
(12, '13516849651', 6000, 2500, 5, 'Prueba5', 1, 9, '2022-02-25 20:48:35');

-- --------------------------------------------------------

--
-- Table structure for table `cajas`
--

CREATE TABLE `cajas` (
  `id_caja` int(11) NOT NULL,
  `num_caja` varchar(15) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 1,
  `fecha_alta` timestamp NULL DEFAULT current_timestamp(),
  `fecha_mod` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cajas`
--

INSERT INTO `cajas` (`id_caja`, `num_caja`, `nombre`, `estado`, `fecha_alta`, `fecha_mod`) VALUES
(1, '1', 'Caja 1', 1, '2022-03-03 01:09:33', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `categorias`
--

CREATE TABLE `categorias` (
  `idcategoria` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `estado` tinyint(1) UNSIGNED NOT NULL DEFAULT 1,
  `date_categorias` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `categorias`
--

INSERT INTO `categorias` (`idcategoria`, `nombre`, `estado`, `date_categorias`) VALUES
(1, 'categoria 1', 1, '2022-02-25 03:00:00'),
(2, 'categoria 2', 1, '2022-02-25 03:00:00'),
(3, 'Lacteos', 1, '2022-02-25 03:00:00'),
(4, 'Dani', 1, '2022-02-25 03:00:00'),
(5, 'Frituras', 1, NULL),
(6, 'Prueba', 1, NULL),
(7, 'Prueba2', 1, NULL),
(8, 'Prueba3', 1, '2022-02-25 20:42:54'),
(9, 'Prueba4', 1, '2022-02-25 20:46:15');

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
  `date_detalleVenta` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detalle_venta`
--

INSERT INTO `detalle_venta` (`iddetalle_venta`, `fk_articulos`, `fk_venta`, `cantidad`, `precio`, `descuento`, `estado`, `date_detalleVenta`) VALUES
(1, 1, 15, 1, 3000, NULL, 1, '2022-03-01 00:12:47'),
(2, 4, 15, 1, 1000, NULL, 1, '2022-03-01 00:12:47'),
(3, 4, 15, 1, 1000, NULL, 1, '2022-03-01 00:12:47'),
(4, 4, 15, 1, 1000, NULL, 1, '2022-03-01 00:12:47'),
(5, 4, 16, 1, 1000, NULL, 1, '2022-03-01 03:19:33'),
(6, 4, 16, 1, 1000, NULL, 1, '2022-03-01 03:19:33'),
(7, 4, 16, 1, 1000, NULL, 1, '2022-03-01 03:19:33'),
(8, 4, 16, 1, 1000, NULL, 1, '2022-03-01 03:19:33'),
(9, 4, 16, 1, 1000, NULL, 1, '2022-03-01 03:19:33'),
(10, 4, 17, 1, 1000, NULL, 1, '2022-03-01 03:21:20'),
(11, 4, 18, 1, 1000, NULL, 1, '2022-03-01 03:25:45'),
(12, 4, 18, 1, 1000, NULL, 1, '2022-03-01 03:25:45'),
(13, 4, 18, 1, 1000, NULL, 1, '2022-03-01 03:25:45'),
(14, 4, 18, 1, 1000, NULL, 1, '2022-03-01 03:25:45'),
(15, 4, 18, 1, 1000, NULL, 1, '2022-03-01 03:25:45'),
(16, 4, 19, 1, 1000, NULL, 1, '2022-03-01 03:40:57'),
(17, 4, 19, 1, 1000, NULL, 1, '2022-03-01 03:40:57'),
(18, 4, 20, 1, 1000, NULL, 1, '2022-03-03 00:19:28'),
(19, 4, 20, 1, 1000, NULL, 1, '2022-03-03 00:19:28'),
(20, 1, 20, 1, 3000, NULL, 1, '2022-03-03 00:19:28'),
(21, 1, 23, 1, 3000, NULL, 1, '2022-03-09 06:29:39'),
(22, 1, 23, 1, 3000, NULL, 1, '2022-03-09 06:29:39'),
(23, 1, 23, 1, 3000, NULL, 1, '2022-03-09 06:29:39'),
(24, 1, 23, 1, 3000, NULL, 1, '2022-03-09 06:29:39'),
(25, 1, 24, 1, 3000, NULL, 1, '2022-03-09 07:27:33'),
(26, 1, 24, 1, 3000, NULL, 1, '2022-03-09 07:27:33'),
(27, 1, 25, 1, 3000, NULL, 1, '2022-03-09 07:31:21'),
(28, 1, 25, 1, 3000, NULL, 1, '2022-03-09 07:31:21'),
(29, 4, 26, 1, 1000, NULL, 1, '2022-03-09 07:34:24'),
(30, 4, 26, 1, 1000, NULL, 1, '2022-03-09 07:34:24'),
(31, 4, 26, 1, 1000, NULL, 1, '2022-03-09 07:34:24'),
(32, 4, 26, 1, 1000, NULL, 1, '2022-03-09 07:34:24'),
(33, 1, 27, 1, 3000, NULL, 1, '2022-03-09 16:23:06'),
(34, 1, 27, 1, 3000, NULL, 1, '2022-03-09 16:23:06'),
(35, 1, 27, 1, 3000, NULL, 1, '2022-03-09 16:23:06'),
(36, 1, 27, 1, 3000, NULL, 1, '2022-03-09 16:23:06'),
(37, 1, 27, 1, 3000, NULL, 1, '2022-03-09 16:23:06'),
(38, 1, 27, 1, 3000, NULL, 1, '2022-03-09 16:23:06'),
(39, 1, 28, 1, 3000, NULL, 1, '2022-03-09 16:29:14'),
(40, 1, 28, 1, 3000, NULL, 1, '2022-03-09 16:29:14'),
(41, 1, 28, 1, 3000, NULL, 1, '2022-03-09 16:29:14'),
(42, 3, 30, 1, 2500, NULL, 1, '2022-03-10 02:44:52'),
(43, 3, 30, 1, 2500, NULL, 1, '2022-03-10 02:44:52'),
(44, 3, 31, 1, 2500, NULL, 1, '2022-03-10 02:51:57'),
(45, 3, 31, 1, 2500, NULL, 1, '2022-03-10 02:51:57'),
(46, 3, 32, 1, 2500, NULL, 1, '2022-03-10 02:59:01'),
(47, 3, 32, 1, 2500, NULL, 1, '2022-03-10 02:59:01'),
(48, 3, 32, 1, 2500, NULL, 1, '2022-03-10 02:59:01'),
(49, 4, 34, 1, 1000, NULL, 1, '2022-03-10 03:04:11'),
(50, 4, 34, 1, 1000, NULL, 1, '2022-03-10 03:04:11');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `idrole` int(11) NOT NULL,
  `rol` varchar(30) NOT NULL,
  `desc` varchar(250) DEFAULT NULL,
  `estado` tinyint(1) UNSIGNED NOT NULL DEFAULT 1,
  `date_rol` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`idrole`, `rol`, `desc`, `estado`, `date_rol`) VALUES
(1, 'ADMIN', 'Administrador del sistema, tiene todas las opciones disponibles', 1, '2022-02-25 03:00:00'),
(2, 'VENDEDOR', 'Puede realizar ventas, anularlas, cargar productos nuevos y acutalizar el stock', 1, '2022-02-25 03:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

CREATE TABLE `usuarios` (
  `idusuario` int(11) UNSIGNED NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `estado` tinyint(1) UNSIGNED NOT NULL DEFAULT 1,
  `pass` varchar(40) DEFAULT NULL,
  `fk_roles` int(11) NOT NULL,
  `date_user` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`idusuario`, `nombre`, `estado`, `pass`, `fk_roles`, `date_user`) VALUES
(1, 'Lucas', 1, '1234', 1, '2022-02-25 03:00:00'),
(2, 'Dani', 1, '1234', 1, '2022-02-25 03:00:00'),
(3, 'Vendedor', 1, '1234', 2, '2022-02-25 03:00:00'),
(4, 'Residente', 1, '123456', 1, '2022-03-05 23:15:01');

-- --------------------------------------------------------

--
-- Table structure for table `ventas`
--

CREATE TABLE `ventas` (
  `idventa` int(11) NOT NULL,
  `id_caja` int(11) NOT NULL,
  `fecha` timestamp NULL DEFAULT current_timestamp(),
  `total` int(11) DEFAULT NULL,
  `fk_usuario` int(10) UNSIGNED NOT NULL,
  `estado` tinyint(1) UNSIGNED NOT NULL DEFAULT 1,
  `ajuste` tinyint(1) UNSIGNED DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ventas`
--

INSERT INTO `ventas` (`idventa`, `id_caja`, `fecha`, `total`, `fk_usuario`, `estado`, `ajuste`) VALUES
(1, 1, '2022-02-26 18:01:04', 3000, 1, 1, 0),
(2, 1, '2022-02-26 18:13:05', 3000, 1, 1, 0),
(3, 1, '2022-02-26 18:13:08', 3000, 1, 1, 0),
(4, 1, '2022-02-26 18:13:37', 6000, 1, 1, 0),
(5, 1, '2022-02-26 18:13:39', 6000, 1, 1, 0),
(6, 1, '2022-02-26 21:17:05', 9000, 1, 1, 0),
(7, 1, '2022-02-26 21:32:02', 15000, 3, 1, 0),
(8, 1, '2022-02-26 21:37:27', 18000, 2, 1, 0),
(9, 1, '2022-02-26 21:55:21', 12000, 3, 1, 0),
(10, 1, '2022-02-27 19:59:15', 6000, 3, 1, 0),
(11, 1, '2022-02-28 19:14:42', 3000, 1, 1, 0),
(12, 1, '2022-02-28 23:52:12', 5700, 3, 1, 0),
(13, 1, '2022-02-28 23:55:33', 3000, 2, 1, 0),
(14, 1, '2022-03-01 00:08:25', 4000, 2, 1, 0),
(15, 1, '2022-03-01 00:12:47', 6000, 2, 1, 0),
(16, 1, '2022-03-01 03:19:33', 5000, 1, 1, 0),
(17, 1, '2022-03-01 03:21:20', 1000, 1, 1, 0),
(18, 1, '2022-03-01 03:25:45', 5000, 1, 1, 0),
(19, 1, '2022-03-01 03:40:57', 2000, 3, 1, 0),
(20, 1, '2022-03-03 00:19:28', 5000, 1, 1, 0),
(21, 1, '2022-03-04 23:50:16', 0, 2, 1, 0),
(22, 1, '2022-03-04 23:55:20', 0, 2, 1, 0),
(23, 1, '2022-03-09 06:29:39', 12000, 3, 1, 0),
(24, 1, '2022-03-09 07:27:33', 6000, 1, 1, 0),
(25, 1, '2022-03-09 07:31:21', 6000, 2, 1, 0),
(26, 1, '2022-03-09 07:34:24', 4000, 1, 1, 0),
(27, 1, '2022-03-09 16:23:06', 18000, 1, 1, 0),
(28, 1, '2022-03-09 16:29:14', 9000, 1, 1, 0),
(29, 1, '2022-03-10 02:38:45', 22127, 1, 1, 1),
(30, 1, '2022-03-10 02:44:51', 5000, 1, 1, 0),
(31, 1, '2022-03-10 02:51:57', 5000, 1, 1, 0),
(32, 1, '2022-03-10 02:59:01', 7500, 1, 1, 0),
(33, 1, '2022-03-10 03:01:48', -2500, 1, 1, 1),
(34, 1, '2022-03-10 03:04:11', 2000, 1, 1, 0),
(35, 1, '2022-03-15 01:34:20', 8000, 1, 1, 1),
(36, 1, '2022-03-15 02:41:21', 5000, 1, 1, 1);

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
-- Indexes for table `cajas`
--
ALTER TABLE `cajas`
  ADD PRIMARY KEY (`id_caja`);

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
  ADD UNIQUE KEY `USERS_Nombres` (`nombre`);

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
  MODIFY `id_arqueo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `articulos`
--
ALTER TABLE `articulos`
  MODIFY `idarticulo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `cajas`
--
ALTER TABLE `cajas`
  MODIFY `id_caja` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `categorias`
--
ALTER TABLE `categorias`
  MODIFY `idcategoria` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `detalle_venta`
--
ALTER TABLE `detalle_venta`
  MODIFY `iddetalle_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `idrole` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `idusuario` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `ventas`
--
ALTER TABLE `ventas`
  MODIFY `idventa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

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
-- Constraints for table `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `fk_ventas_usuarios1` FOREIGN KEY (`fk_usuario`) REFERENCES `usuarios` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
