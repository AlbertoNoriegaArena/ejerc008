-- Creación de la tabla provincia
CREATE TABLE provincia (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    poblacion INT NOT NULL
);
