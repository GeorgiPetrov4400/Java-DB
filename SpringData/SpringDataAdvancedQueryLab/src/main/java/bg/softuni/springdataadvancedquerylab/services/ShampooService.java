package bg.softuni.springdataadvancedquerylab.services;

import bg.softuni.springdataadvancedquerylab.entities.Shampoo;
import bg.softuni.springdataadvancedquerylab.entities.Size;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface ShampooService {

    List<Shampoo> findByBrand(String brand);

    List<Shampoo> findByBrandAndSize(String brand, Size size);

    List<Shampoo> findBySizeOrderById(Size size);

    List<Shampoo> findBySizeOrLabelIdOrderByPriceAsc(Size size, Long labelId);

    List<Shampoo> findByPriceIsGreaterThanOrderByPriceDesc(BigDecimal price);

    List<Shampoo> findAllByPriceIsLessThan(BigDecimal price);

    List<Shampoo> findByIngredient(List<String> ingredients);

    List<Shampoo> findByIngredientCountLessThan(int number);
}
