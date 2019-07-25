-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 25-07-2019 a las 03:44:10
-- Versión del servidor: 5.7.19
-- Versión de PHP: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `circo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `animal`
--

DROP TABLE IF EXISTS `animal`;
CREATE TABLE IF NOT EXISTS `animal` (
  `codigo_animal` int(11) NOT NULL,
  `nombre_animal` varchar(45) NOT NULL,
  `especie` varchar(45) NOT NULL,
  `nombre_cuidador` varchar(45) NOT NULL,
  `fecha_ingreso` date NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `id_tipo` int(11) NOT NULL,
  `id_tipo_e` int(11) NOT NULL,
  PRIMARY KEY (`codigo_animal`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `animal`
--

INSERT INTO `animal` (`codigo_animal`, `nombre_animal`, `especie`, `nombre_cuidador`, `fecha_ingreso`, `fecha_nacimiento`, `id_tipo`, `id_tipo_e`) VALUES
(1, 'muñeco', 'pastor', 'daniel', '2019-07-04', '2019-07-12', 1, 2),
(2, 'lobo', 'perro', 'luis', '2019-07-03', '2019-07-02', 2, 1),
(3, 'pickachu', 'pokemon', 'azura', '2019-07-03', '2019-07-03', 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo`
--

DROP TABLE IF EXISTS `tipo`;
CREATE TABLE IF NOT EXISTS `tipo` (
  `id` int(11) NOT NULL,
  `Tipo` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipo`
--

INSERT INTO `tipo` (`id`, `Tipo`) VALUES
(1, 'DOMESTICO'),
(2, 'SALVAJE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_e`
--

DROP TABLE IF EXISTS `tipo_e`;
CREATE TABLE IF NOT EXISTS `tipo_e` (
  `id` int(11) NOT NULL,
  `tipo_e` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipo_e`
--

INSERT INTO `tipo_e` (`id`, `tipo_e`) VALUES
(1, 'EXHIBICIÓN'),
(2, 'PUBLICO');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
