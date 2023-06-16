package com.magicchestcore.dto;

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
    private Integer personId;
    private Date date;
    private List<OrderItemDTO> orderItemList = new ArrayList<>();

}
