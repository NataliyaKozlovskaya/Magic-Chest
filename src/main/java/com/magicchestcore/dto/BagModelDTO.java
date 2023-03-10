package com.magicchestcore.dto;

import com.magicchestcore.models.Bag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BagModelDTO {

    private Integer id;
    private String model;

    //private List<BagDTO> bagList = new ArrayList<>();
}
