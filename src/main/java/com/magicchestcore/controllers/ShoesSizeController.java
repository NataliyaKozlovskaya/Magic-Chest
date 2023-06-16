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


    @GetMapping
    public List <ShoesSizeDTO> findAll() {
        return shoesSizeService.findAll().stream().map(converter::convertToShoesSizeDTO)
                .collect(Collectors.toList());
    }


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


    @PostMapping("/admin")
    public void save(@RequestBody ShoesSizeDTO shoesSizeDTO){
        shoesSizeService.save(converter.convertToShoesSize(shoesSizeDTO));
    }


    @PatchMapping("/admin")
    public void update(@RequestBody ShoesSizeDTO updatedShoesSizeDTO) {
        shoesSizeService.update(converter.convertToShoesSize(updatedShoesSizeDTO));
    }


    @DeleteMapping("/admin/{id}")
    public void delete(@PathVariable("id") Integer id) {
        shoesSizeService.delete(id);
    }

}
