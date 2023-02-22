package com.magicchestcore.repositories;


import com.magicchestcore.models.Dress;
import com.magicchestcore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
