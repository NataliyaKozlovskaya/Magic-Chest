package com.magicchestcore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class PromoCodeDTO {

    private Integer id;
    private String name;
    private Integer discount;
    private Integer personId;
}
