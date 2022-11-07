package bg.softuni.springdataadvancedquerylab.repositories;

import bg.softuni.springdataadvancedquerylab.entities.Shampoo;
import bg.softuni.springdataadvancedquerylab.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    List<Shampoo> findByBrand(String brand);

    List<Shampoo> findByBrandAndSize(String brand, Size size);

    List<Shampoo> findBySizeOrderById(Size size);

    List<Shampoo> findBySizeOrLabelIdOrderByPriceAsc(Size size, Long labelId);

    List<Shampoo> findByPriceIsGreaterThanOrderByPriceDesc(BigDecimal price);

    List<Shampoo> findAllByPriceIsLessThan(BigDecimal price);

    @Query(value = "SELECT s FROM Shampoo AS s JOIN s.ingredients AS i WHERE i.name IN :ingredients ")
    List<Shampoo> findByIngredients(List<String> ingredients);

    @Query(value = "SELECT s FROM Shampoo AS s WHERE s.ingredients.size < :number")
    List<Shampoo> findByIngredientCountLessThan(int number);
}
