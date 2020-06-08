package ecs.shipment.controller;

import ecs.shipment.model.Shipment;
import ecs.shipment.service.ShipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class ShipmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShipmentController.class);

    @Autowired
    private ShipmentService shipmentService;

    @GetMapping("/shipment/get/{shipmentId}")
    public Shipment getShipment(@PathVariable Long shipmentId) {
        LOGGER.info("getShipment - {}", shipmentId);

        Shipment shipment = shipmentService.findShipmentById(shipmentId);
        return shipment;
    }

    @GetMapping("/shipment/list")
    public List<Shipment> listShipments() {
        LOGGER.info("listShipments");

        List<Shipment> orders = shipmentService.listShipments();
        return orders;
    }

    @PostMapping("/shipment/save")
    public void saveShipment(@RequestBody @NotNull Shipment shipment) {

        LOGGER.info("saveShipment - {}", shipment.getShipmentId());

        shipmentService.createShipment(shipment);
    }
}
