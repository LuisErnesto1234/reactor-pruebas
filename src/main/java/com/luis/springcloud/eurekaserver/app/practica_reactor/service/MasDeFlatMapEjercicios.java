package com.luis.springcloud.eurekaserver.app.practica_reactor.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class MasDeFlatMapEjercicios {
    public static void main(String[] args) {
        /*TODO: Ejercicio 1: Sistema de procesamiento de órdenes distribuido
            Tienes un Flux<Order> de órdenes de compra
            Cada orden necesita validarse en 3 servicios externos paralelos (inventario, pagos, envío)
            Solo procesar órdenes que pasen TODAS las validaciones
            Si alguna validación falla, reintentar 2 veces antes de descartar
            Al final enviar notificación por email y SMS a cada cliente*/

        Flux<OrderItems> orderItemsFlux = Flux.just(
                new OrderItems("SM1", 3, BigDecimal.valueOf(22.6)),
                new OrderItems("SM2", 20, BigDecimal.valueOf(12.4)));

        Flux<OrderProduct> orderProductFlux = orderItemsFlux
                .collectList()
                .flatMap(producto -> {

                OrderProduct orderProduct = OrderProduct.builder()
                            .orderId("SM32")
                            .items(producto.reversed())
                            .status(OrderStatus.PENDING)
                            .customerEmail("luis@gmail.com")
                            .paymentMethodId("23s")
                            .customerPhone("9902134524")
                            .shippingAddress("Av Los Incas")
                            .totalAmount((BigDecimal) producto.reversed().stream()
                                    .map(p -> p.getUnitPrice()
                                            .multiply(BigDecimal.valueOf(p.getQuantity()))))
                            .build();

                if (orderProduct.getStatus() == OrderStatus.PAYMENT_VALIDATED){
                    ValidationResult validationResult =
                            new ValidationResult(true, "Exito", ValidationService.PAYMENT);

                    if (validationResult.isValid()){

                    }
                }


                })
        });

    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
class OrderProduct {
    private String orderId;
    private String customerId;
    private String customerEmail;
    private String customerPhone;
    private List<OrderItems> items;
    private BigDecimal totalAmount;
    private String shippingAddress;
    private String paymentMethodId;
    private OrderStatus status; // PENDING, VALIDATED, REJECTED
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class OrderItems {
    private String productId;
    private Integer quantity;
    private BigDecimal unitPrice;
}

enum OrderStatus {
    PENDING, INVENTORY_VALIDATED, PAYMENT_VALIDATED,
    SHIPPING_VALIDATED, FULLY_VALIDATED, REJECTED
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class ValidationResult {
    private boolean isValid;
    private String errorMessage;
    private ValidationService serviceType;
}

enum ValidationService {
    INVENTORY, PAYMENT, SHIPPING
}