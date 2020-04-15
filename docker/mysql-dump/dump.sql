-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 14-04-2020 a las 18:03:07
-- Versión del servidor: 5.7.29-0ubuntu0.18.04.1
-- Versión de PHP: 7.2.24-0ubuntu0.18.04.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dani`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categories`
--

CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'Ayuda Humanitaria'),
(2, 'Cooperación al desarrollo'),
(3, 'Cultura y arte social'),
(4, 'Derechos humanos'),
(5, 'Inmigración,refugio y asilo'),
(6, 'Medio ambiente'),
(7, 'Personas en riesgo de exclusión'),
(8, 'Personas sin hogar'),
(9, 'Protección de animales'),
(10, 'Salud');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comments`
--

CREATE TABLE `comments` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `message` longtext,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `comments`
--

INSERT INTO `comments` (`id`, `email`, `message`, `name`) VALUES
(288, 'theroxmine@hotmail.es', 'Esta página es genial,¡no me extrañaría que en el futuro ganase el Nobel de la PAZ!', 'Daniel'),
(510, 'angular@angular.com', 'Perfecto', 'Angular');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(536),
(536),
(536),
(536),
(536),
(536);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequences`
--

CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `hibernate_sequences`
--

INSERT INTO `hibernate_sequences` (`sequence_name`, `next_val`) VALUES
('default', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `likes`
--

CREATE TABLE `likes` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `volunteering_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `likes`
--

INSERT INTO `likes` (`id`, `user_id`, `volunteering_id`) VALUES
(209, 65, 97);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ngos`
--

CREATE TABLE `ngos` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `description` longtext,
  `email` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `postal` varchar(255) DEFAULT NULL,
  `responsiblename` varchar(255) DEFAULT NULL,
  `responsiblesurname` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ngos`
--

INSERT INTO `ngos` (`id`, `address`, `description`, `email`, `image`, `name`, `password`, `postal`, `responsiblename`, `responsiblesurname`, `telephone`) VALUES
(15, 'Calle Centro Comercial Mocha Chica, 0 S/N, Villanueva de la cañada', 'Tenemos el placer de darle la bienvenida a la Fundación de Ayuda a los Animales (F.A.A.).En donde el respeto a los animales, al amor y el cariño que sentimos hacia ellos es lo que mueve a nuestra Fundación a luchar día a día por todos aquellos animales que sufren el comportamiento inhumano con el que algunas personas premian su ayuda y fidelidad. Por eso desde la Fundación de Ayuda a los Animales luchamos para que todos los animales puedan tener una vida digna y para que animales abandonados puedan recibir el amor, la amistad y lealtad que merecen. Desde la FAA apoyamos a Organizaciones y Albergues dedicados a esta difícil lucha que es la Defensa de los Animales. Por otro lado, consideramos que colaborar en la lucha por un mundo más justo con los animales es colaborar por un mundo más solidario y menos violento en general', 'informa@aventura.org', 'image-1586864842471.jpg', 'Fundación de Ayuda a los Animales ', '$2a$10$0ijsEkj0Ievjanccb3tIhupNYybgTVHZd8s0o0kqhC2arxFxHwdDe', '28691', 'Diez', 'Diez', '912345215'),
(29, 'Calle de Cavanilles, 33, Madrid', 'Médicos Sin Fronteras es una organización de acción médico-humanitaria: asistimos a personas amenazadas por conflictos armados, violencia, epidemias o enfermedades olvidadas, desastres naturales y exclusión de la atención médica.  Nuestros equipos están formados por miles de profesionales de la salud, especialistas en logística y personal administrativo, unidos y regidos por nuestra carta magna en pos de brindar asistencia humanitaria. La acción humanitaria es un gesto solidario de sociedad civil a sociedad civil, de persona a persona, cuya finalidad es preservar la vida y aliviar el sufrimiento de otros seres humanos: esta es nuestra razón de ser. Nuestro accionar está guiado por la ética médica y los principios de imparcialidad, independencia y neutralidad, que se encuentran plasmados en nuestra carta magna.', 'info@msf.es', 'image-1586864863707.jpg', 'Médicos Sin Fronteras', '$2a$10$.vdJO5ICvZmlFjmIZwynqOsjOR1oUFqyWRStHJeXIQAM2xMiDkk96', '28007', 'Ramón', 'Ramirez', '915411375'),
(54, 'Calle de Bravo Murillo, 178, Madrid', 'Somos una ONG apartidista y aconfesional que lucha contra la pobreza y la desigualdad. Impulsamos la dignidad y la solidaridad para la construcción de un mundo justo.\r\n\r\nTenemos más de 38 años de historia. Con tu ayuda hoy trabajamos en 20 países de América Latina, Asia, África y Europa, incluidos España y Portugal. Además, estamos desarrollando nuestra presencia en 3 nuevos países. En total apoyamos a más de 1,4 millones de personas.', 'informacion@ayudaenaccion.org', 'image-1586864881641.jpg', 'Ayuda en acción', '$2a$10$.vdJO5ICvZmlFjmIZwynqOsjOR1oUFqyWRStHJeXIQAM2xMiDkk96', '28020', 'Jaime', 'Montalvo', '900858588'),
(55, 'Calle del Dr. Esquerdo, 138, Madrid', 'Todos los niños tienen derecho a un futuro. En España y en todo el mundo, trabajamos cada día para asegurar que todos los niños sobreviven, aprenden y están protegidos frente a la violencia. Cuando se produce una emergencia, y los niños son más vulnerables, somos siempre los primeros en llegar y los últimos en marcharnos. Atendemos las necesidades de los niños y nos aseguramos de que sus voces son escuchadas. Conseguimos cambios duraderos en la vida de millones de niños, incluso en aquellos a los que cuesta más llegar.  Hacemos todo lo que sea necesario para lograr que todos los niños, cada día y cuando ocurre una emergencia, puedan cambiar sus vidas y el futuro que estamos construyendo juntos.', 'recepcion.central@savethechildren.org', 'image-1586864912954.jpg', 'Save the children', '$2a$10$.vdJO5ICvZmlFjmIZwynqOsjOR1oUFqyWRStHJeXIQAM2xMiDkk96', '28007', 'Andrés', 'Conde', '915130500'),
(516, 'Calle de Mauricio Legendre, 36,Madrid', '¿Por qué siguen muriendo 15.000 niños cada día por causas evitables? ¿Por qué sigue habiendo grupos armados que utilizan a niños con fines militares? ¿Por qué millones de niños en Siria no pueden ir al colegio?  Seguro que muchas veces te has hecho este tipo de preguntas. Desgraciadamente, y a pesar de que se han conseguido muchos avances, el mundo sigue siendo un lugar injusto y peligroso para muchos niños.  Por todo esto, en UNICEF tenemos una misión muy clara: conseguir que los más de 7.000 millones de habitantes del planeta conozcan y defiendan los derechos de la infancia en todo momento y en todo lugar.  Porque solo así conseguiremos cambios reales y duraderos en la vida de los niños y niñas.', 'unicef@unicef.es', 'image-1586866513008.jpg', 'Unicef', '$2a$10$RWR9YGVzTSfAO/.nr5CBueqbp/BqoCmmc0C44ESORbeeTq82hgcIW', ' 28046 ', 'Ludwik ', 'Rajchman', '91 378 95 55'),
(518, 'Gran Vía de San Francisco, 8,  Madrid', 'WWF España forma parte de la red de WWF, la mayor organización internacional independiente dedicada a la defensa de la naturaleza y el medio ambiente. Nuestra misión es conservar la naturaleza, sus hábitats y especies, y luchar contra las amenazas sobre la vida en la Tierra.  Fundada en 1961, hoy trabajamos en más de 100 países y contamos con el apoyo de cerca de 5 millones de miembros en todo el mundo.  Para alcanzar nuestra misión, trabajamos con todas nuestras fuerzas, pasión y creatividad, para defender la naturaleza y las personas. ', 'info@wwf.es', 'image-1586866558203.jpg', 'Fondo Mundial para la Naturaleza', '$2a$10$5yqJTDj8esHzJQrFw87SiOcBGyo7t0IP2ccTzNarH8ybp6.7LlfJi', '28005', ' Bernardo ', 'de Lippe-Biesterfeld', ' 913 54 05 78'),
(520, ' Av. del General Perón, 32, 2º Izquierda, Madrid', 'Nos esforzamos por garantizar que todas las personas tengan derecho a buscar asilo y encontrar un refugio seguro en otro Estado, con la opción de regresar eventualmente a su hogar, integrarse o reasentarse.  Durante los momentos de desplazamiento, proporcionamos asistencia de emergencia crítica, como por ejemplo agua potable, saneamiento y atención médica, así como albergue, mantas, artículos para el hogar y, a veces, alimentos. También organizamos transporte y paquetes de asistencia para las personas que regresan a sus hogares, así como proyectos de generación de ingresos para quienes se reasientan.  Nuestra asistencia transforma las vidas destruidas.', 'spama@unhcr.org', 'image-1586876839267.jpg', 'Alto Comisionado de las Naciones Unidas para los Refugiados', '$2a$10$WKyHTY5B13JTcDMrpq9TjO0LE3Ylnirk2TrflvEfxmEqSL/ZvOItK', '28020 ', ' Filippo ', 'Grandi', ' 661 70 64 62'),
(526, 'Calle Europa, 8, Madrid', 'Acción para el Desarrollo y la Igualdad es una organización no lucrativa, apolítica y aconfesional que trabaja en favor de un desarrollo humano sostenible, justo y solidario a través de la promoción y defensa de los derechos humanos, sociales, económicos y culturales, la equidad de género y la participación comunitaria.  Nuestras líneas de acción se encuadran en los Objetivos del Milenio dando prioridad a aquellos comprometidos con la erradicación de la pobreza y la promoción de la equidad de género tanto en el ámbito nacional (Acción Social) como en el internacional (Cooperación para el Desarrollo).', 'accion@accion.es', 'image-1586861967473.jpg', 'Acción para el Desarrollo y la Igualdad', '$2a$10$SPKIjlh6ymYaTJTdGgjLz.Oh0sw05C/uchEn/oHuhq1eRscuoCE4e', '28027', 'Manuel', 'Sánchez Barajas', '913456724'),
(527, 'La fiabougou, Bamako, MALI', 'Soleil d’Afrique es una ONG presente en Benin una de las regiones mas pobres de África. El objetivo principal es que la gente de los pueblos y sobretodo los niños , puedan beneficiarse de todos los tratamientos médicos y medicinas necesarios. Asímismo hacer que la economia pueda desarrollarse de manera compatible y respetando las costumbres y tradiciones locales. Los costes principales del Centro de Diagnóstico y Análisis , así como el anexo del Hospital , serán posibles por el apoyo del proyecto Agrícola que es la primera actividad para financiar el proyecto Tutela Sanitaria y al mismo tiempo es de gran ayuda al desarrollo económico y social.', 'info@soleildafrique.org', 'image-1586861857148.jpg', 'Soleil d´Afrique', '$2a$10$oa3qiW23Xgjtr94Cg.gBwO6GGYNkqnegpyfpNIGE3dNtcpgju9s12', '26054', 'Sara', 'Alonso Pérez', '94567821');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `registered_at` datetime(6) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `city`, `email`, `image`, `name`, `password`, `registered_at`, `surname`, `telephone`) VALUES
(61, 'Toledo', 'rhernan@gmail.com', 'image-1586863785075.jpg', 'Raul', '$2a$10$Pj0ssI/7D8a6rvW9eWYXj.YxRzpIt1t.y7joag5NNFbFPC6HVRuvC', '2020-03-02 00:00:00.000000', 'Hernández', '677555444'),
(65, 'Pamplona', 'rosalpz95@gmail.com', 'image-1586863630051.jpg', 'Rosa', '$2a$10$DHtCZvvMoP9knT5w4hrEku2yh0KeKs97S.Ir3NMUsxgI/PclOZgMm', '2020-03-02 00:00:00.000000', 'Merino', '680523542'),
(163, 'Murcia', 'jesushernandez@gmail.com', 'image-1586863842873.jpg', 'Jesús', '$2a$10$DHtCZvvMoP9knT5w4hrEku2yh0KeKs97S.Ir3NMUsxgI/PclOZgMm', '2020-03-02 00:00:00.000000', 'Hernández', '688555444'),
(289, 'Sevilla', 'esthrn3@gmail.com', 'image-1586863877009.jpg', 'Esteban', '$2a$10$DHtCZvvMoP9knT5w4hrEku2yh0KeKs97S.Ir3NMUsxgI/PclOZgMm', NULL, 'Hernández', '888888888'),
(531, 'Albacete', 'sandrajkl@gamil.com', 'image-1586864236343.jpg', 'Sandra', '$2a$10$vHX6aKKoOxme0GGgDxyjIO5yF2knVUj3w7lJoHylWz4NB9E29DQ5S', '2020-04-13 22:00:00.000000', 'Garcia Ramírez', '645789123'),
(532, 'Huesca', 'mariasr89@gmail.com', 'image-1586864394918.jpg', 'María', '$2a$10$vkFxzl1kHOpxncktD1f.Ouji3tQfScagOYDq0hTs64twzeVeI4iHa', '2020-04-13 22:00:00.000000', 'Santos Ruiz', '659781234'),
(533, 'Guadalajara', 'alesiadf@gamil.com', 'image-1586864839166.jpg', 'Alexia', '$2a$10$BRbVlMVnAsgh/PO7Q0uyfe.agQ23q0wFr2/fDuoowAedCgaHEx/nK', '2020-04-13 22:00:00.000000', 'Pérez Gil', '654823159'),
(534, 'Bilbao', 'beacf@gmail.com', 'image-1586864962151.jpg', 'Beatriz', '$2a$10$vpNNdA0fAfpe.yDsD9xrquFOnxUvqQa9mTsQwavbismIQGgMABGj2', '2020-04-13 22:00:00.000000', 'Chamizo Fernández', '654213987'),
(535, 'Móstoles', 'marcoscl@outlook.es', 'image-1586865188603.jpg', 'Marcos', '$2a$10$WKyHTY5B13JTcDMrpq9TjO0LE3Ylnirk2TrflvEfxmEqSL/ZvOItK', '2020-04-13 22:00:00.000000', 'Cara Libro', '689459457');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users_volunteerings`
--

CREATE TABLE `users_volunteerings` (
  `id` bigint(20) NOT NULL,
  `date` datetime(6) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `volunteering_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `users_volunteerings`
--

INSERT INTO `users_volunteerings` (`id`, `date`, `user_id`, `volunteering_id`) VALUES
(198, '2020-03-08 22:42:56.240000', 65, 98),
(203, '2020-03-08 23:28:40.512000', 65, 4),
(208, '2020-03-09 00:51:09.453000', 65, 97);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
(65, 'ROLE_USER'),
(61, 'ROLE_USER'),
(163, 'ROLE_USER'),
(289, 'ROLE_USER'),
(535, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `volunteerings`
--

CREATE TABLE `volunteerings` (
  `id` bigint(20) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `description` longtext,
  `email` varchar(255) DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `ong_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `volunteerings`
--

INSERT INTO `volunteerings` (`id`, `city`, `description`, `email`, `enddate`, `image`, `name`, `startdate`, `category_id`, `ong_id`) VALUES
(4, 'Alcalá de Henares', 'Este proyecto plantea, como objetivo fundamental, desarrollar y fortalecer las capacidades de respuesta de las Asambleas Locales, Comarcales e Insulares. Tiene como actores principales del mismo a los miembros de Órganos de Gobierno y personas con responsabilidad en el ámbito de la gestión de Cruz Roja Española en la red local. Decimos que dichas personas (presidentes/as, vicepresidentes/as, referentes de las distintas áreas/planes, etc.) serán los actores principales ya que a ellos y ellas corresponderá incorporar e implementar, en sus ámbitos correspondientes, las líneas de trabajo marcadas en este proyecto, con el apoyo de las estructuras de soporte de otros ámbitos de Cruz Roja, fundamentalmente provinciales o autonómicos uniprovinciales. El proyecto quiere incidir sobre los ámbitos clave que afectan a la realidad local: - las personas (sus capacidades y relaciones) - los procesos y metodologías - la gestión de los recursos. Para todo esto, por una parte, se plantea la mejora de las competencias de los Órganos de Gobierno y estructuras de gestión para, entre otros aspectos, asegurar el adecuado liderazgo que les corresponde. Asimismo, pretende articular mecanismos efectivos de relación y coordinación interna (dentro de la propia asamblea) y externa (con otros ámbitos de Cruz Roja, así como con agentes externos). Y todo ello, además, desarrollando estrategias para garantizar una gestión adecuada con las personas que componen nuestra base social, fomentando la participación a todos los niveles. Por otra parte, se plantea la adecuada implementación de procesos y metodologías, adecuadas a la realidad local, que mejoren la eficacia y eficiencia en nuestras respuestas. Siendo uno de los procesos clave la planificación anual del trabajo de la asamblea local/comarcal/insular, ajustada a la realidad y necesidades de su entorno y a la estrategia de la propia Organización; apostando, de forma decidida, por la evaluación y la mejora continua. Por último, el proyecto persigue propiciar las condiciones para una adecuada y eficiente gestión de los recursos (económicos, materiales, infraestructuras, humanos, etc.) con que la asamblea cuenta para lograr el cumplimiento de su misión. ', 'informa@cruzroja.es', '2020-08-09', 'image-1586866631664.jpg', 'Agente de desarrollo local', '2020-03-08', 9, 15),
(97, 'Buenos Aires', '¡Por un poco de música en nuestras vidas! Además de fomentar valores positivos y contar con múltiples beneficios, la música refuerza capacidades y aptitudes en los más pequeños. Desde Ayuda en Acción te invitamos a participar en nuestro concierto solidario Acordes con Solidaridad el próximo 4 de Marzo de 2021 en el Teatro Real de Madrid. Además de disfrutar de la mano del director Pablo Heras-Casado y la Orquesta Sinfónica de Madrid, estarás contribuyendo a una buena causa: luchar contra la pobreza infantil en España.  Todos los fondos recaudados serán destinados a nuestro programa Aquí también, que apoya a la infancia y familias en riesgo de pobreza y exclusión en nuestro país. ¿Quieres disfrutar de una de las actividades solidarias para niños más esperadas del año? ¡Te esperamos!', 'informacion@ayudaenaccion.org', '2021-03-05', 'image-1586875360032.jpg', 'Concierto solidario contra la pobreza y la marginación', '2021-03-04', 7, 54),
(98, 'Pamplona', 'El Programa de Voluntariado de Cuidado Infantil con sede en Pamplona, en una casa de acogida para niños. La casa tiene alrededor de 6-8 niños en el grupo de edad de hasta 3 años en un momento dado. Si bien la mayoría de los niños son adoptados cuando cruzan tres, hay otros niños que necesitan atención y cuidado continuos. Los viajeros en su año sabático que buscan oportunidades para trabajar para niños pueden optar por ser voluntarios para este proyecto. Esta puede ser una gran experiencia de aprendizaje para los voluntarios, ya que pueden pasar tiempo con los niños pequeños y probar su propio nivel de paciencia y resistencia. Este programa es muy adecuado para aquellos que aman estar cerca de los niños y pueden manejarlos realmente bien.', 'informa@aventura.org', '2020-07-18', 'image-1586866706416.jpg', 'Cuidado infantil', '2020-05-05', 1, 15),
(321, 'Alcalá de Henares', 'Este proyecto plantea, como objetivo fundamental, desarrollar y fortalecer las capacidades de respuesta de las Asambleas Locales, Comarcales e Insulares. Tiene como actores principales del mismo a los miembros de Órganos de Gobierno y personas con responsabilidad en el ámbito de la gestión de Cruz Roja Española en la red local. Decimos que dichas personas (presidentes/as, vicepresidentes/as, referentes de las distintas áreas/planes, etc.) serán los actores principales ya que a ellos y ellas corresponderá incorporar e implementar, en sus ámbitos correspondientes, las líneas de trabajo marcadas en este proyecto, con el apoyo de las estructuras de soporte de otros ámbitos de Cruz Roja, fundamentalmente provinciales o autonómicos uniprovinciales. El proyecto quiere incidir sobre los ámbitos clave que afectan a la realidad local: - las personas (sus capacidades y relaciones) - los procesos y metodologías - la gestión de los recursos. Para todo esto, por una parte, se plantea la mejora de las competencias de los Órganos de Gobierno y estructuras de gestión para, entre otros aspectos, asegurar el adecuado liderazgo que les corresponde. Asimismo, pretende articular mecanismos efectivos de relación y coordinación interna (dentro de la propia asamblea) y externa (con otros ámbitos de Cruz Roja, así como con agentes externos). Y todo ello, además, desarrollando estrategias para garantizar una gestión adecuada con las personas que componen nuestra base social, fomentando la participación a todos los niveles. Por otra parte, se plantea la adecuada implementación de procesos y metodologías, adecuadas a la realidad local, que mejoren la eficacia y eficiencia en nuestras respuestas. Siendo uno de los procesos clave la planificación anual del trabajo de la asamblea local/comarcal/insular, ajustada a la realidad y necesidades de su entorno y a la estrategia de la propia Organización; apostando, de forma decidida, por la evaluación y la mejora continua. Por último, el proyecto persigue propiciar las condiciones para una adecuada y eficiente gestión de los recursos (económicos, materiales, infraestructuras, humanos, etc.) con que la asamblea cuenta para lograr el cumplimiento de su misión. ', 'informa@aventura.org', '2020-08-09', 'image-1586866858966.jpg', 'Agente de ayuda', '2020-03-08', 9, 15),
(517, 'Bangui', '¿Por qué siguen muriendo 15.000 niños cada día por causas evitables? ¿Por qué sigue habiendo grupos armados que utilizan a niños con fines militares? ¿Por qué millones de niños en Siria no pueden ir al colegio?  Seguro que muchas veces te has hecho este tipo de preguntas. Desgraciadamente, y a pesar de que se han conseguido muchos avances, el mundo sigue siendo un lugar injusto y peligroso para muchos niños.  Por todo esto, en UNICEF tenemos una misión muy clara: conseguir que los más de 7.000 millones de habitantes del planeta conozcan y defiendan los derechos de la infancia en todo momento y en todo lugar.  Porque solo así conseguiremos cambios reales y duraderos en la vida de los niños y niñas.', 'unicef@unicef.es', '2021-04-11', 'image-1586875928231.jpg', 'Niños en República Centroafricana', '2020-04-11', 4, 516),
(519, 'Yuba', 'Cada día, un promedio de tres rinocerontes son asesinados por furtivos. El comercio ilegal es la mayor amenaza para las distintas especies de rinoceronte y las está llevando al borde de la extinción.  El cuerno de rinoceronte es uno de los productos más codiciados en el mercado negro mundial donde su precio supera incluso al del oro. La falsa creencia en Asia sobre sus supuestas propiedades para curar el cáncer ha desencadenado la masacre de los últimos rinocerontes.  Muchas poblaciones de rinoceronte  están al borde de extinguirse, como es el caso del rinoceronte blanco del norte, del que tan solo quedan dos ejemplares hembras.  Desde WWF luchamos sin descanso contra el tráfico de especies, apoyando a los guardas que se enfrentan cuerpo a cuerpo a los furtivos, trabajando con los gobiernos para el endurecer la persecución de este crimen y promoviendo la prohibición del consumo en los mercados de destino, principalmente en Asia.  ¡ ÚNETE A LA LUCHA CONTRA EL TRÁFICO DE ESPECIES!', 'info@wwf.es', '2023-04-18', 'image-1586876337749.jpg', '¡Que no te importe un cuerno! Lucha contra el tráfico de Rinocerontes', '2023-02-14', 9, 518),
(521, 'Erikler', 'El mundo es testigo de un número sin precedentes de personas obligadas a huir de la violencia y la persecución. El resultado es una crisis de albergue, con millones de familias que llegan a los campamentos de refugiados o que luchan en viviendas de bajo nivel y albergues a medio construir.  Nadie quede afuera es una campaña mundial para recaudar fondos para albergar a estas familias.  Solo ACNUR tiene el alcance y la experiencia para enfrentar esta crisis a gran escala, pero no podemos hacerlo solos. Se parte de la solución y ayuda a asegurar que nadie quede afuera.', 'spama@unhcr.org', '2022-05-25', 'image-1586876671637.jpg', 'Que nadie quede afuera', '2021-10-09', 5, 520),
(522, 'Idlib', 'La guerra incesante dio lugar a una crisis migratoria siria, en la que millones de personas escaparon de sus hogares por los incesantes bombardeos y enfrentamientos, la mayoría apenas con lo puesto. Niños, ancianos, personas con discapacidades, hombres y mujeres sin otra opción que la de huir por sus vidas.  Muchos perecieron en el intento; algún caso saltó a los titulares de los diarios, como el del niño Aylan Kurdi, hallado sin vida en una playa turca. Pero en su inmensa mayoría, las penurias de los refugiados sirios son una tragedia ignorada y en muchos casos, encuentran hostilidad en sus lugares de destino.Ayudanos con tu colaboración a acabar con tanto sufrimiento.', 'spama@unhcr.org', '2020-04-14', 'image-1586876740604.jpg', 'Voluntariado en Siria', '2020-04-11', 5, 520),
(523, 'Mphomwwe(Malawi)', '15.000 niñas y niños mueren cada día antes de llegar a su quinto cumpleaños, la mayoría de ellos por causas que se pueden prevenir.  Mejorar la nutrición y la salud, asegurar el acceso a agua potable y saneamiento, y promover hábitos saludables logran prevenir y tratar las principales causas de mortalidad infantil, que incluyen la neumonía, la diarrea y la malaria, ofreciendo a todos los niños la oportunidad de crecer sanos y desarrollarse plenamente.¡Únete y colabora para que no mueran de esta fomra!', 'unicef@unicef.es', '2020-04-11', 'image-1586876137350.jpg', 'Voluntariado por la supervivencia de niños en Malawi', '2020-04-11', 1, 516),
(524, 'Leazún', 'La situación del visón europeo es dramática. Si no actuamos a tiempo, en menos de cinco años desaparecerá \'el lince de nuestros ríos\'. Tenemos que salvar al carnívoro más amenazado de Europa, junto al lince ibérico. Los expertos ya lo consideran un animal en peligro crítico de extinción, pero sigue siendo el gran olvidado por nuestro Gobierno. ¡NO PODEMOS PERMITIRLO!  Su área de distribución se ha reducido un 90% con respecto a la original y la población española – que no llega a los 500 visones europeos - es uno de los últimos reductos de la especie en todo el mundo. Si dejamos que desaparezca, nos quedaremos sin una especie emblemática de nuestra fauna y que, además, actúa como el mejor indicador de la buena calidad de los ríos. Ayudanos a limpiar los ríos para que los visones  vuelvan a su hábitat natural y no desaparezcan', 'info@wwf.es', '2023-06-13', 'image-1586876530156.jpg', 'Proteccion del Visón Europeo', '2022-07-13', 9, 518);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `hibernate_sequences`
--
ALTER TABLE `hibernate_sequences`
  ADD PRIMARY KEY (`sequence_name`);

--
-- Indices de la tabla `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK262xipml267yt3khewvd0kti2` (`volunteering_id`),
  ADD KEY `FKnvx9seeqqyy71bij291pwiwrg` (`user_id`);

--
-- Indices de la tabla `ngos`
--
ALTER TABLE `ngos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_f6ocr31gs6hnewlkuwr6o7j85` (`email`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Indices de la tabla `users_volunteerings`
--
ALTER TABLE `users_volunteerings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5fxgsyic9v9pn8s94unymj151` (`volunteering_id`),
  ADD KEY `FKteu3q0nvex0i8ekw1gnlvq3pn` (`user_id`);

--
-- Indices de la tabla `user_roles`
--
ALTER TABLE `user_roles`
  ADD KEY `FKhfh9dx7w3ubf1co1vdev94g3f` (`user_id`);

--
-- Indices de la tabla `volunteerings`
--
ALTER TABLE `volunteerings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3kddb3713v6nnky3lcdyruu6f` (`ong_id`),
  ADD KEY `FK5k8rhhuptkxnoajr1npfrivp6` (`category_id`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `likes`
--
ALTER TABLE `likes`
  ADD CONSTRAINT `FK262xipml267yt3khewvd0kti2` FOREIGN KEY (`volunteering_id`) REFERENCES `volunteerings` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FKnvx9seeqqyy71bij291pwiwrg` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `users_volunteerings`
--
ALTER TABLE `users_volunteerings`
  ADD CONSTRAINT `FK5fxgsyic9v9pn8s94unymj151` FOREIGN KEY (`volunteering_id`) REFERENCES `volunteerings` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FKteu3q0nvex0i8ekw1gnlvq3pn` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `volunteerings`
--
ALTER TABLE `volunteerings`
  ADD CONSTRAINT `FK3kddb3713v6nnky3lcdyruu6f` FOREIGN KEY (`ong_id`) REFERENCES `ngos` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK5k8rhhuptkxnoajr1npfrivp6` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
