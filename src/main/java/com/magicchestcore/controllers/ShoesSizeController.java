package com.magicchestcore.controllers;

import com.magicchestcore.config.util.Converter;
import com.magicchestcore.dto.ShoesSizeDTO;
import com.magicchestcore.models.ShoesSize;
import com.magicchestcore.servicies.ShoesSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shoesSize")
public class ShoesSizeController {
    private final ShoesSizeService shoesSizeService;
    private final Converter converter;
    @Autowired
    public ShoesSizeController(ShoesSizeService shoesSizeService, Converter converter) {
        this.shoesSizeService = shoesSizeService;
        this.converter = converter;
    }

// admin, user
    @GetMapping
    public List <ShoesSizeDTO> findAll() {
        return shoesSizeService.findAll().stream().map(converter::convertToShoesSizeDTO)
                .collect(Collectors.toList());
    }
// admin, user
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        Optional<ShoesSize> shoesSize = shoesSizeService.findById(id);
        if(shoesSize.isPresent()){
            ShoesSizeDTO shoesSizeDTO = converter.convertToShoesSizeDTO(shoesSize.get());
            return ResponseEntity.ok(shoesSizeDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    // admin
    @PostMapping("/admin")
    public void save(@RequestBody ShoesSizeDTO shoesSizeDTO){
        shoesSizeService.save(converter.convertToShoesSize(shoesSizeDTO));
    }

    // admin
    @PatchMapping("/admin/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody ShoesSizeDTO updateShoesSizeDTO) {
        shoesSizeService.update(id, converter.convertToShoesSize(updateShoesSizeDTO));
    }

    // admin
    @DeleteMapping("/admin/{id}")
    public void delete(@PathVariable("id") Integer id) {
        shoesSizeService.delete(id);
    }

}
