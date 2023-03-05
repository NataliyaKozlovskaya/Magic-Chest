package com.magicchestcore.controllers;

import com.magicchestcore.config.util.Converter;
import com.magicchestcore.dto.DressModelDTO;
import com.magicchestcore.models.DressModel;
import com.magicchestcore.servicies.DressModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dressModel")
public class DressModelController {
    private final DressModelService dressModelService;
    private final Converter converter;
    @Autowired
    public DressModelController(DressModelService dressModelService, Converter converter) {
        this.dressModelService = dressModelService;
        this.converter = converter;
    }

    @GetMapping
    public List <DressModelDTO> findAll() {
        return dressModelService.findAll().stream().map(converter::convertToDressModelDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        Optional<DressModel> dressModel = dressModelService.findById(id);
        if(dressModel.isPresent()){
            DressModelDTO dressModelDTO = converter.convertToDressModelDTO(dressModel.get());
            return ResponseEntity.ok(dressModelDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    // admin
    @PostMapping("/admin")
    public void save(@RequestBody DressModelDTO dressModelDTO){
       dressModelService.save(converter.convertToDressModel(dressModelDTO));
    }

    // admin
    @PatchMapping("/admin/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody DressModelDTO updateDressModelDTO) {
        dressModelService.update(id, converter.convertToDressModel(updateDressModelDTO));
    }

    // admin
    @DeleteMapping("/admin/{id}")
    public void delete(@PathVariable("id") Integer id) {
        dressModelService.delete(id);
    }


}
