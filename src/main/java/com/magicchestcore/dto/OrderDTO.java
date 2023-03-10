package com.magicchestcore.dto;

import com.magicchestcore.models.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Integer id;
    private Integer price;
   // private Date date;

    private Integer personId;
    //private PersonDTO person;

    private List<OrderItemDTO> orderItemList = new ArrayList<>();

}
