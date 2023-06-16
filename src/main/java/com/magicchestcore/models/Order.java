package com.magicchestcore.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(exclude = {"orderItemList"})
@ToString(exclude = {"orderItemList"})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="price")
    private Integer price;

    @Column(name="date")
    private Date date;

    @ManyToOne
    @JoinColumn(name="person_id")
    private Person person;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true)
    private List<OrderItem> orderItemList = new ArrayList<>();


}