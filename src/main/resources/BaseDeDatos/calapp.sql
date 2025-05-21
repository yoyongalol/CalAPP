-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-05-2025 a las 23:10:07
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `calapp`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alimento`
--

CREATE TABLE `alimento` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `calorias` int(11) DEFAULT NULL,
  `proteinas` double DEFAULT NULL,
  `grasas` double DEFAULT NULL,
  `carbohidratos` double DEFAULT NULL,
  `categoria` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `alimento`
--

INSERT INTO `alimento` (`id`, `nombre`, `calorias`, `proteinas`, `grasas`, `carbohidratos`, `categoria`) VALUES
(1, 'pechuga de pollo 100g', 327, 24, 8, 0, 'carne'),
(2, 'manzana', 52, 0.26, 0.17, 13, 'fruta'),
(3, 'Plátano', 89, 1.09, 0.33, 23, 'fruta');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alimento_entrada`
--

CREATE TABLE `alimento_entrada` (
  `id_entrada` int(11) NOT NULL,
  `id_alimento` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entradadiaria`
--

CREATE TABLE `entradadiaria` (
  `id` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `email_usuario` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `entradadiaria`
--

INSERT INTO `entradadiaria` (`id`, `fecha`, `email_usuario`) VALUES
(1, '2025-05-17', NULL),
(2, '2025-05-17', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `email` varchar(35) NOT NULL,
  `contraseña` varchar(35) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `altura` double DEFAULT NULL,
  `objetivo_calorias` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`email`, `contraseña`, `nombre`, `edad`, `peso`, `altura`, `objetivo_calorias`) VALUES
('asd', 'asd', 'asd', 12, 12, 12, 0),
('dilan@gmail.com', 'dylan', 'Dilan', 19, 78, 173, 0),
('hola@gmail.com', '1303', 'adios', 12, 20, 120, 1800),
('javi@gmail.com', '1234', 'javi', 80, 200, 175, 3000),
('jose@gmail.com', 'jose', 'jose', 20, 100, 157, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alimento`
--
ALTER TABLE `alimento`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `alimento_entrada`
--
ALTER TABLE `alimento_entrada`
  ADD PRIMARY KEY (`id_entrada`,`id_alimento`),
  ADD KEY `id_alimento` (`id_alimento`);

--
-- Indices de la tabla `entradadiaria`
--
ALTER TABLE `entradadiaria`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_email_usuario` (`email_usuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `email_3` (`email`),
  ADD KEY `email_2` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alimento`
--
ALTER TABLE `alimento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `entradadiaria`
--
ALTER TABLE `entradadiaria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alimento_entrada`
--
ALTER TABLE `alimento_entrada`
  ADD CONSTRAINT `alimento_entrada_ibfk_1` FOREIGN KEY (`id_entrada`) REFERENCES `entradadiaria` (`id`),
  ADD CONSTRAINT `alimento_entrada_ibfk_2` FOREIGN KEY (`id_alimento`) REFERENCES `alimento` (`id`);

--
-- Filtros para la tabla `entradadiaria`
--
ALTER TABLE `entradadiaria`
  ADD CONSTRAINT `fk_email_usuario` FOREIGN KEY (`email_usuario`) REFERENCES `usuario` (`email`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
