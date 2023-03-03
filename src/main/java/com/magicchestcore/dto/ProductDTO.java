package com.magicchestcore.dto;

import com.magicchestcore.models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Integer id;
    private String name;
    //private Integer quantity;
    private Double price;

    private ColorDTO color;
    private DressModelDTO dressModel;
    private DressSizeDTO dressSize;

    //private ShoesModelDTO shoesModel;
   // private BagModelDTO bagModel;
    //private ShoesSize shoesSize;
    //private BagSize bagSize;
    private List<OrderItemDTO> orderItemList=new ArrayList<>();



}
