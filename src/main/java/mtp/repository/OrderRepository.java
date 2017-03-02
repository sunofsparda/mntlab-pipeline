package mtp.repository;

import mtp.domain.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<Order, UUID>, OrderRepositoryCustom {}
