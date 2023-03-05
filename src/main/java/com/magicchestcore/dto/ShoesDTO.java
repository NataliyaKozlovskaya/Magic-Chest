package com.magicchestcore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShoesDTO {

    private String name;
    private ShoesModelDTO shoesModelDTO;
    private ShoesSizeDTO shoesSizeDTO;

}
