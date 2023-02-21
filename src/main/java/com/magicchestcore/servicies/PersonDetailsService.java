package com.magicchestcore.servicies;

import com.magicchestcore.models.Person;
import com.magicchestcore.repositories.PersonRepository;
import com.magicchestcore.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional(readOnly = true)
public class PersonDetailsService implements UserDetailsService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<Person> person= personRepository.findByUsername(username);
        System.out.println("Ищет");
        if(person.isEmpty()) {
            throw new UsernameNotFoundException("Person not found");
        }
        return new PersonDetails(person.get());
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Optional <Person> findById(Integer id){
        return personRepository.findById(id);
    }

    @Transactional
    public void save(Person person){
        personRepository.save(person);
    }

    @Transactional
    public void update(Integer id, Person updatedPerson){
        updatedPerson.setId(id);
        personRepository.save(updatedPerson);
    }
    @Transactional
    public void deleteById(Integer id){
        personRepository.deleteById(id);
    }
}
