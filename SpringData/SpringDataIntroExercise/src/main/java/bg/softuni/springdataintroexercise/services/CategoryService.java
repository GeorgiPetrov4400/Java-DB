package bg.softuni.springdataintroexercise.services;

import bg.softuni.springdataintroexercise.entities.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getRandomCategories();
}
