package ecs.shipment.model.serde;

import ecs.shipment.model.Shipment;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class ShipmentSerializer extends JsonSerializer<Shipment> {
}
