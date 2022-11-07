package bg.softuni.springdataadvancedquerylab.services;

import bg.softuni.springdataadvancedquerylab.entities.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IngredientService {

    List<Ingredient> findIngredientByNameStartsWith(String letters);

    List<Ingredient> selectByNames (List<String> names);

    void deleteIngredientsByName(String name);

    void updateAllPrice();

    void updateAllPriceByNames(List<String> names);
}
