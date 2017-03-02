package mtp.web;

import mtp.domain.Order;
import mtp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {

    private final OrderService OrderService;

    @Autowired
    public OrderController(OrderService OrderService) {
        this.OrderService = OrderService;
    }

    @GetMapping(path = "/")
    public String get() {
        return "ping";
    }   

    @GetMapping(path = "/getStoreOrder/{store}")
    public List<Order> findOrdersInStore(@PathVariable("store") String store) {
        return this.OrderService.findOrdersInStore(store);
    }
}
