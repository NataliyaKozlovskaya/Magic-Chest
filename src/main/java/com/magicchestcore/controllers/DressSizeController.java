package com.magicchestcore.controllers;

import com.magicchestcore.dto.DressSizeDTO;
import com.magicchestcore.models.DressSize;
import com.magicchestcore.servicies.DressSizeService;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;
    @Autowired
    public DressSizeController(DressSizeService dressSizeService, ModelMapper modelMapper) {
        this.dressSizeService = dressSizeService;
        this.modelMapper = modelMapper;
    }
// admin, user
    @GetMapping
    public List <DressSizeDTO> findAll() {
        return dressSizeService.findAll().stream().map(this::convertToDressSizeDTO)
                .collect(Collectors.toList());
    }
// admin, user
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        Optional<DressSize> dressSize = dressSizeService.findById(id);
        if(dressSize.isPresent()){
            DressSizeDTO dressSizeDTO = convertToDressSizeDTO(dressSize.get());
            return ResponseEntity.ok(dressSizeDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    // admin
    @PostMapping("/admin")
    public void save(@RequestBody DressSize dressSize){
        dressSizeService.save(dressSize);
    }

    // admin
    @PatchMapping("/admin/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody DressSizeDTO updateDressSize) {
        dressSizeService.update(id, convertToDressSize(updateDressSize));
    }

    // admin
    @DeleteMapping("/admin/{id}")
    public void delete(@PathVariable("id") Integer id) {
        dressSizeService.delete(id);
    }

    public DressSize convertToDressSize(DressSizeDTO dressSizeDTO){
        return modelMapper.map(dressSizeDTO,DressSize.class);
    }

    public DressSizeDTO convertToDressSizeDTO(DressSize dressSize){
        return modelMapper.map(dressSize, DressSizeDTO.class);
    }


}
