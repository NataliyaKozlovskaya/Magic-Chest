package com.magicchestcore.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "dress_model")
public class DressModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "model")
    private String model;

    @OneToMany(mappedBy = "dressModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dress> dresses = new ArrayList<>();
}
