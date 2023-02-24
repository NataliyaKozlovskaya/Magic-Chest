package com.magicchestcore.models;

import lombok.*;
import org.hibernate.mapping.Bag;

import javax.persistence.*;
import java.util.List;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    private Integer quantity;

    @OneToMany
    @JoinColumn(name="color_id")
    private List<Color> colors;

    @OneToMany
    @JoinColumn(name="dress_model_id")
    private List<DressModel> dressModelList;

    @OneToMany
    @JoinColumn(name="shoes_model_id")
    private List<ShoesModel> shoesModelList;

    @OneToMany
    @JoinColumn(name="bag_model_id")
    private List<BagModel> bagModelList;

    @OneToMany
    @JoinColumn(name="dress_size_id")
    private List<DressSize> dressSizeList;


}
