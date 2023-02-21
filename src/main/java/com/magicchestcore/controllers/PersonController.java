package com.magicchestcore.controllers;

import com.magicchestcore.models.Person;
import com.magicchestcore.security.PersonDetails;
import com.magicchestcore.servicies.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonDetailsService personDetailsService;

    @Autowired
    public PersonController(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @GetMapping
    public List<Person> findAll() {
        return personDetailsService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> findById(@PathVariable("id") Integer id){
        return personDetailsService.findById(id);
    }

    @PostMapping
    public void create(@RequestBody Person person){
        personDetailsService.save(person);
    }

    @PatchMapping("{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody Person updatePerson){
        personDetailsService.update(id, updatePerson);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        personDetailsService.deleteById(id);
    }


    @GetMapping("/showPersonInfo")
    public Person showPersonInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails principal = (PersonDetails) authentication.getPrincipal();
        System.out.println(principal.getPerson());
        return (principal.getPerson());
    }
}

