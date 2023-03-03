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
@Entity
@Table(name = "bag_model")
public class BagModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "model")
    private String model;

    @OneToMany(mappedBy = "bagModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bag> bagList = new ArrayList<>();
}
