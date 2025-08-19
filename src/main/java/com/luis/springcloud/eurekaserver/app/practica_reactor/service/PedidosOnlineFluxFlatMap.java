package com.luis.springcloud.eurekaserver.app.practica_reactor.service;

import lombok.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

public class PedidosOnlineFluxFlatMap {
    public static void main(String[] args) {
        /*📝TODO Enunciado del ejercicio
               Una tienda online recibe pedidos de clientes en diferentes momentos del día.
                Cada pedido contiene:
                El nombre del cliente.
                Una lista de productos comprados (con nombre y precio).
                👉 Tu tarea es:
                Simular un Flux de clientes (Flux<String>) con algunos nombres de clientes.
                Para cada cliente, simular que va a realizar un pedido. Ese pedido debe estar representado como un
                Flux<Product> con 2 o 3 productos diferentes.
                Usar flatMap para transformar el Flux<String> de clientes en un Flux<Order> (donde cada cliente tendrá
                un pedido con sus productos).
                Mostrar en consola el nombre del cliente y el detalle de su pedido (productos y precios).
                📌 Reglas:
                Define una clase Product y una clase Order (con cliente y lista de productos).
                Debes usar flatMap para relacionar clientes → pedidos.
                El flujo debe simular asincronía: por ejemplo, con delayElements o Mono.delay para que los pedidos
                no lleguen en el mismo tiempo.*/

        Flux<String> clientsFlux = Flux.just("Luis", "Ernesto", "Mateo");
        Flux<Product> productsFlux = Flux.just(new Product("Saltado de Pollo", BigDecimal.valueOf(11.2)),
                new Product("Pollo a la Brasa", BigDecimal.valueOf(20.5)));

        Flux<Order> orderFlux = clientsFlux.flatMap(client ->
                productsFlux.collectList()  // devuelve Mono<List<Product>>
                        .map(products -> Order.builder()
                                .clienteName(client)
                                .products(products)
                                .countOrder(products.size())
                                .totalPay(products.stream()
                                        .map(Product::getPrice)
                                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                                .build()
                        )
        );

        orderFlux.subscribe(System.out::println);

        /*TODO: Tienes un Flux<String> con una lista de nombres: ["laptop", "mouse", "monitor", "teclado"].
            Usando map, transforma todos los nombres a mayúsculas y muéstralos en consola.*/

        Flux.just("laptop", "mouse", "monitor", "teclado")
                .map(String::toUpperCase)
                .subscribe(System.out::println);

        /*TODO: Tienes un Flux<BigDecimal> con precios de productos: [100.0, 250.0, 75.0].
            Con map, aplica un 10% de descuento a cada precio y muéstralos.*/
        Flux.just(BigDecimal.valueOf(100.0), BigDecimal.valueOf(250.0), BigDecimal.valueOf(75.0))
                .map(precio -> precio.multiply(BigDecimal.valueOf(0.9)))
                .subscribe(System.out::println);

        /*TODO: 📝 Ejercicio 3: Generar objetos a partir de datos simples
           Tienes un Flux<String> con nombres de clientes: ["Luis", "Ana", "Mateo"].
           Crea con map una lista de objetos Cliente donde cada cliente tenga un id generado
           automáticamente (por ejemplo, el índice) y el nombre del cliente.*/

        Flux<String> clientsNames = Flux.just("Luis", "Ana", "Mateo");

        clientsNames
                .index()
                .map(tuple -> new Cliente(tuple.getT1().intValue() + 1, tuple.getT2()))
                .subscribe(cliente -> System.out.println("Los clientes son: " + cliente));

        /*TODO: Simulación de API de usuarios
            Tienes un Flux<String> con nombres de usuarios: ["Luis", "Ana", "Mateo"].
            Usando flatMap, simula una llamada asíncrona (por ejemplo con Mono.just(...)
            y delayElement(Duration.ofMillis(500))) que devuelva un objeto Usuario con nombre
            y un id autogenerado.*/

        Flux<String> nombresFlux = Flux.just("Luis", "Ana", "Mateo");

//        Flux<Cliente> clientesFlux = nombresFlux
//                .flatMap(cliente -> {
//                    Flux.just()
//                    new Cliente(,cliente)
//                })
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
class Order {
    private String clienteName;
    private List<Product> products;
    private Integer countOrder;
    private BigDecimal totalPay;
}

@AllArgsConstructor
@ToString
class Cliente{
    private Integer id;
    private String name;
}
