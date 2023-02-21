package com.magicchestcore.models;

import lombok.*;
import org.hibernate.mapping.Bag;

import javax.persistence.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    //@OneToOne----???????
//    @Column(name="shoes")
//    private Shoes shoes;
//
//    @Column(name="bag")
//    private Bag bag;
}
