package com.example.json.productShop.repositories;

import com.example.json.productShop.entities.User;
import com.example.json.productShop.entities.dtos.UserDTO;
import com.example.json.productShop.entities.dtos.UserWithProductsWrapperDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN u.sellingProducts p WHERE p.buyer IS NOT NULL")
    List<User> findAllBySellingProductsGreaterThanEqual();

//    @Query("SELECT u FROM User u JOIN u.sellingProducts p" +
//            " WHERE p.buyer IS NOT NULL ORDER BY u.sellingProducts.size DESC, u.lastName ASC")
    List<User> findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName();
}
