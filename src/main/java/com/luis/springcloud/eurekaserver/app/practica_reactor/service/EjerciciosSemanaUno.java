package com.luis.springcloud.eurekaserver.app.practica_reactor.service;

import reactor.core.publisher.Flux;

public class EjerciciosSemanaUno {
    public static void main(String[] args) {
//        Flux<Integer> flujoEnteros = Flux.range(1, 10)
//                .filter(n -> n % 2 == 0);
//
//        flujoEnteros.subscribe(
//                valor -> System.out.println("on next -> " + valor),
//                valor -> System.out.println("on error -> " + valor.getLocalizedMessage()),
//                () -> System.out.println("on complete -> flujo terminado ")
//        );

        /*TODO: Multiplicación de valores
           Genera un Flux con los números del 1 al 5.
           Usa map para multiplicarlos por 3.
           Suscríbete mostrando cada valor y un mensaje de finalización.*/

//        Flux<Integer> flujoNumeros2 = Flux.range(1, 5);
//
//        flujoNumeros2.map(n -> n * 3)
//                .subscribe(v -> System.out.println("Resultado: " + v));


        /*TODO: Control de error
           Crea un Flux con [2, 4, 0, 8].
           En un map, divide 10 / n.
           Maneja el error que ocurre con el 0 y muestra el mensaje en onError.*/

//        Integer[] arreglo = {2, 4, 0, 8};
//        Flux<Integer> flujoNumeros3 = Flux.fromArray(arreglo)
//                .map(n -> 10 / n)
//                .onErrorReturn(-1);
//
//        flujoNumeros3.subscribe(
//                onNext -> System.out.println("Valor -> " + onNext),
//                onError -> System.out.println("Error : " + onError.getLocalizedMessage())
//        );


        /*TODO: onErrorReturn
            Repite el ejercicio anterior, pero usa onErrorReturn(-1) para que el
            flujo no muera y devuelva -1 en lugar de fallar.*/

//        flujoNumeros3.subscribe(
//                onNext -> System.out.println("Valor -> " + onNext),
//                onError -> System.out.println("Error : " + onError)
//        );

        /*TODO: onErrorResume
           Crea un Flux con [1, 2, 3, 4].
           Haz que si el número es mayor que 2, se dispare un error.
           Usa onErrorResume para continuar con un Flux que emita [99, 100].*/
//
//        Flux<String> flujoString1 = Flux.just("A", "B", "C");
//        Flux<String> flujoString2 = Flux.just("D", "E", "F");
//
//        Flux<String> flujoString3 = flujoString1
//                .flatMap(l ->
//                        flujoString2.map(f -> f));
//
//        flujoString3.subscribe(v -> System.out.println("v = " + v));

        /*TODO Suma con reduce
           Genera un Flux con los números del 1 al 5.
           Usa reduce para calcular la suma total.
           Suscríbete para mostrar el resultado.*/
//        Flux<Integer> numerosEjercicioSuma = Flux.range(1, 5)
//                .reduce(Integer::sum)
//                .flux();
//
//        numerosEjercicioSuma.subscribe(v -> System.out.println("suma total = " + v));

        /*TODO: Simulación de error en filtro
           Crea un Flux con [1,2,3,4,5].
           En el filtro, si el número es 3, lanza un error manualmente.
           Suscríbete mostrando onNext, onError y onComplete.*/

        Flux<Integer> ultimoFlux = Flux.just(1, 2, 5, 0);

//        ultimoFlux.subscribe(
//                v -> System.out.println("onNext = " + v),
//                onError -> System.out.println("onError = " + onError.getMessage()));

        ultimoFlux.map(n -> n / 10)
                .onErrorReturn(e -> e instanceof ArithmeticException,
                        -1)
                .subscribe(System.out::println);

    }
}
