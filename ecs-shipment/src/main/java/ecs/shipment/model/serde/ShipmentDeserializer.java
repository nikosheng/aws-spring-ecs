package ecs.shipment.model.serde;

import ecs.shipment.model.Shipment;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class ShipmentDeserializer extends JsonDeserializer<Shipment> {

    public ShipmentDeserializer() {
        super(Shipment.class);
    }
}
