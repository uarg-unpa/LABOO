-- 1. Crear la base de datos y usarla
CREATE DATABASE IF NOT EXISTS BANCO;
USE BANCO;

-- 2. Crear la tabla CUENTAS según la consigna
CREATE TABLE CUENTAS (
    Cuenta INT PRIMARY KEY,
    NombreCliente VARCHAR(100) NOT NULL,
    Saldo DOUBLE NOT NULL,
    TipoCuenta CHAR(1) CHECK (TipoCuenta IN ('A', 'C'))
);

-- 3. Crear la tabla MOVIMIENTOS según la consigna
CREATE TABLE MOVIMIENTOS (
    Id INT AUTO_INCREMENT PRIMARY KEY, -- Agregamos un ID interno para mantener un orden
    Cuenta INT,
    Mov CHAR(1) CHECK (Mov IN ('D', 'E')),
    Importe DOUBLE NOT NULL,
    FOREIGN KEY (Cuenta) REFERENCES CUENTAS(Cuenta) ON DELETE CASCADE
);

-- 4. Insertar datos de prueba para poder validar el Java
INSERT INTO CUENTAS (Cuenta, NombreCliente, Saldo, TipoCuenta) VALUES 
(101, 'Lionel Messi', 150000.00, 'A'),
(102, 'Emanuel Ginobili', 85000.50, 'C'),
(103, 'Paula Pareto', 10000.00, 'A');