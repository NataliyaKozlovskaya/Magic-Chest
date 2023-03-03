package com.magicchestcore.controllers;

import com.magicchestcore.dto.DressModelDTO;
import com.magicchestcore.dto.PersonDTO;
import com.magicchestcore.models.DressModel;
import com.magicchestcore.models.Person;
import com.magicchestcore.servicies.DressModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dressModel")
public class DressModelController {
    private final DressModelService dressModelService;
    private final ModelMapper modelMapper;
    @Autowired
    public DressModelController(DressModelService dressModelService, ModelMapper modelMapper) {
        this.dressModelService = dressModelService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List <DressModelDTO> findAll() {
        return dressModelService.findAll().stream().map(this::convertToDressModelDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        Optional<DressModel> dressModel = dressModelService.findById(id);
        if(dressModel.isPresent()){
            DressModelDTO dressModelDTO = convertToDressModelDTO(dressModel.get());
            return ResponseEntity.ok(dressModelDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    // admin
    @PostMapping("/admin")
    public void save(@RequestBody DressModelDTO dressModel){
       dressModelService.save(convertToDressModel(dressModel));
    }

    // admin
    @PatchMapping("/admin/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody DressModelDTO updateDressModel) {
        dressModelService.update(id, convertToDressModel(updateDressModel));
    }

    // admin
    @DeleteMapping("/admin/{id}")
    public void delete(@PathVariable("id") Integer id) {
        dressModelService.delete(id);
    }
    public DressModel convertToDressModel(DressModelDTO dressModelDTO){
        return modelMapper.map(dressModelDTO, DressModel.class);
    }

    public DressModelDTO convertToDressModelDTO(DressModel dressModel){
        return modelMapper.map(dressModel, DressModelDTO.class);
    }



}
