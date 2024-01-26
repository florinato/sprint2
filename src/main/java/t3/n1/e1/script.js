
var mongoose = require('mongoose');var Schema = mongoose.Schema;var clientes = new Schema({datosCliente: {
    email: {type: String, required: true},direccion: {type: String, required: true},telefono: {type: String, required: true},fechaRegistro: {type: Date, required: true},recomendado: {type: Schema.Types.ObjectId}},ventas: {
    idCompra: {type: Number, required: true},gafas: {
    idGafas: {type: String, required: true},montura: {type: String, required: true},colorMontura: {type: String, required: true},colorCristales: {
    L: {type: String, required: true},R: {type: String, required: true}},graduacion: {
    L: {type: Number, required: true},R: {type: Number, required: true}},marca: {type: String, required: true},precio: {type: Number, required: true}}}}); 