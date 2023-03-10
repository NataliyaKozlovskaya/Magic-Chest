package com.magicchestcore.controllers;

import com.magicchestcore.config.util.Converter;
import com.magicchestcore.dto.ColorDTO;
import com.magicchestcore.models.Color;
import com.magicchestcore.servicies.ColorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/color")
public class ColorController {
    private final ColorService colorService;
    private final Converter converter;
    @Autowired
    public ColorController(ColorService colorService, Converter converter) {
        this.colorService = colorService;
        this.converter = converter;
    }

// admin, user
    @GetMapping("/color")
    public List <ColorDTO> findAll() {
        return colorService.findAll().stream().map(converter::convertToColorDTO)
                .collect(Collectors.toList());
    }
// admin, user
    @GetMapping("/color/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        Optional<Color> color = colorService.findById(id);
        if(color.isPresent()){
            ColorDTO colorDTO = converter.convertToColorDTO(color.get());
            return ResponseEntity.ok(colorDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    // admin
    @PostMapping("/admin/color")
    public void save(@RequestBody ColorDTO colorDTO){
        colorService.save(converter.convertToColor(colorDTO));
    }

    // admin
    @PatchMapping("/admin/color/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody ColorDTO updateColorDTO) {
        colorService.update(id, converter.convertToColor(updateColorDTO));
    }

    // admin
    @DeleteMapping("/admin/color/{id}")
    public void delete(@PathVariable("id") Integer id) {
        colorService.delete(id);
    }

}
