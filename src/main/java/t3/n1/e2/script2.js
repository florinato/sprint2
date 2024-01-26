
db.createCollection( 'gafas', {validator: {$jsonSchema: {bsonType: 'object',title:'gafas',required: [         'marca',          'venta'],properties: {marca: {bsonType: 'string'},venta: {bsonType: 'object',
title:'object',required: [         'id',          'tipoMontura',          'colorMontura',          'colorCristal',          'graduacion',          'precio',          'cliente'],properties: {id: {bsonType: 'objectId'},tipoMontura: {bsonType: 'string'},colorMontura: {bsonType: 'string'},colorCristal: {bsonType: 'object',
title:'object',required: [         'L',          'R'],properties: {L: {bsonType: 'string'},R: {bsonType: 'string'}}},graduacion: {bsonType: 'object',
title:'object',required: [         'L',          'R'],properties: {L: {bsonType: 'double'},R: {bsonType: 'double'}}},precio: {bsonType: 'double'},cliente: {bsonType: 'object',
title:'object',required: [         'nombre',          'direccion',          'telefono',          'email',          'fechaDeAlta'],properties: {nombre: {bsonType: 'string'},direccion: {bsonType: 'object',
title:'object',required: [         'calle',          'numero',          'puerta',          'ciudad',          'pais',          'codigoPostal'],properties: {calle: {bsonType: 'string'},numero: {bsonType: 'int'},piso: {bsonType: 'int'},puerta: {bsonType: 'string'},ciudad: {bsonType: 'string'},pais: {bsonType: 'string'},codigoPostal: {bsonType: 'string'}}},telefono: {bsonType: 'int'},email: {bsonType: 'string'},fechaDeAlta: {bsonType: 'date'}}}}},proveedor: {bsonType: 'object',
title:'object',required: [         'id',          'nombre',          'direccion',          'telefono',          'fax',          'NIF'],properties: {id: {bsonType: 'objectId'},nombre: {bsonType: 'string'},direccion: {bsonType: 'object',
title:'object',required: [         'calle',          'numero',          'piso',          'puerta',          'ciudad',          'pais',          'codigoPostal'],properties: {calle: {bsonType: 'string'},numero: {bsonType: 'int'},piso: {bsonType: 'int'},puerta: {bsonType: 'string'},ciudad: {bsonType: 'string'},pais: {bsonType: 'string'},codigoPostal: {bsonType: 'string'}}},telefono: {bsonType: 'int'},fax: {bsonType: 'int'},NIF: {bsonType: 'string'}}}}         }      }});  