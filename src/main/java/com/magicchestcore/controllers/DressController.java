package com.magicchestcore.controllers;

import com.magicchestcore.dto.DressDTO;
import com.magicchestcore.models.Dress;
import com.magicchestcore.servicies.DressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//TODO нужен ли контроллер

@RestController
@RequestMapping("/dress")
public class DressController {
    private final DressService dressService;
    private final ModelMapper modelMapper;

    @Autowired
    public DressController(DressService dressService, ModelMapper modelMapper) {
        this.dressService = dressService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<Dress> findAll() {
        return dressService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Dress> findById(@PathVariable("id") Integer id) {
        return dressService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody Dress dress){
       dressService.save(dress);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody Dress updateDress) {
        dressService.update(id, updateDress);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        dressService.delete(id);
    }



//    public Dress convertToDress(DressDTO dressDTO){
//        return modelMapper.map(dressDTO, Dress.class);
//    }
//
//    public DressDTO convertToDressDTO(Dress dress){
//        return modelMapper.map(dress, DressDTO.class);
//    }

}
