-- Listar el nombre de todos los productos
SELECT nombre FROM producto;

-- Listar nombres y precios de todos los productos
SELECT nombre, precio FROM producto;

-- Listar todas las columnas de la tabla producto
SELECT * FROM producto;

-- Listar el nombre de los productos y su precio en euros y dólares (USD)
SELECT nombre, precio, precio * 1.1 AS precio_usd FROM producto;

-- Listar el nombre de los productos y su precio en euros y dólares (USD) con alias
SELECT nombre AS 'nom de producto', precio AS euros, precio * 1.1 AS dólares FROM producto;

-- Listar nombres y precios de todos los productos, convirtiendo los nombres a mayúscula
SELECT UPPER(nombre), precio FROM producto;

-- Listar nombres y precios de todos los productos, convirtiendo los nombres a minúscula
SELECT LOWER(nombre), precio FROM producto;

-- Listar el nombre de todos los fabricantes y los dos primeros caracteres del nombre en mayúsculas
SELECT nombre, UPPER(SUBSTRING(nombre, 1, 2)) AS iniciales FROM fabricante;

-- Listar nombres y precios de todos los productos, redondeando el valor del precio
SELECT nombre, ROUND(precio, 0) AS precio_redondeado FROM producto;

-- Listar nombres y precios de todos los productos, truncando el valor del precio a sin decimales
SELECT nombre, TRUNCATE(precio, 0) AS precio_truncado FROM producto;

-- Listar el código de los fabricantes que tienen productos en la tabla producto
SELECT DISTINCT codigo_fabricante FROM producto;

-- Listar los nombres de los fabricantes ordenados de manera ascendente
SELECT nombre FROM fabricante ORDER BY nombre ASC;

-- Listar los nombres de los fabricantes ordenados de manera descendente
SELECT nombre FROM fabricante ORDER BY nombre DESC;

-- Listar los nombres de los productos ordenados, primero por nombre de manera ascendente y luego por precio de manera descendente
SELECT nombre, precio FROM producto ORDER BY nombre ASC, precio DESC;

-- Retornar una lista con las 5 primeras filas de la tabla fabricante
SELECT * FROM fabricante LIMIT 5;

-- Retornar una lista con 2 filas a partir de la cuarta fila de la tabla fabricante
SELECT * FROM fabricante LIMIT 2 OFFSET 3;

-- Listar el nombre y el precio del producto más barato
SELECT nombre, precio FROM producto ORDER BY precio ASC LIMIT 1;

-- Listar el nombre y el precio del producto más caro
SELECT nombre, precio FROM producto ORDER BY precio DESC LIMIT 1;

-- Listar el nombre de todos los productos del fabricante cuyo código de fabricante es igual a 2
SELECT nombre FROM producto WHERE codigo_fabricante = 2;

-- Listar el nombre del producto, precio y nombre del fabricante de todos los productos
SELECT producto.nombre, producto.precio, fabricante.nombre FROM producto JOIN fabricante ON producto.codigo_fabricante = fabricante.codigo;

-- Listar el nombre del producto, precio y nombre del fabricante de todos los productos, ordenados alfabéticamente por el nombre del fabricante
SELECT producto.nombre, producto.precio, fabricante.nombre FROM producto JOIN fabricante ON producto.codigo_fabricante = fabricante.codigo ORDER BY fabricante.nombre;

-- Listar el código del producto, nombre del producto, código del fabricante y nombre del fabricante de todos los productos
SELECT producto.codigo, producto.nombre, fabricante.codigo, fabricante.nombre FROM producto JOIN fabricante ON producto.codigo_fabricante = fabricante.codigo;

-- Listar el nombre del producto, su precio y el nombre de su fabricante, del producto más barato
SELECT producto.nombre, producto.precio, fabricante.nombre FROM producto JOIN fabricante ON producto.codigo_fabricante = fabricante.codigo ORDER BY producto.precio ASC LIMIT 1;

-- Listar el nombre del producto, su precio y el nombre de su fabricante, del producto más caro
SELECT producto.nombre, producto.precio, fabricante.nombre FROM producto JOIN fabricante ON producto.codigo_fabricante = fabricante.codigo ORDER BY producto.precio DESC LIMIT 1;

-- Listar todos los productos del fabricante Lenovo
SELECT producto.nombre FROM producto JOIN fabricante ON producto.codigo_fabricante = fabricante.codigo WHERE fabricante.nombre = 'Lenovo';

-- Listar todos los productos del fabricante Crucial con precio mayor a 200 euros
SELECT producto.nombre FROM producto JOIN fabricante ON producto.codigo_fabricante = fabricante.codigo WHERE fabricante.nombre = 'Crucial' AND producto.precio > 200;

-- Listar todos los productos de los fabricantes Asus, Hewlett-Packard y Seagate, sin usar el operador IN
SELECT producto.nombre FROM producto JOIN fabricante ON producto.codigo_fabricante = fabricante.codigo WHERE fabricante.nombre = 'Asus' OR fabricante.nombre = 'Hewlett-Packard' OR fabricante.nombre = 'Seagate';

-- Listar todos los productos de los fabricantes Asus, Hewlett-Packard y Seagate, usando el operador IN
SELECT producto.nombre FROM producto JOIN fabricante ON producto.codigo_fabricante = fabricante.codigo WHERE fabricante.nombre IN ('Asus', 'Hewlett-Packard', 'Seagate');

-- Retorna un listado con el nombre y el precio de todos los productos de los fabricantes cuyo nombre termina en 'e'
SELECT producto.nombre, producto.precio FROM producto JOIN fabricante ON producto.codigo_fabricante = fabricante.codigo WHERE fabricante.nombre LIKE '%e';

-- Retorna un listado con el nombre y el precio de todos los productos cuyo nombre de fabricante contenga 'w'
SELECT producto.nombre, producto.precio FROM producto JOIN fabricante ON producto.codigo_fabricante = fabricante.codigo WHERE fabricante.nombre LIKE '%w%';

-- Retorna un listado con el nombre de producto, precio y nombre de fabricante, de todos los productos con un precio mayor o igual a 180€, ordenados primero por precio descendente y luego por nombre ascendente
SELECT producto.nombre, producto.precio, fabricante.nombre FROM producto JOIN fabricante ON producto.codigo_fabricante = fabricante.codigo WHERE producto.precio >= 180 ORDER BY producto.precio DESC, producto.nombre ASC;

-- Retorna un listado con el código y el nombre de fabricante, solo de aquellos fabricantes que tienen productos asociados
SELECT DISTINCT fabricante.codigo, fabricante.nombre FROM fabricante JOIN producto ON fabricante.codigo = producto.codigo_fabricante;

-- Retorna un listado de todos los fabricantes junto con los productos que tiene cada uno, incluyendo aquellos sin productos asociados
SELECT fabricante.nombre, producto.nombre FROM fabricante LEFT JOIN producto ON fabricante.codigo = producto.codigo_fabricante;

-- Retorna un listado donde solo aparezcan aquellos fabricantes que no tienen ningún producto asociado
SELECT fabricante.nombre FROM fabricante LEFT JOIN producto ON fabricante.codigo = producto.codigo_fabricante WHERE producto.codigo IS NULL;

-- Retorna todos los productos del fabricante Lenovo (sin utilizar INNER JOIN)
SELECT producto.nombre FROM producto, fabricante WHERE producto.codigo_fabricante = fabricante.codigo AND fabricante.nombre = 'Lenovo';

-- Retorna todas las datos de los productos que tienen el mismo precio que el producto más caro del fabricante Lenovo (sin usar INNER JOIN)
SELECT producto.* FROM producto, (SELECT MAX(precio) AS max_precio FROM producto, fabricante WHERE producto.codigo_fabricante = fabricante.codigo AND fabricante.nombre = 'Lenovo') AS maximo WHERE producto.precio = maximo.max_precio;

-- Lista el nombre del producto más caro del fabricante Lenovo
SELECT producto.nombre FROM producto JOIN fabricante ON producto.codigo_fabricante = fabricante.codigo WHERE fabricante.nombre = 'Lenovo' ORDER BY producto.precio DESC LIMIT 1;

-- Lista el nombre del producto más barato del fabricante Hewlett-Packard
SELECT producto.nombre FROM producto JOIN fabricante ON producto.codigo_fabricante = fabricante.codigo WHERE fabricante.nombre = 'Hewlett-Packard' ORDER BY producto.precio ASC LIMIT 1;

-- Retorna todos los productos de la base de datos que tienen un precio mayor o igual al producto más caro del fabricante Lenovo
SELECT producto.nombre FROM producto WHERE producto.precio >= (SELECT MAX(precio) FROM producto JOIN fabricante ON producto.codigo_fabricante = fabricante.codigo WHERE fabricante.nombre = 'Lenovo');

-- Lista todos los productos del fabricante Asus que tienen un precio superior al precio medio de todos sus productos
SELECT producto.nombre FROM producto JOIN fabricante ON producto.codigo_fabricante = fabricante.codigo WHERE fabricante.nombre = 'Asus' AND producto.precio > (SELECT AVG(precio) FROM producto WHERE codigo_fabricante = fabricante.codigo);
