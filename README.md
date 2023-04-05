S3_G5


### Entrega Individual:
- Gisel Lorenzatti


Repositorio Sprint 3, bootcamp de Java.

#### Aclaración

Tomé la decision de realizar todo en idioma español a excepción de cosas propias del desarrollo (Ej. Request, Response).
Para mostrar o filtrar la lista de vuelos por Business, genere un método en el controlador, una query de consulta en el 
repositorio que extiende de JPA y desarrolle la funcionalidad del método en el servicio de vuelos.
Luego genere dos tipo de resting unitarios: uno sin mock y otro con mock
### Vuelos:

Link para listar vuelos por business: "/api/v1/flights/business"

RESPONSE:
[
{
"id": 2,
"nroVuelo": "PIBA-1420",
"origen": "Puerto Iguazú",
"destino": "Bogotá",
"tipoAsiento": "Business",
"precioPorPersona": 43200.0,
"fechaIda": "2022-02-10",
"fechaVuelta": "2022-02-20"
},
{
"id": 5,
"nroVuelo": "TUPI-3369",
"origen": "Tucumán",
"destino": "Puerto Iguazú",
"tipoAsiento": "Business",
"precioPorPersona": 12530.0,
"fechaIda": "2022-02-12",
"fechaVuelta": "2022-02-23"
},
{
"id": 9,
"nroVuelo": "BOBA-6567",
"origen": "Bogotá",
"destino": "Buenos Aires",
"tipoAsiento": "Business",
"precioPorPersona": 57000.0,
"fechaIda": "2022-02-15",
"fechaVuelta": "2022-02-28"
},
{
"id": 12,
"nroVuelo": "MEPI-9986",
"origen": "Medellín",
"destino": "Puerto Iguazú",
"tipoAsiento": "Business",
"precioPorPersona": 41640.0,
"fechaIda": "2022-04-17",
"fechaVuelta": "2022-05-02"
}
]



