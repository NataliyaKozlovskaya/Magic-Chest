package com.magicchestcore.models;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Shoes extends Product {

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "shoes_model_id")
    private ShoesModel shoesModel;

    @ManyToOne
    @JoinColumn(name = "shoes_size_id")
    private ShoesSize shoesSize;

}
