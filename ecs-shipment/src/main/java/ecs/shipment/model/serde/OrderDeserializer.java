package ecs.shipment.model.serde;

import ecs.shipment.model.Order;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class OrderDeserializer extends JsonDeserializer<Order> {
    public OrderDeserializer() {
        super(Order.class);
    }
}
