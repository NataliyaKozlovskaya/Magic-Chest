package com.magicchestcore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class PersonAuthDTO {

    private String username;
    private String password;
    private String address;


}
