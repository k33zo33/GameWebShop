package hr.k33zo.gamewebshop.repository;

import hr.k33zo.gamewebshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);
    List<Order> findByUserIdAndOrderDate(Long userId, Timestamp orderDate);

    List<Order> findByUserIdAndOrderDateBetween(Long userId, Timestamp startTimestamp, Timestamp endTimestamp);
    List<Order> findByOrderDateBetween(Timestamp startTimestamp, Timestamp endTimestamp);




}
