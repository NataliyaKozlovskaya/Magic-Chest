package com.magicchestcore.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dress")
public class Dress {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @OneToOne
    @JoinColumn(name = "dress_model_id")
    private DressModel dressModel;

    @Column(name = "quantity")
    private Integer quantity;
}
//    @Column(name = "dress_color")
//    @OneToOne(mappedBy = "dress")
//    private DressColor color;
//
//    @Column(name = "dress_size")
//    @OneToOne
//    private DressSize size;

//
//
//    @Column(name="price")
//    private Integer price;


