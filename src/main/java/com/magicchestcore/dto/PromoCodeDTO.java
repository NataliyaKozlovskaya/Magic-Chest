package com.magicchestcore.dto;

import com.magicchestcore.models.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class PromoCodeDTO {

    private Integer id;
    private String name;
    private Integer discount;

//    private PersonDTO person;

    private Integer personId;
}
