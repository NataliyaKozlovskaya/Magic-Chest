package com.magicchestcore.controllers;

import com.magicchestcore.config.util.Converter;
import com.magicchestcore.dto.ShoesDTO;
import com.magicchestcore.models.Shoes;
import com.magicchestcore.servicies.ShoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//TODO нужен ли контроллер

@RestController
@RequestMapping("/shoes")
public class ShoesController {
    private final ShoesService shoesService;
    private final Converter converter;

    @Autowired
    public ShoesController(ShoesService shoesService, Converter converter) {
        this.shoesService = shoesService;
        this.converter = converter;
    }

    // admin, user
    @GetMapping
    public List<ShoesDTO> findAll() {
        return shoesService.findAll().stream().map(converter::convertToShoesDTO).collect(Collectors.toList());
    }

    // admin, user
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        Optional<Shoes> shoes = shoesService.findById(id);
        if(shoes.isPresent()){
            ShoesDTO shoesDTO = converter.convertToShoesDTO(shoes.get());
            return ResponseEntity.ok(shoesDTO);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // admin
    @PostMapping
    public void save(@RequestBody ShoesDTO shoesDTO){
        shoesService.save(converter.convertToShoes(shoesDTO));
    }

    // admin
    @PatchMapping("/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody ShoesDTO updateShoesDTO) {
        shoesService.update(id, converter.convertToShoes(updateShoesDTO));
    }

    // admin
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        shoesService.delete(id);
    }





}
