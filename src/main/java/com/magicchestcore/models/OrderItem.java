package com.magicchestcore.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {
//    @Id//не будет
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="id")
//    private Integer id;


    @Id
    @ManyToOne//
    @JoinColumn(name="order_id")
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    private Integer price;
    private Integer quantity;

}
