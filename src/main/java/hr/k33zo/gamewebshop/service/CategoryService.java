package hr.k33zo.gamewebshop.service;


import hr.k33zo.gamewebshop.model.Category;
import hr.k33zo.gamewebshop.repository.CategoryRepo;
import hr.k33zo.gamewebshop.repository.ItemRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;
    private final ItemRepo itemRepo;

    public CategoryService(CategoryRepo categoryRepo, ItemRepo itemRepo) {
        this.categoryRepo = categoryRepo;
        this.itemRepo = itemRepo;
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepo.findById(id);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
    public void saveCategory(Category category) {
        categoryRepo.save(category);
    }

    public void deleteCategoryById(Long id) {
           itemRepo.findByCategoryId(id).forEach(item -> {
                item.setCategory(categoryRepo.findById((long) 1).orElse(new Category("No category",
                        "This item's category was deleted.")));
                itemRepo.save(item);
            });
            categoryRepo.deleteById(id);
    }
}
