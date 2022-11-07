package bg.softuni.springdataadvancedquerylab;

import bg.softuni.springdataadvancedquerylab.entities.Ingredient;
import bg.softuni.springdataadvancedquerylab.entities.Shampoo;
import bg.softuni.springdataadvancedquerylab.services.IngredientService;
import bg.softuni.springdataadvancedquerylab.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ShampooService shampooService;

    private final IngredientService ingredientService;

    @Autowired
    public ConsoleRunner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

//        this.shampooService.findByBrand("Cotton Fresh")
//                .forEach(shampoo -> System.out.println(shampoo.getId()));

//        this.shampooService.findByBrandAndSize("Cotton Fresh", Size.LARGE)
//                .forEach(shampoo -> System.out.println(shampoo.getPrice()));


        //1. Select Shampoos by Size
//        String inputSize = scanner.nextLine();
//
//        Size size = Size.valueOf(inputSize);
//
//        this.shampooService.findBySizeOrderById(size)
//                .forEach(shampoo ->
//                        System.out.println(shampoo.getBrand() + " " + inputSize + " " + shampoo.getPrice()));


        //2. Select Shampoos by Size or Label
//        String sizeInput = scanner.nextLine();
//        long labelIdInput = Long.parseLong(scanner.nextLine());
//
//        Size size = Size.valueOf(sizeInput);
//
//        this.shampooService.findBySizeOrLabelIdOrderByPriceAsc(size, labelIdInput)
//                .forEach(shampoo ->
//                        System.out.println(shampoo.getBrand() + " " + shampoo.getSize() + " " + shampoo.getPrice()));


        //3. Select Shampoos by Price
//        BigDecimal priceInput = new BigDecimal(scanner.nextLine());
//
//        this.shampooService.findByPriceIsGreaterThanOrderByPriceDesc(priceInput)
//                .forEach(shampoo ->
//                        System.out.println(shampoo.getBrand() + " " + shampoo.getSize() + " " + shampoo.getPrice()));


        //4. Select Ingredients by Name
//        String lettersInput = scanner.nextLine();
//
//        this.ingredientService.findIngredientByNameStartsWith(lettersInput)
//                .forEach(ingredient -> System.out.println(ingredient.getName()));


        //5. Select Ingredients by Names
//        String ingredientInput = scanner.nextLine();
//
//        List<String> ingredients = new ArrayList<>();
//
//        while (!ingredientInput.isBlank()) {
//            ingredients.add(ingredientInput);
//
//            ingredientInput = scanner.nextLine();
//        }
//
//        this.ingredientService.selectByNames(ingredients).forEach(ingredient -> System.out.println(ingredient.getName()));


        //6. Count Shampoos by Price
//        BigDecimal priceInput = new BigDecimal(scanner.nextLine());
//
//        List<Shampoo> allByPriceIsLessThan = this.shampooService.findAllByPriceIsLessThan(priceInput);
//
//        System.out.println(allByPriceIsLessThan.size());


        //7. Select Shampoos by Ingredients
//        String ingredientInput = scanner.nextLine();
//
//        List<String> ingredients = new ArrayList<>();
//
//        while (!ingredientInput.isBlank()) {
//            ingredients.add(ingredientInput);
//
//            ingredientInput = scanner.nextLine();
//        }
//
//        this.shampooService.findByIngredient(ingredients)
//                .forEach(shampoo -> System.out.println(shampoo.getBrand()));


        //8. Select Shampoos by Ingredients Count
//        int count = Integer.parseInt(scanner.nextLine());
//
//        this.shampooService.findByIngredientCountLessThan(count)
//                .forEach(shampoo -> System.out.println(shampoo.getBrand()));


        //9. Delete Ingredients by Name
//        String deleteIngredient = scanner.nextLine();
//
//        this.ingredientService.deleteIngredientsByName(deleteIngredient);


        //10. Update Ingredients by Price
//        this.ingredientService.updateAllPrice();


        //11. Update Ingredients by Names
//        String name = scanner.nextLine();
//
//        String nameInput = scanner.nextLine();
//
//        List<String> ingredients = new ArrayList<>();
//
//        while (!nameInput.isBlank()) {
//            ingredients.add(name);
//
//            nameInput = scanner.nextLine();
//        }
//
//        this.ingredientService.updateAllPriceByNames(ingredients);
    }
}
