package com.magicchestcore.controllers;

import com.magicchestcore.models.Product;
import com.magicchestcore.servicies.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> findById(@PathVariable("id") Integer id) {
        return productService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody Product product){
       productService.save(product);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody Product updateProduct) {
        productService.update(id, updateProduct);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        productService.delete(id);
    }

}
