-- Crear la base de datos OpticaDB si no existe
CREATE DATABASE IF NOT EXISTS OpticaDB;

-- Usar la base de datos OpticaDB
USE OpticaDB;

-- Crear tabla Proveedores
CREATE TABLE IF NOT EXISTS Proveedores (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    direccion TEXT,
    telefono VARCHAR(20),
    fax VARCHAR(20),
    NIF VARCHAR(20)
);

-- Crear tabla Gafas
CREATE TABLE IF NOT EXISTS Gafas (
    id_gafas INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(255),
    graduacion_izq FLOAT,
    graduacion_der FLOAT,
    tipo_montura VARCHAR(100),
    color_montura VARCHAR(100),
    color_vidrio_izq VARCHAR(100),
    color_vidrio_der VARCHAR(100),
    precio DECIMAL(10,2),
    id_proveedor INT,
    FOREIGN KEY (id_proveedor) REFERENCES Proveedores(id_proveedor)
);

-- Crear tabla Clientes
CREATE TABLE IF NOT EXISTS Clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    direccion_postal TEXT,
    telefono VARCHAR(20),
    email VARCHAR(255),
    fecha_registro DATE,
    recomendado_por INT,
    FOREIGN KEY (recomendado_por) REFERENCES Clientes(id_cliente)
);

-- Crear tabla Empleados
CREATE TABLE IF NOT EXISTS Empleados (
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255)
);

-- Crear tabla Ventas
CREATE TABLE IF NOT EXISTS Ventas (
    id_venta INT AUTO_INCREMENT PRIMARY KEY,
    id_empleado INT,
    id_cliente INT,
    id_gafas INT,
    fecha_venta DATE,
    FOREIGN KEY (id_empleado) REFERENCES Empleados(id_empleado),
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente),
    FOREIGN KEY (id_gafas) REFERENCES Gafas(id_gafas)
);
