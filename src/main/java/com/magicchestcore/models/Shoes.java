package com.magicchestcore.models;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Shoes extends Product {

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})//?????  all - не работает
    @JoinColumn(name = "shoes_model_id")
    private ShoesModel shoesModel;


    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "shoes_size_id")
    private ShoesSize size;

}
