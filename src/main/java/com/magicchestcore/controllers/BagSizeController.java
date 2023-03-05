package com.magicchestcore.controllers;

import com.magicchestcore.config.util.Converter;
import com.magicchestcore.dto.BagSizeDTO;
import com.magicchestcore.models.BagSize;
import com.magicchestcore.servicies.BagSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bagSize")
public class BagSizeController {
    private final BagSizeService bagSizeService;
    private final Converter converter;
    @Autowired
    public BagSizeController(BagSizeService bagSizeService, Converter converter) {
        this.bagSizeService = bagSizeService;
        this.converter = converter;
    }

// admin, user
    @GetMapping
    public List <BagSizeDTO> findAll() {
        return bagSizeService.findAll().stream().map(converter::convertToBagSizeDTO)
                .collect(Collectors.toList());
    }
// admin, user
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        Optional<BagSize> bagSize = bagSizeService.findById(id);
        if(bagSize.isPresent()){
            BagSizeDTO bagSizeDTO = converter.convertToBagSizeDTO(bagSize.get());
            return ResponseEntity.ok(bagSizeDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    // admin
    @PostMapping("/admin")
    public void save(@RequestBody BagSizeDTO bagSizeDTO){
        bagSizeService.save(converter.convertToBagSize(bagSizeDTO));
    }

    // admin
    @PatchMapping("/admin/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody BagSizeDTO updateBagSizeDTO) {
        bagSizeService.update(id, converter.convertToBagSize(updateBagSizeDTO));
    }

    // admin
    @DeleteMapping("/admin/{id}")
    public void delete(@PathVariable("id") Integer id) {
        bagSizeService.delete(id);
    }

}
