package com.magicchestcore.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {


    @Id
    @ManyToOne
    private Order order;

    @Id
    @ManyToOne
    private Product product;
    private Integer quantity;
    private Integer price;



}
