package com.luis.springcloud.eurekaserver.app.practica_reactor.service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@Slf4j
public class OnErrorResumeEjercicios {
    public static void main(String[] args) {
        /*TODO: Ejercicios con onErrorReturn
           Conversión de temperaturas
           Simula un Flux<String> con valores de temperaturas leídas de sensores.
           Uno de los valores está corrupto (ejemplo "??"). Convierte todos a Double
           y usa onErrorReturn para devolver un valor fijo (ejemplo 0.0) cuando ocurra el error.*/

//        Flux<String> fluxTemperaturas = Flux.just("15", "20.2", "35.1", "??", "25.2");

// Opción 1: onErrorReturn → valor fijo
//        fluxTemperaturas
//                .map(Double::parseDouble)
//                .onErrorReturn(0.0)
//                .subscribe(System.out::println);

// Opción 2: onErrorResume → flujo alternativo
//        fluxTemperaturas
//                .map(Double::parseDouble)
//                .onErrorResume(error -> {
//                    System.out.println("Error detectado: " + error.getMessage());
//                    return Flux.just(0.0, -1.0); // valores alternativos
//                })
//                .subscribe(
//                        valor -> System.out.println("onNext -> " + valor),
//                        error -> System.out.println("onError -> " + error.getMessage()),
//                        () -> System.out.println("onComplete -> Flujo Terminado")
//                );

        /*TODO: Servicio de precios de productos online
            Tienes un Flux<String> con precios de productos obtenidos de una API externa
            ("10.5", "20.0", "NaN", "30.1").
            Convierte a Double.
            Si ocurre un error, usa onErrorResume para cambiar a un Flux alternativo con
            un precio fijo (ej. Flux.just(9.99)).
            Usa doOnError para simular un log: "Error al procesar precio -> {mensaje}".*/

//        Flux<String> preciosExternos = Flux.just("10.5", "20.5", "NaN", "30.1");
//
//        preciosExternos.map(Double::parseDouble)
//                .map(BigDecimal::valueOf)
//                .doOnError(e -> log.error("Ocurrio un error {}", e.getMessage()))
//                .onErrorResume(error -> {
//                    if (error instanceof RuntimeException)
//                        return Flux.just(BigDecimal.valueOf(0.0));
//                    else
//                        return Flux.error(error);
//                }).doOnComplete(() -> log.info("Flujo terminado"))
//                .doFinally(signal -> log.info("El flujo termino con señal {}", signal))
//                .subscribe(
//                        onContinue -> System.out.println("onContinue = " + onContinue),
//                        onError -> System.out.println("onError.getMessage() = " + onError.getMessage())
//                );

        /*TODO: Registro de actividad en un sistema bancario
           Simula un Flux<String> con movimientos bancarios ("Deposito:100", "Retiro:50", "ERROR", "Deposito:200").
           Si ocurre un error en la conversión de valores, usa onErrorReturn para devolver "Movimiento inválido".
           Usa doOnError para simular un registro en la bitácora de auditoría:
           "⚠️ Se detectó un movimiento sospechoso".*/
        Flux<String> sistemaBancario = Flux.just("Deposito:100", "Deposito: 50", "Retiro:300", "ERROR",
                "Deposito: 200");

        sistemaBancario
                .filter(tipo -> tipo.startsWith("Deposito") || tipo.startsWith("Retiro"))
                .filter(mov -> mov.contains(":"))
                .map(n -> n.split(":")[1])
                .map(Double::parseDouble)
                .doOnNext(num -> log.info("Numero aceptado {}", num))
                .onErrorReturn(-1.0)
                .subscribe(
                        ok -> System.out.println("Procesado: " + ok),
                        error -> System.out.println("Error encontrado: " + error.getMessage()),
                        () -> System.out.println("Flujo terminado")
                );

    }
}
