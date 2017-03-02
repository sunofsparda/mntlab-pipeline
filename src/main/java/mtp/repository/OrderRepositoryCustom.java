package mtp.repository;

import  mtp.domain.Order;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> findByStore(String store);
}
