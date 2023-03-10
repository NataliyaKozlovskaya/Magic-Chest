package com.magicchestcore.models;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Dress extends Product {

//    @Column(name="name")
//    private String name;

    @ManyToOne
    @JoinColumn(name = "dress_model_id")
    private DressModel dressModel;

    @ManyToOne
    @JoinColumn(name = "dress_size_id")
    private DressSize dressSize;
}

