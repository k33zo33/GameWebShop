package hr.k33zo.gamewebshop.repository;

import hr.k33zo.gamewebshop.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {

    List<Item> findByName(String name);
    Long findIdByName(String name);
    List<Item> findByCategoryId(Long id);
}
