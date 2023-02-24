package com.magicchestcore.models;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Bag extends Product {

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})//?????  all - не работает
    @JoinColumn(name = "bag_model_id")
    private BagModel bagModel;


    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "bag_size_id")
    private BagSize size;

}
