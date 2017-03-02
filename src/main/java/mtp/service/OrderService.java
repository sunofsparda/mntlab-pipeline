package mtp.service;


import mtp.domain.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order save(Order Order);
    Order update(Order Order);
    Order findOne(UUID uuid);
    void delete(UUID uuid);

    List<Order> findOrdersInStore(String store);
}
