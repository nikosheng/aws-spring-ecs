package ecs.user.model.serde;

import ecs.user.model.Shipment;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class ShipmentSerializer extends JsonSerializer<Shipment> {
}
