CREATE TABLE `usuario` (
                        `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
                        `nombre` varchar(1024) DEFAULT NULL,
                        `clave` VARCHAR(60) DEFAULT NULL,
                        PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;