package com.magicchestcore.repositories;

import com.magicchestcore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product, Integer>{
        List<Product> findAllByDtype(String dtype);

}
