package cm.ls.orderservice.repository;

import cm.ls.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
