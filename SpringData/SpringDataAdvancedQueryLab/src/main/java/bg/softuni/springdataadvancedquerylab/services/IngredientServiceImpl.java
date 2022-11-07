package bg.softuni.springdataadvancedquerylab.services;

import bg.softuni.springdataadvancedquerylab.entities.Ingredient;
import bg.softuni.springdataadvancedquerylab.repositories.IngredientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;


    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    @Override
    public List<Ingredient> findIngredientByNameStartsWith(String letters) {
        return this.ingredientRepository.findIngredientByNameStartsWith(letters);
    }

    @Override
    public List<Ingredient> selectByNames(List<String> names) {
        return this.ingredientRepository.findByNameInOrderByPriceAsc(names);
    }

    @Override
    @Transactional
    public void deleteIngredientsByName(String name) {
        this.ingredientRepository.deleteIngredientsByName(name);
    }

    @Override
    @Transactional
    public void updateAllPrice() {
        this.ingredientRepository.updateAllPrice();
    }

    @Override
    @Transactional
    public void updateAllPriceByNames(List<String> names) {
        this.ingredientRepository.updateAllPriceByNames(names);
    }

}

