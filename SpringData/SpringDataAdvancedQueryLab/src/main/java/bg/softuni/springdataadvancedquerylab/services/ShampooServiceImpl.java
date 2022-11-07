package bg.softuni.springdataadvancedquerylab.services;

import bg.softuni.springdataadvancedquerylab.entities.Shampoo;
import bg.softuni.springdataadvancedquerylab.entities.Size;
import bg.softuni.springdataadvancedquerylab.repositories.ShampooRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> findByBrand(String brand) {
        return this.shampooRepository.findByBrand(brand);
    }

    @Override
    public List<Shampoo> findByBrandAndSize(String brand, Size size) {
        return this.shampooRepository.findByBrandAndSize(brand, size);
    }

    @Override
    public List<Shampoo> findBySizeOrderById(Size size) {
        return this.shampooRepository.findBySizeOrderById(size);
    }

    @Override
    public List<Shampoo> findBySizeOrLabelIdOrderByPriceAsc(Size size, Long labelId) {
        return this.shampooRepository.findBySizeOrLabelIdOrderByPriceAsc(size, labelId);
    }

    @Override
    public List<Shampoo> findByPriceIsGreaterThanOrderByPriceDesc(BigDecimal price) {
        return this.shampooRepository.findByPriceIsGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public List<Shampoo> findAllByPriceIsLessThan(BigDecimal price) {
        return this.shampooRepository.findAllByPriceIsLessThan(price);
    }

    @Override
    public List<Shampoo> findByIngredient(List<String> ingredients) {
        return this.shampooRepository.findByIngredients(ingredients);
    }

    @Override
    public List<Shampoo> findByIngredientCountLessThan(int number) {
        return this.shampooRepository.findByIngredientCountLessThan(number);
    }

}
