package com.luis.springcloud.eurekaserver.app.practica_reactor.service;

import reactor.core.publisher.Flux;

import java.util.List;

public class PedidosOnlineFlux {
    public static void main(String[] args) {
        //TODO Simulamos llegada de datos a partir de una consulta a una api externa
        List<String> preciosApiExterna = List.of("Televisor:1200.50",
                "Silla:NaN",
                "Escritorio:800.00",
                "Lámpara:invalid",
                "Celular:950.75");

        /*TODO: Lo que debes hacer con esto es: 😒*/
        /*TODO: Procesar el flujo con Reactor (Flux).
            Separar producto y precio con split(":").
            Convertir el precio en BigDecimal si es válido.
            Si el precio no se puede parsear (ejemplo "NaN" o "invalid"), reemplazarlo por BigDecimal.ZERO.
            Mostrar en consola el producto junto con su precio final.
            Usar al menos una de estas herramientas:
                doOnError
                onErrorResume
                onErrorReturn
            Al finalizar, mostrar un mensaje que diga: "Procesamiento de pedidos completado".*/

//        Flux.fromIterable(preciosApiExterna)
//                .map(precioCompleto -> {
//                    String partes = precioCompleto.split(":")[1]
//                })

    }
}
