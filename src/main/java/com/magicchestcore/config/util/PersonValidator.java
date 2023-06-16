package com.magicchestcore.config.util;

import com.magicchestcore.dto.PersonDTO;
import com.magicchestcore.servicies.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonDetailsService personDetailsService;

    @Autowired
    public PersonValidator(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PersonDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PersonDTO personDTO = (PersonDTO) target;
        try{
            personDetailsService.loadUserByUsername(personDTO.getUsername());
        }catch (UsernameNotFoundException ignored){
            return;
        }
        errors.reject("", "A user with the same name already exists");
    }
}
