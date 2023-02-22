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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @OneToMany
    private List<Product> products;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="sum")
    private Double  sum;

}
