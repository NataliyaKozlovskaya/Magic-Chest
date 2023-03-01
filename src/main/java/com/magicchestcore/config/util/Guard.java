package com.magicchestcore.config.util;

import com.magicchestcore.security.PersonDetails;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class Guard {
    public boolean checkUserId(Authentication authentication, Integer id){
        PersonDetails principal = (PersonDetails) authentication.getPrincipal();
        return id.equals(principal.getPerson().getId());
    }

}
