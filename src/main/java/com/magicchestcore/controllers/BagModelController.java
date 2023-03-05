package com.magicchestcore.controllers;

import com.magicchestcore.config.util.Converter;
import com.magicchestcore.dto.BagModelDTO;
import com.magicchestcore.models.BagModel;
import com.magicchestcore.servicies.BagModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bagModel")
public class BagModelController {
    private final BagModelService bagModelService;
    private final Converter converter;
    @Autowired
    public BagModelController(BagModelService bagModelService, Converter converter) {
        this.bagModelService = bagModelService;
        this.converter = converter;
    }

    @GetMapping
    public List <BagModelDTO> findAll() {
        return bagModelService.findAll().stream().map(converter::convertToBagModelDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        Optional<BagModel> bagModel = bagModelService.findById(id);
        if(bagModel.isPresent()){
            BagModelDTO bagModelDTO = converter.convertToBagModelDTO(bagModel.get());
            return ResponseEntity.ok(bagModelDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    // admin
    @PostMapping("/admin")
    public void save(@RequestBody BagModelDTO bagModelDTO){
        bagModelService.save(converter.convertToBagModel(bagModelDTO));
    }

    // admin
    @PatchMapping("/admin/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody BagModelDTO updateBagModelDTO) {
        bagModelService.update(id, converter.convertToBagModel(updateBagModelDTO));
    }

    // admin
    @DeleteMapping("/admin/{id}")
    public void delete(@PathVariable("id") Integer id) {
        bagModelService.delete(id);
    }


}
