package com.magicchestcore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BagDTO {

    private String name;
    private BagModelDTO bagModelDTO;
    private BagSizeDTO bagSizeDTO;

}
