package ecs.order.controller;

import ecs.order.model.Order;
import ecs.order.model.vo.OrderVO;
import ecs.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Value("${version.tag:}")
    private String version;

    @GetMapping("/order/get/{orderId}")
    public OrderVO getOrder(@PathVariable Long orderId) {
        LOGGER.info("getOrder - {}", orderId);

        Order order = orderService.findOrderById(orderId);
        OrderVO orderVO = OrderVO.OrderVOBuilder.newBuilder()
                 .version(version)
                 .orderId(order.getOrderId())
                 .customerId(order.getCustomerId())
                 .itemId(order.getItemId())
                 .shipmentId(order.getShipmentId())
                 .shipmentAddress(order.getShipmentAddress()).build();
        return orderVO;
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
