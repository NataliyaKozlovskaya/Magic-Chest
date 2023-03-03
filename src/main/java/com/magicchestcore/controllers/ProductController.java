package com.magicchestcore.controllers;

import com.magicchestcore.dto.ProductDTO;
import com.magicchestcore.models.Product;
import com.magicchestcore.servicies.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

// user, admin
    @GetMapping
    public List<ProductDTO> findAll() {
        return productService.findAll().stream().map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }


    // admin, user
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        Optional<Product> product = productService.findById(id);
        if(product.isPresent()){
            ProductDTO productDTO = convertToProductDTO(product.get());
            return ResponseEntity.ok(productDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    // admin
    @PostMapping("/admin")
    public void save(@RequestBody ProductDTO product){
       productService.save(convertToProduct(product));
    }

    // admin
    @PatchMapping("/admin/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody ProductDTO updateProduct) {
        productService.update(id, convertToProduct(updateProduct));
    }

    // admin
    @DeleteMapping("/admin/{id}")
    public void delete(@PathVariable("id") Integer id) {
        productService.delete(id);
    }

    public Product convertToProduct(ProductDTO productDTO){
        return modelMapper.map(productDTO, Product.class);
    }

    public ProductDTO convertToProductDTO(Product product){
        return modelMapper.map(product, ProductDTO.class);
    }
}
