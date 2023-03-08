S1_G5


Integrantes: Mateo Ascona, Jonatan Garcia, Gisel Lorenzatti, Franco Ambort, Celeste Sanchez, Gisela Burgos.
Repositorio sprint 1, bootcamp de Java

Link para todos los hoteles: http://localhost:8080/api/v1/hotels

Links para testear filtro: http://localhost:8080/api/v1/hotel?dateFrom=11/02/2022&dateTo=10/03/2022&destination=Puerto Iguazú

http://localhost:8080/api/v1/hotel?dateFrom=11/02/2022&dateTo=10/03/2022&destination=Buenos Aires

http://localhost:8080/api/v1/hotel?dateFrom=11/02/2022&dateTo=10/03/2022&destination=Bogotá

Link para la reserva de hotel: http://localhost:8080/api/v1/booking

{
    "userName": "Cristian",
    "hotelBookingDto":
        {
            "fechaDesde": "20",
            "fechaHasta": "20",
            "destino": "Rafaela",
            "codigoHotel": "2CH4OA",
            "cantidadPersonas": 2,
            "tipoHabitacion": "single",
            "personas": [
                {
                    "dni": "37.575.676",
                    "nombre": "Fanco",
                    "apellido": "Ambort",
                    "fechaNacimiento": "7 Febrero 1994",
                    "email": "ambortfranco94@gmail.com"
                },
                {
                    "dni": "37.575.676",
                    "nombre": "Fanco",
                    "apellido": "Ambort",
                    "fechaNacimiento": "7 Febrero 1994",
                    "email": "ambortfranco94@gmail.com"
                }
            ],
            "metodoPago": {
                "tipo": "credito",
                "numero": "3423213",
                "cuotas": 18
            } 
        }
}
Link para todos los vuelos: http://localhost:8080/api/v1/flights

Links para testear filtro: http://localhost:8080/api/v1/flights?fechaPartida=2022/02/10&fechaRegreso=2022/02/21&destino=Bogotá
                           http://localhost:8080/api/v1/hotels?fechaPartida=2022/02/10&fechaRegreso=2022/03/23&destino=Puerto Iguazú

Link para la reserva del vuelo: http://localhost:8080/api/v1/flight-reservation
(metodo POST objeto de ejemplo)
{
    "userName": "arjonamiguel@gmail.com",
    "vueloReservaDto": {
        "fechaDesde": "2021/11/10",
        "fechaHasta": "2021/11/10",
        "origen": "Buenos Aires",
        "destino": "Puerto Iguazú",
        "codigoVuelo": "FFFF-0002",
        "cantidadAsientos": 2,
        "claseAsientos": "ECONOMY",
        "personas": [
            {
                "dni": "12345678",
                "nombre": "Pepito",
                "apellido": "Gomez",
                "fechaNacimiento": "10/11/1982",
                "email": "arjonamiguel@gmail.com"
            },
            {
                "dni": "13345678",
                "nombre": "Fulanito",
                "apellido": "Gomez",
                "fechaNacimiento": "10/11/1983",
                "email": "arjonamiguel2@gmail.com"
            }
        ],
        "metodoPago": {
            "tipo": "CREDIT",
            "numero": "1234-1234-1234-1234",
            "cuotas": 6
        }
    }
}


