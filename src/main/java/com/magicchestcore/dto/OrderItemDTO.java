package com.magicchestcore.dto;

import com.magicchestcore.models.Order;
import com.magicchestcore.models.Product;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor


public class OrderItemDTO {

    //private OrderDTO orderDTO;
    private ProductDTO product;
    private Integer price;
    private Integer quantity;

}
