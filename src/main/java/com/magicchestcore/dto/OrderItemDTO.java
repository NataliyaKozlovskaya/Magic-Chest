package com.magicchestcore.dto;

import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class OrderItemDTO {

    private Integer orderId;

    private ProductDTO productDTO;
    //private Integer productId;

    private Integer price;
    private Integer quantity;

}
