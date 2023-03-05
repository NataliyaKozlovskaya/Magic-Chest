package com.magicchestcore.controllers;

import com.magicchestcore.config.util.Converter;
import com.magicchestcore.dto.BagDTO;
import com.magicchestcore.models.Bag;
import com.magicchestcore.servicies.BagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//TODO нужен ли контроллер

@RestController
@RequestMapping("/bag")
public class BagController {
    private final BagService bagService;
    private final Converter converter;

    @Autowired
    public BagController(BagService bagService, Converter converter) {
        this.bagService = bagService;
        this.converter = converter;
    }

    // admin, user
    @GetMapping
    public List<BagDTO> findAll() {
        return bagService.findAll().stream().map(converter::convertToBagDTO).collect(Collectors.toList());
    }

    // admin, user
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        Optional<Bag> bag = bagService.findById(id);
        if(bag.isPresent()){
            BagDTO bagDTO = converter.convertToBagDTO(bag.get());
            return ResponseEntity.ok(bagDTO);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // admin
    @PostMapping
    public void save(@RequestBody BagDTO bagDTO){
        bagService.save(converter.convertToBag(bagDTO));
    }

    // admin
    @PatchMapping("/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody BagDTO updateBagDTO) {
        bagService.update(id, converter.convertToBag(updateBagDTO));
    }

    // admin
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        bagService.delete(id);
    }

}
