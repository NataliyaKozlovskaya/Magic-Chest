package com.magicchestcore.servicies;

import com.magicchestcore.config.util.EnumRole;
import com.magicchestcore.dto.PersonAuthDTO;
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
    public Person register(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole(EnumRole.USER);
        Person savedPerson = personRepository.save(person);
        return savedPerson;
    }



    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Optional<Person> findById(Integer id){
        return personRepository.findById(id);
    }


    //for user как проверить , что он себя изменяет?
    //НЕ НРАВИТСЯ ЧТО КАК В СОХРАНЕНИИ ЗАНОВО НАДО НАЗНАЧАТЬ
    @Transactional//ПЕРЕДЕЛАТЬ TODO:
    public void update(Integer id, PersonAuthDTO personAuthDTO){
      Person person = personRepository.getById(id);
      if(personAuthDTO.getUsername() != null){
          person.setUsername(personAuthDTO.getUsername());
      }
        if(personAuthDTO.getPassword() != null){
            person.setPassword(passwordEncoder.encode(personAuthDTO.getPassword()));
        }
        if(personAuthDTO.getAddress() != null) {
            person.setAddress(personAuthDTO.getAddress());
        }
        personRepository.save(person);
    }
}
