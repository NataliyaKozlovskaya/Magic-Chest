package com.magicchestcore.controllers;

import com.magicchestcore.config.util.Converter;
import com.magicchestcore.config.util.PersonValidator;
import com.magicchestcore.dto.PersonAuthDTO;
import com.magicchestcore.dto.PersonDTO;
import com.magicchestcore.models.Person;
import com.magicchestcore.servicies.PersonDetailsService;
import com.magicchestcore.servicies.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final PersonDetailsService personDetailsService;
    private final PersonValidator personValidator;
    private final Converter converter;

    @Autowired
    public PersonController(PersonService personService, PersonDetailsService personDetailsService,
                            PersonValidator personValidator, Converter converter) {
        this.personService = personService;
        this.personDetailsService = personDetailsService;
        this.personValidator = personValidator;
        this.converter = converter;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> create(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult){
        personValidator.validate(personDTO, bindingResult);
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.toString(), HttpStatus.CONFLICT);
        }
        Person person = personService.register(converter.convertToPerson(personDTO));
        PersonDTO registeredPerson = converter.convertToPersonDTO(person);
        return ResponseEntity.ok(registeredPerson);
    }

    @PostMapping("/login")
    public void login(@RequestBody PersonAuthDTO personAuthDTO){
       String username = personAuthDTO.getUsername();
       personDetailsService.loadUserByUsername(username);
    }


    @GetMapping
    public ResponseEntity<List<PersonDTO>> findAll() {
        List<Person> people = personService.findAll();
        List<PersonDTO> listPerson = people.stream().map(converter::convertToPersonDTO).collect(Collectors.toList());
        return ResponseEntity.ok(listPerson);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable("id") Integer id){
        Optional<Person> person = personService.findById(id);
        if(person.isPresent()){
            PersonDTO personDTO = converter.convertToPersonDTO(person.get());
            return ResponseEntity.ok(personDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/admin/{id}")
    public ResponseEntity<?> findByIdAdmin(@PathVariable("id") Integer id){
        Optional<Person> person = personService.findById(id);
        if(person.isPresent()){
            PersonDTO personDTO = converter.convertToPersonDTO(person.get());
            return ResponseEntity.ok(personDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping
    public void update(@RequestBody PersonDTO personDTO){
        personService.update(converter.convertToPerson(personDTO));
    }


    @PostMapping("/admin/lock/{id}")
    public ResponseEntity<?> lockPerson(@PathVariable("id") Integer id){
        Optional<Person> person = personService.findById(id);
        person.ifPresent(value -> value.setAccountNonLocked(false));
        return ResponseEntity.ok().build();
    }


    @PostMapping("/admin/unlock/{id}")
    public ResponseEntity<?> unLockPerson(@PathVariable("id") Integer id){
        Optional<Person> person = personService.findById(id);
        person.ifPresent(value -> value.setAccountNonLocked(true));
        return ResponseEntity.ok().build();
    }

}

