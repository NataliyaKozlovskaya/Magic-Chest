package com.magicchestcore.dto;

import com.magicchestcore.models.OrderItem;
import com.magicchestcore.models.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OrderDTO {

    private Integer price;
    private Date date;
    private PersonDTO person;
    private List<OrderItem> orderItemList = new ArrayList<>();
}
