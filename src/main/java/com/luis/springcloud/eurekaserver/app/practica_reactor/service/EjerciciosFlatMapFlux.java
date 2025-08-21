package com.luis.springcloud.eurekaserver.app.practica_reactor.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

public class EjerciciosFlatMapFlux {
    public static void main(String[] args) {
        /*TODO: Pedidos de productos por cliente
            Tienes:
            Un Flux<String> con clientes: ["Carlos", "María"].
            Un Flux<Product> con productos: [Laptop, Mouse].
            Usando flatMap, genera un Flux<Order> donde cada cliente reciba todos los productos
            disponibles (cada cliente tendrá su propia orden con la lista de productos).*/

       Flux<String> clienteFlux = Flux.just("Carlos", "Maria");
       Flux<Producto> productoFlux = Flux.just(new Producto("Laptop", BigDecimal.valueOf(20.5)),
               new Producto("Mouse", BigDecimal.valueOf(30.2)));

       Flux<Orders> ordersFluxs = clienteFlux
               .flatMap(cliente ->
                   productoFlux.map(producto -> Orders.builder()
                           .nombre(cliente)
                           .producto(producto)
                           .total(producto.getPrecio().multiply(BigDecimal.valueOf(2L)))
                           .build()));

       ordersFluxs.subscribe(System.out::println);

//        Flux<Orders> ordersFlux = Flux.zip(clienteFlux, productoFlux)
//                .map(tupla ->
//                        Orders.builder()
//                                .nombre(tupla.getT1())
//                                .producto(tupla.getT2())
//                                .build());
//
//        ordersFlux.subscribe(System.out::println);

    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
class Usuario{
    private Long id;
    private String nombre;
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
class Producto{
    private String nombre;
    private BigDecimal precio;
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
class Orders{
    private String nombre;
    private Producto producto;
    private BigDecimal total;
}
