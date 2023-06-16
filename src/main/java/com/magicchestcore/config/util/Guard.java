package com.magicchestcore.config.util;

import com.magicchestcore.models.Person;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class Guard {
    public boolean checkUserId(Authentication authentication, Integer id){
        Person principal = (Person) authentication.getPrincipal();
        return id.equals(principal.getId());
    }

}
