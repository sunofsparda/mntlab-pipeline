package mtp.repository;

import mtp.domain.Order;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final CassandraTemplate cassandraTemplate;

    @Autowired
    public OrderRepositoryImpl(CassandraTemplate cassandraTemplate) {
        this.cassandraTemplate = cassandraTemplate;
    }

    @Override
    public List<Order> findByStore(String store) {
        Select select = QueryBuilder.select().from("storeorder");
        select.where(QueryBuilder.eq("store", store));
        return this.cassandraTemplate.select(select, Order.class);
    }
}
