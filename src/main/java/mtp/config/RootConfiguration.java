package mtp.config;

import mtp.repository.OrderRepository;
import mtp.service.OrderService;
import mtp.service.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackageClasses = OrderRepository.class)
public class RootConfiguration {

    @Bean
    public OrderService OrderService(OrderRepository OrderRepository) {
        return new OrderServiceImpl(OrderRepository);
    }

}
