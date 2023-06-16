package com.magicchestcore.servicies;

import com.magicchestcore.models.Product;
import com.magicchestcore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public List<Product> findAllByDtype(String dtype){
        return productRepository.findAllByDtype(dtype);
    }

    public Optional<Product> findById(Integer id){
        return productRepository.findById(id);
    }

    @Transactional
    public void save(Product product){
        productRepository.save(product);
    }

    @Transactional
    public void update(Product upDatedProduct){
        productRepository.save(upDatedProduct);
    }

    @Transactional
    public void delete(Integer id){
        productRepository.deleteById(id);
    }
}
