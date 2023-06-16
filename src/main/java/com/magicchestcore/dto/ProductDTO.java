package com.magicchestcore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.magicchestcore.config.util.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)//скрывает поля null
public class ProductDTO {

    private ProductType productType;
    private Integer id;
    private String name;
    private Integer quantity;
    private Integer price;

}
