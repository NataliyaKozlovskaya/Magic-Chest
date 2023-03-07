package com.magicchestcore.models;

import com.magicchestcore.config.util.ProductType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="DTYPE", insertable = false, updatable = false)
    private String dtype;

    @Column(name="name")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;

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

    @ManyToOne
    @JoinColumn(name="shoes_size_id")
    private ShoesSize shoesSize;

    @ManyToOne
    @JoinColumn(name="bag_size_id")
    private BagSize bagSize;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItemList = new ArrayList<>();
}
