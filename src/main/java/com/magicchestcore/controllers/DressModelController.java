package com.magicchestcore.controllers;

import com.magicchestcore.models.Dress;
import com.magicchestcore.models.DressModel;
import com.magicchestcore.servicies.DressModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dressModel")
public class DressModelController {
    private final DressModelService dressModelService;

    @Autowired
    public DressModelController(DressModelService dressModelService) {
        this.dressModelService = dressModelService;
    }

    @GetMapping
    public List <DressModel> findAll() {
        return dressModelService.findAll();
    }

    @GetMapping("/{id}")
    public Optional <DressModel> findById(@PathVariable("id") Integer id) {
        return dressModelService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody DressModel dressModel){
       dressModelService.save(dressModel);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody DressModel updateDressModel) {
        dressModelService.update(id, updateDressModel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        dressModelService.delete(id);
    }

}
