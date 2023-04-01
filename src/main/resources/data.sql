INSERT INTO hoteles
  (codigo_hotel, nombre, lugar_ciudad, tipo_de_habitacion, precio_por_noche, disponible_desde, disponible_hasta, reservado)
VALUES
  ('CH-0002', 'Cataratas Hotel', 'Puerto Iguazú', 'Doble', 6300.00, '2022-02-10', '2022-03-20', false),
  ('CH-0003', 'Cataratas Hotel 2', 'Puerto Iguazú', 'Triple', 8200.00, '2022-02-10', '2022-03-23', false),
  ('HB-0001', 'Hotel Bristol', 'Buenos Aires', 'Single', 5435.00, '2022-02-10', '2022-03-19', false),
  ('BH-0002', 'Hotel Bristol 2', 'Buenos Aires', 'Doble', 7200.00, '2022-02-12', '2022-04-17', false),
  ('SH-0002', 'Sheraton', 'Tucumán', 'Doble', 5790.00, '2022-04-17', '2022-05-23', false),
  ('SH-0001', 'Sheraton 2', 'Tucumán', 'Single', 4150.00, '2022-01-02', '2022-02-19', false),
  ('SE-0001', 'Selina', 'Bogotá', 'Single', 3900.00, '2022-01-23', '2022-11-23', false),
  ('SE-0002', 'Selina 2', 'Bogotá', 'Doble', 5840.00, '2022-01-23', '2022-10-15', false),
  ('EC-0003', 'El Campín', 'Bogotá', 'Triple', 7020.00, '2022-02-15', '2022-03-27', false),
  ('CP-0004', 'Central Plaza', 'Medellín', 'Múltiple', 8600.00, '2022-03-01', '2022-04-17', false),
  ('CP-0002', 'Central Plaza 2', 'Medellín', 'Doble', 6400.00, '2022-02-10', '2022-03-20', false),
  ('BG-0004', 'Bocagrande', 'Cartagena', 'Múltiple', 9370.00, '2022-04-17', '2022-06-12', false);


  INSERT INTO vuelos
      (nro_vuelo, origen, destino, tipo_asiento, precio_por_persona, fecha_ida, fecha_vuelta)
  VALUES
      ('BAPI-1235', 'Buenos Aires', 'Puerto Iguazú', 'Economy', 6500, '2022-02-10', '2022-02-15'),
      ('PIBA-1420', 'Puerto Iguazú', 'Bogotá', 'Business', 43200, '2022-02-10', '2022-02-20'),
      ('PIBA-1420', 'Puerto Iguazú', 'Bogotá', 'Economy', 25735, '2022-02-10', '2022-02-21'),
      ('BATU-5536', 'Buenos Aires', 'Tucumán', 'Economy', 7320, '2022-02-10', '2022-02-17'),
      ('TUPI-3369', 'Tucumán', 'Puerto Iguazú', 'Business', 12530, '2022-02-12', '2022-02-23'),
      ('TUPI-3369', 'Tucumán', 'Puerto Iguazú', 'Economy', 5400, '2022-01-02', '2022-01-16'),
      ('BOCA-4213', 'Bogotá', 'Cartagena', 'Economy', 8000, '2022-01-23', '2022-02-05'),
      ('CAME-0321', 'Cartagena', 'Medellín', 'Economy', 7800, '2022-01-23', '2022-01-31'),
      ('BOBA-6567', 'Bogotá', 'Buenos Aires', 'Business', 57000, '2022-02-15', '2022-02-28'),
      ('BOBA-6567', 'Bogotá', 'Buenos Aires', 'Economy', 39860, '2022-03-01', '2022-03-14'),
      ('BOME-4442', 'Bogotá', 'Medellín', 'Economy', 11000, '2022-02-10', '2022-02-24'),
      ('MEPI-9986', 'Medellín', 'Puerto Iguazú', 'Business', 41640, '2022-04-17', '2022-05-02');

 INSERT INTO status_code
       (code, mensaje)
   VALUES
   (200, "Ok")