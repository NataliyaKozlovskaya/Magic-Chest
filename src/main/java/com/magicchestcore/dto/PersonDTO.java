package com.magicchestcore.dto;

import com.magicchestcore.config.util.EnumRole;
import com.magicchestcore.models.Order;
import com.magicchestcore.models.PromoCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor

public class PersonDTO {

    private Integer id;
    //private EnumRole role;
    private String username;
    //private String password;

    private List<PromoCodeDTO> promoCodes = new ArrayList<>();


}
