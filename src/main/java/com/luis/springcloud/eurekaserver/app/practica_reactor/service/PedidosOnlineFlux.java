/*
 * Copyright (c) 2025, Daza and/or its affiliates. All rights reserved.
 * DAZA COMPANY PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.luis.springcloud.eurekaserver.app.practica_reactor.service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
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

        Flux.fromIterable(preciosApiExterna)
                .map(precioCompleto -> precioCompleto.split(":")).collectMap(nombre -> nombre[0].trim(),
                        precio -> {
                    try {
                        return Double.parseDouble(precio[1].trim());
                    }catch (NumberFormatException e){
                        return 0.0;
                    }
                }).onErrorContinue((error, objeto) ->
                        log.error("Ocurrio un {}, al parsear este elemento {}", error.getMessage(), objeto))
                .subscribe(
                        onContinue -> log.info("Nombre {}, precio{}", onContinue.keySet(), onContinue.values())
                );

    }
}
