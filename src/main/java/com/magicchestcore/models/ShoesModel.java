package com.magicchestcore.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shoes_model")
@Entity
public class ShoesModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "model")
    private String model;

    @OneToMany(mappedBy = "shoesModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shoes> shoesList = new ArrayList<>();

}
