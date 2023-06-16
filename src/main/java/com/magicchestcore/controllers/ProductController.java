package com.magicchestcore.controllers;

import com.magicchestcore.config.util.Converter;

import com.magicchestcore.dto.ProductDTO;
import com.magicchestcore.models.Product;
import com.magicchestcore.servicies.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    public ProductController(ProductService productService, Converter converter) {
        this.productService = productService;
        this.converter = converter;
    }

    @GetMapping
    public List<ProductDTO> findAll(@RequestParam(value = "dtype", required = false) String dtype) {
        if(dtype==null){
            return productService.findAll().stream().map(converter::convertToProductDTO).collect(Collectors.toList());
        }else{
          return productService.findAllByDtype(dtype).stream().map(converter::convertToProductDTO).collect(Collectors.toList());
        }
    }

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

    @PostMapping("/admin")
    public void save(@RequestBody ProductDTO productDTO) {
        productService.save(converter.convertToProductType(productDTO));
    }


    @PatchMapping("/admin")
    public void update(@RequestBody ProductDTO updateProductDTO) {
        productService.update(converter.convertToProduct(updateProductDTO));
    }


    @DeleteMapping("/admin/{id}")
    public void delete(@PathVariable("id") Integer id) {
        productService.delete(id);
    }

}
