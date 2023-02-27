package com.magicchestcore.servicies;

import com.magicchestcore.config.util.EnumRole;
import com.magicchestcore.dto.PersonDTO;
import com.magicchestcore.models.Person;
import com.magicchestcore.repositories.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole(EnumRole.USER);

        personRepository.save(person);
    }

//    public void enrichPerson(Person person){
//        person.setRole(EnumRole.USER);
//    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Optional<Person> findById(Integer id){
        return personRepository.findById(id);
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






//    public  List<Order> findByPerson(Integer id) {
//        Optional<Person> person = personRepository.findById(id);
//        if (person.isPresent())
//            return person.get().getOrders();
//        return null;
//    }


// не будет
//    @Transactional
//    public void save(Person person){
//        personRepository.save(person);
//    }

}
