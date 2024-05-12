// Conexión a la base de datos (ajustar según sea necesario)
db = connect("localhost:27017/pizzeria");

// Creación de las colecciones y añadir algunos registros de ejemplo

// Colección de Tiendas
db.tiendas.insertMany([
    { _id: 1, direccion: "Calle Falsa 123", codigo_postal: "08001", localidad: "Barcelona", provincia: "Barcelona" },
    { _id: 2, direccion: "Avenida Siempre Viva 742", codigo_postal: "28001", localidad: "Madrid", provincia: "Madrid" }
]);

// Colección de Empleados
db.empleados.insertMany([
    { _id: 1, nombre: "Juan Perez", NIF: "12345678A", telefono: "600100200", tienda_id: 1, rol: "cocinero" },
    { _id: 2, nombre: "Ana Lopez", NIF: "87654321B", telefono: "600300400", tienda_id: 1, rol: "repartidor" },
    { _id: 3, nombre: "Carlos Gomez", NIF: "18273645C", telefono: "600500600", tienda_id: 2, rol: "cocinero" },
    { _id: 4, nombre: "Laura Martín", NIF: "56473829D", telefono: "600700800", tienda_id: 2, rol: "repartidor" }
]);

// Colección de Clientes
db.clientes.insertMany([
    { _id: 1, nombre: "Roberto Morales", direccion: "Gran Via 22", codigo_postal: "08002", localidad: "Barcelona", provincia: "Barcelona", telefono: "610200300" },
    { _id: 2, nombre: "Marta Vidal", direccion: "Paseo de la Castellana 100", codigo_postal: "28002", localidad: "Madrid", provincia: "Madrid", telefono: "620400500" }
]);

// Colección de Productos
db.productos.insertMany([
    { _id: 1, nombre: "Pizza Margarita", descripcion: "Pizza de tomate y queso", imagen: "url-imagen-margarita", precio: 8 },
    { _id: 2, nombre: "Hamburguesa de Queso", descripcion: "Hamburguesa con queso cheddar", imagen: "url-imagen-hamburguesa", precio: 6 },
    { _id: 3, nombre: "Coca Cola", descripcion: "Bebida gaseosa", imagen: "url-imagen-coca", precio: 2 }
]);

// Colección de Comandas
db.comandas.insertMany([
    { _id: 1, cliente_id: 1, fecha_hora: new Date(), tipo_entrega: "domicilio", productos: [{ producto_id: 1, cantidad: 1 }, { producto_id: 3, cantidad: 2 }], total: 12, tienda_id: 1, empleado_id: 2 },
    { _id: 2, cliente_id: 2, fecha_hora: new Date(), tipo_entrega: "tienda", productos: [{ producto_id: 2, cantidad: 2 }, { producto_id: 3, cantidad: 1 }], total: 14, tienda_id: 2, empleado_id: 3 },
    { _id: 3, cliente_id: 1, fecha_hora: new Date(), tipo_entrega: "domicilio", productos: [{ producto_id: 1, cantidad: 2 }, { producto_id: 3, cantidad: 1 }], total: 18, tienda_id: 1, empleado_id: 2 },
    { _id: 4, cliente_id: 2, fecha_hora: new Date(), tipo_entrega: "domicilio", productos: [{ producto_id: 2, cantidad: 1 }, { producto_id: 3, cantidad: 3 }], total: 12, tienda_id: 2, empleado_id: 4 }
]);
