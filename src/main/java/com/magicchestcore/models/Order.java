package com.magicchestcore.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="price")
    private Integer price;

    @Column(name="date")
    private Date date;



    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<OrderItem> orderItemList;
}