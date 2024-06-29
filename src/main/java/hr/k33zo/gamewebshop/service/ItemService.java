package hr.k33zo.gamewebshop.service;

import hr.k33zo.gamewebshop.model.Item;
import hr.k33zo.gamewebshop.repository.ItemRepo;
import hr.k33zo.gamewebshop.repository.OrderItemRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Getter
@Service
public class ItemService {


    private final ItemRepo itemRepository;
    private final OrderItemRepo orderItemRepo;

    @Autowired
    public ItemService(ItemRepo itemRepository, OrderItemRepo orderItemRepo) {
        this.itemRepository = itemRepository;
        this.orderItemRepo = orderItemRepo;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public double getPriceByName(String name) {
        return itemRepository.findByName(name).stream()
                .findFirst()
                .map(Item::getPrice)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    public Long getItemIdByName(String name) {
        return itemRepository.findIdByName(name);
    }

    public List<Item> getItemsByCategoryId(Long id) {
        return itemRepository.findByCategoryId(id);
    }


}
