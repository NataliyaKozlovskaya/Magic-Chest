package com.magicchestcore.controllers;

import com.magicchestcore.config.util.PersonValidator;
import com.magicchestcore.dto.PersonDTO;
import com.magicchestcore.models.Person;
import com.magicchestcore.security.PersonDetails;
import com.magicchestcore.servicies.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final ModelMapper modelMapper;
    //private final G

    @Autowired
    public PersonController(PersonService personService,
                            PersonValidator personValidator, ModelMapper modelMapper) {
        this.personService = personService;
        this.personValidator = personValidator;
        this.modelMapper = modelMapper;
    }

// for admin
    @GetMapping("/admin")
    public List<PersonDTO> findAll() {
        return personService.findAll().stream().map(this::convertToPersonDTO)
                .collect(Collectors.toList());
    }

    // for user

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        PersonDetails principal = (PersonDetails) authentication.getPrincipal();
//        //personService.c
//        Integer id1 = principal.getPerson().getId();
//        if(!id1.equals(id)){
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
        Optional<Person> person = personService.findById(id);
        if(person.isPresent()){
            PersonDTO personDTO = convertToPersonDTO(person.get());
            return ResponseEntity.ok(personDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }


    //for admin
    @GetMapping("/admin/{id}")
    public ResponseEntity findPersonById(@PathVariable("id") Integer id){
        Optional<Person> person = personService.findPersonById(id);
        if(person.isPresent()){
            PersonDTO personDTO = convertToPersonDTO(person.get());
            return ResponseEntity.ok(personDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

// for user
    @PostMapping("/registration")
    public String create(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult){
        personValidator.validate(personDTO, bindingResult);
        if(bindingResult.hasErrors()){
            return bindingResult.toString();
        }
        personService.register(convertToPerson(personDTO));
        return "Регистрация прошла успешно";
    }


    // for user
    @PatchMapping("{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody PersonDTO updatePersonDTO){
        personService.update(id, convertToPerson(updatePersonDTO));
    }


    // Надо ли это оставлять  ?
    @GetMapping("/showPersonInfo")
    public Person showPersonInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails principal = (PersonDetails) authentication.getPrincipal();
        System.out.println(principal.getPerson());
        return (principal.getPerson());
    }

    public Person convertToPerson(PersonDTO personDTO){
        return modelMapper.map(personDTO, Person.class);
    }

    public PersonDTO convertToPersonDTO(Person person){
        return modelMapper.map(person, PersonDTO.class);
    }

}

