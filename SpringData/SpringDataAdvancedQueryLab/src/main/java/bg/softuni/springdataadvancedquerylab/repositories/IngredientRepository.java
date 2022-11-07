package bg.softuni.springdataadvancedquerylab.repositories;

import bg.softuni.springdataadvancedquerylab.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findIngredientByNameStartsWith(String letters);

    List<Ingredient> findByNameInOrderByPriceAsc(List<String> names);

    void deleteIngredientsByName(String name);

    @Query(value = "UPDATE Ingredient AS i SET i.price = i.price * 1.10")
    @Modifying
    void updateAllPrice();

    @Query(value = "UPDATE Ingredient AS i SET i.price = i.price * 2 WHERE i.name IN :names")
    @Modifying
    void updateAllPriceByNames(List<String> names);
}
