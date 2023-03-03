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

    @ManyToOne
    @JoinColumn(name = "dress_model_id")
    private DressModel dressModel;

    @ManyToOne
    @JoinColumn(name = "dress_size_id")
    private DressSize dressSize;
}

