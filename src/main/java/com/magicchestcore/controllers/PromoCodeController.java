package com.magicchestcore.controllers;

import com.magicchestcore.config.util.Converter;
import com.magicchestcore.dto.PromoCodeDTO;
import com.magicchestcore.models.PromoCode;
import com.magicchestcore.servicies.PromoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
//@RequestMapping
public class PromoCodeController {

   private final PromoCodeService promoCodeService;
    private final Converter converter;
    @Autowired
    public PromoCodeController(PromoCodeService promoCodeService, Converter converter) {
        this.promoCodeService = promoCodeService;
        this.converter = converter;
    }

    @GetMapping("/admin/promoCode")
    public ResponseEntity<List<PromoCodeDTO>> findAll() {
        List<PromoCode> promoCodeList = promoCodeService.findAll();
        List<PromoCodeDTO> collect = promoCodeList.stream().map(converter::convertToPromoCodeDTO2).collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }

    @GetMapping("/admin/promoCode/{personId}")
    public ResponseEntity<List<PromoCodeDTO>> findAllByPersonId(@PathVariable("personId") Integer personId){
        List<PromoCode> promoCodeList = promoCodeService.findAllByPersonId(personId);
        List<PromoCodeDTO> collect = promoCodeList.stream().map(converter::convertToPromoCodeDTO).collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }

    @GetMapping("/admin/promoCodeId/{id}")
    public ResponseEntity<PromoCodeDTO> findById(@PathVariable("id") Integer id){
        Optional<PromoCode> promoCode = promoCodeService.findById(id);
        if(promoCode.isPresent()){
            PromoCodeDTO promoCodeDTO = converter.convertToPromoCodeDTO(promoCode.get());
            return ResponseEntity.ok(promoCodeDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("admin/promoCode/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody PromoCodeDTO updatePromoCodeDTO){
        promoCodeService.update(id, converter.convertToPromoCode(updatePromoCodeDTO));
    }

    @PostMapping("/admin/promoCode")
    public void create(@RequestBody PromoCodeDTO promoCodeDTO){
        promoCodeService.save(converter.convertToPromoCode(promoCodeDTO));
    }

    @DeleteMapping("/admin/promoCode/{id}")
    public void delete(@PathVariable("id") Integer id){
        promoCodeService.delete(id);
    }
}

