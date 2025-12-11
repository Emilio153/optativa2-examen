-- Inserciones en la tabla cliente
INSERT INTO cliente (nombre, direccion, email, telefono) VALUES
('Juan Pérez', 'Calle Mayor 1', 'juan.perez@mail.com', '600111222'),
('Ana López', 'Av. Sevilla 5', 'ana.lopez@mail.com', '600111223'),
('Luis Sánchez', 'Plaza Sol 7', 'luis.sanchez@mail.com', '600111224'),
('Carlos Ruiz', 'Calle Luna 8', 'carlos.ruiz@mail.com', '600111225'),
('Laura Torres', 'Calle Olivo 3', 'laura.torres@mail.com', '600111226'),
('Sofía García', 'Av. Madrid 23', 'sofia.garcia@mail.com', '600111227'),
('Miguel Fernández', 'Calle Norte 11', 'miguel.fernandez@mail.com', '600111228'),
('Lucía Díaz', 'Calle Oeste 15', 'lucia.diaz@mail.com', '600111229'),
('Javier Moreno', 'Av. Granada 20', 'javier.moreno@mail.com', '600111230'),
('Elena Martín', 'Calle Sur 21', 'elena.martin@mail.com', '600111231');

-- Inserciones en la tabla pizza
INSERT INTO pizza (nombre, descripcion, precio, disponible, vegana, vegetariana) VALUES
('Margarita', 'Tomate, mozzarella y albahaca', 7.50, true, false, true),
('Pepperoni', 'Pepperoni, mozzarella y tomate', 8.00, true, false, false),
('Cuatro Quesos', 'Mozzarella, parmesano, gorgonzola, emmental', 8.50, true, false, true),
('Barbacoa', 'Carne, salsa barbacoa, cebolla, mozzarella', 9.00, true, false, false),
('Vegetal', 'Verduras variadas y mozzarella', 7.50, true, false, true),
('Veganísima', 'Queso vegano y verduras', 8.00, true, true, true),
('Jamon York', 'Jamon york y mozzarella', 7.80, true, false, false),
('Hawaiana', 'Piña, jamón york y mozzarella', 8.00, true, false, false),
('Carbonara', 'Bacon, cebolla, crema y mozzarella', 8.50, true, false, false),
('Mexicana', 'Jalapeños, carne, mozzarella, tomate', 9.00, true, false, false);

-- Inserciones en la tabla pedido
INSERT INTO pedido (id_cliente, fecha, total, metodo, notas) VALUES
(1, '2025-11-01 20:15:00', 22.50, 'RECOGER', 'Sin cebolla en la pizza'),
(2, '2025-11-02 21:00:00', 17.00, 'DOMICILIO', 'Entregar rápido'),
(3, '2025-11-03 19:45:00', 30.00, 'LOCAL', 'Cumpleaños'),
(4, '2025-11-04 22:10:00', 12.50, 'RECOGER', 'Con mucho picante'),
(5, '2025-11-05 18:50:00', 25.00, 'DOMICILIO', 'Sin gluten'),
(6, '2025-11-06 20:00:00', 18.00, 'LOCAL', 'Alergia al marisco'),
(7, '2025-11-07 21:15:00', 15.90, 'RECOGER', 'Sin queso'),
(8, '2025-11-08 20:30:00', 29.80, 'DOMICILIO', 'Llamar al llegar'),
(9, '2025-11-09 19:20:00', 10.50, 'LOCAL', 'Cortar en 6 trozos'),
(10, '2025-11-10 22:00:00', 36.75, 'DOMICILIO', 'Para grupo grande');

-- Inserciones en la tabla pizza_pedido
INSERT INTO pizza_pedido (id_pizza, id_pedido, cantidad, precio) VALUES
(1, 1, 1, 7.50),
(2, 1, 2, 16.00),
(3, 2, 2, 17.00),
(4, 3, 3, 27.00),
(5, 4, 1, 7.50),
(6, 5, 2, 16.00),
(7, 6, 1, 7.80),
(8, 7, 1, 8.00),
(9, 8, 2, 17.00),
(10, 9, 1, 9.00);
