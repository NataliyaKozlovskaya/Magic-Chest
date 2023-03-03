package com.magicchestcore.models;
import lombok.*;
import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "bag")
public class Bag extends Product {

    @ManyToOne
    @JoinColumn(name = "bag_model_id")
    private BagModel bagModel;


    @ManyToOne
    @JoinColumn(name = "bag_size_id")
    private BagSize bagSize;

}
