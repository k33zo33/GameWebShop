package hr.k33zo.gamewebshop.service;

import hr.k33zo.gamewebshop.model.OrderItem;
import hr.k33zo.gamewebshop.repository.OrderItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepo orderItemRepo;

    @Autowired
    public OrderItemService(OrderItemRepo orderItemRepo) {
        this.orderItemRepo = orderItemRepo;
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepo.findAll();
    }

    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepo.findById(id);
    }
    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepo.save(orderItem);
    }
    public void deleteOrderItemById(Long id) {
        orderItemRepo.deleteById(id);
    }
}
