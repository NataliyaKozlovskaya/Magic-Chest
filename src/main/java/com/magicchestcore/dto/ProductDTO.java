package com.magicchestcore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.magicchestcore.config.util.ProductType;
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
@JsonInclude(JsonInclude.Include.NON_NULL)//скрывает поля null
public class ProductDTO {

    private ProductType productType;
    private Integer id;
    private String name;
    private Integer quantity;
    private Double price;

    private ColorDTO color;
    private DressModelDTO dressModel;
    private DressSizeDTO dressSize;

    private ShoesModelDTO shoesModel;
    private ShoesSizeDTO shoesSize;

    private BagModelDTO bagModel;
    private BagSizeDTO bagSize;

    private List<OrderItemDTO> orderItemList = new ArrayList<>();



}
