package com.magicchestcore.controllers;

import com.magicchestcore.config.util.PersonValidator;
import com.magicchestcore.dto.PersonDTO;
import com.magicchestcore.models.Person;
import com.magicchestcore.security.PersonDetails;
import com.magicchestcore.servicies.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public PersonController(PersonService personService,
                            PersonValidator personValidator, ModelMapper modelMapper) {
        this.personService = personService;
        this.personValidator = personValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<PersonDTO> findAll() {
        return personService.findAll().stream().map(this::convertToPersonDTO)
                .collect(Collectors.toList());
    }

    // for admin
    @GetMapping("/{id}")
    public Optional<PersonDTO> findById(@PathVariable("id") Integer id){
        return convertToPersonDTO(personService.findById(id));
    }


    @PostMapping("/registration")
    public String create(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult){
        personValidator.validate(personDTO, bindingResult);
        if(bindingResult.hasErrors()){
            return bindingResult.toString();
            //bindingResult.getFieldError();
                    //Map.of("message", "Ошибка!");
        }
        personService.register(convertToPerson(personDTO));
        return "Регистрация прошла успешно";
    }

    @PatchMapping("{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody PersonDTO updatePersonDTO){
        personService.update(id, convertToPerson(updatePersonDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        personService.deleteById(id);
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

