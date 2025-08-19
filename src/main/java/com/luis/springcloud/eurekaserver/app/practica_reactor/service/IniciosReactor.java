package com.luis.springcloud.eurekaserver.app.practica_reactor.service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import java.util.List;

@Slf4j
public class IniciosReactor {

    public static void main(String[] args) {

        /*TODO Apartir de esta lista como esta:
        *   Tu tarea es procesar estos datos con Reactor para:
            1. Extraer solo el número (ignorar el texto).
            2. Eliminar valores no numéricos o con error (NaN, abc, etc.).
            3. Convertirlos a Double.
            4. Mostrar los precios válidos.
            Calcular el promedio de los precios válidos.*/

        List<String> preciosRaw = List.of(
                "Producto A: 100.50",
                "Producto B: NaN",
                "Producto C:   200.75 ",
                "Error en precio",
                "Producto D: 300",
                "Producto E: abc",
                "Producto F: 450.90"
        );

        Flux.fromIterable(preciosRaw)
                .map(cadena -> {
                    String[] partes = cadena.split(":");
                    return partes.length > 1 ? partes[1].trim() : "";
                })
                .filter(valor -> !valor.isEmpty() && Character.isDigit(valor.charAt(0)))
                .map(Double::parseDouble)
                .onErrorContinue((error, objeto) -> System.out.println("Valor invalido ignorado: "
                        + objeto))
                .doOnError(error -> log.error("Ocurrio un error inesperado{}", error.getMessage()))
                .doOnNext(value -> log.info("Valor aprobado, {}", value))
                .collectList()
                .map(lista -> lista.stream().mapToDouble(Double::doubleValue).average().orElse(0))
                .subscribe(
                        onContinue -> System.out.println("onContinue: " + onContinue),
                        onError -> System.out.println("Error: " + onError)
                );


    }

}
