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
@Table(name = "shoes_size")
public class ShoesSize {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "size")
    private Integer size;

    @OneToMany(mappedBy = "shoesSize", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shoes> shoesList = new ArrayList<>();
}
