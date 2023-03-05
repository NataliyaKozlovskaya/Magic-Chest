package com.magicchestcore.controllers;

import com.magicchestcore.config.util.Converter;
import com.magicchestcore.dto.DressDTO;
import com.magicchestcore.models.Dress;
import com.magicchestcore.servicies.DressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//TODO нужен ли контроллер

@RestController
@RequestMapping("/dress")
public class DressController {
    private final DressService dressService;
    private final Converter converter;

    @Autowired
    public DressController(DressService dressService, Converter converter) {
        this.dressService = dressService;
        this.converter = converter;
    }

    // admin, user
    @GetMapping
    public List<DressDTO> findAll() {
        return dressService.findAll().stream().map(converter::convertToDressDTO).collect(Collectors.toList());
    }

    // admin, user
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        Optional<Dress> dress = dressService.findById(id);
        if(dress.isPresent()){
            DressDTO dressDTO = converter.convertToDressDTO(dress.get());
            return ResponseEntity.ok(dressDTO);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // admin
    @PostMapping
    public void save(@RequestBody DressDTO dressDTO){
       dressService.save(converter.convertToDress(dressDTO));
    }

    // admin
    @PatchMapping("/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody DressDTO updateDressDTO) {
        dressService.update(id, converter.convertToDress(updateDressDTO));
    }

    // admin
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        dressService.delete(id);
    }





}
