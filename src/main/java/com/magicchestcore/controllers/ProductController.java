package com.magicchestcore.controllers;

import com.magicchestcore.config.util.Converter;
import com.magicchestcore.config.util.ProductType;
import com.magicchestcore.dto.ProductDTO;
import com.magicchestcore.models.Product;
import com.magicchestcore.servicies.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final Converter converter;
    public ProductController(ProductService productService, Converter converter) {
        this.productService = productService;
        this.converter = converter;
    }

// user, admin
    @GetMapping
    public List<ProductDTO> findAll(@RequestParam(value = "dtype", required = false) String dtype) {
        if(dtype==null){
            return productService.findAll().stream().map(converter::convertToProductDTO).collect(Collectors.toList());
        }else{
          return productService.findAllByDtype(dtype).stream().map(converter::convertToProductDTO).collect(Collectors.toList());
        }
    }


    // admin, user
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        Optional<Product> product = productService.findById(id);
        if(product.isPresent()){
            ProductDTO productDTO = converter.convertToProductDTO(product.get());
            return ResponseEntity.ok(productDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    // admin
    @PostMapping("/admin")
    public void save(@RequestBody ProductDTO productDTO) {
        productService.save(converter.convertToProductType(productDTO));
    }

    // admin
    @PatchMapping("/admin/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody ProductDTO updateProductDTO) {
        productService.update(id, converter.convertToProduct(updateProductDTO));
    }

    // admin
    @DeleteMapping("/admin/{id}")
    public void delete(@PathVariable("id") Integer id) {
        productService.delete(id);
    }


}
