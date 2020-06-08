package ecs.order.controller;

import ecs.order.model.Order;
import ecs.order.model.Shipment;
import ecs.order.service.OrderService;
import ecs.order.util.MapBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Value("${variables.shipment.uri:http://localhost:8001}")
    private String shipmentHost;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/order/get/{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
        LOGGER.info("getOrder - {}", orderId);

        Order order = orderService.findOrderById(orderId);

        if (order != null) {
            ResponseEntity<Shipment> responseEntity = restTemplate.getForEntity(
                    shipmentHost + "/api/v1/shipment/get/{shipmentId}",
                    Shipment.class, new MapBuilder.Builder<Long>().map("shipmentId", order.getShipmentId()).build().map());
            Shipment shipment = responseEntity.getBody();

            if (shipment != null) {
                order.setShipmentAddress(shipment.getShipmentAddress());
            }
        }

        return order;
    }

    @GetMapping("/order/list")
    public List<Order> listOrders() {
        LOGGER.info("listOrders");

        List<Order> orders = orderService.listOrders();
        return orders;
    }

    @PostMapping("/order/save")
    public void saveOrder(@RequestBody @NotNull Order order) {

        LOGGER.info("saveOrder - {}", order.getOrderId());

        orderService.createOrder(order);
    }
}
