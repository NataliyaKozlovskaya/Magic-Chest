package com.magicchestcore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DressDTO {

    private String name;
    private DressModelDTO dressModel;
    private DressSizeDTO dressSize;

}
