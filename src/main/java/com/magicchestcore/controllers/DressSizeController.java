package com.magicchestcore.controllers;

import com.magicchestcore.config.util.Converter;
import com.magicchestcore.dto.DressSizeDTO;
import com.magicchestcore.models.DressSize;
import com.magicchestcore.servicies.DressSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dressSize")
public class DressSizeController {
    private final DressSizeService dressSizeService;
    private final Converter converter;
    @Autowired
    public DressSizeController(DressSizeService dressSizeService, Converter converter) {
        this.dressSizeService = dressSizeService;
        this.converter = converter;
    }

// admin, user
    @GetMapping
    public List <DressSizeDTO> findAll() {
        return dressSizeService.findAll().stream().map(converter::convertToDressSizeDTO)
                .collect(Collectors.toList());
    }
// admin, user
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        Optional<DressSize> dressSize = dressSizeService.findById(id);
        if(dressSize.isPresent()){
            DressSizeDTO dressSizeDTO = converter.convertToDressSizeDTO(dressSize.get());
            return ResponseEntity.ok(dressSizeDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    // admin
    @PostMapping("/admin")
    public void save(@RequestBody DressSizeDTO dressSizeDTO){
        dressSizeService.save(converter.convertToDressSize(dressSizeDTO));
    }

    // admin
    @PatchMapping("/admin/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody DressSizeDTO updateDressSizeDTO) {
        dressSizeService.update(id, converter.convertToDressSize(updateDressSizeDTO));
    }

    // admin
    @DeleteMapping("/admin/{id}")
    public void delete(@PathVariable("id") Integer id) {
        dressSizeService.delete(id);
    }

}
