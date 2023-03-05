package com.magicchestcore.controllers;

import com.magicchestcore.config.util.Converter;
import com.magicchestcore.dto.ShoesModelDTO;
import com.magicchestcore.models.ShoesModel;
import com.magicchestcore.servicies.ShoesModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shoesModel")
public class ShoesModelController {
    private final ShoesModelService shoesModelService;
    private final Converter converter;
    @Autowired
    public ShoesModelController(ShoesModelService shoesModelService, Converter converter) {
        this.shoesModelService = shoesModelService;
        this.converter = converter;
    }

    @GetMapping
    public List <ShoesModelDTO> findAll() {
        return shoesModelService.findAll().stream().map(converter::convertToShoesModelDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        Optional<ShoesModel> shoesModel = shoesModelService.findById(id);
        if(shoesModel.isPresent()){
            ShoesModelDTO shoesModelDTO = converter.convertToShoesModelDTO(shoesModel.get());
            return ResponseEntity.ok(shoesModelDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    // admin
    @PostMapping("/admin")
    public void save(@RequestBody ShoesModelDTO shoesModelDTO){
        shoesModelService.save(converter.convertToShoesModel(shoesModelDTO));
    }

    // admin
    @PatchMapping("/admin/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody ShoesModelDTO updateShoesModelDTO) {
        shoesModelService.update(id, converter.convertToShoesModel(updateShoesModelDTO));
    }

    // admin
    @DeleteMapping("/admin/{id}")
    public void delete(@PathVariable("id") Integer id) {
        shoesModelService.delete(id);
    }


}
