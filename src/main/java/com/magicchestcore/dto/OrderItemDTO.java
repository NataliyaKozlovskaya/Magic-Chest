package com.magicchestcore.dto;

import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class OrderItemDTO {

    //private OrderDTO orderDTO;
    //private ProductDTO product;
    private Integer price;
    private Integer quantity;

}
