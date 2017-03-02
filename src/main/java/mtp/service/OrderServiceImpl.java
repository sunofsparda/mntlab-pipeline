package mtp.service;

import mtp.domain.Order;
import mtp.repository.OrderRepository;

import java.util.List;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository OrderRepository;

    

    public OrderServiceImpl(OrderRepository OrderRepository) {
        this.OrderRepository = OrderRepository;
      
    }

    @Override
    public Order save(Order Order) {
        if (Order.getId() == null) {
            Order.setId(UUID.randomUUID());
        }
        this.OrderRepository.save(Order);
        return Order;
    }

    @Override
    public Order update(Order Order) {
        this.OrderRepository.save(Order);
        return Order;
    }

    @Override
    public Order findOne(UUID uuid) {
        return this.OrderRepository.findOne(uuid);
    }

    @Override
    public void delete(UUID uuid) {
        Order Order = this.OrderRepository.findOne(uuid);
        if (Order != null) {
            this.OrderRepository.delete(uuid);
           
        }
    }

    @Override
    public List<Order> findOrdersInStore(String store) {
        return this.OrderRepository.findByStore(store);
    }
}
