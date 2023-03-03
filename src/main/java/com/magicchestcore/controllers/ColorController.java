package com.magicchestcore.controllers;

import com.magicchestcore.dto.ColorDTO;
import com.magicchestcore.dto.DressSizeDTO;
import com.magicchestcore.models.Color;
import com.magicchestcore.models.DressSize;
import com.magicchestcore.servicies.ColorService;
import com.magicchestcore.servicies.DressSizeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/color")
public class ColorController {
    private final ColorService colorService;
    private final ModelMapper modelMapper;
    @Autowired
    public ColorController(ColorService colorService, ModelMapper modelMapper) {
        this.colorService = colorService;
        this.modelMapper = modelMapper;
    }
// admin, user
    @GetMapping
    public List <ColorDTO> findAll() {
        return colorService.findAll().stream().map(this::convertToColorDTO)
                .collect(Collectors.toList());
    }
// admin, user
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        Optional<Color> color = colorService.findById(id);
        if(color.isPresent()){
            ColorDTO colorDTO = convertToColorDTO(color.get());
            return ResponseEntity.ok(colorDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    // admin
    @PostMapping("/admin")
    public void save(@RequestBody Color color){
        colorService.save(color);
    }

    // admin
    @PatchMapping("/admin/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody ColorDTO updateColor) {
        colorService.update(id, convertToColor(updateColor));
    }

    // admin
    @DeleteMapping("/admin/{id}")
    public void delete(@PathVariable("id") Integer id) {
        colorService.delete(id);
    }

    public Color convertToColor(ColorDTO colorDTO){
        return modelMapper.map(colorDTO, Color.class);
    }

    public ColorDTO convertToColorDTO(Color color){
        return modelMapper.map(color, ColorDTO.class);
    }


}
