package com.magicchestcore.models;

import lombok.*;
import org.hibernate.mapping.Bag;

import javax.persistence.*;
import java.util.ArrayList;
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

    @ManyToOne
    @JoinColumn(name="color_id")
    private Color color;

   @ManyToOne
   @JoinColumn(name="dress_model_id")
    private DressModel dressModel;

    @ManyToOne
    @JoinColumn(name="shoes_model_id")
    private ShoesModel shoesModel;

    @ManyToOne
    @JoinColumn(name="bag_model_id")
    private BagModel bagModel;

    @ManyToOne
    @JoinColumn(name="dress_size_id")
    private DressSize dressSize;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItemList=new ArrayList<>();
}
