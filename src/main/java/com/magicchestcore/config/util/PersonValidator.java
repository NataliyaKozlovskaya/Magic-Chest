package com.magicchestcore.config.util;


import com.magicchestcore.dto.PersonAuthDTO;
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
        return PersonAuthDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        PersonAuthDTO personAuthDTO = (PersonAuthDTO) target;
        try{
            personDetailsService.loadUserByUsername(personAuthDTO.getUsername());
        }catch (UsernameNotFoundException ignored){
            return;
        }
        errors.reject("", "Человек с таким именем уже существует");
//        errors.rejectValue("username", "", "Человек с таким именем уже существует");
    }
}
