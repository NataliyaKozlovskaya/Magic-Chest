package com.magicchestcore.controllers;

import com.magicchestcore.config.util.Converter;
import com.magicchestcore.config.util.PersonValidator;
import com.magicchestcore.dto.PersonAuthDTO;
import com.magicchestcore.dto.PersonDTO;
import com.magicchestcore.models.Person;
import com.magicchestcore.security.PersonDetails;
import com.magicchestcore.servicies.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;
    private final PersonValidator personValidator;
    private final Converter converter;

    @Autowired
    public PersonController(PersonService personService, PersonValidator personValidator, Converter converter) {
        this.personService = personService;
        this.personValidator = personValidator;
        this.converter = converter;
    }

// for admin
    @GetMapping
    public List<PersonDTO> findAll() {
        return personService.findAll().stream().map(converter::convertToPersonDTO)
                .collect(Collectors.toList());
    }

    // for user

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id){
        Optional<Person> person = personService.findById(id);
        if(person.isPresent()){
            PersonDTO personDTO = converter.convertToPersonDTO(person.get());
            return ResponseEntity.ok(personDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

// заменить этот код на один метод - ?
    //for admin
    @GetMapping("/admin/{id}")
    public ResponseEntity findByIdAdmin(@PathVariable("id") Integer id){
        Optional<Person> person = personService.findById(id);
        if(person.isPresent()){
            PersonDTO personDTO = converter.convertToPersonDTO(person.get());
            return ResponseEntity.ok(personDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

// for user
    @PostMapping("/registration")
    public String create(@RequestBody @Valid PersonAuthDTO personAuthDTO, BindingResult bindingResult){
        personValidator.validate(personAuthDTO, bindingResult);
        if(bindingResult.hasErrors()){
            return bindingResult.toString();
        }
        personService.register(converter.convertToPerson(personAuthDTO));
        return "Регистрация прошла успешно";
    }


    // for user
    @PatchMapping("{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody PersonAuthDTO personAuthDTO){
        personService.update(id, converter.convertToPerson(personAuthDTO));
    }


    // Надо ли это оставлять  ?
    @GetMapping("/showPersonInfo")
    public Person showPersonInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails principal = (PersonDetails) authentication.getPrincipal();
        System.out.println(principal.getPerson());
        return (principal.getPerson());
    }


}

