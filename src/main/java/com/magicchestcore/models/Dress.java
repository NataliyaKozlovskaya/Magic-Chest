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

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})//?????  all - не работает
    @JoinColumn(name = "dress_model_id")
    private DressModel dressModel;


    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "dress_size_id")
    private DressSize size;
}

