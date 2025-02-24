-- Creación de la tabla "clients"
CREATE TABLE IF NOT EXISTS clients (
                                       id SERIAL PRIMARY KEY,
                                       name VARCHAR(100) NOT NULL,
                                       gender VARCHAR(10) NOT NULL,
                                       age INTEGER NOT NULL,
                                       identification VARCHAR(50) NOT NULL,
                                       address VARCHAR(200),
                                       phone VARCHAR(20),
                                       client_id VARCHAR(50) NOT NULL,
                                       password VARCHAR(100) NOT NULL,
                                       status BOOLEAN DEFAULT TRUE
);

-- Creación de la tabla "accounts"
CREATE TABLE IF NOT EXISTS accounts (
                                        id SERIAL PRIMARY KEY,
                                        account_number VARCHAR(20) NOT NULL,
                                        account_type VARCHAR(20) NOT NULL,
                                        initial_balance DOUBLE PRECISION NOT NULL,
                                        status BOOLEAN DEFAULT TRUE,
                                        client_id INTEGER NOT NULL REFERENCES clients(id)
);

-- Creación de la tabla "movements"
CREATE TABLE IF NOT EXISTS movements (
                                         id SERIAL PRIMARY KEY,
                                         date DATE NOT NULL,
                                         movement_type VARCHAR(20) NOT NULL,
                                         amount DOUBLE PRECISION NOT NULL,
                                         balance DOUBLE PRECISION NOT NULL,
                                         account_id INTEGER NOT NULL REFERENCES accounts(id)
);

-- Inserción de datos en la tabla "clients" (mínimo 4 registros)
INSERT INTO clients (name, gender, age, identification, address, phone, client_id, password, status) VALUES
                                                                                                         ('Juan Pérez', 'MALE', 30, 'ID123456', 'Calle Falsa 123', '555-1234', 'CLI-1111-UUID', 'pass123', TRUE),
                                                                                                         ('María García', 'FEMALE', 25, 'ID234567', 'Avenida Siempre Viva 742', '555-5678', 'CLI-2222-UUID', 'pass234', TRUE),
                                                                                                         ('Carlos López', 'MALE', 40, 'ID345678', 'Boulevard de los Sueños 10', '555-9012', 'CLI-3333-UUID', 'pass345', TRUE),
                                                                                                         ('Ana Torres', 'FEMALE', 35, 'ID456789', 'Plaza Mayor 5', '555-3456', 'CLI-4444-UUID', 'pass456', TRUE);

-- Inserción de datos en la tabla "accounts" (mínimo 4 registros)
-- Se asume que los IDs de clients insertados son 1, 2, 3 y 4 respectivamente.
INSERT INTO accounts (account_number, account_type, initial_balance, status, client_id) VALUES
                                                                                            ('1234567890', 'SAVINGS', 1000.00, TRUE, 1),
                                                                                            ('2345678901', 'CURRENT', 2000.00, TRUE, 2),
                                                                                            ('3456789012', 'SAVINGS', 1500.00, TRUE, 3),
                                                                                            ('4567890123', 'CURRENT', 3000.00, TRUE, 4);

-- Inserción de datos en la tabla "movements" (mínimo 4 registros)
-- Se asume que los IDs de accounts insertados son 1, 2, 3 y 4 respectivamente.
INSERT INTO movements (date, movement_type, amount, balance, account_id) VALUES
                                                                             ('2025-02-01', 'DEPOSIT', 500.00, 1000.00, 1),
                                                                             ('2025-02-02', 'WITHDRAWAL', -200.00, 2000.00, 2),
                                                                             ('2025-02-03', 'DEPOSIT', 300.00, 1500.00, 3),
                                                                             ('2025-02-04', 'WITHDRAWAL', -100.00, 3000.00, 4);
