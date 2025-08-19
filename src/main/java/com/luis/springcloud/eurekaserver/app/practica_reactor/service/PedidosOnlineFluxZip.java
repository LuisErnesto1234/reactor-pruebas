package com.luis.springcloud.eurekaserver.app.practica_reactor.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

public class PedidosOnlineFluxZip {
    public static void main(String[] args) {
        /*TODO 📝 Enunciado del reto
           Imagina que trabajas en una plataforma de pedidos online.
            Tienes tres fuentes de datos simuladas:
            Flux<String> con los nombres de los productos: ["Laptop", "Mouse", "Teclado", "Monitor"].
            Flux<Double> con los precios de cada producto: [1200.0, 25.5, 75.0, 300.0].
            Flux<Integer> con las cantidades pedidas de cada producto: [2, 5, 3, 1].*/

        /*todo SALIDA ESPERADA:
           Laptop x2 = 2400.0
            Mouse x5 = 127.5
            Teclado x3 = 225.0
            Monitor x1 = 300.0
            TOTAL = 302.5*/

//        Flux<String> nameProductsFlux = Flux.just("Laptop", "Mouse", "Teclado", "Monitor");
//        Flux<BigDecimal> priceProductsFlux = Flux.just(BigDecimal.valueOf(1200.0), BigDecimal.valueOf(25.5),
//                BigDecimal.valueOf(75.0), BigDecimal.valueOf(300.0));
//        Flux<Integer> countOrdersProductsFlux = Flux.just(2, 5, 3, 1);
//
//        Flux<Product> productFlatMapFlux =
//                Flux.zip(nameProductsFlux, priceProductsFlux, countOrdersProductsFlux)
//                        .map(tuple -> Product.builder()
//                                .name(tuple.getT1())
//                                .price(tuple.getT2())
//                                .countOrder(tuple.getT3())
//                                .build());
//
//        productFlatMapFlux.subscribe(onContinue ->
//                System.out.println(onContinue.getName() + " X" + onContinue.getPrice() + " = " + onContinue.getPrice()));
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
class Product{
    private String name;
    private BigDecimal price;
}
