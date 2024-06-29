package hr.k33zo.gamewebshop.service;

import hr.k33zo.gamewebshop.model.Order;
import hr.k33zo.gamewebshop.repository.OrderRepo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepo.findByUserId(userId);
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepo.findById(id);
    }

    public Order saveOrder(Order order) {
        orderRepo.save(order);
        return order;
    }

    public void deleteOrderById(Long id) {
        orderRepo.deleteById(id);
    }

    public List<Order> searchByUserIdAndPurchaseDate(long userId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        Timestamp startTimestamp = Timestamp.valueOf(startOfDay);

        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        Timestamp endTimestamp = Timestamp.valueOf(endOfDay);

        return orderRepo.findByUserIdAndOrderDateBetween(userId, startTimestamp, endTimestamp);
    }

    public List<Order> getAllOrdersByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        Timestamp startTimestamp = Timestamp.valueOf(startOfDay);

        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        Timestamp endTimestamp = Timestamp.valueOf(endOfDay);

        return orderRepo.findByOrderDateBetween(startTimestamp, endTimestamp);
    }



}
