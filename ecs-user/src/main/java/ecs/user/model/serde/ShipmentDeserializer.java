package ecs.user.model.serde;

import ecs.user.model.Shipment;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class ShipmentDeserializer extends JsonDeserializer<Shipment> {

    public ShipmentDeserializer() {
        super(Shipment.class);
    }
}
