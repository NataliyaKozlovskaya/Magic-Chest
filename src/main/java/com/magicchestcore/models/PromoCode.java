package com.magicchestcore.models;

import com.magicchestcore.config.util.EnumRole;
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
@Table(name = "promo_code")
public class PromoCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="discount")
    private Integer discount;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;


}
